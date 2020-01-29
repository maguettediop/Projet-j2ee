<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ajout d'un utilisateur</title>
	<link rel="stylesheet" href="<c:url value='/style.css'/>">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<c:import url="inc/entete.jsp"/>
	<c:import url="inc/menu.jsp"/>
	<div class="container col-md-5">
		 <div class="card">
         	<div class="card-body">
				<form class="form-horizontal" method="post" action="add">
					<caption>
						<h2>
							Ajouter Client
						</h2>
					</caption>
					
						<div class="form-group">
						<label>Nom :</label>
						<input class="form-control" type="text" name="nom" value="${ requestScope.utilisateur.nom }">
						<span>${ messageErreurs.nom }</span><br>
						</div>
						
						<div class="form-group">
						<label>Prénom :</label>
						<input class="form-control" type="text" name="prenom" value="${ requestScope.utilisateur.prenom }">
						<span>${ messageErreurs.prenom }</span><br>
						</div>
						
						<div class="form-group">
							<label>Login :</label>
							<input class="form-control" type="text" name="login" value="${ requestScope.utilisateur.login }">
							<span>${ messageErreurs.login }</span><br>
						</div>
						
						<div class="form-group">
							<label>Password :</label>
							<input class="form-control" type="password" name="password">
							<span>${ messageErreurs.password }</span>
						</div>
						
						<div class="form-group">
							<label>Password (bis) :</label>
							<input class="form-control" type="password" name="passwordBis">
							<span>${ messageErreurs.passwordBis }</span>
						</div>
						<input type="submit" value="Ajouter">
						<span class="${ empty messageErreurs ? 'succes' : 'erreur'}">${ statusMessage }</span>
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>