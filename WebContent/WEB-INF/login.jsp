<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
		<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	
</head>
<body><c:import url="inc/entete.jsp"/>
	<c:import url="inc/menu.jsp"/>
	<div class="container col-md-5">
	<form method="post">
		<fieldset class="form-group">
			<legend>Connexion</legend>
			<label>Login</label>
			<input class="form-control" type="text" name="login">
			<label>Password</label>
			<input class="form-control" type="password" name="password">
			<input class="" type="submit" value="Se connecter">
		</fieldset>
	</form>
	</div>
</body>
</html>