package longho.nienluan.traicayngoainhap.Model.DanhGia;

import android.util.Log;

import org.json.JSONArray;
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
    public List<DanhGia> LayDanhSachDanhGiaCuaTraiCay(int matraicay, int limit){
        List<DanhGia> danhGias = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachDanhGiaTheoMaTraiCay");

        HashMap<String,String> hsMaLoai = new HashMap<>();
        hsMaLoai.put("masp",String.valueOf(matraicay));

        HashMap<String,String> hsLimit = new HashMap<>();
        hsLimit.put("limit",String.valueOf(limit));

        attrs.add(hsHam);
        attrs.add(hsMaLoai);
        attrs.add(hsLimit);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachSanPham = jsonObject.getJSONArray("DanhSachDanhGia");
            int dem = jsonArrayDanhSachSanPham.length();

            for (int i = 0; i<dem; i++){
                DanhGia danhGia = new DanhGia();
                JSONObject object = jsonArrayDanhSachSanPham.getJSONObject(i);

                danhGia.setTenThietBi(object.getString("TenThietBi"));
                danhGia.setNoiDungDG(object.getString("NoiDungDG"));
                danhGia.setSoSaoDG(object.getInt("SoSaoDG"));
                danhGia.setMaTraiCay(object.getInt("MaTraiCay"));
                danhGia.setMaDG(object.getString("MaDG"));
                danhGia.setNgayDG(object.getString("NgayDG"));
                danhGia.setMaNguoiDung(object.getInt("MaNguoiDung"));
                danhGia.setTenNguoiDung(object.getString("TenNguoiDung"));

                danhGias.add(danhGia);


            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return danhGias;
    }
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

        HashMap<String,String> hsMaNguoiDung = new HashMap<>();
        hsMaNguoiDung.put("manguoidung", String.valueOf(danhGia.getMaNguoiDung()));

        HashMap<String,String> hsNoiDung = new HashMap<>();
        hsNoiDung.put("noidung",danhGia.getNoiDungDG());

        HashMap<String,String> hsSoSao = new HashMap<>();
        hsSoSao.put("sosao", String.valueOf(danhGia.getSoSaoDG()));

        HashMap<String,String> hsTenThietBi = new HashMap<>();
        hsTenThietBi.put("tenthietbi", String.valueOf(danhGia.getTenThietBi()));

        attrs.add(hsHam);
        attrs.add(hsMADG);
        attrs.add(hsMaSP);
        attrs.add(hsMaNguoiDung);
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
