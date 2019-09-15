package longho.nienluan.traicayngoainhap.Model.ThongTinNguoiDung;

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

public class ModelThongTinNguoiDung {
    public nguoidung LayThongTinNguoiDung(int manguoidung){
        nguoidung nguoidung = new nguoidung();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayThongTinNguoiDung");
        HashMap<String,String> hsMaND = new HashMap<>();
        hsMaND.put("MaNguoiDung", String.valueOf(manguoidung));

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
    public Boolean ThayDoiThongTinNguoiDung(nguoidung nguoiDung){
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> atts = new ArrayList<>();

        boolean kiemtra = false;

        HashMap<String, String> hmHam = new HashMap<>();
        hmHam.put("ham","ThayDoiThongTinNguoiDung");
        HashMap<String, String> hmMaNguoiDung = new HashMap<>();
        hmMaNguoiDung.put("MaNguoiDung", String.valueOf(nguoiDung.getMaNguoiDung()));
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

        atts.add(hmHam);
        atts.add(hmMaNguoiDung);
        atts.add(hmTenNguoiDung);
        atts.add(hmDiaChiND);
        atts.add(hmSoDienThoaiND);
        atts.add(hmEmailND);
        atts.add(hmGioiTinh);
        atts.add(hmMatKhau);
        atts.add(hmCauHoi);
        atts.add(hmCauTraLoi);

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
