<?php
	define("DBSERVER","localhost");//
	define("DBUSERNAME","root");
	define("DBPASSWORD","");
	define("DBNAME","traicay");

	date_default_timezone_set("Asia/Ho_Chi_Minh");

	//cau hinh ket noi
    // $db_localhost="localhost";
    // $db_user="root";
    // $db_pass="";
    // $db_data="traicay";
    $cookie_name = 'siteAuth';
    $cookie_time = (60); // 30 days
 //    $dbconect = mysql_connect($db_localhost,$db_user,$db_pass) or die('ket noi khong thanh cong');
 //    mysql_select_db($db_data,$dbconect);
	// mysql_query("set names 'utf8'");

	$conn = mysqli_connect(DBSERVER,DBUSERNAME,DBPASSWORD,DBNAME);//chuỗi kết nối
	$conn -> set_charset("utf8");//chuyển dữ liệu qua utf8
	if(!$conn){
		die('Connect error : '.mysqli_connect_errno());
	}
?>

