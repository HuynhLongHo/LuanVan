package longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import longho.nienluan.traicayngoainhap.EmailVerification.GenerateRandomNumber;
import longho.nienluan.traicayngoainhap.EmailVerification.SendMail;
import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Presenter.DangKy.PresenterLogicDangKy;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.ViewDangKy;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class FragmentDangKy extends Fragment implements ViewDangKy, View.OnClickListener, View.OnFocusChangeListener {
    PresenterLogicDangKy presenterLogicDangKy;
    Button btnDangKy, btnDangKyFacebook;
    EditText edtTenNguoiDung, edtEmail,edtSoDienThoai, edtDiaChi,edtMatKhau,edtNhapLaiMatKhau, edtCauHoi, edtCauTraLoi;
    RadioGroup rdgGioiTinh;
    TextInputLayout layout_TenNguoiDung, layout_Email, layout_SoDienThoai, layout_DiaChi, layout_MatKhau, layout_NhapLaiMatKhau, layout_CauHoi, layout_CauTraLoi;
    Boolean kiemtrathongtin = false;
    CallbackManager callbackManager;
    ModelDangNhap modelDangNhap;
    String emaildk,matkhaudk;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangky,container,false);

        btnDangKy = view.findViewById(R.id.btnDangKy);
        btnDangKyFacebook = view.findViewById(R.id.btnDangNhapFacebookDK);
        edtTenNguoiDung = view.findViewById(R.id.edHoTenDK);
        edtEmail = view.findViewById(R.id.edEmailDK);
        edtSoDienThoai = view.findViewById(R.id.edSoDienThoaiDK);
        edtDiaChi = view.findViewById(R.id.edDiaChiDK);
        edtMatKhau = view.findViewById(R.id.edMatKhauDK);
        edtNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhauDK);
//        edtCauHoi = view.findViewById(R.id.edCauHoi);
//        edtCauTraLoi = view.findViewById(R.id.edCauTraLoi);
        rdgGioiTinh = view.findViewById(R.id.rdg_GioiTinh);
        layout_TenNguoiDung = view.findViewById(R.id.input_edHoTenDK);
        layout_Email = view.findViewById(R.id.input_edEmailDK);
        layout_SoDienThoai= view.findViewById(R.id.input_edSoDienThoaiDK);
        layout_DiaChi = view.findViewById(R.id.input_edDiaChiDK);
        layout_MatKhau = view.findViewById(R.id.input_edMatKhauDK);
        layout_NhapLaiMatKhau = view.findViewById(R.id.input_edNhapLaiMatKhauDK);
        layout_CauHoi = view.findViewById(R.id.input_edCauHoi);
        layout_CauTraLoi= view.findViewById(R.id.input_edCauTraLoi);

        presenterLogicDangKy = new PresenterLogicDangKy(this);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>(){

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                        Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                        startActivity(iTrangChu);
                    }

                    @Override
                    public void onCancel() {
                        Log.d("kiemtra","Thoat");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Log.d("kiemtra","lỗi");
                    }
                });

        btnDangKy.setOnClickListener(this);
        btnDangKyFacebook.setOnClickListener(this);
        edtTenNguoiDung.setOnFocusChangeListener(this);
        edtEmail.setOnFocusChangeListener(this);
        edtSoDienThoai.setOnFocusChangeListener(this);
        edtDiaChi.setOnFocusChangeListener(this);
        edtMatKhau.setOnFocusChangeListener(this);
        edtNhapLaiMatKhau.setOnFocusChangeListener(this);
