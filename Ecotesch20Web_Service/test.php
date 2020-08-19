<?php 

//Validacion del metodo de envio
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
	//Incluir el contenido del archivo
	include('conexion.php');

	//Creacion de conexion
	$conexion = mysqli_connect($hostname, $user, $hostpass, $db);

	//Determina el tipo de consulta que se 
	//va a realizar desde la app
	switch($_POST['opcion']){
		case 'resultadotest':
			//Valores que recibe por parte de la aplicacion
			$usuario = $_POST['usuario'];
			$resu = $_POST['resu'];
			
				$query2 = "INSERT INTO resultadoquest(calificacion,usuario) VALUES ('$resu','$usuario')";
				$r=mysqli_query($conexion,$query2);
				$response['error'] = false;
				$response['mensaje'] = "Registro exitoso";
			break;
			}
			
			
	}

	mysqli_close($conexion);

	//codificar en json
	echo json_encode($response);


 ?>