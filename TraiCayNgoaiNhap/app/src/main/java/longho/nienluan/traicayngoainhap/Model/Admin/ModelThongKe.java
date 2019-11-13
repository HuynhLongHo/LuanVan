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
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ThongKe;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ModelThongKe {
    public List<ThongKe> LayDanhSachDonDatHangTheoNam(){
        List<ThongKe> thongKes = new ArrayList<>();

        List<HashMap<String,String>> attrs = new ArrayList<>();
        String dataJSON = "";

        String duongdan = TrangChuActivity.SERVER_NAME;

        HashMap<String,String> hsHam = new HashMap<>();
        hsHam.put("ham","LayDanhSachDonDatHangTheoNam");

        attrs.add(hsHam);


        DownloadJSON downloadJSON = new DownloadJSON(duongdan,attrs);
        //end phương thức post
        downloadJSON.execute();

        try {
            dataJSON = downloadJSON.get();

            JSONObject jsonObject = new JSONObject(dataJSON);
            JSONArray jsonArrayDanhSachThongKe = jsonObject.getJSONArray("DanhSachThongKeTheoNam");
            int dem = jsonArrayDanhSachThongKe.length();

            for (int i = 0; i<dem; i++){
                JSONObject object = jsonArrayDanhSachThongKe.getJSONObject(i);
                ThongKe thongKe = new ThongKe();
                thongKe.setNam(object.getInt("Nam"));
                thongKe.setSoLuongDDH(object.getInt("SoLuongDDH"));
                thongKe.setTongDoanhThuNam(object.getInt("TongDoanhThuNam"));

                
                List<DonDatHang> donDatHangList = new ArrayList<>();
                JSONArray arrayChiTietTrangThai = object.getJSONArray("ChiTietTrangThai");
                int soluong = arrayChiTietTrangThai.length();
                for (int j=0; j<soluong; j++){
                    JSONObject objectChiTietTrangThai = arrayChiTietTrangThai.getJSONObject(j);
                    DonDatHang donDatHang = new DonDatHang();
                    donDatHang.setTrangThaiGiao(objectChiTietTrangThai.getString("TrangThaiGiao"));
                    donDatHang.setSoLuongDonHang(objectChiTietTrangThai.getInt("SoLuongDonHang"));
                    donDatHangList.add(donDatHang);
                }

                List<traicay> traicayList = new ArrayList<>();
                JSONArray arrayTraiCay = object.getJSONArray("ThongKeTraiCay");
                int soluongtc = arrayTraiCay.length();
                for (int j=0; j<soluongtc; j++){
                    JSONObject objectChiTietTraiCay = arrayTraiCay.getJSONObject(j);
                    traicay traicay = new traicay();
                    traicay.setMaTraiCay(objectChiTietTraiCay.getInt("MaTraiCay"));
                    traicay.setTenTraiCay(objectChiTietTraiCay.getString("TenTraiCay"));
                    traicay.setSoLuongBan(objectChiTietTraiCay.getInt("SoLuongBan"));
                    traicay.setSoTienBan(objectChiTietTraiCay.getInt("SoTienBan"));
                    traicay.setHinhTraiCay(TrangChuActivity.SERVER + objectChiTietTraiCay.getString("HinhTraiCay"));
                    traicayList.add(traicay);
                }

                List<nguoidung> nguoidungList = new ArrayList<>();
                JSONArray arrayNguoiDung = object.getJSONArray("ThongKeNguoiDung");
                int soluongnd = arrayNguoiDung.length();
                for (int j=0; j<soluongnd; j++){
                    JSONObject objectNguoiDung = arrayNguoiDung.getJSONObject(j);
                    nguoidung nguoidung = new nguoidung();
                    nguoidung.setMaNguoiDung(objectNguoiDung.getInt("MaNguoiDung"));
                    nguoidung.setTenNguoiDung(objectNguoiDung.getString("TenNguoiDung"));
                    nguoidung.setSoLanMua(objectNguoiDung.getInt("SoLanMua"));
                    nguoidung.setTongTienMua(objectNguoiDung.getInt("TongTienMua"));
                    nguoidungList.add(nguoidung);
                }

                thongKe.setDonDatHangList(donDatHangList);
                thongKe.setTraicayList(traicayList);
                thongKe.setNguoidungList(nguoidungList);
                thongKes.add(thongKe);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return thongKes;
    }
}