//        edtCauHoi.setOnFocusChangeListener(this);
//        edtCauTraLoi.setOnFocusChangeListener(this);

        return view;
    }

    @Override
    public void DangKyThanhCong() {
        Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
        modelDangNhap = new ModelDangNhap();
        boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),emaildk,matkhaudk);
        if(kiemtra){
            Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
            startActivity(iTrangChu);
        }
    }

    @Override
    public void DangKyThatBai() {
        Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();
        switch (id){
            case R.id.btnDangKy:
                btnDangKy();
                break;
        }
        switch (id){
            case R.id.btnDangNhapFacebookDK:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangKy.this, Arrays.asList("public_profile"));
                break;
        }

    }

    private void btnDangKy(){
        String hoten = edtTenNguoiDung.getText().toString();
        emaildk = edtEmail.getText().toString();
        String sodienthoai = edtSoDienThoai.getText().toString();
        String diachi = edtDiaChi.getText().toString();
        matkhaudk = edtMatKhau.getText().toString();
        String nhaplaimatkhau = edtNhapLaiMatKhau.getText().toString();
//        String cauhoi = edtCauHoi.getText().toString();
//        String cautraloi = edtCauTraLoi.getText().toString();
        int id = rdgGioiTinh.getCheckedRadioButtonId();
        RadioButton rad = getView().findViewById(id);
        String gioitinh = rad.getText().toString();

        if(kiemtrathongtin) {
            int gt;
            final nguoidung nguoiDung = new nguoidung();
            nguoiDung.setTenNguoiDung(hoten);
            nguoiDung.setEmailND(emaildk);
            nguoiDung.setSoDienThoaiND(sodienthoai);
            nguoiDung.setDiaChiND(diachi);
            nguoiDung.setMatKhau(matkhaudk);
//            nguoiDung.setCauHoi(cauhoi);
//            nguoiDung.setCauTraLoi(cautraloi);
            if(gioitinh.equals("Nam"))
                gt=1;
            else
                gt=0;
            nguoiDung.setGioiTinh(gt);
            nguoiDung.setMaQuyen(0);

            presenterLogicDangKy.ThucHienDangKy(nguoiDung);


//            GenerateRandomNumber generateRandomNumber = new GenerateRandomNumber();
//            final int OTP = generateRandomNumber.RandomNumber();
//
//            SendMail sendMail = new SendMail(getActivity(),email,"Xác nhận", "Mã xác nhận của bạn là: " + OTP);
//            sendMail.execute();
//            //Builder thứ 2
//            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
//            ad.setTitle("Nhâp mã OTP");
//            ad.setMessage("Vui lòng nhập mã vừa gửi đến email bạn!");
//            final EditText edtOTP = new EditText(getContext());
//            ad.setView(edtOTP);
//            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dlg, int which) {
//                    int otp = Integer.parseInt(edtOTP.getText().toString());
//                    if(OTP == otp){
//                        presenterLogicDangKy.ThucHienDangKy(nguoiDung);
//                    }else{
//                        Toast.makeText(getActivity(), "Mã OTP sai!", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//            ad.show();
        }else{
            Log.d("kiemtra","Dang ky that bai ");
        }

    }

    @Override
    public void onFocusChange(View view, boolean b) {

        int id = view.getId();
        switch (id){
            case R.id.edHoTenDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        layout_TenNguoiDung.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        layout_TenNguoiDung.setError(null);
                        kiemtrathongtin = true;
                    }
                }
                break;


            case R.id.edEmailDK:
                if(!b){

                    String chuoi = ((EditText)view).getText().toString();

                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        layout_Email.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{

                        Boolean kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches();
                        if(!kiemtraemail){
                            layout_Email.setError("Đây không phải là địa chỉ Email !");
                            kiemtrathongtin = false;
                        }else{
                            layout_Email.setError(null);
                            kiemtrathongtin = true;
                        }
                    }
                }
                break;

            case R.id.edDiaChiDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        layout_DiaChi.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        layout_DiaChi.setError(null);
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edSoDienThoaiDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        layout_SoDienThoai.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        layout_SoDienThoai.setError(null);
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    if(chuoi.trim().equals("") || chuoi.equals(null)){
                        layout_MatKhau.setError("Bạn chưa nhập mục này !");
                        kiemtrathongtin = false;
                    }else{
                        layout_MatKhau.setError(null);
                        kiemtrathongtin = true;
                    }
                }
                break;

            case R.id.edNhapLaiMatKhauDK:
                if(!b){
                    String chuoi = ((EditText)view).getText().toString();
                    String matkhau = edtMatKhau.getText().toString();
                    if(!chuoi.equals(matkhau)){
                        layout_NhapLaiMatKhau.setError("Mật khẩu không trùng khớp !");
                        kiemtrathongtin = false;
                    }else{
                        layout_NhapLaiMatKhau.setError(null);
                        kiemtrathongtin = true;
                    }
                }
                ;break;

//            case R.id.edCauHoi:
//                if(!b){
//                    String chuoi = ((EditText)view).getText().toString();
//                    if(chuoi.trim().equals("") || chuoi.equals(null)){
//                        layout_CauHoi.setError("Bạn chưa nhập mục này !");
//                        kiemtrathongtin = false;
//                    }else{
//                        layout_CauHoi.setError(null);
//                        kiemtrathongtin = true;
//                    }
//                }
//                break;
//            case R.id.edCauTraLoi:
//                if(!b){
//                    String chuoi = ((EditText)view).getText().toString();
//                    if(chuoi.trim().equals("") || chuoi.equals(null)){
//                        layout_CauTraLoi.setError("Bạn chưa nhập mục này !");
//                        kiemtrathongtin = false;
//                    }else{
//                        layout_CauTraLoi.setError(null);
//                        kiemtrathongtin = true;
//                    }
//                }
//                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }
}
