<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytags"%>
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

</head>

<body>

	<div id="wrapper">

		<mytags:sidebar/>

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
						<p class="lead">You have ${databases} databases
							defined.</p>
					</div>
					<div class="col-md-8">
						<form action="query/new.htm" method="post"
							class="form-horizontal">
							<fieldset>
								<legend>New Query</legend>
								<div class="control-group">
									<label class="control-label" for="name">Name</label>
									<div class="controls">
										<input type="text" name="name" id="name" /></br>
									</div>
								</div>
								<div class="control-group">
									<label for="name">Query Text</label>
									<div class="controls">
										<textarea type="text" class="field" name="query" id="query"></textarea>
									</div>
								</div>
							
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
	<script src="js/jquery.js"></script>
	<script src="js/bootstrap.js"></script>
	<!-- Put this into a custom JavaScript file to make things more organized -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("active");
		});
	</script>
</body>
</html>




















