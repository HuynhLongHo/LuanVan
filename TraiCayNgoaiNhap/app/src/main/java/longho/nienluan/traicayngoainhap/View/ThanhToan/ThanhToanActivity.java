package longho.nienluan.traicayngoainhap.View.ThanhToan;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import longho.nienluan.traicayngoainhap.R;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;
//    EditText edTenNguoiNhan,edDiaChi,edSoDT;
//    ImageButton imNhanTienKhiGiaoHang,imChuyenKhoan;
//    TextView txtNhanTienKhiGiaoHang,txtChuyenKhoan;
//    Button btnThanhToan;
//    CheckBox cbThoaThuan;
//    PresenterLogicThanhToan presenterLogicThanhToan;
//    List<ChiTietHoaDon> chiTietHoaDons = new ArrayList<>();

    int chonHinhThuc = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_thanhtoan);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        edTenNguoiNhan = (EditText) findViewById(R.id.edTenNguoiNhan);
//        edDiaChi = (EditText) findViewById(R.id.edDiaChi);
//        edSoDT = (EditText) findViewById(R.id.edSoDT);
//        imNhanTienKhiGiaoHang = (ImageButton) findViewById(R.id.imNhanTienKhiGiaoHang);
//        imChuyenKhoan = (ImageButton) findViewById(R.id.imChuyenKhoan);
//        btnThanhToan = (Button) findViewById(R.id.btnThanhToan);
//        cbThoaThuan = (CheckBox) findViewById(R.id.cbThoaThuan);
//        txtNhanTienKhiGiaoHang = (TextView) findViewById(R.id.txtNhanTienKhiGiaoHang);
//        txtChuyenKhoan = (TextView) findViewById(R.id.txtChuyenKhoan);
//
//        presenterLogicThanhToan = new PresenterLogicThanhToan(this,this);
//        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();

        setSupportActionBar(toolbar);

//        btnThanhToan.setOnClickListener(this);
//        imNhanTienKhiGiaoHang.setOnClickListener(this);
//        imChuyenKhoan.setOnClickListener(this);
    }
}
