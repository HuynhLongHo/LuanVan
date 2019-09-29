package longho.nienluan.traicayngoainhap.Model.ChiTietTraiCay;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelChiTietTraiCay {
    public traicay LayChiTietTraiCayTheoMa(int matraicay){
        traicay traicay = new traicay();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayChiTietTraiCayTheoMa");
        HashMap<String,String> hsMaTC = new HashMap<>();
        hsMaTC.put("matraicay", String.valueOf(matraicay));

        attrs.add(hsHam);
        attrs.add(hsMaTC);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayTraiCay = jsonObject.getJSONArray("traicay");
            int dem = jsonArrayTraiCay.length();

            for (int i = 0; i<dem; i++){
                JSONObject object = jsonArrayTraiCay.getJSONObject(i);

                traicay.setMaTraiCay(object.getInt("MaTraiCay"));
                traicay.setMaLTC(object.getInt("MaLTC"));
                traicay.setMaNCC(object.getInt("MaNCC"));
                traicay.setMaQG(object.getInt("MaQG"));
                traicay.setTenTraiCay(object.getString("TenTraiCay"));
                traicay.setGiaBan(object.getInt("GiaBan"));
                traicay.setGiaKM(object.getInt("GiaKM"));
                traicay.setHinhTraiCay(object.getString("HinhTraiCay"));
                traicay.setHinhChiTiet(object.getString("HinhChiTiet"));
                traicay.setMieuTaTC(object.getString("MieuTaTC"));
                traicay.setLuotMua(object.getInt("LuotMua"));
                traicay.setThanhPhanDinhDuong(object.getString("ThanhPhanDinhDuong"));
                traicay.setMoiTruongTrong(object.getString("MoiTruongTrong"));
                traicay.setSoLuongTon(object.getInt("SoLuongTon"));
                traicay.setTenNCC(object.getString("TenNCC"));
                traicay.setDiaChiNCC(object.getString("DiaChiNCC"));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return traicay;
    }

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
                danhGia.setTieuDe(object.getString("TieuDe"));

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
}
