<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="utf-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Listado de peliculas</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm navbar-light bg-light nav-tabs">
			<div class="navbar-brand col-md-3">
				<a href="/"><button type="button" class="btn btn-warning">Volver</button></a>
			</div>
			<h1 style="text-align:center" class="col-md-6">Cartelera de peliculas</h1>
            <form class="form-inline my-2 my-lg-0" action="buscarPelis" method="POST">
            	<input class="form-control mr-sm-2" type="text" name="filtro" placeholder="Buscar">
            	<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Buscar</button>
            </form>
        </nav>
        
	<div style="text-align:center">
		<p style="color:red">${message}</p>
	</div>
	
	<br>
	
	<div class="row justify-content-md-center">
		
			<c:forEach items="${listaPelis}" var="item">
				<div class="card" style="width: 18rem; margin-right: 6px; margin-top: 6px;" class="col-6 col-md-4 col-lg-3">
				<img class="card-img-top" src="${item.foto}" alt="Card image cap" width="120px" height="180px">
					<ul class="list-group list-group-flush">
					<div class="card-header"><strong>Titulo:</strong> ${item.titulo}</div>
						<li class="list-group-item"><strong>Director:</strong> ${item.director}</li>
						<li class="list-group-item"><strong>Fecha:</strong> ${item.fecha}</li>
						<li class="list-group-item">
						<form action="moreInfo" method="POST" accept-charset="UTF-8">
							<input type="hidden" name="director" value="${item.director}">
							<input type="hidden" name="titulo" value="${item.titulo}">
							<input type="hidden" name="fecha" value="${item.fecha}">
							<input type="hidden" name="descripcion" value="${item.descripcion}">
							<input type="hidden" name="foto" value="${item.foto}">
							<input class="btn btn-outline-primary" type="submit" value="Para mas informacion...">
						</form>
						</li>
					</ul>	
				</div>
				<br>
				<br>
			</c:forEach>
		
		</div>
	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>
</html>