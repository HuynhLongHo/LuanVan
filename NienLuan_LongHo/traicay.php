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
		case 'LayDanhSachQuocGia':
			$ham();
			break;
		case 'LayDanhSachDangKhuyenMai':
			$ham();
			break;
		case 'LayDanhSachSapKhuyenMai':
			$ham();
			break;
		case 'LayDanhSachTop10KhuyenMai':
			$ham();
			break;
		case 'LayDanhSachTopTraiCayTheoLuotMua':
			$ham();
			break;
		case 'LayDanhSachTraiCayGiaRe':
			$ham();
			break;
		case 'LayDanhSachTraiCayNgauNhien':
			$ham();
			break;
		case 'LayDanhSachTraiCayTheoMaLoai':
			$ham();
			break;
		case 'LayDanhSachTraiCayTheoQuocGia':
			$ham();
			break;
		case 'LayDanhSachTraiCayKhuyenMai':
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
		 		array_push($chuoijson, array('MaLTC' => $dong["MaLTC"],'TenLTC' => $dong["TenLTC"],'HinhLTC' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/LoaiTraiCay/".$dong["HinhLTC"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"loaitraicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachQuocGia()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM quocgia";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaQG' => $dong["MaQG"],'TenQG' => $dong["TenQG"],'QuocKiQG' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/QuocGia/".$dong["QuocKiQG"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"quocgia\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachDangKhuyenMai()
	{
		include_once("config.php");
		// $truyvan = "SELECT * FROM khuyenmai where (NgayBatDau <= Date(Now())) and (NgayKetThuc >= Date(Now()))";
		$truyvan = "SELECT * FROM khuyenmai where Date(Now()) between NgayBatDau and NgayKetThuc";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaKM' => $dong["MaKM"],'TenKM' => $dong["TenKM"],'NgayBatDau' => $dong["NgayBatDau"],'NgayKetThuc' => $dong["NgayKetThuc"],'HinhKM' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/KhuyenMai/".$dong["HinhKM"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"khuyenmai\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachSapKhuyenMai()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM khuyenmai where NgayBatDau > Date(Now())";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaKM' => $dong["MaKM"],'TenKM' => $dong["TenKM"],'NgayBatDau' => $dong["NgayBatDau"],'NgayKetThuc' => $dong["NgayKetThuc"],'HinhKM' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/KhuyenMai/".$dong["HinhKM"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"khuyenmai\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachTop10KhuyenMai()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM khuyenmai ORDER BY NgayBatDau DESC LIMIT 10";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaKM' => $dong["MaKM"],'TenKM' => $dong["TenKM"],'NgayBatDau' => $dong["NgayBatDau"],'NgayKetThuc' => $dong["NgayKetThuc"],'HinhKM' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/KhuyenMai/".$dong["HinhKM"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"khuyenmai\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachTopTraiCayTheoLuotMua()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM traicay ORDER BY LuotMua DESC";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'LuotMua' => $dong["LuotMua"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachTraiCayGiaRe()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM traicay ORDER BY GiaBan ASC";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'LuotMua' => $dong["LuotMua"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachTraiCayNgauNhien()
	{
		include_once("config.php");
		$truyvan = "SELECT * FROM traicay ORDER BY RAND()";
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'LuotMua' => $dong["LuotMua"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachTraiCayTheoMaLoai()
	{
		//cách test http://localhost/NienLuan_LongHo/traicay.php?ham=LayDanhSachTraiCayTheoMaLoai&maLTC=1
		include_once("config.php");
		$MaLTC = $_POST["maLTC"];
		$truyvan = "SELECT * FROM traicay WHERE MaLTC =".$MaLTC;
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'LuotMua' => $dong["LuotMua"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function LayDanhSachTraiCayTheoQuocGia()
	{
		//cách test http://localhost/NienLuan_LongHo/traicay.php?ham=LayDanhSachTraiCayTheoMaLoai&maLTC=1
		include_once("config.php");
		$MaQG = $_POST["maqg"];
		$truyvan = "SELECT * FROM traicay WHERE MaQG =".$MaQG;
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'LuotMua' => $dong["LuotMua"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}
	function LayDanhSachTraiCayKhuyenMai()
	{
		//cách test http://localhost/NienLuan_LongHo/traicay.php?ham=LayDanhSachTraiCayTheoMaLoai&maLTC=1
		include_once("config.php");
		$MaKM = $_POST["makm"];
		$truyvan = "SELECT * FROM chitietkhuyenmai ctkm join traicay tc on ctkm.MaTraiCay = tc.MaTraiCay  WHERE MaKM =".$MaKM;
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'GiaKM' => $dong["GiaKM"],'LuotMua' => $dong["LuotMua"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}
?>