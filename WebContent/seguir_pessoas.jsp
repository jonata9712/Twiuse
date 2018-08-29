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
<!-- SEGUIR PESSOAS -->
			<c:if test="${usuario != null}">
				<c:if test="${not empty listaTodasPessoas}">

					<div class="card bg-dark text-white" id="seguir">
						<div class="card-body">
							<div class="card-heading">
								<h3 class="card-title">Quem seguir:</h3>
							</div>
							<table>

								<c:forEach var="pessoa" items="${listaTodasPessoas}" begin="0"
									end="9" step="1">
									<tr>
										<td>

											<form class="navbar-form" action="/Twiuse/SeguirServlet"
												method="post">
												<div class="form-group"></div>

												<a href="/Twiuse/VisitarServlet?pessoa=${pessoa.usuario}"
													class="text-white"><img
													src="/Twiuse/RecuperaFoto?id=${pessoa.id}"
													class="rounded-circle img-thumbnail foto_publicacoes" /></a> <input
													type="hidden" name="idPessoa" value="${pessoa.id}">
												<a href="/Twiuse/VisitarServlet?pessoa=${pessoa.usuario}"
													class="text-white">${pessoa.nome}</a>
												<button type="submit" class="btn btn-primary">Seguir</button>
											</form>


										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>

				</c:if>
			</c:if>
</body>
</html>