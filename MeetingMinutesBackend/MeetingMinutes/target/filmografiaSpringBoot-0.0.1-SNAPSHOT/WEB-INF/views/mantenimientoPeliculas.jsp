<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Mantenimiento Peliculas</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm navbar-light bg-light nav-tabs">
			<div class="navbar-brand col-md-3">
				<form action="volverwelcome" method="POST">
					<input type="hidden" name="usuario" value="${usuario}">
					<input type="submit" class="btn btn-warning" value="Volver">
				</form>
				<a href="/"><button type="button" class="btn btn-primary">Inicio</button></a>
			</div>
			<h1 style="text-align:center" class="col-md-6">Mantenimiento de Peliculas</h1>
        </nav>
	
	<div style="text-align:center">

		<div class="row justify-content-center">
			<div class="col-auto">
				<table
					class="mt-5 table table-striped table-inverse table-responsive table-center table-bordered text-center">
					<caption>Listado de Peliculas de todos los tiempos</caption>
					<thead class="thead-inverse">
						<tr>
							<th id="director">Director</th>
							<th id="titulo">Título</th>
							<th id="fecha">Fecha</th>
							<th id="calificacion">Calificacion</th>
							<th id="modificar">Modificar</th>
							<th id="eliminar">Eliminar</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listaPelis}" var="item">

							<tr>

								<td>${item.director}</td>
								<td>${item.titulo}</td>
								<td>${item.fecha}</td>
								<td>${item.calificacion}</td>
								<td>
									<form action="updateFormPeliculas" method="POST" accept-charset="UTF-8">
										<input type="hidden" name="director" value="${item.director}">
										<input type="hidden" name="fecha" value="${item.fecha}">
										<input type="hidden" name="tituloPeli" value="${item.titulo}">
										<input type="hidden" name="descripcion" value="${item.descripcion}">
										<input type="hidden" name="foto" value="${item.foto}">
										<input type="hidden" name="usuario" value="${usuario}">
										<div style="align:center">
											<input type="submit" class="btn btn-primary" value="Modificar">
										</div>
									</form>
								</td>
								<td>
									<form action="deletePeliculas" method="POST" accept-charset="UTF-8">
										<input type="hidden" name="titulo" value="${item.titulo}">
										<input type="hidden" name="usuario" value="${usuario}">
										<div style="align:center">
											<input type="submit" class="btn btn-danger" value="Eliminar">
										</div>
									</form>
								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		
		<form action="formPeliculas" method="POST">
			<input type="hidden" name="usuario" value="${usuario}">
			<div style="align:center">
				<input type="submit" class="btn btn-primary" value="Añadir pelicula">
			</div>
		</form>

	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>