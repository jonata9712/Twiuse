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
	<div class="col-md-3">
		<!-- SOBRE VOCÊ -->
		<c:if test="${usuario != null}">
			<div class="card border-info mb-3 bg-dark text-white">
				<div class="card-heading">
					<h3 class="card-title">Sobre você</h3>
				</div>
				<div class="card-body">
					<h3>${usuario.nome}</h3>
					<img src="/Twiuse/RecuperaFoto?id=${usuario.id}"
						class="rounded-circle img-thumbnail" id="foto_perfil" /> <br />
					<br />

					<!-- ENVIAR FOTO -->
					<form action="/Twiuse/uploadFoto" method="post"
						enctype="multipart/form-data">
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

</body>
</html>