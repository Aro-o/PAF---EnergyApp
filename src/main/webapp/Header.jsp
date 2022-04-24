<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="Login.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Power Grid System</title>
</head>
<body>
  <header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: maroon">
			<div>
				<a href="" class="navbar-brand"> Power Grid System </a>
			</div>

           <ul class="navbar-nav">
				<button onclick="document.getElementById('id01').style.display='block'" style="width:auto; background-color:maroon;">Login</button>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Home</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Energy Consumption Calculator</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Power Interruption Notices</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Billpay</a></li>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Feedback</a></li>
			</ul>
		</nav>
		
	</header> 

</body>
</html>