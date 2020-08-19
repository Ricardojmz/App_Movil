
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
		case 'registro':
			//Valores que recibe por parte de la aplicacion
			$nombrer = $_POST['nombrer'];
			$userr = $_POST['userr'];
			$emailr = $_POST['emailr'];
			$passr = $_POST['passr'];
			$fecha = $_POST['fecha'];
			$punto = 0;

			//Con esta consulta va a buscar que el registro este dentro de la Base de datos

			$query1 = "SELECT * FROM usuarios WHERE correo = '$emailr' OR usuario = '$userr'";
			

			//Ejecucion del query
			$res = mysqli_fetch_array(mysqli_query($conexion,$query1));

			//Validacion de repetecion de datos
			if ($res > 0) {
				$response['error'] = true;
				$response['mensaje'] = "El nombre de usuario o correo ya existen";
			}
			else{
			
				$query2 = "INSERT INTO usuarios VALUES ('','$nombrer','$userr','$emailr',MD5('$passr'),'$fecha',$punto)";
				$r=mysqli_query($conexion,$query2);
				$response['error'] = false;
				$response['mensaje'] = "Registro exitoso";

			}
			break;
			
	}

	mysqli_close($conexion);

	//codificar en json
	echo json_encode($response);
}

 ?>