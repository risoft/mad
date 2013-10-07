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
      
      <!-- Sidebar -->
      <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
          <li class="sidebar-brand"><a href="#">MAD Query Viewer</a></li>
          <li><a href="#">&nbsp;Databases</a></li>
          <li><a href="#">&nbsp;Queries</a></li>
          <li><a href="j_spring_security_logout">&nbsp;Logout</a></li>
        	
        </ul>
      </div>
          
      <!-- Page content -->
      <div id="page-content-wrapper">
        <div class="content-header">
          <h1>
            <a id="menu-toggle" href="#" class="btn btn-default"><i class="icon-reorder"></i></a>
            Mobile and Desktop Query Viewer
          </h1>
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