
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
<title>Alta Pelicula</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm navbar-light bg-light nav-tabs">
			<div class="navbar-brand col-md-3">
				<form action="MantenimientoPelicula" method="POST">
					<input type="hidden" name="usuario" value="${usuario}">
					<input type="submit" class="btn btn-danger" value="Cancelar">
				</form>
			</div>
			<h1 style="text-align:center" class="col-md-6">Bienvenido al alta de Peliculas</h1>
        </nav>
	
	<div style="text-align:center">
	
		<p>Rellenando el siguiente formulario podrás añadir una nueva pelicula.</p>
		
		<form action="addPeliculas" method="POST" accept-charset="UTF-8">
			
			<div class="form-group">
			
				<input type="hidden" name="usuario" value="${usuario}">
				<input class="form-control" type="text" name="director" placeholder="Nombre director" required>
				<small class="form-text text-muted"></small>
				<input class="form-control" type="text" name="titulo" placeholder="Titulo pelicula" required>
				<small class="form-text text-muted"></small>
				<input class="form-control" type="date" name="fecha" placeholder="YYYY-MM-DD" required>
				<small class="form-text text-muted">Formato de fecha YYYY-MM-DD</small>
				<input class="form-control" type="text" name="descripcion" placeholder="Descripcion" required>
				<small class="form-text text-muted"></small>
				<input class="form-control" type="text" name="foto" placeholder="URL de la foto" required>
				<small class="form-text text-muted">Tamaño de la foto 1920x1080</small>
				<br>
				<input class="btn btn-success" type="submit" value="Añadir pelicula">
				
			</div>
		
		</form>
		
	</div>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	
</body>
</html>