package longho.nienluan.traicayngoainhap.View.DoiMatKhau;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Presenter.DoiMatKhau.PresenterLogicDoiMatKhau;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.DangNhapActivity;

public class QuenMatKhauActivity extends AppCompatActivity implements ViewDoiMatKhau,View.OnClickListener {

    PresenterLogicDoiMatKhau presenterLogicDoiMatKhau;
    EditText edMatKhauMoi, edXacNhanMatKhau;
    TextInputLayout input_edMatKhauMoi, input_edXacNhanMatKhau;
    Button btnXacNhan;
    String manguoidung,matkhaucu,matkhaumoi,xacnhanmatkhau,matkhauhientai;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_quenmatkhau);

        manguoidung = getIntent().getStringExtra("MaNguoiDung");
        matkhauhientai = getIntent().getStringExtra("MatKhau");

        edMatKhauMoi = findViewById(R.id.edMatKhauMoi);
        edXacNhanMatKhau = findViewById(R.id.edXacNhanMatKhau);
        input_edMatKhauMoi = findViewById(R.id.input_edMatKhauMoi);
        input_edXacNhanMatKhau = findViewById(R.id.input_edXacNhanMatKhau);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenterLogicDoiMatKhau = new PresenterLogicDoiMatKhau(this);
        btnXacNhan.setOnClickListener(this);

    }

    @Override
    public void DoiMatKhauThanhCong() {
        Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void LayMatKhau(nguoidung nguoidung) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnXacNhan:
                if(KiemTraMatKhau()){
                    nguoidung nguoidung = new nguoidung();
                    nguoidung.setMaNguoiDung(Integer.parseInt(manguoidung));
                    nguoidung.setMatKhau(matkhaumoi);
                    presenterLogicDoiMatKhau.DoiMatKhau(nguoidung);

                    Intent iDangNhap = new Intent(this,DangNhapActivity.class);
                    startActivity(iDangNhap);
                }

        }
    }

    public boolean KiemTraMatKhau(){
        boolean kiemtra = true;
        matkhaumoi = edMatKhauMoi.getText().toString();
        xacnhanmatkhau = edXacNhanMatKhau.getText().toString();

        if(!matkhaumoi.equals(xacnhanmatkhau)){
            kiemtra = false;
            input_edXacNhanMatKhau.setError("Mật khẩu mới chưa trùng khớp");
        }
        else {
            input_edXacNhanMatKhau.setError(null);
            if(matkhaumoi.equals(matkhauhientai)){
                Toast.makeText(this, "Bạn đang sử dụng mật khẩu này!", Toast.LENGTH_SHORT).show();
                kiemtra = false;
            }
        }



        return kiemtra;
    }
}
