<?php
    session_start();
    include_once("config.php");
    //kiem tra cookie xem da tôn tai chua
    //neu chua thi minh ha dang nhap;

    if(empty($_SESSION['username'])){
        if(isset($_COOKIE[$cookie_name])){
        	parse_str($_COOKIE[$cookie_name]);
            $sql2="select * from nguoidung nd, quyentruycap q where nd.EmailND='$usr' and nd.MatKhau='$hash' and q.TenQuyen = 'Admin' and nd.MaQuyen = q.MaQuyen";
            $result2=mysqli_query($conn, $sql2);
            if($result2){
                $row=mysqli_fetch_array($result2);
                //lưu lại session để không bị trả về trang này vì không có session
                $_SESSION['username']=$row['EmailND'];
                $_SESSION['password']=$row['MatKhau'];
                $_SESSION['tennguoidung']=$row['TenNguoiDung'];
                header('location:index.php');
                exit;
            }
        }
    }
    else{
        header('location:index.php');//chuyển qua trang đăng nhập thành công
        exit;
    }

    if(isset($_POST['submit'])){
        $username=$_POST['username'];
        $password=$_POST['password'];
        $a_check=((isset($_POST['remember'])!=0)?1:"");
        if($username=="" || $password==""){
            echo "vui long dien day du thong tin";
            exit;
        }
        else{
            $truyvan="select * from nguoidung nd, quyentruycap q where nd.EmailND='$username' and nd.MatKhau='$password' and q.TenQuyen = 'Admin' and nd.MaQuyen = q.MaQuyen";
            echo $truyvan;
            $result = mysqli_query($conn, $truyvan);
            if(!$result){
                echo "loi cau truy van".mysql_error();
                exit;
            }
            $row=mysqli_fetch_array($result);
            $f_user=$row['EmailND'];
            $f_pass=$row['MatKhau'];
            $TenNguoiDung=$row['TenNguoiDung'];
            if($f_user==$username && $f_pass==$password){
                $_SESSION['username']=$f_user;
                $_SESSION['password']=$f_pass;
                $_SESSION['tennguoidung']=$TenNguoiDung;
                if($a_check==1){
                	setcookie ($cookie_name, 'usr='.$f_user.'&hash='.$f_pass, time() + $cookie_time);
            	}
                header('location:index.php');//chuyền qua trang đăng nhập thành công
                exit;
        	}
    	}
    }
?>

<!DOCTYPE html>
	<html>
	<head>
	    <title>Login Remember</title>
	    <link rel="stylesheet" type="text/css" href="style.css">
	    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css" rel="stylesheet"> <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	</head>
	<body>
		<div class="container">
		    <div class="form">
		        <form action="login.php" method="post">
		                <h3 class="btn btn-primary form-control">Đăng nhập</h3>
		                <table class="table">
		                    <tr>
		                        <td><label class="label label-primary">Email</label></td>
		                        <td><input type="text"  class="form-control" name="username" value=""></td>
		                    </tr>
		                    <tr>
		                        <td><label class="label label-primary">Mật khẩu</label></td>
		                        <td><input type="password" class="form-control" name="password" value=""></td>
		                    </tr>
		                    <tr>
		                    	<td></td>
		                        <td>
		                            <div class="remember">
		                                <input type="checkbox"  name="remember" value="1">
		                                <label>Ghi nhớ đăng nhập</label>
		                            </div>
		                        </td>
		                    </tr>
		                    <tr>
		                        <td colspan="2">
		                            <input type="submit" class="form-control btn-info submit_login" value="Login" name="submit">
		                        </td>
		                    </tr>
		                </table>
		        </form>
		    </div>
		</div>
		<script type="text/javascript" src="../js/bootstrap.min.js"></script>
		</body>
</html>
