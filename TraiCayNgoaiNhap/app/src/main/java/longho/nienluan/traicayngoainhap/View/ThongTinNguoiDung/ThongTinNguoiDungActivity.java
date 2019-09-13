package longho.nienluan.traicayngoainhap.View.ThongTinNguoiDung;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ThongTinNguoiDung.ModelThongTinNguoiDung;
import longho.nienluan.traicayngoainhap.Presenter.ThongTinNguoiDung.PresenterLogicThongTinNguoiDung;
import longho.nienluan.traicayngoainhap.R;

public class ThongTinNguoiDungActivity extends AppCompatActivity implements ViewThongTinNguoiDung{

    RadioGroup rdg_GioiTinh;
    RadioButton rdoNam, rdoNu;
    ModelDangNhap modelDangNhap;
    EditText edHoTen, edEmail, edDiaChi, edSoDienThoai, edMatKhau, edCauHoi, edCauTraLoi;
    int MaNguoiDung;
    TextInputLayout input_edHoTen, input_edEmail, input_edDiaChi, input_edSoDienThoai, input_edMatKhau, input_edCauHoi, input_edCauTraLoi;
    PresenterLogicThongTinNguoiDung presenterLogicThongTinNguoiDung;
    boolean trangthai;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_thongtinnguoidung);

        edHoTen = findViewById(R.id.edHoTen);
        edEmail = findViewById(R.id.edEmail);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDienThoai = findViewById(R.id.edSoDienThoai);
        edMatKhau = findViewById(R.id.edMatKhau);
        edCauHoi = findViewById(R.id.edCauHoi);
        edCauTraLoi = findViewById(R.id.edCauTraLoi);

        input_edHoTen = findViewById(R.id.input_edHoTen);
        input_edEmail = findViewById(R.id.input_edEmail);
        input_edDiaChi = findViewById(R.id.input_edDiaChi);
        input_edSoDienThoai = findViewById(R.id.input_edSoDienThoai);
        input_edMatKhau = findViewById(R.id.input_edMatKhau);
        input_edCauHoi = findViewById(R.id.input_edCauHoi);
        input_edCauTraLoi = findViewById(R.id.input_edCauTraLoi);

        rdg_GioiTinh = findViewById(R.id.rdg_GioiTinh);
        rdoNam = findViewById(R.id.rdoNam);
        rdoNu = findViewById(R.id.rdoNu);

        modelDangNhap = new ModelDangNhap();
        MaNguoiDung = Integer.parseInt(modelDangNhap.LayMaNguoiDung(this));
        presenterLogicThongTinNguoiDung = new PresenterLogicThongTinNguoiDung(this);
        presenterLogicThongTinNguoiDung.LayThongTinNguoiDung(MaNguoiDung);
    }

    @Override
    public void HienThiThongTinNguoiDung(nguoidung nguoidung) {
        trangthai = false;
        SetEnable(trangthai);

        edHoTen.setText(nguoidung.getTenNguoiDung());
        edEmail.setText(nguoidung.getEmailND());
        edDiaChi.setText(nguoidung.getDiaChiND());
        edSoDienThoai.setText(nguoidung.getSoDienThoaiND());
        edMatKhau.setText(nguoidung.getMatKhau());
        edCauHoi.setText(nguoidung.getCauHoi());
        edCauTraLoi.setText(nguoidung.getCauTraLoi());
        if(nguoidung.getGioiTinh()==1){
            rdoNam.isChecked();
        }
        else{
            rdoNu.isChecked();
        }
    }

    public void SetEnable(boolean trangthai){
        input_edHoTen.setEnabled(trangthai);
        input_edEmail.setEnabled(trangthai);
        input_edDiaChi.setEnabled(trangthai);
        input_edSoDienThoai.setEnabled(trangthai);
        input_edMatKhau.setEnabled(trangthai);
        input_edCauHoi.setEnabled(trangthai);
        input_edCauTraLoi.setEnabled(trangthai);
        rdg_GioiTinh.setEnabled(trangthai);
        rdoNam.setEnabled(trangthai);
        rdoNu.setEnabled(trangthai);
    }
}
