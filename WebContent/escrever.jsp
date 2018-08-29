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
</body>
</html>