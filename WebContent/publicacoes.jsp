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
	<div class="col-md-6 publicacoes">
			<!-- meio -->
			<c:choose>
				<c:when test="${!leituraInicial}">
					<%
						response.sendRedirect("/Twiuse/inicio");
					%>
				</c:when>
				<c:when test="${listatwt == null}">

					<c:if test="${usuario != null}">
						<h2>Poxa, você ainda não segue ninguém :/</h2>
						<h3>
							Aproveita e dá uma olhadinha aqui do lado <span
								class="glyphicon glyphicon-hand-right" aria-hidden="true"></span>
						</h3>
					</c:if>

				</c:when>
				<c:otherwise>
					<c:forEach var="twitter" items="${listatwt}">
						<div class="card text-white bg-primary mb-3 border-warning">

							<div class="card-body">
								<img src="/Twiuse/RecuperaFoto?id=${twitter.idPessoa}"
									class="rounded-circle img-thumbnail foto_publicacoes" />
								<h5 class="card-title text-left">${twitter.pessoa.nome}
									Disse:</h5>

							</div>
							<p class="card-text text-center">${twitter.mensagem}</p>
							<h6 class="card-subtitle mb-2 text-white font-italic text-left"
								style="padding-left: 5px;">Data: ${twitter.dataTwitter}</h6>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>

		</div>

</body>
</html>