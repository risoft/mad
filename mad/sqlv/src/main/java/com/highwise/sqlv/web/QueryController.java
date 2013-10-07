package com.highwise.sqlv.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.highwise.sqlv.model.Database;
import com.highwise.sqlv.model.VQuery;
import com.highwise.sqlv.webhelp.Page;
import com.highwise.sqlv.webhelp.QueryResult;
import com.highwise.sqlv.webhelp.ResultSetDto;

@Controller
public class QueryController {

	@Autowired private SessionFactory sessionFactory;
	
	@RequestMapping("query-dddnew.htm")
	public String saveQuery(@PathVariable String connectionName, VQuery query)
	{
		ModelAndView mav = new ModelAndView("new-query");
		Database vconnection = (Database)
		sessionFactory.getCurrentSession().
			createQuery("from VConnection where name=:name")
			.setParameter("name", connectionName).uniqueResult();

		query.setConnection(vconnection);
		
		sessionFactory.getCurrentSession().save(query);
		return "redirect:../../queries.htm";
	}
	
	@RequestMapping("query-new.htm")
	public ModelAndView newQuery()
	{
		return new ModelAndView("query-new");
	}
	
	@RequestMapping("connection/{connectionName}/queries.htm")
	public ModelAndView getQueries(@PathVariable String connectionName) throws Exception
	{
		Session s = sessionFactory.getCurrentSession();
		List<VQuery> queries = (List<VQuery>)
				s.createQuery("from VQuery q where q.connection.name=:connectionName")
					.setParameter("connectionName", connectionName).list();
		
		ModelAndView mav = new ModelAndView("queries");
		mav.addObject("queries", queries);
		return mav;
	}
	
	@RequestMapping("query/{queryId}/metadata.json")
	public @ResponseBody ResultSetDto metadata(@PathVariable long queryId) throws SQLException, Exception
	{
		Page page = new Page();
		page.setLimit(-1);
		page.setPage(-1);
		page.setStart(-1);
		ResultSetDto result =  execute(queryId, page);
		result.setRows(null);
		return result;
	}
	
	@RequestMapping("query/{queryId}/execute.json")
	public @ResponseBody ResultSetDto execute(@PathVariable long queryId,
			Page page) throws SQLException, Exception
	{
		Session s = sessionFactory.getCurrentSession();
		VQuery vquery = (VQuery)s.createQuery("from VQuery where id=:queryId")
			.setParameter("queryId", queryId).uniqueResult();
		
		Statement stmt = getStatement(vquery.getConnection());

		
		ResultSet rs = stmt.executeQuery(vquery.getQuery());
				
		rs.setFetchDirection(ResultSet.FETCH_FORWARD);
		rs.setFetchSize(50);
		rs.last();
		int totalCount = rs.getRow();
		
		if (totalCount < page.getStart())
		{
			ResultSetDto r = new ResultSetDto(null, null, totalCount);
			r.setSuccess(false);
			return r;
		}
		
		if (page.getLimit() < 0)  // metadata
		{
			List<String> columns = getColumnNames(rs);
			return new ResultSetDto(columns, null, totalCount);
		}
		else
		{
			List<List<Object>> rows = getRows(rs, page);
			close(stmt);
			int size = Math.min(rows.size(), page.getLimit());
			return new ResultSetDto(null, rows.subList(0, size), totalCount);
		}
	}

	private void close(Statement stmt)
			throws SQLException {
		Connection connection = stmt.getConnection();
		stmt.close();
		connection.close();
	}

	private void close(ResultSet rs)
			throws SQLException {
		Statement stmt = rs.getStatement();
		rs.close();
		close(stmt);
	}
	
	
	@RequestMapping("connection/{connectionName}/query/{queryName}/execute.htm")
	public ModelAndView executeQuery(@PathVariable String queryName,
									@PathVariable String connectionName) throws Exception
	{
		
		Database vConnection = (Database)
				sessionFactory.getCurrentSession().createQuery("from VConnection where name=:connectionName")
					.setParameter("connectionName", connectionName).uniqueResult();
		
		VQuery vquery = (VQuery)
				sessionFactory.getCurrentSession().createQuery("from VQuery q where name=:name" +
						" and q.connection.name=:connectionName")
					.setParameter("name", queryName)
					.setParameter("connectionName", connectionName).uniqueResult();
	
		
		Statement stmt = getStatement(vConnection);
		ResultSet rs = stmt.executeQuery(vquery.getQuery());
		
		ModelAndView mav = new ModelAndView("result-set");
		mav.addObject("columns", getColumnNames(rs));
		mav.addObject("rows", getRows(rs, null));
		
		close(rs);
		
		return mav;
	}

	
	private Statement getStatement(Database vConnection) throws Exception,
			SQLException {
		Properties p = new Properties();
		p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		p.setProperty("url", vConnection.getUrl());
		p.setProperty("username", vConnection.getUsername());
		p.setProperty("password", vConnection.getPassword());

		DataSource ds = BasicDataSourceFactory.createDataSource(p);	
		Connection connection = ds.getConnection();
		Statement stmt = connection.createStatement();
				
		return stmt;
	}
	
	@RequestMapping("queries.json")
	public @ResponseBody QueryResult queries() throws JsonGenerationException, JsonMappingException, IOException
	{
		List<VQuery> queries = (List<VQuery>)
			sessionFactory.getCurrentSession().createQuery("from VQuery").list();
		QueryResult qr = new QueryResult(queries);
		ObjectMapper om = new ObjectMapper();
		return qr;
	}
	
	private List<String> getColumnNames(ResultSet rs) throws SQLException
	{
		List<String> r = new ArrayList<String>(rs.getMetaData().getColumnCount());
		for(int i=0; i < rs.getMetaData().getColumnCount(); i++)
		{
			r.add(rs.getMetaData().getColumnLabel(1+i));
		}
		return r;
	}
	
	private List<List<Object>> getRows(ResultSet rs, Page page) throws SQLException
	{
		List<List<Object>> r = new ArrayList<List<Object>>();
		
		if (page == null)
		{
			page = new Page();
			page.setLimit(Integer.MAX_VALUE);
			page.setPage(1);
			page.setStart(0);
		}
		if (!rs.absolute(page.getStart()+1)) return r;
		
		for(int j=0; j < page.getLimit(); j++)
		{
			int columns = rs.getMetaData().getColumnCount();
			List<Object> row = new ArrayList<Object>(columns);
			int currentRow = rs.getRow();
			row.add(currentRow);
			for(int i=0; i < columns; i++)
			{
				Object o = rs.getObject(1+i);
				row.add(o);
			}
			
			r.add(row);
			if(!rs.next()) break;
		}
		
		return r;
		
	}
	
}

