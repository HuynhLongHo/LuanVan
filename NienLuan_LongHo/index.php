<?php
	include_once("config.php");
	$truyvan = "select * from dondathang";


	$result = mysqli_query($conn, $truyvan);

	if (mysqli_num_rows($result) > 0)
	{
		echo "<table border=1 cellspacing=0 cellpading=0>
		<tr> <td>Mã đơn hàng</td> <td>Tên người đặt</td></tr>";
		while($row = mysqli_fetch_assoc($result)) {
	        echo "<tr> <td>" .$row["MaDDH"]. "</td><td>" .$row["TenNguoiDatHang"]. "</td></tr>";
	    }
		echo "</table>";

	}
	else {
	    echo "Không có record nào";
	}

	mysqli_close($conn);

?>