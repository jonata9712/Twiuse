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

	<div class="row centro col-md-12">

		<div class="col-md-3">
			<!-- SOBRE VOCÊ -->
			<c:if test="${usuario != null}">
				<div class="card border-info mb-3 bg-dark text-white">
					<div class="card-heading">
						<h3 class="card-title">Sobre você</h3>
					</div>
					<div class="card-body">
					<h3>${usuario.nome}</h3>
					<img src="/Twiuse/RecuperaFoto?id=${usuario.id}" class="rounded-circle img-thumbnail" id="foto_perfil"/>
					
						<br /> <br />

						<!-- ENVIAR FOTO -->
						<form action="/Twiuse/uploadFoto" method="post" enctype="multipart/form-data">
							<div class="form-group">
								<label for="foto">Escolha uma foto</label> <input type="file"
									class="form-control-file" id="foto" name="foto"> <br />
								<button type="submit" class="btn btn-primary">Enviar</button>
							</div>
						</form>
						<!-- ENVIAR FOTO -->

					</div>
				</div>

			</c:if>


			<!-- SOBRE VOCÊ -->


			

		</div>

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
							<img src="/Twiuse/RecuperaFoto?id=${twitter.idPessoa}" class="rounded-circle img-thumbnail foto_publicacoes"/> 
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
		
		<div class="col-md-3">
		<!-- ESCREVER -->
			<c:if test="${usuario != null}">
				<div class="card bg-dark text-white">
					<form action="/Twiuse/Twittar" method="post">
						<div class="form-group">
							<label for="comment">Escrever:</label>

							<textarea class="form-control-md form-control" rows="5"
								id="comment" name="mensagem"></textarea>


						</div>
						<button type="submit" class="btn btn-primary">Twittar</button>
					</form>
				</div>

			</c:if>
			<!-- ESCREVER -->
			
			<!-- SEGUIR PESSOAS -->
			<c:if test="${usuario != null}">
				<c:if test="${not empty listaTodasPessoas}">

					<div class="card bg-dark text-white" id="seguir">
						<div class="card-body">
						<div class="card-heading">
						<h3 class="card-title">Quem seguir: </h3>
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
													class="text-white"><img src="/Twiuse/RecuperaFoto?id=${pessoa.id}" class="rounded-circle img-thumbnail foto_publicacoes"/></a>
												
												
												<input type="hidden" name="idPessoa" value="${pessoa.id}">
												<a href="/Twiuse/VisitarServlet?pessoa=${pessoa.usuario}"
													class="text-white">${pessoa.nome}</a>
												<button type="submit" class="btn btn-default">Seguir</button>
											</form>

										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>

				</c:if>
			</c:if>
		</div>
		<!-- SEGUIR PESSOAS END -->
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
		crossorigin="anonymous"></script>

</body>
</html>
