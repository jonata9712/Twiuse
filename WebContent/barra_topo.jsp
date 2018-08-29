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
<div class="row col-md-12">
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark col-md-12"
			id="barra-topo">

			<ul class="navbar-nav mr-auto">

				<li class="nav-item"><a href="/Twiuse/inicio" class="nav-link">Início</a></li>
				<c:if test="${usuario == null}">
					<li class="nav-item"><a href="/Twiuse/cadastrar.jsp"
						class="nav-link">Cadastre-se</a></li>
				</c:if>
				<c:if test="${usuario != null}">
					<li class="nav-item"><a href="/Twiuse/SairServlet"
						class="nav-link">Sair</a></li>
				</c:if>


			</ul>
			<c:if test="${usuario == null}">
				<form class="form-inline" action="/Twiuse/LoginServlet" method="post">
					<div class="form-group">
						<input type="text" class="form-control mr-sm-2"
							placeholder="Usuario" name="usuario" v> <input
							type="password" class="form-control" placeholder="Senha"
							name="senha">
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</form>
			</c:if>

		</nav>
	</div>
</body>
</html>