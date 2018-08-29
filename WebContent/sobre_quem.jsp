<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.twitter.TwitterDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Twitter</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<script type="js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<nav class="col-md-3 col-md-pull-9">
		<!-- Lado esquerdo -->
		<c:if test="${pessoa != null}">
			<div class="card border-info mb-3 bg-dark text-white">
				<div class="card-heading">
					<h3>Sobre quem</h3>
				</div>
				<div class="card-body">
					<h3 class="card-headding">${pessoa.nome}</h3>
					<img src="/Twiuse/RecuperaFoto?id=${pessoa.id}"
						class="rounded-circle img-thumbnail" id="foto_perfil" /> <br />



					<c:choose>
						<c:when test="${botaoDeixarSeguir}">
						<br />
							<form class="navbar-form" action="/Twiuse/DeixarSeguirServlet"
								method="post">
								<input type="hidden" name="idPessoa" value="${pessoa.id}">
								<button type="submit" class="btn btn-danger">Deixar de
									Seguir</button>
							</form>

						</c:when>
						<c:otherwise>
						<br />
							<form class="navbar-form" action="/Twiuse/SeguirServlet"
								method="post">
								<input type="hidden" name="idPessoa" value="${pessoa.id}">
								<button type="submit" class="btn btn-primary">Seguir</button>
							</form>
						</c:otherwise>
					</c:choose>

				</div>
			</div>
		</c:if>
	</nav>
</body>
</html>