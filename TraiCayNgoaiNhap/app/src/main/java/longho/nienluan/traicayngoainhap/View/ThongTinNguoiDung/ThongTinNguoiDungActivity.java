package longho.nienluan.traicayngoainhap.View.ThongTinNguoiDung;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ThongTinNguoiDung.ModelThongTinNguoiDung;
import longho.nienluan.traicayngoainhap.Presenter.ThongTinNguoiDung.PresenterLogicThongTinNguoiDung;
import longho.nienluan.traicayngoainhap.R;

public class ThongTinNguoiDungActivity extends AppCompatActivity implements ViewThongTinNguoiDung,View.OnClickListener {

    String manguoidung,hoten,email,sodienthoai,diachi,matkhau,cauhoi,cautraloi;
    Button btnCapNhat, btnLuu, btnKhongLuu;
    RadioGroup rdg_GioiTinh;
    RadioButton rdoNam, rdoNu;
    ModelDangNhap modelDangNhap;
    EditText edHoTen, edEmail, edDiaChi, edSoDienThoai, edMatKhau;
    int MaNguoiDung;
    TextInputLayout input_edHoTen, input_edEmail, input_edDiaChi, input_edSoDienThoai, input_edMatKhau;
    PresenterLogicThongTinNguoiDung presenterLogicThongTinNguoiDung;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_thongtinnguoidung);

        edHoTen = findViewById(R.id.edHoTen);
        edEmail = findViewById(R.id.edEmail);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDienThoai = findViewById(R.id.edSoDienThoai);
        edMatKhau = findViewById(R.id.edMatKhau);
//        edCauHoi = findViewById(R.id.edCauHoi);
//        edCauTraLoi = findViewById(R.id.edCauTraLoi);

        input_edHoTen = findViewById(R.id.input_edHoTen);
        input_edEmail = findViewById(R.id.input_edEmail);
        input_edDiaChi = findViewById(R.id.input_edDiaChi);
        input_edSoDienThoai = findViewById(R.id.input_edSoDienThoai);
        input_edMatKhau = findViewById(R.id.input_edMatKhau);
