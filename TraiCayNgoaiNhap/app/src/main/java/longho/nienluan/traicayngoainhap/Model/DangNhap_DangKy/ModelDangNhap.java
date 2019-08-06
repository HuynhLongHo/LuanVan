package longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelDangNhap {
    AccessToken accessToken;
    AccessTokenTracker accessTokenTracker;

    public AccessToken LayTokenFacebookHienTai() {

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
            }
        };

        accessToken = AccessToken.getCurrentAccessToken();

        return accessToken;

    }

    public String LayCachedDangNhap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String TenNguoiDung = cachedDangNhap.getString("TenNguoiDung","");

        return TenNguoiDung;
    }
    public void CapNhatCachedDangNhap(Context context,String TenNguoiDung){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("TenNguoiDung",TenNguoiDung);

        editor.commit();
    }
    public boolean KiemTraDangNhap(Context context, String EmailND, String MatKhau){
        boolean kiemtra = false;
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","KiemTraDangNhap");

        HashMap<String,String> hsTenDangNhap = new HashMap<>();
        hsTenDangNhap.put("EmailND",EmailND);

        HashMap<String,String> hsMatKhau = new HashMap<>();
        hsMatKhau.put("MatKhau",MatKhau);

        attrs.add(hsHam);
        attrs.add(hsTenDangNhap);
        attrs.add(hsMatKhau);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieu = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieu);
            String jsonKetQua = jsonObject.getString("ketqua");
            if(jsonKetQua.equals("true")){
                kiemtra = true;
                String tennv = jsonObject.getString("TenNguoiDung");

                CapNhatCachedDangNhap(context,tennv);

            }else{
                kiemtra = false;
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return kiemtra;
    }
}
