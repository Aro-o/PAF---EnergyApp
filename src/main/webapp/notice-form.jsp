<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Notices</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${notice != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${notice == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${notice != null}">
            			Edit Notice
            		</c:if>
						<c:if test="${notice == null}">
            			Add New Notice
            		</c:if>
					</h2>
				</caption>

				<c:if test="${notice != null}">
					<input type="hidden" name="id" value="<c:out value='${notice.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Topic</label> <input type="text"
						value="<c:out value='${notice.topic}' />" class="form-control"
						name="topic" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Areas Affected</label> <input type="text"
						value="<c:out value='${notice.areasAffected}' />" class="form-control"
						name="areasAffected">
				</fieldset>

				<fieldset class="form-group">
					<label>Date</label> <input type="text"
						value="<c:out value='${notice.date}' />" class="form-control"
						name="date">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Details</label> <input type="text"
						value="<c:out value='${notice.details}' />" class="form-control"
						name="details">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
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