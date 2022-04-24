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

	<jsp:include page="Header.jsp" />
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${notices != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${notices == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${notices != null}">
            			Edit Notice
            		</c:if>
						<c:if test="${notices == null}">
            			Add New Notice
            		</c:if>
					</h2>
				</caption>

				<c:if test="${notices != null}">
					<input type="hidden" name="id" value="<c:out value='${notices.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Topic</label> <input type="text"
						value="<c:out value='${notices.Topic}' />" class="form-control"
						name="Topic" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Areas Affected</label> <input type="text"
						value="<c:out value='${notices.areasAffected}' />" class="form-control"
						name="areasAffected">
				</fieldset>

				<fieldset class="form-group">
					<label>Date</label> <input type="text"
						value="<c:out value='${notices.Date}' />" class="form-control"
						name="Date">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Details</label> <input type="text"
						value="<c:out value='${notices.Details}' />" class="form-control"
						name="Details">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>