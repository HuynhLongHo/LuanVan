package longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class FragmentDangNhap extends Fragment implements View.OnClickListener {
    Button btn_DangNhapFacebook, btn_DangNhap;
    ModelDangNhap modelDangNhap;
    EditText edEmailDangNhap,edMatKhau;
    CallbackManager callbackManager;
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

        btn_DangNhapFacebook.setOnClickListener(this);
        btn_DangNhap.setOnClickListener(this);

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
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }
}
