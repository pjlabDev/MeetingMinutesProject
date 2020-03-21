<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"crossorigin="anonymous">
<title>Lista directores</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm navbar-light bg-light nav-tabs">
			<div class="navbar-brand col-md-3">
				<a href="consultarDirector"><button type="button" class="btn btn-warning">Volver a consultar</button></a>
				<a href="/"><button type="button" class="btn btn-primary">Inicio</button></a>
			</div>
			<h1 style="text-align:center" class="col-md-6">Directores consultados</h1>
        </nav>

	<div style="text-align:center">

		<div class="row justify-content-center">
			<div class="col-auto">
				<table
					class="mt-5 table table-striped table-inverse table-responsive table-center table-bordered text-center">
					<caption>Lista Directores Consultados</caption>
					<thead class="thead-inverse">
						<tr>
							<th id="directores">Directores Consultados</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaDirectores}" var="item">

							<tr>

								<td>${item}</td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>