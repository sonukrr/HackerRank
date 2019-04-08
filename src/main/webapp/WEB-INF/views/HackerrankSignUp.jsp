<%@ page language="java" isELIgnored="false" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hackerrank</title>
<link rel="stylesheet" type="text/css" href="../CSS/HackerrankDesign.css"> 
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script>
function validateForm()
{
	var x = document.forms["loginForm"]["name"].value;
    if (x == "") {
        alert("Name must be filled out");
        return false;
    }
    var x = document.forms["loginForm"]["email"].value;
    if (x == "") {
        alert("email must be filled out");
        return false;
    }
    var x = document.forms["loginForm"]["password"].value;
    if (x == "") {
        alert("password must be filled out");
        return false;
    }
    var regex2 = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/;
    if( x.match(regex2))
    {
    	return true;
    }
    else
	{
    	alert("password not in correct format");
		return false;
	}

    
}

function login()
{
	var p1=document.getElementById("p5");
	p1.innerHTML="<form method ='post' action='LoginUserServlet' onsubmit='return validateForm()' name='signUpForm'>"+
	
	"<input  class='input icon1' type='text' name ='email' placeholder='User email'  class='icon1'><br>"+
	
	"<input class='input icon3' type='password' name ='password' placeholder='Password'>"+
	
	"<br><br><br>"+
	
	"<input type='checkbox' style='float:left;'>Remember me &nbsp;"+
	"<a href='#' style='float:right;'>Forgot your password?</a>"+
	"<br><br><input type='submit' value='Log in' class='button' >"+
	
	"<br><p class='p1' style='color:red;'>${message}</p>"+
"</form>";
	}
	
	function signup()
	{
		var p1=document.getElementById("p5");
		
		
	}

</script>

</head>
<body>
<div class="container">
	<div class="header" >

			<img src="https://hrcdn.net/hackerrank/assets/styleguide/logo_wordmark-13074b67abceb42ce8fd38bdeaac6926.svg" style="width:40%;height: 10%">
	


	</div>
	
	<div class="content" >
		<div class="top">
		
		<a href="login" class="buttonL disabledL" >Log in</a>
		<a href="#" class="buttonS " >Sign up</a>
		
		</div>
		
		
		<div class="main">

		
		
		<br>
		<br><br>
		<form method ="post" action="RegisterUser" onsubmit="return validateForm()" name="loginForm">
		
			<input class="input icon1" type="text" name ="userName" placeholder="First and Last name" ><br>
			
			<input class="input icon2" type="text" name ="email" placeholder="User email"><br>
			
			<input class="input icon3" type="password" name ="password" placeholder="Password">
			
			<br><br><br>

		
			<input type="submit" value="Create an account" class="button" >
			<br>
			<p class="p1" style="color:red;">${message}</p>
		</form>
		
		<hr>

		<p class="p3">Or connect with</p>
	
		<input type="submit" value="Facebook" class="button1 fa fa-facebook" style="background-color:#3b5998 ;">&nbsp;
		<input type="submit" value="google" class="button1 fa fa-google" style="background-color: #DD4B39 ;">&nbsp;
		<input type="submit" value="Linkedln" class="button1 fa fa-linkedin" style="background-color:#0077B5  ;">&nbsp;
		<input type="submit" value="GitHub" class="button1 fa fa-facebook" style="background-color: #4183C4 ;">&nbsp;
		
		
		<br>
		<br>
		
		<p class="p2">By signing up, you agree to our <span style="color:blue">Terms of Service</span> and  <span style="color:blue">Privacy Policy. </span></p>
		
		
		</div>
		<p class="p1">Are you looking for HackerRank for Work?</p>
	
	</div>


	
</div>

</body>


</html>