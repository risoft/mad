package com.highwise.sqlv.web;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.highwise.sqlv.model.Database;

@Controller
public class DatabasesController {

	@Autowired private SessionFactory sessionFactory;
	
	@RequestMapping("database-save.htm")
	public String saveConnection(Database database)
	{
		sessionFactory.getCurrentSession().saveOrUpdate(database);
		return "redirect:database-form.htm";
	}
	
	@RequestMapping("database-form.htm")
	public ModelAndView databaseForm()
	{
		ModelAndView mav = new ModelAndView("database-new");
		List<Database> databases = (List<Database>)
				sessionFactory.getCurrentSession().createQuery("from Database").list();
	
		mav.addObject("databases", databases);
		return mav;
	}
	
	@RequestMapping("database-load.json")
	public @ResponseBody Database loadDatabase(@RequestParam long id)
	{
		ModelAndView mav = new ModelAndView("database-new");
		Database database = (Database)
				sessionFactory.getCurrentSession().createQuery("from Database where id=:id")
				.setParameter("id", id).uniqueResult();
		return database;
	}
	
	
	@RequestMapping("databases.htm")
	public ModelAndView getConnections()
	{
		ModelAndView mav = new ModelAndView("databases");
		List<Database> connections =
			sessionFactory.getCurrentSession().createQuery("from VConnection").list();
		mav.addObject("connections", connections);
		return mav;
	}
		
	@RequestMapping("database-verify.htm")
	public @ResponseBody boolean isDatabaseOk(Database connection) throws Exception
	{
		Properties p = new Properties();
		p.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		p.setProperty("url", connection.getUrl());
		p.setProperty("username", connection.getUsername());
		p.setProperty("password", connection.getPassword());
		try {
			DataSource ds = BasicDataSourceFactory.createDataSource(p);	
			Connection conn= ds.getConnection();
			conn.close();
		} catch (Exception e) {
			return false;
		}

		return true;
	}
	

}

