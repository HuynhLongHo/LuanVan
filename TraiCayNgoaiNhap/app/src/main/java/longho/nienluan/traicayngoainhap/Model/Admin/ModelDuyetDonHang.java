package longho.nienluan.traicayngoainhap.Model.Admin;

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
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelDuyetDonHang {
    public List<DonDatHang> AdminLayDanhSachDonDatHangChuaDuyet(){
        List<DonDatHang> donDatHangs = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","AdminLayDanhSachDonDatHangChuaDuyet");

        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachDonDatHang = jsonObject.getJSONArray("DanhSachDonDatHang");
            int dem = jsonArrayDanhSachDonDatHang.length();

            for (int i = 0; i<dem; i++){
                JSONObject object = jsonArrayDanhSachDonDatHang.getJSONObject(i);
                DonDatHang donDatHang = new DonDatHang();
                donDatHang.setMaDDH(object.getInt("MaDDH"));
                donDatHang.setMaNguoiDung(object.getInt("MaNguoiDung"));
                donDatHang.setTenNguoiDatHang(object.getString("TenNguoiDatHang"));
                donDatHang.setSoDienThoaiDatHang(object.getString("SoDienThoaiDatHang"));
                donDatHang.setDiaChiDatHang(object.getString("DiaChiDatHang"));
                donDatHang.setNgayDat(object.getString("NgayDat"));
                donDatHang.setNgayGiao(object.getString("NgayGiao"));
                donDatHang.setTrangThaiGiao(object.getString("TrangThaiGiao"));
                donDatHang.setMoTa(object.getString("MoTa"));

                List<ChiTietDDH> chiTietDDHList = new ArrayList<>();
                JSONArray arrayDanhSachChiTietDDH = object.getJSONArray("DanhSachTraiCay");
                int soluong = arrayDanhSachChiTietDDH.length();

                for (int j=0; j<soluong; j++){
                    JSONObject objectChiTietDDH = arrayDanhSachChiTietDDH.getJSONObject(j);

                    ChiTietDDH chiTietDDH = new ChiTietDDH();
                    chiTietDDH.setMaDDH(objectChiTietDDH.getInt("MaDDH"));
                    chiTietDDH.setMaTraiCay(objectChiTietDDH.getInt("MaTraiCay"));
                    chiTietDDH.setSoLuongDat(objectChiTietDDH.getInt("SoLuongDat"));
                    chiTietDDH.setGiaBanHD(objectChiTietDDH.getInt("GiaBanHD"));

                    traicay traicay = new traicay();
                    traicay.setMaTraiCay(objectChiTietDDH.getInt("MaTraiCay"));
                    traicay.setTenTraiCay(objectChiTietDDH.getString("TenTraiCay"));
                    traicay.setGiaBan(objectChiTietDDH.getInt("GiaBan"));
                    traicay.setSoLuongTon(objectChiTietDDH.getInt("SoLuongTon"));
                    traicay.setHinhTraiCay(TrangChuActivity.SERVER + objectChiTietDDH.getString("HinhTraiCay"));

                    chiTietDDH.setTraicay(traicay);

                    chiTietDDHList.add(chiTietDDH);
                }

                donDatHang.setChiTietDDHList(chiTietDDHList);
                donDatHangs.add(donDatHang);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return donDatHangs;
    }
    public Boolean AdminDuyetDonHang(DonDatHang donDatHang){
        String duongdan = TrangChuActivity.SERVER_NAME;
        List<HashMap<String, String>> atts = new ArrayList<>();

        boolean kiemtra = false;

        HashMap<String, String> hmHam = new HashMap<>();
        hmHam.put("ham","AdminDuyetDonHang");
        HashMap<String, String> hmMaDDH = new HashMap<>();
        hmMaDDH.put("MaDDH", String.valueOf(donDatHang.getMaDDH()));
        HashMap<String, String> hmTrangThaiGiao = new HashMap<>();
        hmTrangThaiGiao.put("TrangThaiGiao",donDatHang.getTrangThaiGiao());
        HashMap<String, String> hmMaNguoiDuyet = new HashMap<>();
        hmMaNguoiDuyet.put("MaNguoiDuyet", String.valueOf(donDatHang.getMaNguoiDuyet()));

        atts.add(hmHam);
        atts.add(hmMaDDH);
        atts.add(hmTrangThaiGiao);
        atts.add(hmMaNguoiDuyet);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,atts);
        downloadJSON.execute();

        try {
            String dulieuJson = downloadJSON.get();
            JSONObject jsonObject = new JSONObject(dulieuJson);
            String ketqua = jsonObject.getString("ketqua");
            if(ketqua.equals("true")){
                kiemtra = true;
            }else
                kiemtra = false;
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
