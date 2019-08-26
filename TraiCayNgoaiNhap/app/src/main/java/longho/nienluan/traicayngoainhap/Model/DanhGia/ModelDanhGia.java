package longho.nienluan.traicayngoainhap.Model.DanhGia;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelDanhGia {
    public boolean ThemDanhGia(DanhGia danhGia){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDanhGia");

        HashMap<String,String> hsMADG = new HashMap<>();
        hsMADG.put("madg",danhGia.getMaDG());

        HashMap<String,String> hsMaSP = new HashMap<>();
        hsMaSP.put("masp",String.valueOf(danhGia.getMaTraiCay()));

        HashMap<String,String> hsTieuDe = new HashMap<>();
        hsTieuDe.put("tieude",danhGia.getTieuDe());

        HashMap<String,String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNoiDung());

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSoSaoDG()));

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi", String.valueOf(danhGia.getTenThietBi()));

        attrs.add(hsHam);
        attrs.add(hsMADG);
        attrs.add(hsMaSP);
        attrs.add(hsTieuDe);
        attrs.add(hsNoiDung);
        attrs.add(hsSoSao);
        attrs.add(hsTenThietBi);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        downloadJSON.execute();

        try {
            String dulieuJSON = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJSON);
            String ketqua = jsonObject.getString("ketqua");
            Log.d("kiemtra",ketqua);
            if(ketqua.equals("true")){
                kiemtra = true;
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
