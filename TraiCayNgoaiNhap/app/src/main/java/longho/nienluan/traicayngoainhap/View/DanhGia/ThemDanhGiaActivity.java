package longho.nienluan.traicayngoainhap.View.DanhGia;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;
import android.provider.Settings.System;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.Presenter.DanhGia.PresenterLogicDanhGia;
import longho.nienluan.traicayngoainhap.R;

public class ThemDanhGiaActivity extends AppCompatActivity implements ViewDanhGia,RatingBar.OnRatingBarChangeListener,View.OnClickListener {

    TextInputLayout input_edTieuDe,input_edNoiDung;
    EditText edTieuDe,edNoiDung;
    RatingBar rbDanhGia;
    int matraicay = 0;
    int sosao = 0;
    String manguoidung;
    String tentraicay;
    Button btnDongYDanhGia;
    PresenterLogicDanhGia presenterLogicDanhGia;
    ModelDangNhap modelDangNhap;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_themdanhgia);

        input_edNoiDung = findViewById(R.id.input_edNoiDungDanhGia);
        input_edTieuDe =findViewById(R.id.input_edTieuDeDanhGia);
        edTieuDe = findViewById(R.id.edTieuDe);
        edNoiDung = findViewById(R.id.edNoiDung);
        rbDanhGia = findViewById(R.id.rbDanhGia);
        btnDongYDanhGia = findViewById(R.id.btnDongYDanhGia);

        matraicay = getIntent().getIntExtra("matraicay",0);
        tentraicay = getIntent().getStringExtra("tentraicay");
        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        modelDangNhap = new ModelDangNhap();
        manguoidung = modelDangNhap.LayMaNguoiDung(this);

        rbDanhGia.setOnRatingBarChangeListener(this);
        btnDongYDanhGia.setOnClickListener(this);

    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        sosao = (int) rating;
    }

    @Override
    public void DanhGiaThanhCong() {
        Toast.makeText(this, "Đánh giá thành công!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void DanhGiaThatBai() {
        Toast.makeText(this,"Hôm nay đã đánh giá " + tentraicay + " !", Toast.LENGTH_LONG).show();

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoMa(List<DanhGia> danhGiaList) {

    }

    @SuppressLint("MissingPermission")
    @Override
    public void onClick(View v) {
        String madg = System.getString(this.getContentResolver(),Settings.Secure.ANDROID_ID);
        String tenthietbi = Build.MODEL;
        String tieude = edTieuDe.getText().toString();
        String noidung = edNoiDung.getText().toString();

        if(tieude.trim().length() > 0){
            input_edTieuDe.setErrorEnabled(false);
            input_edTieuDe.setError("");
        }else{
            input_edTieuDe.setErrorEnabled(true);
            input_edTieuDe.setError("Bạn chưa nhập tiêu đề !");
        }

        if(noidung.trim().length() > 0){
            input_edNoiDung.setError("");
            input_edNoiDung.setErrorEnabled(false);
        }else{
            input_edNoiDung.setErrorEnabled(true);
            input_edNoiDung.setError("Bạn chưa nhập nội dung");
        }

        if(!input_edNoiDung.isErrorEnabled() && !input_edTieuDe.isErrorEnabled()){
            DanhGia danhGia = new DanhGia();
            danhGia.setMaTraiCay(matraicay);
            danhGia.setMaDG(madg);
            danhGia.setMaNguoiDung(Integer.parseInt(manguoidung));
            danhGia.setNoiDungDG(noidung);
            danhGia.setSoSaoDG(sosao);
            danhGia.setTenThietBi(tenthietbi);

            presenterLogicDanhGia.ThemDanhGia(danhGia);
            finish();

        }
    }
}
