package longho.nienluan.traicayngoainhap.View.DatHang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.DatHang.PresenterLogicDatHang;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class DatHangActivity extends AppCompatActivity implements View.OnClickListener,ViewDatHang {
    Toolbar toolbar;
    EditText edTenNguoiNhan, edDiaChi, edSoDT;
    ImageButton imNhanTienKhiGiaoHang, imChuyenKhoan;
    TextView txtNhanTienKhiGiaoHang, txtChuyenKhoan;
    Button btnThanhToan;
    CheckBox cbThoaThuan;
    PresenterLogicDatHang presenterLogicThanhToan;
    List<ChiTietDDH> chiTietDDHList = new ArrayList<>();

    int chonHinhThuc = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_dathang);

        toolbar = findViewById(R.id.toolbar);
        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDT = findViewById(R.id.edSoDT);
        imNhanTienKhiGiaoHang = findViewById(R.id.imNhanTienKhiGiaoHang);
        imChuyenKhoan = findViewById(R.id.imChuyenKhoan);
        btnThanhToan = findViewById(R.id.btnDatHang);
//        cbThoaThuan = (CheckBox) findViewById(R.id.cbThoaThuan);
        txtNhanTienKhiGiaoHang = findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = findViewById(R.id.txtChuyenKhoan);
//
        presenterLogicThanhToan = new PresenterLogicDatHang(this,this);
        presenterLogicThanhToan.LayDanhSachSanPhamTrongGioHang();

        setSupportActionBar(toolbar);

        btnThanhToan.setOnClickListener(this);
        imNhanTienKhiGiaoHang.setOnClickListener(this);
        imChuyenKhoan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnDatHang:
                String tennguoinhan = edTenNguoiNhan.getText().toString();
                String sodt = edSoDT.getText().toString();
                String diachi = edDiaChi.getText().toString();

                if (tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0) {
                    DonDatHang donDatHang = new DonDatHang();
                    donDatHang.setTenNguoiDatHang(tennguoinhan);
                    donDatHang.setSoDienThoaiDatHang(sodt);
                    donDatHang.setDiaChiDatHang(diachi);
                    donDatHang.setChuyenKhoan(chonHinhThuc);
                    donDatHang.setChiTietDDHList(chiTietDDHList);
                    presenterLogicThanhToan.ThemDDH(donDatHang);

                } else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.imNhanTienKhiGiaoHang:
                ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang,txtChuyenKhoan);
                chonHinhThuc = 0;
                break;

            case R.id.imChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan,txtNhanTienKhiGiaoHang);
                chonHinhThuc = 1;
                break;
        }
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Thành công!", Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(DatHangActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Thất bại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<traicay> traicayList) {
        for (int i = 0; i < traicayList.size(); i++) {
            ChiTietDDH chiTietDDH = new ChiTietDDH();
            chiTietDDH.setMaTraiCay(traicayList.get(i).getMaTraiCay());
            chiTietDDH.setSoLuongDat(traicayList.get(i).getSoLuong());

            chiTietDDHList.add(chiTietDDH);
        }
    }

    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon){
        txtDuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorBlack));
    }
    private int getIdColor(int idcolor){

        int color =0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }
}
