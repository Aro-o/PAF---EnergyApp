<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="Login.jsp" />
<html>
<head>
<title>Power Grid System</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: maroon">
			<div>
				<a href="" class="navbar-brand"> power Grid System </a>
			</div>

            <ul class="navbar-nav">
				<button onclick="document.getElementById('id01').style.display='block'" style="width:auto; background-color:maroon;">Login</button>
			</ul>
			
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Notices</a></li>
			</ul>
		</nav>
	</header>
	<br>
            
	<br>
	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Notices</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Notice</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Topic</th>
						<th>Areas Affected</th>
						<th>Date</th>
						<th>Details</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
				
					<c:forEach var="notice" items="${listNotice}">

						<tr>
							<td><c:out value="${notice.id}" /></td>
							<td><c:out value="${notice.topic}" /></td>
							<td><c:out value="${notice.areasAffected}" /></td>
							<td><c:out value="${notice.date}" /></td>
							<td><c:out value="${notice.details}" /></td>
							<td><a href="edit?id=<c:out value='${notice.id}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="delete?id=<c:out value='${notice.id}' />">Delete</a></td>
						</tr>
					</c:forEach>
		
				</tbody>

			</table>
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div class="navbar navbar-expand-md navbar-dark navbar-fixed-bottom"
			style="background-color: maroon">
      <div class="container">
        <p class="navbar-text">© PowerGridSystem</p>
      </div>
    </div>
</body>
</html>