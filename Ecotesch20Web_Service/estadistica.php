<?php 

header('Content-Type: text/html; charset=UTF-8');

include ("conexion.php");
//Creacion de conexion
	$conexion = mysqli_connect($hostname, $user, $hostpass, $db);
	mysqli_set_charset($conexion,'utf8');

$usuario=$_GET['usuario'];
			//Con esta consulta va a buscar que el registro este dentro de la Base de datos
			$query = "SELECT * FROM regbotella WHERE usuario='$usuario'";

			//$res = mysqli_fetch_array(mysqli_query($conexion,$query));
			$r = $conexion -> query($query);

				while($fila = $r->fetch_array()){
					$datos[] = array_map('utf8_encode', $fila);
				}

				
			echo json_encode($datos);
			

	mysqli_close($conexion);

	

 ?>