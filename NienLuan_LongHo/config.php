<?php
	define("DBSERVER","localhost");//
	define("DBUSERNAME","root");
	define("DBPASSWORD","");
	define("DBNAME","traicay");

	date_default_timezone_set("Asia/Ho_Chi_Minh");

	$conn = mysqli_connect(DBSERVER,DBUSERNAME,DBPASSWORD,DBNAME);//chuỗi kết nối
	$conn -> set_charset("utf8");//chuyển dữ liệu qua utf8
	if(!$conn){
		die('Connect error : '.mysqli_connect_errno());
	}
?>