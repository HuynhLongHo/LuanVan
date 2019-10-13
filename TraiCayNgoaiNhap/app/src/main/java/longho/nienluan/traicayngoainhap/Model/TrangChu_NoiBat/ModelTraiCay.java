package longho.nienluan.traicayngoainhap.Model.TrangChu_NoiBat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelTraiCay {

    public List<traicay> LayDanhSachTraiCayKhuyenMai(){
        List<traicay> traicayList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachTraiCayKhuyenMai");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTraiCay = jsonObject.getJSONArray("traicay");
            int dem = jsonArrayTraiCay.length();

            for (int i = 0; i<dem; i++){
                traicay traicay = new traicay();
                JSONObject object = jsonArrayTraiCay.getJSONObject(i);

                traicay.setMaTraiCay(object.getInt("MaTraiCay"));
                traicay.setTenTraiCay(object.getString("TenTraiCay"));
                traicay.setHinhTraiCay(object.getString("HinhTraiCay"));
                traicay.setGiaBan(object.getInt("GiaBan"));
                traicay.setGiaKM(object.getInt("GiaKM"));
                traicay.setLuotMua(object.getInt("LuotMua"));
                traicayList.add(traicay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return traicayList;
    }

    public List<traicay> LayDanhSachTopTraiCayTheoLuotMua(){
        List<traicay> traicayList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachTopTraiCayTheoLuotMua");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTraiCay = jsonObject.getJSONArray("traicay");
            int dem = jsonArrayTraiCay.length();

            for (int i = 0; i<dem; i++){
                traicay traicay = new traicay();
                JSONObject object = jsonArrayTraiCay.getJSONObject(i);

                traicay.setMaTraiCay(object.getInt("MaTraiCay"));
                traicay.setTenTraiCay(object.getString("TenTraiCay"));
                traicay.setHinhTraiCay(object.getString("HinhTraiCay"));
                traicay.setGiaBan(object.getInt("GiaBan"));
                traicay.setGiaKM(object.getInt("GiaKM"));
                traicay.setLuotMua(object.getInt("LuotMua"));
                traicayList.add(traicay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return traicayList;
    }

    public List<traicay> LayDanhSachTraiCayGiaRe(){
        List<traicay> traicayList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachTraiCayGiaRe");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTraiCay = jsonObject.getJSONArray("traicay");
            int dem = jsonArrayTraiCay.length();

            for (int i = 0; i<dem; i++){
                traicay traicay = new traicay();
                JSONObject object = jsonArrayTraiCay.getJSONObject(i);

                traicay.setMaTraiCay(object.getInt("MaTraiCay"));
                traicay.setTenTraiCay(object.getString("TenTraiCay"));
                traicay.setHinhTraiCay(object.getString("HinhTraiCay"));
                traicay.setGiaBan(object.getInt("GiaBan"));
                traicay.setGiaKM(object.getInt("GiaKM"));
                traicay.setLuotMua(object.getInt("LuotMua"));
                traicayList.add(traicay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return traicayList;
    }

    public List<traicay> LayDanhSachTraiCayNgauNhien(){
        List<traicay> traicayList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachTraiCayNgauNhien");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTraiCay = jsonObject.getJSONArray("traicay");
            int dem = jsonArrayTraiCay.length();

            for (int i = 0; i<dem; i++){
                traicay traicay = new traicay();
                JSONObject object = jsonArrayTraiCay.getJSONObject(i);

                traicay.setMaTraiCay(object.getInt("MaTraiCay"));
                traicay.setTenTraiCay(object.getString("TenTraiCay"));
                traicay.setHinhTraiCay(object.getString("HinhTraiCay"));
                traicay.setGiaBan(object.getInt("GiaBan"));
                traicay.setGiaKM(object.getInt("GiaKM"));
                traicay.setLuotMua(object.getInt("LuotMua"));
                traicayList.add(traicay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return traicayList;
    }
}
