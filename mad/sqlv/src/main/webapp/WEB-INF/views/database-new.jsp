<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<title>Mobile and Desktop Query Viewer</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.css" rel="stylesheet">

<!-- Add custom CSS here -->
<link href="css/simple-sidebar.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet">
<mytags:jquery />
<mytags:select2 />
<mytags:bootstrap />
</head>

<body>

	<div id="wrapper">

		<mytags:sidebar />

		<!-- Page content -->
		<div id="page-content-wrapper">
			<div class="content-header">
				<h1>
					<a id="menu-toggle" href="#" class="btn btn-default"><i
						class="icon-reorder"></i></a> Mobile and Desktop Query Viewer
				</h1>
			</div>
			<!-- Keep all page content within the page-content inset div! -->
			<div class="page-content inset">
				<div class="row">
					<div class="col-md-12">
						<p class="lead">You have ${fn:length(databases)} databases defined.</p>

						<script>
							$(document).ready(function() {
								
								$("#id").select2({
									allowClear : true,
									placeholder : 'Create New Database'
									
								});
								
								$("#id").on("change", function(e)
										{
											if(!e.added)
											{
												$("#database-form")[0].reset();
												return;	
											}
											var id = e.added.id;
											var text = e.added.text;
											$.ajax("database-load.json", 
												{
													data: {id:id},
													success: function(data,status, xhr)
													{
														$("#database-form").deserialize(data);
													}
												});
											console.log(e.added.id+ "  "+e.added.text);
										});
							});
						</script>
					</div>
					<div class="col-md-8">

						<form action="database-save.htm" method="post" role="form" id="database-form"
							class="form-horizontal">
							<fieldset>
								<legend>New Database Connection Info</legend>
								<div class="form-group">
									<label class="col-lg-2 control-label" for="id">Load</label>
									<div class="col-lg-10">
										<select contenteditable="true" name='id' id="id"
											class="dddform-control" style="width:100%">
											<option value="0"></option>
											<c:forEach var="database" items="${databases}">
												<option value="${database.id}">${fn:escapeXml(database.name)}</option>
											</c:forEach>
											
										</select>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label" for="name">Name</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" name="name" id="name">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label" for="name">Url</label>
									<div class="col-lg-10">
										<input type="text" class="form-control" name="url" id="url">
									</div>
								</div>
								<div class="form-group">

									<label class="col-lg-2 control-label" for="name">Username</label>
									<div class="col-lg-10"">
										<input class="form-control" type="text" class="field"
											name="username" id="username">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-2 control-label" for="name">Password</label>
									<div class="col-lg-10">
										<input class="form-control" type="password" class="field"
											name="password" id="password">
									</div>
								</div>
								<button class="btn col-lg-offset-2" type="button">Try Connection</button>
								<button class="btn primary" type="submit">Send</button>
							</fieldset>
						</form>

					</div>

				</div>
			</div>
		</div>

	</div>

	<!-- Bootstrap core JavaScript -->
	<!-- Placed at the end of the document so the pages load faster -->

	<script src="js/bootstrap.js"></script>
	<!-- Put this into a custom JavaScript file to make things more organized -->
	<script>

	</script>
</body>
</html>







</form>