//        input_edCauHoi = findViewById(R.id.input_edCauHoi);
//        input_edCauTraLoi = findViewById(R.id.input_edCauTraLoi);

        rdg_GioiTinh = findViewById(R.id.rdg_GioiTinh);
        rdoNam = findViewById(R.id.rdoNam);
        rdoNu = findViewById(R.id.rdoNu);

        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnLuu = findViewById(R.id.btnLuu);
        btnKhongLuu = findViewById(R.id.btnKhongLuu);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        modelDangNhap = new ModelDangNhap();
        MaNguoiDung = Integer.parseInt(modelDangNhap.LayMaNguoiDung(this));
        presenterLogicThongTinNguoiDung = new PresenterLogicThongTinNguoiDung(this);
        presenterLogicThongTinNguoiDung.LayThongTinNguoiDung(MaNguoiDung);

        btnCapNhat.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnKhongLuu.setOnClickListener(this);
    }

    @Override
    public void HienThiThongTinNguoiDung(nguoidung nguoidung) {
        SetEnable(false);

        edHoTen.setText(nguoidung.getTenNguoiDung());
        edEmail.setText(nguoidung.getEmailND());
        edDiaChi.setText(nguoidung.getDiaChiND());
        edSoDienThoai.setText(nguoidung.getSoDienThoaiND());
        edMatKhau.setText(nguoidung.getMatKhau());
//        edCauHoi.setText(nguoidung.getCauHoi());
//        edCauTraLoi.setText(nguoidung.getCauTraLoi());
        if(nguoidung.getGioiTinh()==1){
            rdoNam.setChecked(true);
        }
        else{
            rdoNu.setChecked(true);
        }
    }

    @Override
    public void CapNhatThanhCong() {
        Toast.makeText(this, "Đã cập nhật thông tin", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void CapNhatThatBai() {
        Toast.makeText(this, "Cập nhật thông tin thất bại", Toast.LENGTH_SHORT).show();
    }

    public void SetEnable(boolean trangthai){
        input_edHoTen.setEnabled(trangthai);
//        input_edEmail.setEnabled(trangthai);
        input_edDiaChi.setEnabled(trangthai);
        input_edSoDienThoai.setEnabled(trangthai);
        input_edMatKhau.setEnabled(trangthai);
//        input_edCauHoi.setEnabled(trangthai);
//        input_edCauTraLoi.setEnabled(trangthai);
        rdg_GioiTinh.setEnabled(trangthai);
        rdoNam.setEnabled(trangthai);
        rdoNu.setEnabled(trangthai);
        btnCapNhat.setEnabled(!trangthai);
        if(trangthai){
            btnLuu.setVisibility(View.VISIBLE);
            btnKhongLuu.setVisibility(View.VISIBLE);
        }
        else {
            btnLuu.setVisibility(View.GONE);
            btnKhongLuu.setVisibility(View.GONE);
        }

    }

    public boolean kiemtrathongtin(){
        boolean kiemtra = true;
        if(hoten.trim().length()==0){
            input_edHoTen.setError("Chưa nhập họ tên");
            edHoTen.requestFocus();
            kiemtra &= false;
        }else{
            input_edHoTen.setError(null);
        }
        if(email.trim().length()==0){
            input_edEmail.setError("Chưa nhập email");
            edHoTen.requestFocus();
            kiemtra &= false;
        }else{
            input_edEmail.setError(null);
        }
        if(diachi.trim().length()==0){
            input_edDiaChi.setError("Chưa nhập họ tên");
            edHoTen.requestFocus();
            kiemtra &= false;
        }else{
            input_edDiaChi.setError(null);
        }
        if(sodienthoai.trim().length()==0){
            input_edSoDienThoai.setError("Chưa nhập họ tên");
            edHoTen.requestFocus();
            kiemtra &= false;
        }else{
            input_edSoDienThoai.setError(null);
        }
        if(matkhau.trim().length()==0){
            input_edMatKhau.setError("Chưa nhập họ tên");
            edHoTen.requestFocus();
            kiemtra &= false;
        }else{
            input_edMatKhau.setError(null);
        }
//        if(cauhoi.trim().length()==0){
//            input_edCauHoi.setError("Chưa nhập họ tên");
//            edHoTen.requestFocus();
//            kiemtra &= false;
//        }else{
//            input_edCauHoi.setError(null);
//        }
//        if(cautraloi.trim().length()==0){
//            input_edCauTraLoi.setError("Chưa nhập họ tên");
//            edHoTen.requestFocus();
//            kiemtra &= false;
//        }else{
//            input_edCauTraLoi.setError(null);
//        }
        return kiemtra;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnCapNhat:
                SetEnable(true);
                break;
            case R.id.btnLuu:
                SetEnable(false);

                manguoidung = modelDangNhap.LayMaNguoiDung(this);
                hoten = edHoTen.getText().toString();
                email = edEmail.getText().toString();
                sodienthoai = edSoDienThoai.getText().toString();
                diachi = edDiaChi.getText().toString();
                matkhau = edMatKhau.getText().toString();
//                cauhoi = edCauHoi.getText().toString();
//                cautraloi = edCauTraLoi.getText().toString();
                int idGT = rdg_GioiTinh.getCheckedRadioButtonId();
                RadioButton rad = findViewById(idGT);
                String gioitinh = rad.getText().toString().trim();

                if(kiemtrathongtin()) {
                    int gt;
                    nguoidung nguoiDung = new nguoidung();
                    nguoiDung.setMaNguoiDung(MaNguoiDung);
                    nguoiDung.setTenNguoiDung(hoten);
                    nguoiDung.setEmailND(email);
                    nguoiDung.setSoDienThoaiND(sodienthoai);
                    nguoiDung.setDiaChiND(diachi);
                    nguoiDung.setMatKhau(matkhau);
                    nguoiDung.setCauHoi(cauhoi);
                    nguoiDung.setCauTraLoi(cautraloi);
                    if(gioitinh=="Nam")
                        gt=1;
                    else
                        gt=0;
                    nguoiDung.setGioiTinh(gt);

                    presenterLogicThongTinNguoiDung.ThayDoiThongTinNguoiDung(nguoiDung);
                    modelDangNhap.CapNhatCachedDangNhap(this,hoten, String.valueOf(MaNguoiDung));
                }
                else {
                    Toast.makeText(this, "Lỗi nhập thông tin", Toast.LENGTH_SHORT).show();
                    SetEnable(true);
                }
                break;
            case R.id.btnKhongLuu:
                presenterLogicThongTinNguoiDung.LayThongTinNguoiDung(MaNguoiDung);

        }
    }
}
