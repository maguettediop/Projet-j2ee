<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifier User</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</head>
<body>
	<jsp:include page="inc/entete.jsp"/>
	<jsp:include page="inc/menu.jsp"/>
	<c:choose>
		<c:when test="${ !empty requestScope.client }">
			<div class="container col-md-5">
				 <div class="card">
		         	<div class="card-body">
						<form class="form-horizontal" method="post" action="update">
							<caption>
								<h2>
									Modifier Client
								</h2>
							</caption>
							
								<div class="form-group">
								<label>Nom :</label>
								<input class="form-control" type="text" name="nom" value="${ client.nom }">
								<span>${ messageErreurs.nom }</span><br>
								</div>
								
								<div class="form-group">
								<label>Prénom :</label>
								<input class="form-control" type="text" name="prenom" value="${ client.prenom }">
								<span>${ messageErreurs.prenom }</span><br>
								</div>
								
								<div class="form-group">
									<label>Login :</label>
									<input class="form-control" type="text" name="login" value="${ client.login }">
									<span>${ messageErreurs.login }</span><br>
								</div>
								
								<div class="form-group">
									<label>Password :</label>
									<input class="form-control" type="password" name="password" value="${client.password}">
									<span>${ messageErreurs.password }</span>
								</div>
								<c:if test="${client != null}">
                          		  <input type="hidden" name="id" value="<c:out value='${client.id}' />" />
                    			 </c:if>
								
								<input class="btn btn-primary" type="submit" value="Modifier">
								<span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
							
						</form>
					</div>
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<p>L'utilisateur que vous souhaitez modifier n'existe pas</p>"
		</c:otherwise>
	</c:choose>
</body>
</html>