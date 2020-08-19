<?php 

header('Content-Type: text/html; charset=UTF-8');

include ("conexion.php");
//Creacion de conexion
	$conexion = mysqli_connect($hostname, $user, $hostpass, $db);
	mysqli_set_charset($conexion,'utf8');

$usuario=$_GET['usuario'];

$query = "SELECT nombre, usuario, correo FROM usuarios WHERE usuario = '$usuario'";
$r = $conexion -> query($query);

while($fila = $r->fetch_array()){
	$producto[] = array_map('utf8_encode', $fila);
}

echo json_encode($producto);
?>		