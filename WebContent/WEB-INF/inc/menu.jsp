<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<hr>
<ul>
	<c:choose>
		<c:when test="${empty sessionScope.utilisateur }">
			<li><a href="<c:url value='/login'/>">Se connecter</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="<c:url value='/clients/add'/>">Ajouter</a></li>
			<li><a href="<c:url value='/clients/list'/>">Lister</a></li>
			<li><a href="<c:url value='/logout'/>">Se déconnecter</a></li>
		</c:otherwise>
	</c:choose>
</ul>
<hr>