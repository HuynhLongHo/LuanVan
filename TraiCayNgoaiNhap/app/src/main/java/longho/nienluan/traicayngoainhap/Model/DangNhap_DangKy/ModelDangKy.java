package longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelDangKy {

    public Boolean DangKy(nguoidung nguoiDung){
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> atts = new ArrayList<>();

        boolean kiemtra = false;

        HashMap<String, String> hmHam = new HashMap<>();
        hmHam.put("ham","DangKy");
        HashMap<String, String> hmTenNguoiDung = new HashMap<>();
        hmTenNguoiDung.put("TenNguoiDung",nguoiDung.getTenNguoiDung());
        HashMap<String, String> hmDiaChiND = new HashMap<>();
        hmDiaChiND.put("DiaChiND",nguoiDung.getDiaChiND());
        HashMap<String, String> hmSoDienThoaiND = new HashMap<>();
        hmSoDienThoaiND.put("SoDienThoaiND",nguoiDung.getSoDienThoaiND());
        HashMap<String, String> hmEmailND = new HashMap<>();
        hmEmailND.put("EmailND",nguoiDung.getEmailND());
        HashMap<String, String> hmGioiTinh = new HashMap<>();
        hmGioiTinh.put("GioiTinh",String.valueOf(nguoiDung.getGioiTinh()));
        HashMap<String, String> hmMatKhau = new HashMap<>();
        hmMatKhau.put("MatKhau",nguoiDung.getMatKhau());
        HashMap<String, String> hmCauHoi = new HashMap<>();
        hmCauHoi.put("CauHoi",nguoiDung.getCauHoi());
        HashMap<String, String> hmCauTraLoi = new HashMap<>();
        hmCauTraLoi.put("CauTraLoi",nguoiDung.getCauTraLoi());
        HashMap<String, String> hmMaQuyen = new HashMap<>();
        hmMaQuyen.put("MaQuyen","0");

        atts.add(hmHam);
        atts.add(hmTenNguoiDung);
        atts.add(hmDiaChiND);
        atts.add(hmSoDienThoaiND);
        atts.add(hmEmailND);
        atts.add(hmGioiTinh);
        atts.add(hmMatKhau);
        atts.add(hmCauHoi);
        atts.add(hmCauTraLoi);
        atts.add(hmMaQuyen);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,atts);
        downloadJSON.execute();

        try {
            String dulieuJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true")){
                kiemtra = true;
            }else
                kiemtra = false;
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
