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
		case 'LayThongTinNguoiDung':
			$ham();
			break;
		case 'ThayDoiThongTinNguoiDung':
			$ham();
			break;
		case 'DoiMatKhauNguoiDung':
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
		case 'LayChiTietTraiCayTheoMa':
			$ham();
			break;
		case 'ThemDanhGia':
			$ham();
			break;
		case 'LayDanhSachDanhGiaTheoMaTraiCay':
			$ham();
			break;
		case 'ThemDonDatHang':
			$ham();
			break;
		case 'LayDanhSachDonDatHangTheoMaND':
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
				$MaNguoiDung = $dong["MaNguoiDung"];
			}
			echo "{\"ketqua\":\"true\",\"TenNguoiDung\":\"".$TenNguoiDung."\",\"MaNguoiDung\":\"".$MaNguoiDung."\"}";
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
	function LayThongTinNguoiDung()
	{
		include_once("config.php");
		if(isset($_POST["MaNguoiDung"])){
			$MaNguoiDung = $_POST["MaNguoiDung"];
			$truyvan = "SELECT * FROM nguoidung WHERE MaNguoiDung = '".$MaNguoiDung."'";
		}
		if(isset($_POST["EmailND"])){
			$EmailND = $_POST["EmailND"];
			$truyvan = "SELECT * FROM nguoidung WHERE EmailND like '".$EmailND."'";
		}
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		// array_push($chuoijson, array('MaLTC' => $dong["MaLTC"],'TenLTC' => $dong["TenLTC"],'HinhLTC' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/LoaiTraiCay/".$dong["HinhLTC"] ));//in theo nhu cầu
		 		$chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"nguoidung\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function ThayDoiThongTinNguoiDung(){
    	include_once("config.php");
    	$MaNguoiDung = $_POST["MaNguoiDung"];
		$TenNguoiDung = $_POST["TenNguoiDung"];
		$DiaChiND = $_POST["DiaChiND"];
		$SoDienThoaiND = $_POST["SoDienThoaiND"];
		$EmailND = $_POST["EmailND"];
		$GioiTinh = $_POST["GioiTinh"];
		$MatKhau = $_POST["MatKhau"];
		$CauHoi = $_POST["CauHoi"];
		$CauTraLoi = $_POST["CauTraLoi"];

		// $MaNguoiDung = $_GET["MaNguoiDung"];
		// $TenNguoiDung = $_GET["TenNguoiDung"];
		// $DiaChiND = $_GET["DiaChiND"];
		// $SoDienThoaiND = $_GET["SoDienThoaiND"];
		// $EmailND = $_GET["EmailND"];
		// $GioiTinh = $_GET["GioiTinh"];
		// $MatKhau = $_GET["MatKhau"];
		// $CauHoi = $_GET["CauHoi"];
		// $CauTraLoi = $_GET["CauTraLoi"];

		$truyvan = "UPDATE nguoidung SET TenNguoiDung = '".$TenNguoiDung."', DiaChiND = '".$DiaChiND."', SoDienThoaiND = '".$SoDienThoaiND."', EmailND = '".$EmailND."', GioiTinh = '".$GioiTinh."', MatKhau = '".$MatKhau."', CauHoi = '".$CauHoi."', CauTraLoi = '".$CauTraLoi."' WHERE MaNguoiDung = '".$MaNguoiDung."'";
		if(mysqli_query($conn,$truyvan)){
			echo "{\"ketqua\":\"true\"}";
		}
		else echo "{\"ketqua\":\"false\"}";
	}
	function DoiMatKhauNguoiDung(){
    	include_once("config.php");
    	$MaNguoiDung = $_POST["MaNguoiDung"];
		$MatKhau = $_POST["MatKhau"];

		// $MaNguoiDung = $_GET["MaNguoiDung"];
		// $MatKhau = $_GET["MatKhau"];

		$truyvan = "UPDATE nguoidung SET MatKhau = '".$MatKhau."' WHERE MaNguoiDung = '".$MaNguoiDung."'";
		if(mysqli_query($conn,$truyvan)){
			echo "{\"ketqua\":\"true\"}";
		}
		else echo "{\"ketqua\":\"false\"}";
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
		$truyvan = "SELECT * FROM loaitraicay ORDER BY TenLTC ASC";
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

	function LayChiTietTraiCayTheoMa()
	{
		//cách test http://localhost/NienLuan_LongHo/traicay.php?ham=LayDanhSachTraiCayTheoMaLoai&maLTC=1
		include_once("config.php");
		$MaTraiCay = $_POST["matraicay"];
		$truyvan = "SELECT * FROM traicay tc,nhacungcap ncc WHERE MaTraiCay =".$MaTraiCay." AND tc.MaNCC = ncc.MaNCC" ;
		$ketqua = mysqli_query($conn, $truyvan);
		$chuoijson = array();
		if($ketqua){
			while($dong = mysqli_fetch_array($ketqua)){
		 		// echo $dong["TenLTC"]."<br>";
		 		// in ra mảng theo cấu trúc bảng
		 		array_push($chuoijson, array('MaTraiCay' => $dong["MaTraiCay"],'MaLTC' => $dong["MaLTC"],'MaNCC' => $dong["MaNCC"],'MaQG' => $dong["MaQG"],'TenTraiCay' => $dong["TenTraiCay"],'GiaBan' => $dong["GiaBan"],'HinhTraiCay' => "http://".$_SERVER['SERVER_NAME']."/NienLuan_LongHo"."/Image"."/TraiCay/".$dong["HinhTraiCay"],'HinhChiTiet' => $dong["HinhChiTiet"],'MieuTaTC' => $dong["MieuTaTC"],'LuotMua' => $dong["LuotMua"],'ThanhPhanDinhDuong' => $dong["ThanhPhanDinhDuong"],'MoiTruongTrong' => $dong["MoiTruongTrong"],'SoLuongTon' => $dong["SoLuongTon"],'TenNCC' => $dong["TenNCC"],'DiaChiNCC' => $dong["DiaChiNCC"] ));//in theo nhu cầu
		 		// $chuoijson[]=$dong;//in ra toàn bộ bảng nhưng không đúng định dạng
		 	}
		 	echo "{";
		 	echo "\"traicay\":";
		 	echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);
		 	echo "";
		 	echo "}";
	 	}
	}

	function ThemDanhGia(){
		include_once("config.php");

		if(isset($_POST["madg"]) || isset($_POST["masp"]) || isset($_POST["tenthietbi"]) || isset($_POST["tieude"]) || isset($_POST["noidung"]) || isset($_POST["sosao"]) ){
			$madg = $_POST["madg"];
			$masp = $_POST["masp"];
			$tenthietbi = $_POST["tenthietbi"];
			$tieude = $_POST["tieude"];
			$noidung = $_POST["noidung"];
			$sosao = $_POST["sosao"];
		}

		$ngaydang = date("Y/m/d");
		// $ngaydang = Date(Now());

		$truyvan = "INSERT INTO danhgia (MaDG,MaTraiCay,TenThietBi,TieuDe,NoiDungDG,SoSaoDG,NgayDG) VALUES ('".$madg."', '".$masp."', '".$tenthietbi."', '".$tieude."', '".$noidung."', '".$sosao."', '".$ngaydang."' )";
		$ketqua = mysqli_query($conn,$truyvan);

		if($ketqua){
			echo "{ketqua:true}";
		}else{
			echo "{ketqua:false}";
		}

	}

	function LayDanhSachDanhGiaTheoMaTraiCay(){
		include_once("config.php");
		$chuoijson = array();

		if(isset($_POST["masp"]) || isset($_POST["limit"]) ){
			$masp = $_POST["masp"];
			$limit = $_POST["limit"];
		}

		$truyvan = "SELECT * FROM danhgia WHERE MaTraiCay = ".$masp." ORDER BY NgayDG LIMIT ".$limit." ,10";
		$ketqua = mysqli_query($conn,$truyvan);

		echo "{";
		echo "\"DanhSachDanhGia\":";

		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {
				$chuoijson[] = $dong;
			}
		}

		echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);

		echo "}";

	}

	function ThemDonDatHang(){
		include_once("config.php");

		if(isset($_POST["danhsachsanpham"]) || isset($_POST["manguoidung"]) || isset($_POST["tennguoinhan"]) || isset($_POST["sodt"]) || isset($_POST["diachi"]) || isset($_POST["chuyenkhoan"]) ){
			$danhsachsanpham = $_POST["danhsachsanpham"];
			$manguoidung = $_POST["manguoidung"];
			$tennguoinhan = $_POST["tennguoinhan"];
			$sodt = $_POST["sodt"];
			$diachi = $_POST["diachi"];
			$chuyenkhoan = $_POST["chuyenkhoan"];
		}

		$ngayhientai = date("Y/m/d");
		$ngaygiao = date_create($ngayhientai);
		$ngaygiao = date_modify($ngaygiao,"+3 days");
		$ngaygiao = date_format($ngaygiao,"Y/m/d") ;
		$trangthai = "chờ kiểm duyệt";

		$truyvan = "INSERT INTO dondathang (MaNguoiDung,NgayDat,NgayGiao,TrangThaiGiao,TenNguoiDatHang,SoDienThoaiDatHang,DiaChiDatHang,ChuyenKhoan) VALUES ('".$manguoidung."', '".$ngayhientai."', '".$ngaygiao."', '".$trangthai."', '".$tennguoinhan."', '".$sodt."', '".$diachi."', '".$chuyenkhoan."')";
		$ketqua = mysqli_query($conn,$truyvan);

		if($ketqua){
			$mahd = mysqli_insert_id($conn);
			$chuoijsonandroid = json_decode($danhsachsanpham);
			$arrayDanhSachSanPham = $chuoijsonandroid->DANHSACHSANPHAM;
			$dem = count($arrayDanhSachSanPham);

			for($i=0; $i<$dem; $i++){
				$jsonObect = $arrayDanhSachSanPham[$i];

				$masp = $jsonObect->MaTraiCay;
				$soluong = $jsonObect->SoLuongDat;

				$truyvan = "INSERT INTO chitietddh (MaDDH,MaTraiCay,SoLuongDat) VALUES ('".$mahd."', '".$masp."', '".$soluong."')";
				$ketqua1 = mysqli_query($conn,$truyvan);


			}

			echo "{ketqua:true}" ;

		}else{
			echo "{ketqua:false}";
		}

	}
	function LayDanhSachDonDatHangTheoMaND(){
		include_once("config.php");
		$chuoijson = array();

		if(isset($_POST["manguoidung"])){
			$manguoidung = $_POST["manguoidung"];
		}

		// if(isset($_GET["manguoidung"])){
		// 	$manguoidung = $_GET["manguoidung"];
		// }

		$truyvan = "SELECT * FROM dondathang where MaNguoiDung = ".$manguoidung;
		$ketqua = mysqli_query($conn,$truyvan);

		echo "{";
		echo "\"DanhSachDonDatHang\":";

		if($ketqua){
			while ($dong = mysqli_fetch_array($ketqua)) {

				$truyvanchitietddh = "SELECT * FROM chitietddh ctddh, traicay tc WHERE ctddh.MaDDH = '".$dong["MaDDH"]."' AND ctddh.MaTraiCay = tc.MaTraiCay";

				$ketquadondathang = mysqli_query($conn,$truyvanchitietddh);

				$chuoijsondanhsachsanpham = array();

				if($ketquadondathang){
					while ( $dongsanpham = mysqli_fetch_array($ketquadondathang) ) {
						$chuoijsondanhsachsanpham[] = $dongsanpham;
					}
				}

				array_push($chuoijson, array("MaDDH"=>$dong["MaDDH"],"MaNguoiDung"=>$dong["MaNguoiDung"],"TenNguoiDatHang"=>$dong["TenNguoiDatHang"],"SoDienThoaiDatHang"=>$dong["SoDienThoaiDatHang"],"DiaChiDatHang"=>$dong["DiaChiDatHang"],"NgayDat"=>$dong["NgayDat"],"NgayGiao"=>$dong["NgayGiao"],"TrangThaiGiao"=>$dong["TrangThaiGiao"],"MoTa"=>$dong["MoTa"],"DanhSachTraiCay"=>$chuoijsondanhsachsanpham));

			}
		}

		echo json_encode($chuoijson,JSON_UNESCAPED_UNICODE);

		echo "}";
	}
?>