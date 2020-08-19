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
		case 'producto':
			//Valores que recibe por parte de la aplicacion
			$cb = $_POST['cb'];
			$usuario = $_POST['usuario'];
			$fecha = $_POST['fecha'];
			$valor = 1;

			//Con esta consulta va a buscar que el registro este dentro de la Base de datos
			$query = "SELECT codbarras FROM producto WHERE codbarras = '$cb'";

			//Ejecucion del query
			$res = mysqli_fetch_array(mysqli_query($conexion,$query));

			//Validacion de existencia de datos
			if(isset($res)){
				$query2 = "INSERT INTO regbotella VALUES ('','$usuario',$cb,'$fecha','$valor')";
				$r = mysqli_query($conexion,$query2);
				$response['error'] = false;
				$response['mensaje'] = "Datos correctos";
			}
			else{
				$response['error'] = true;
				$response['mensaje'] = "Este producto no esta registrado en la base de datos";
			}
			break;
		case 'punto':
			$punto = $_POST['punto'];
			$usuario = $_POST['usuario'];
			if($punto == '1'){

				$query2 = "UPDATE usuarios SET puntos = ( SELECT puntos FROM usuarios WHERE correo = '$usuario') + 1 WHERE correo = '$usuario'";
				$res2 = mysqli_query($conexion,$query2);
				$response['error'] = false;
				$response['mensaje'] = "Actualizado";
			}
			break;

			
	}
	//codificar en json
	echo json_encode($response);

	mysqli_close($conexion);

	
}

 ?>