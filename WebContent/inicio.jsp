<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.twitter.TwitterDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Twitter</title>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<nav class="navbar navbar-default col-lg-12" id="barra-topo">
		<div class="container">
			<div>
				<ul class="nav navbar-nav navbar-left">

					<li><a href="/Twiuse/inicio">Início</a></li>
					<c:if test="${usuario == null}">
						<li><a href="/Twiuse/cadastrar.jsp">Cadastre-se</a></li>
					</c:if>
					<c:if test="${usuario != null}">
						<li><a href="/Twiuse/SairServlet">Sair</a></li>
					</c:if>


				</ul>

				<c:if test="${usuario == null}">
					<form class="navbar-form navbar-right"
						action="/Twiuse/LoginServlet" method="post">
						Entrar
						<div class="form-group">
							<input type="text" class="form-control" placeholder="Usuario"
								name="usuario" v> <input type="password"
								class="form-control" placeholder="Senha" name="senha">
						</div>
						<button type="submit" class="btn btn-default">Login</button>
					</form>
				</c:if>
			</div>
		</div>
	</nav>
	<div class="container">

		<div class="col-md-6 col-md-push-3 centro">
			<!-- meio -->
			<c:if test="${usuario != null}">
				<div class="panel panel-default col-md-5" id="escrever">
					<form action="/Twiuse/Twittar" method="post">
						<div class="form-group">
							<label for="comment">Escrever:</label>
							<textarea class="form-control" rows="5" id="comment"
								name="mensagem"></textarea>
						</div>
						<button type="submit" class="btn btn-default">Twittar</button>
					</form>
				</div>

			</c:if>
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
						<div class="panel panel-default" id="painel">
							<div class="panel-heading">
								<h3 class="panel-title">${twitter.pessoa.nome}disse:</h3>
							</div>
							<div class="panel-body">${twitter.mensagem}</div>
						</div>
					</c:forEach>
				</c:otherwise>
			</c:choose>


		</div>
		<aside class="col-md-3 col-md-push-5">
			<!-- SEGUIR PESSOAS -->
			<c:if test="${usuario != null}">
				<div class="panel panel-default" id="seguir">
					<div class="panel-body">
						<table>

							<c:forEach var="pessoa" items="${listaTodasPessoas}" begin="0"
								end="9" step="1">
								<tr>
									<td>
										<form class="navbar-form" action="/Twiuse/SeguirServlet"
											method="post">
											<div class="form-group"></div>
											<input type="hidden" name="idPessoa" value="${pessoa.id}">
											<a href="/Twiuse/VisitarServlet?pessoa=${pessoa.usuario}">${pessoa.nome}</a>
											<button type="submit" class="btn btn-default">Seguir</button>
										</form>

									</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</c:if>

		</aside>
		<nav class="col-md-3 col-md-pull-9" id="sobre">
			<!-- Lado esquerdo -->
			<c:if test="${usuario != null}">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Sobre você</h3>
					</div>
					<div class="panel-body">${usuario.nome}</div>
				</div>
			</c:if>
		</nav>
	</div>
</body>
</html>
