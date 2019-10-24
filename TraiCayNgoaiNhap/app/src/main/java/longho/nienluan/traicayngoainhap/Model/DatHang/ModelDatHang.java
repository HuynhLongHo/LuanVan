package longho.nienluan.traicayngoainhap.Model.DatHang;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelDatHang {
    public boolean ThemDonDatHang(DonDatHang donDatHang){
        String duongdan = TrangChuActivity.SERVER_NAME;
        boolean kiemtra = false;
        List<HashMap<String,String>> attrs = new ArrayList<>();

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","ThemDonDatHang");

        List<ChiTietDDH> chiTietDDHList = donDatHang.getChiTietDDHList();

        String chuoijson = "{\"DANHSACHSANPHAM\" :[ ";
        for (int i=0; i<chiTietDDHList.size();i++){
            chuoijson += "{";
            chuoijson += "\"MaTraiCay\" : " + chiTietDDHList.get(i).getMaTraiCay() + ",";
            chuoijson += "\"GiaBanHD\" : " + chiTietDDHList.get(i).getGiaBanHD() + ",";
            chuoijson += "\"SoLuongDat\" : " + chiTietDDHList.get(i).getSoLuongDat();

            if(i==chiTietDDHList.size() -1 ){
                chuoijson += "}";
            }else{
                chuoijson += "},";
            }

        }

        chuoijson += "]}";

        HashMap<String,String> hsDanhSachSanPham = new HashMap<>();
        hsDanhSachSanPham.put("danhsachsanpham",chuoijson);

        HashMap<String,String> hsMaNguoiDung = new HashMap<>();
        hsMaNguoiDung.put("manguoidung", String.valueOf(donDatHang.getMaNguoiDung()));

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan",donDatHang.getTenNguoiDatHang());

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt",String.valueOf(donDatHang.getSoDienThoaiDatHang()));

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", donDatHang.getDiaChiDatHang());

        HashMap<String,String> hsMoTa = new HashMap<>();
        hsMoTa.put("mota", donDatHang.getMoTa());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan",String.valueOf(donDatHang.getChuyenKhoan()));

        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsMaNguoiDung);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
        attrs.add(hsMoTa);
        attrs.add(hsChuyenKhoan);

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
}
