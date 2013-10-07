<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<html>
<head>
<mytags:bootstrap/>
</head>
<body>
<mytags:sidebar />


<h2>New Conection</h2>
<form action="connection-save.htm" method="post">
<label for="name">Name</label>
<input class="field" type="text" name="name" id="name"/></br>
<label for="name">Url</label>
<input type="text" class="field" name="url" id="url"><br/>
<label for="name">Username</label>
<input type="text" class="field" name="username" id="username"><br/>
<label for="name">Password</label>
<input type="password" class="field" name="password" id="password"><br/>
<button class="btn" type="button" class="btn">Test</button>
<button class="btn" type="submit" class="btn">Send</button>

</form>
<mytags:footer/>
</body>
</html>
