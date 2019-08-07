package longho.nienluan.traicayngoainhap.Model.TrangChu_QuocGia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.QuocGia;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelQuocGia {

    public List<QuocGia> LayDanhSachQuocGia(){
        List<QuocGia> quocGiaList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachQuocGia");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayQuocGia = jsonObject.getJSONArray("quocgia");
            int dem = jsonArrayQuocGia.length();

            for (int i = 0; i<dem; i++){
                QuocGia quocGia = new QuocGia();
                JSONObject object = jsonArrayQuocGia.getJSONObject(i);

                quocGia.setMaQG(object.getInt("MaQG"));
                quocGia.setTenQG(object.getString("TenQG"));
                quocGia.setQuocKiQG(object.getString("QuocKiQG"));

                quocGiaList.add(quocGia);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return quocGiaList;
    }
}
