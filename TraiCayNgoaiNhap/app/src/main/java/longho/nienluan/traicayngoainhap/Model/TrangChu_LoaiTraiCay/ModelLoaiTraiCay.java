package longho.nienluan.traicayngoainhap.Model.TrangChu_LoaiTraiCay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DSloaitraicay;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelLoaiTraiCay {

    public List<DSloaitraicay> LayDanhSachLoaiTraiCay(){
        List<DSloaitraicay> DSLoaiTraiCayList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachLoaiTraiCay");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachLoaiTraiCay = jsonObject.getJSONArray("loaitraicay");
            int dem = jsonArrayDanhSachLoaiTraiCay.length();

            for (int i = 0; i<dem; i++){
                DSloaitraicay dSloaitraicay = new DSloaitraicay();
                JSONObject object = jsonArrayDanhSachLoaiTraiCay.getJSONObject(i);

                dSloaitraicay.setMaLTC(object.getInt("MaLTC"));
                dSloaitraicay.setTenLTC(object.getString("TenLTC"));
                dSloaitraicay.setHinhLTC(object.getString("HinhLTC"));

                DSLoaiTraiCayList.add(dSloaitraicay);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return DSLoaiTraiCayList;
    }
}
