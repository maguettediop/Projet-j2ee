<%@page import="beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liste des utilisateurs</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
	<c:import url="inc/entete.jsp"/>
	<c:import url="inc/menu.jsp"/>
	<c:choose>
		<c:when test="${ empty clients }">
			<p>La liste des utilisateurs est vide</p>
		</c:when>
		<c:otherwise>
			<table class="table" border="1" >
				<thead>
					<th>Nom</th>
					<th>Prénom</th>
					<th>Login</th>
					<th>Password</th>
					<th>Actions</th>
				</thead>
				<c:forEach items="${ clients }" var="client">
					<tr>
						<td><c:out value="${ client.nom }"/></td>
						<td><c:out value="${ client.prenom }"/></td>
						<td><c:out value="${ client.login }"/></td>
						<td><c:out value="${ client.password }"/></td>
						<td><a href="update?id=${client.id} ">Modifier</a> &nbsp;&nbsp; <a href="delete?id=${client.id}">Supprimer</a></td>
						
					</tr>
				</c:forEach>
			</table>
		</c:otherwise>
	</c:choose>
	
</body>
</html>