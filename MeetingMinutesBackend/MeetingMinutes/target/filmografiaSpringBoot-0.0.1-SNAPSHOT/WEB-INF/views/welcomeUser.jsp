<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Pagina de bienvenida</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm navbar-light bg-light nav-tabs">
			<div class="navbar-brand col-md-3">
				<a href="loginPage"><button type="button" class="btn btn-warning">Volver</button></a>
			</div>
			<h1 style="text-align:center" class="col-md-6">Bienvenido ${usuario}</h1>
        </nav>
		
		<br>
		
	<div style="text-align: center; bg-color:light-gray">
	
		<form action="MantenimientoPelicula" method="POST" accept-charset="UTF-8">
			<input type="hidden" name="usuario" value="${usuario}">
			<input type="submit" class="btn btn-primary" value="Mantenimiento Peliculas">
		</form>
		<br>
		<form action="addFormUser" method="POST" accept-charset="UTF-8">
			<input type="hidden" name="usuario" value="${usuario}">
			<input type="submit" class="btn btn-primary" value="Alta Usuario Admin">
		</form>
		<br>		
		<form action="sacarId" method="POST" accept-charset="UTF-8">
			<input type="hidden" name="usuario" value="${usuario}">
			<input type="submit" class="btn btn-primary" value="Calificar Pelicula">
		</form>
		
	</div>


</body>
</html>