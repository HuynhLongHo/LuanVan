package longho.nienluan.traicayngoainhap.Model.TrangChu_Khuyenmai;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.KhuyenMai;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelKhuyenMai {
    public List<KhuyenMai> LayDanhSachDangKhuyenMai(){
        List<KhuyenMai> quocGiaList = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachDangKhuyenMai");

        attrs.add(hsHam);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayQuocGia = jsonObject.getJSONArray("khuyenmai");
            int dem = jsonArrayQuocGia.length();

            for (int i = 0; i<dem; i++){
                KhuyenMai khuyenMai = new KhuyenMai();
                JSONObject object = jsonArrayQuocGia.getJSONObject(i);

                khuyenMai.setMaKM(object.getInt("MaKM"));
                khuyenMai.setTenKM(object.getString("TenKM"));
                khuyenMai.setNgayBatDau(object.getString("NgayBatDau"));
                khuyenMai.setNgayKetThuc(object.getString("NgayKetThuc"));
                khuyenMai.setHinhKM(object.getString("HinhKM"));

                quocGiaList.add(khuyenMai);
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
