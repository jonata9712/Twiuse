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
	<jsp:include page="barra_topo.jsp" />
	<div class="row centro col-md-12">
		<jsp:include page="sobre_quem.jsp" />


		<c:choose>
			<c:when test="${listatwt == null}">
				<div class="col-md-6 publicacoes">
					<h1>${pessoa.nome} Ainda não publicou nada :/</h1>
				</div>
			</c:when>
			<c:otherwise>
				<jsp:include page="publicacoes.jsp" />
			</c:otherwise>
		</c:choose>

		<div class="col-md-3">
			<jsp:include page="seguir_pessoas.jsp" />
		</div>



	</div>
	<footer class="row"> </footer>
	</div>
</body>
</html>