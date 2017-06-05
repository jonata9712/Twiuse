<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="dao.twitter.TwitterDao"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script src="js/bootstrap.min.js"></script>
<link href="css/style.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->


			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">

				<ul class="nav navbar-nav">
					<li class="active"><a href="/Twiuse/inicio">In�cio <span
							class="sr-only">(current)</span></a></li>

					<c:if test="${usuario == null}">
						<li><a href="#">Cadastre-se</a></li>
					</c:if>
					<c:if test="${usuario != null}">
						<li><a href="/Twiuse/SairServlet">Sair</a></li>
					</c:if>

				</ul>
				<c:if test="${usuario == null}">
					<form class="navbar-form navbar-left" action="/Twiuse/LoginServlet"
						method="post">
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
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
		</nav>
		</header>
		<div class="row">
			<div role="main" class="col-md-6 col-md-push-3">
				<c:if test="${usuario != null}">
					<div class="panel panel-default">
						<form action="/Twiuse/Twittar"  method="post">
							<div class="form-group">
								<label for="comment">Escrever:</label>
								<textarea class="form-control" rows="5" id="comment" name="mensagem"></textarea>
							</div>
							<button type="submit" class="btn btn-default">Twittar</button>
						</form>
					</div>

				</c:if>
				<c:forEach var="twitter" items="${listatwt}">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h3 class="panel-title">${twitter.pessoa.nome} Disse:</h3>
						</div>
						<div class="panel-body">${twitter.mensagem}</div>
					</div>
				</c:forEach>

			</div>
			<aside role="complementary" class="col-md-3 col-md-push-3"> </aside>
			<nav class="col-md-3 col-md-pull-9"> </nav>
		</div>
		<footer class="row"> </footer>
	</div>
</body>
</html>