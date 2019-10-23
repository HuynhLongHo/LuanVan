package longho.nienluan.traicayngoainhap.View.ThongTinNguoiDung;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Presenter.DangNhap.PresenterLogicDangNhap;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment.ViewDangNhap;
import longho.nienluan.traicayngoainhap.View.DoiMatKhau.QuenMatKhauActivity;

public class MaOTPActivity extends AppCompatActivity implements ViewDangNhap,View.OnClickListener {

    TextView txtOTP;
    Button btnXacNhan;
    String otp,strMatKhau,email,strMaNguoiDung;
    PresenterLogicDangNhap presenterLogicDangNhap;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_otp);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        otp = String.valueOf(getIntent().getIntExtra("otp",0));
        email = getIntent().getStringExtra("email");
        txtOTP = findViewById(R.id.txtOTP);
        btnXacNhan = findViewById(R.id.btnXacNhan);

        presenterLogicDangNhap = new PresenterLogicDangNhap(this);
        presenterLogicDangNhap.LayThongTinNguoiDungBangEmail(email);

        btnXacNhan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnXacNhan:
                String strOTP = String.valueOf(txtOTP.getText());
                if(otp.equals(strOTP)){
                    Intent intent = new Intent(this,QuenMatKhauActivity.class);
                    intent.putExtra("MaNguoiDung",strMaNguoiDung);
                    intent.putExtra("MatKhau",strMatKhau);
                    startActivity(intent);
                }else {
                    Toast.makeText(this, "Mã OTP vừa nhập không đúng!", Toast.LENGTH_SHORT).show();
                }
        }

    }

    @Override
    public void LayThongTinNguoiDungBangEmail(nguoidung nguoidung) {
        strMatKhau = nguoidung.getMatKhau();
        strMaNguoiDung = String.valueOf(nguoidung.getMaNguoiDung());
    }
}
