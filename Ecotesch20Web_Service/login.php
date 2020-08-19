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
		case 'login':
			//Valores que recibe por parte de la aplicacion
			$user = $_POST['user'];
			$pass = $_POST['pass'];

			//Con esta consulta va a buscar que el registro este dentro de la Base de datos
			$query = "SELECT * FROM usuarios WHERE correo = '$user' AND pass = MD5('$pass')";

			//Ejecucion del query
			$res = mysqli_fetch_array(mysqli_query($conexion,$query));

			//Validacion de existencia de datos
			if(isset($res)){
				$response['error'] = false;
				$response['mensaje'] = "Datos correctos";
			}
			else{
				$response['error'] = true;
				$response['mensaje'] = "Datos incorrectos";
			}
			break;
			
	}

	mysqli_close($conexion);

	//codificar en json
	echo json_encode($response);
}

 ?>