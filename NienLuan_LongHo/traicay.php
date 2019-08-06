<?php

	$ham = $_POST["ham"];
	// $ham = $_GET["ham"];

	switch ($ham) {
		case 'LayDanhSachMenu':
			$ham();
			break;
		case 'DangKy':
			$ham();
			break;
		case 'KiemTraDangNhap':
			$ham();
			break;
		case 'LayDanhSachLoaiTraiCay':
			$ham();
			break;
	}


	function KiemTraDangNhap(){
		include_once("config.php");

		$EmailND = $_POST["EmailND"];
		$MatKhau = $_POST["MatKhau"];

		$truyvan = "SELECT * FROM nguoidung WHERE EmailND = '".$EmailND."' AND MatKhau = '".$MatKhau."' ";
		$ketqua = (mysqli_query($conn,$truyvan));
		$demdong = mysqli_num_rows($ketqua);
		if($demdong>=1){
			$TenNguoiDung="";
			while ($dong = mysqli_fetch_array($ketqua)) {
				$TenNguoiDung = $dong["TenNguoiDung"];
			}
			echo "{\"ketqua\":\"true\",\"TenNguoiDung\":\"".$TenNguoiDung."\"}";
		}
		else {
			echo "{\"ketqua\":\"false\"}";
		}
	}

	function DangKy(){
    	include_once("config.php");

		$TenNguoiDung = $_POST["TenNguoiDung"];
		$DiaChiND = $_POST["DiaChiND"];
		$SoDienThoaiND = $_POST["SoDienThoaiND"];
		$EmailND = $_POST["EmailND"];
		$GioiTinh = $_POST["GioiTinh"];
		$MatKhau = $_POST["MatKhau"];
		$CauHoi = $_POST["CauHoi"];
		$CauTraLoi = $_POST["CauTraLoi"];
		$MaQuyen = $_POST["MaQuyen"];
		$truyvan = "INSERT INTO nguoidung (TenNguoiDung, DiaChiND, SoDienThoaiND, EmailND, GioiTinh,MatKhau,CauHoi,CauTraLoi, MaQuyen) VALUES ('".$TenNguoiDung."', '".$DiaChiND."', '".$SoDienThoaiND."', '".$EmailND."', '".$GioiTinh."', '".$MatKhau."', '".$CauHoi."', '".$CauTraLoi."', '".$MaQuyen."')";
		if(mysqli_query($conn,$truyvan)){
			echo "{\"ketqua\":\"true\"}";
		}
		else echo "{\"ketqua\":\"false\"}";
		mysql_close($conn);
	}
	function LayDanhSachMenu()
	{
		include_once("config.php");
		$MaLTC = $_POST["MaLTC"];

		$truyvan = "SELECT * FROM loaitraicay join traicay on loaitraicay.MaLTC = traicay.MaLTC where loaitraicay.MaLTC=".$MaLTC;
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		/*echo $dong["TenLTC"]."<br>";
		 		//in ra mảng theo cấu trúc bảng*/
		 		// array_push($chuoijson, array('MaLTC' => $dong["MaLTC"],'TenLTC' => $dong["TenLTC"] ));//in theo nhu cầu
		 		$chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"loaitraicay\":";
		 	echo json_encode($chuoijson);
		 	echo "";
		 	echo "}";
	 	}
	 	mysql_close($conn);
	}

	function LayDanhSachLoaiTraiCay()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM loaitraicay";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaLTC' => $dong["MaLTC"],'TenLTC' => $dong["TenLTC"],'HinhLTC' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image/"."/LoaiTraiCay/".$dong["HinhLTC"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"loaitraicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}
?>