package longho.nienluan.traicayngoainhap.Model.TrangChu.XuLyJSONMenu;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.LoaiTraiCay;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.loaitraicay_traicay;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class XuLyJSONMenu {

    public List<LoaiTraiCay> ParseJSONMenu(String dulieujson){
        List<LoaiTraiCay> loaitraicayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray LoaiTraiCay = jsonObject.getJSONArray("loaitraicay");// những giá trị trong ngoặc phải giống dữ liệu
            int count = LoaiTraiCay.length();
            for(int i=0; i<=count; i++){
                JSONObject value = LoaiTraiCay.getJSONObject(i);
                LoaiTraiCay dataLoaiTraiCay = new LoaiTraiCay();
                dataLoaiTraiCay.setMaLTC(Integer.parseInt(value.getString("MaLTC")));
                dataLoaiTraiCay.setTenLTC(value.getString("TenLTC"));
                dataLoaiTraiCay.setMaTraiCay(Integer.parseInt(value.getString("MaTraiCay")));
                dataLoaiTraiCay.setTenTraiCay(value.getString("TenTraiCay"));
                dataLoaiTraiCay.setMaDV(Integer.parseInt(value.getString("MaDV")));

                loaitraicayList.add(dataLoaiTraiCay);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaitraicayList;
    }

    public List<LoaiTraiCay> ParseJSONMenuTheoMaTraiCay(String dulieujson){
        List<LoaiTraiCay> loaitraicayList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(dulieujson);
            JSONArray loaitraicay= jsonObject.getJSONArray("loaitraicay");// những giá trị trong ngoặc phải giống dữ liệu
            int count = loaitraicay.length();
            for(int i=0; i<=count; i++){
                JSONObject value = loaitraicay.getJSONObject(i);
                LoaiTraiCay dataLoaiTraiCay = new LoaiTraiCay();
                dataLoaiTraiCay.setMaLTC(Integer.parseInt(value.getString("MaLTC")));
                dataLoaiTraiCay.setTenLTC(value.getString("TenLTC"));
                dataLoaiTraiCay.setMaTraiCay(Integer.parseInt(value.getString("MaTraiCay")));
                dataLoaiTraiCay.setTenTraiCay(value.getString("TenTraiCay"));
                dataLoaiTraiCay.setMaDV(Integer.parseInt(value.getString("MaDV")));

                loaitraicayList.add(dataLoaiTraiCay);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return loaitraicayList;
    }

    public List<LoaiTraiCay> LayTraiCayTheoMaLoai(int MaLTC){
        List<LoaiTraiCay> loaitraicayTraicays = new ArrayList<>();
        List<HashMap<String, String>> atts = new ArrayList<>();
        String dataJSON;

        String duongdan = TrangChuActivity.SERVER_NAME;
        //String duongdan = "http://192.168.43.93:80/NienLuan_LongHo/loaitraicay_traicay.php";

        HashMap<String, String> hmHam = new HashMap<>();
        hmHam.put("ham","LayDanhSachMenu");
        HashMap<String, String> hmMaLTC = new HashMap<>();
        hmMaLTC.put("MaLTC",String.valueOf(MaLTC));

        atts.add(hmHam);
        atts.add(hmMaLTC);
        DownloadJSON downloadJSON = new DownloadJSON(duongdan,atts);
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaitraicayTraicays = xuLyJSONMenu.ParseJSONMenuTheoMaTraiCay(dataJSON);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return loaitraicayTraicays;
    }
}
