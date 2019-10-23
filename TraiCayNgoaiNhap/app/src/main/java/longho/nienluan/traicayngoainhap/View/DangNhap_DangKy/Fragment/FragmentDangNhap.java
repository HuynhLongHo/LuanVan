package longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
import longho.nienluan.traicayngoainhap.Presenter.DangNhap.PresenterLogicDangNhap;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DoiMatKhau.QuenMatKhauActivity;
import longho.nienluan.traicayngoainhap.View.ThongTinNguoiDung.MaOTPActivity;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class FragmentDangNhap extends Fragment implements ViewDangNhap,View.OnClickListener {
    Button btn_DangNhapFacebook, btn_DangNhap;
    ModelDangNhap modelDangNhap;
    EditText edEmailDangNhap,edMatKhau;
    CallbackManager callbackManager;
    TextView txtQuenMatKhau;
    String strMaNguoiDung, strCauHoi, strCauTraLoi, strMatKhau, strEmail;
    PresenterLogicDangNhap presenterLogicDangNhap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_dangnhap,container, false);

        modelDangNhap = new ModelDangNhap();
        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>(){

                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("kiemtra","8");
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



        btn_DangNhapFacebook = view.findViewById(R.id.btn_dangnhapfacebook);
        btn_DangNhap = view.findViewById(R.id.btn_dangnhap);
        edEmailDangNhap = view.findViewById(R.id.edt_emaildangnhap);
        edMatKhau = view.findViewById(R.id.edt_matkhaudangnhap);
        txtQuenMatKhau = view.findViewById(R.id.txtQuenMatKhau);

        btn_DangNhapFacebook.setOnClickListener(this);
        btn_DangNhap.setOnClickListener(this);
        txtQuenMatKhau.setOnClickListener(this);

        presenterLogicDangNhap = new PresenterLogicDangNhap(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_dangnhapfacebook:
                LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
                break;
            case R.id.btn_dangnhap:
                String tendangnhap = edEmailDangNhap.getText().toString();
                String matkhau = edMatKhau.getText().toString();
                boolean kiemtra = modelDangNhap.KiemTraDangNhap(getActivity(),tendangnhap,matkhau);
                if(kiemtra){
                    Intent iTrangChu = new Intent(getActivity(), TrangChuActivity.class);
                    startActivity(iTrangChu);
                }else{
                    Toast.makeText(getActivity(),"Tên đăng nhập và mật khẩu không đúng !",Toast.LENGTH_SHORT).show();
                }
                ;break;
            case R.id.txtQuenMatKhau:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Quên mật khẩu");
                builder.setMessage("Nhập email cần lấy lại mật khẩu:");
                final EditText Email = new EditText(getContext());
                Email.setPadding(30,0,30,20);
                builder.setView(Email);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dlg, int which) {
                        String email = Email.getText().toString();
                        presenterLogicDangNhap.LayThongTinNguoiDungBangEmail(email);

                        if(email.equals(strEmail)){
                            GenerateRandomNumber generateRandomNumber = new GenerateRandomNumber();
                            final int OTP = generateRandomNumber.RandomNumber();

                            SendMail sendMail = new SendMail(getActivity(),strEmail,"Xác nhận", "Mã xác nhận của bạn là: " + OTP);
                            sendMail.execute();
                            //Builder thứ 2
//                            AlertDialog.Builder ad = new AlertDialog.Builder(getContext());
//                            ad.setTitle("Nhâp mã OTP");
//                            ad.setMessage("Vui lòng nhập mã vừa gửi đến email bạn!");
//                            final EditText edtOTP = new EditText(getContext());
//                            ad.setView(edtOTP);
//                            ad.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dlg, int which) {
//                                    int otp = Integer.parseInt(edtOTP.getText().toString());
//                                    if(OTP == otp){
//                                        Intent intent = new Intent(getActivity(),QuenMatKhauActivity.class);
//                                        intent.putExtra("MaNguoiDung",strMaNguoiDung);
//                                        intent.putExtra("MatKhau",strMatKhau);
//                                        startActivity(intent);
//                                    }else{
//                                        Toast.makeText(getActivity(), "Mã OTP sai!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//
//
//                            ad.show();
                            Intent intent = new Intent(getActivity(),MaOTPActivity.class);
                            intent.putExtra("otp",OTP);
                            intent.putExtra("email",email);
                            startActivity(intent);
                        }else{
                            Toast.makeText(getActivity(), "Email bạn vừa nhập chưa được đăng kí!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.show();
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void LayThongTinNguoiDungBangEmail(nguoidung nguoidung) {
        strMaNguoiDung = String.valueOf(nguoidung.getMaNguoiDung());
        strCauHoi = nguoidung.getCauHoi();
        strCauTraLoi = nguoidung.getCauTraLoi();
        strMatKhau = nguoidung.getMatKhau();
        strEmail = nguoidung.getEmailND();
    }
}
