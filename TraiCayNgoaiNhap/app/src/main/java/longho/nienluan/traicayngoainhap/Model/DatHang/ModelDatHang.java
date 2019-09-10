package longho.nienluan.traicayngoainhap.Model.DatHang;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
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

        HashMap<String,String> hsTenNguoiNhan = new HashMap<>();
        hsTenNguoiNhan.put("tennguoinhan",donDatHang.getTenNguoiDatHang());

        HashMap<String,String> hsSoDT = new HashMap<>();
        hsSoDT.put("sodt",String.valueOf(donDatHang.getSoDienThoaiDatHang()));

        HashMap<String,String> hsDiaChi = new HashMap<>();
        hsDiaChi.put("diachi", donDatHang.getDiaChiDatHang());

        HashMap<String,String> hsChuyenKhoan = new HashMap<>();
        hsChuyenKhoan.put("chuyenkhoan",String.valueOf(donDatHang.getChuyenKhoan()));

        attrs.add(hsHam);
        attrs.add(hsDanhSachSanPham);
        attrs.add(hsTenNguoiNhan);
        attrs.add(hsSoDT);
        attrs.add(hsDiaChi);
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
}
