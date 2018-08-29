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
<link rel="stylesheet" href="css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/style.css" />
<script type="js/jquery-3.3.1.min.js"></script>
</head>
<body>
	<c:if test="${not empty seguidores}">

		<div class="card text-white bg-primary mb-3 border-warning">
			<h3 class="card-headding">Quem você segue:</h3>
			<div class="card-body">
				<c:forEach var="pessoa" items="${seguidores}" begin="0" end="5"
					step="1" varStatus="status">

					<tr>
						<c:if test="${status.index != 3}">
							<td><a
								href="/Twiuse/VisitarServlet?pessoa=${pessoa.usuario}"
								class="text-white"><img
									src="/Twiuse/RecuperaFoto?id=${pessoa.id}"
									class="img-thumbnail foto_quem_voce" /></a></td>
						</c:if>
					</tr>
					<tr>
						<c:if test="${status.index >= 3}">
							<td><a
								href="/Twiuse/VisitarServlet?pessoa=${pessoa.usuario}"
								class="text-white"><img
									src="/Twiuse/RecuperaFoto?id=${pessoa.id}"
									class="img-thumbnail foto_quem_voce" /></a></td>
						</c:if>
					</tr>
				</c:forEach>
			</div>


		</div>

	</c:if>

</body>
</html>