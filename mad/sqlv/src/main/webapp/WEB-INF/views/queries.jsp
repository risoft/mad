<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<html>
<head>
<mytags:bootstrap/>
</head>
<body>
<mytags:menu/>
<h3>Queries for ${connectionName}</h3>

<ul>
<c:forEach var="query" items="${queries}">
	<li><a href="query/${query.name}/execute.htm">${query.name}</a></li>
</c:forEach>
	<li><a href="new-query.htm">+Add</a></li>
</ul>

<mytags:footer/>

</body>
</html>
