<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hackerrank</title>
<link rel="stylesheet" type="text/css" href="../CSS/HackerrankDesign.css"> 
<script type="text/javascript" src="jquery-1.2.6.min.js"></script>

<script type="text/javascript">
	  document.getElementById("file-upload").onchange = function(event) {
	    var reader = new FileReader();
	    reader.readAsDataURL(event.srcElement.files[0]);
	    var me = this;
	    reader.onload = function () {
	      var fileContent = reader.result;
	      var x = document.createElement("INPUT");
	      x.setAttribute("value", fileContent);
		  console.log(fileContent);
	    }
	};
</script>
</head>
<body>

<form method ="post" action="./imageUpload">
  Add your image: 
  <input id="file-upload" type="file" 
      accept=".gif,.jpg,.jpeg,.png">
      <input type="hidden" name="url" value="">
  <input type="submit">
</form>
<br>
<img src="../image/<c:out value="${name}"></c:out>.jpg"
							alt="Phani.jpg" style="width: 200px; height: 300px">

		
	
<div class="container">
	<div class="header" >
			<form action="logout" method="get" style="margin-left: 350px;">
			<input type="submit" value="Log Out" class="button"  >
			</form>
			<img src="https://hrcdn.net/hackerrank/assets/styleguide/logo_wordmark-13074b67abceb42ce8fd38bdeaac6926.svg" style="width:40%;height: 10%">
	


	</div>
	
	<div class="content" >
		
		
		
		<div class="main">

		
		<h2>Welcome to Hackerrank. Start coding today.</h2>
		<hr>
			
			<h3 class="p1">
			${message}
			</h3>
		
		<hr>
		
		
	
		
		</div>
	
	</div>


	
</div>
</body>

</html>