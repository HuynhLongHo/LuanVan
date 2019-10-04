package longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
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
    public String LayMaNguoiDung(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String MaNguoiDung = cachedDangNhap.getString("MaNguoiDung","");

        return MaNguoiDung;
    }
    public void CapNhatCachedDangNhap(Context context,String TenNguoiDung, String MaNguoiDung){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("TenNguoiDung",TenNguoiDung);
        editor.putString("MaNguoiDung",MaNguoiDung);

        editor.commit();
    }
    public String LayTenQuyenTruyCap(Context context){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        String TenQuyen = cachedDangNhap.getString("TenQuyen","");

        return TenQuyen;
    }
    public void CapNhatCachedTenQuyen(Context context, String TenQuyen){
        SharedPreferences cachedDangNhap = context.getSharedPreferences("dangnhap",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = cachedDangNhap.edit();
        editor.putString("TenQuyen",TenQuyen);

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
                String maND = jsonObject.getString("MaNguoiDung");
                String tenquyen = jsonObject.getString("TenQuyen");

                CapNhatCachedDangNhap(context,tennv,maND);
                CapNhatCachedTenQuyen(context,tenquyen);

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
    public nguoidung LayThongTinNguoiDung(String EmailND){
        nguoidung nguoidung = new nguoidung();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayThongTinNguoiDung");
        HashMap<String,String> hsMaND = new HashMap<>();
        hsMaND.put("EmailND", String.valueOf(EmailND));

        attrs.add(hsHam);
        attrs.add(hsMaND);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTraiCay = jsonObject.getJSONArray("nguoidung");
            int dem = jsonArrayTraiCay.length();

            for (int i = 0; i<dem; i++){
                JSONObject object = jsonArrayTraiCay.getJSONObject(i);
                nguoidung.setMaNguoiDung(object.getInt("MaNguoiDung"));
                nguoidung.setTenNguoiDung(object.getString("TenNguoiDung"));
                nguoidung.setDiaChiND(object.getString("DiaChiND"));
                nguoidung.setSoDienThoaiND(object.getString("SoDienThoaiND"));
                nguoidung.setEmailND(object.getString("EmailND"));
                nguoidung.setGioiTinh(object.getInt("GioiTinh"));
                nguoidung.setMatKhau(object.getString("MatKhau"));
                nguoidung.setCauHoi(object.getString("CauHoi"));
                nguoidung.setCauTraLoi(object.getString("CauTraLoi"));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return nguoidung;
    }
}
