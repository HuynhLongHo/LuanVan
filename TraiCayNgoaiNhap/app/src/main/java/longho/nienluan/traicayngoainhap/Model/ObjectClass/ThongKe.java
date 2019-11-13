package longho.nienluan.traicayngoainhap.Model.ObjectClass;

import java.util.List;

public class ThongKe {

    int Nam, SoLuongDDH;
    List<DonDatHang> donDatHangList;
    List<traicay> traicayList;
    List<nguoidung> nguoidungList;
    int TongDoanhThuNam;

    public int getTongDoanhThuNam() {
        return TongDoanhThuNam;
    }

    public void setTongDoanhThuNam(int tongDoanhThuNam) {
        TongDoanhThuNam = tongDoanhThuNam;
    }

    public int getNam() {
        return Nam;
    }

    public void setNam(int nam) {
        Nam = nam;
    }

    public int getSoLuongDDH() {
        return SoLuongDDH;
    }

    public void setSoLuongDDH(int soLuongDDH) {
        SoLuongDDH = soLuongDDH;
    }

    public List<DonDatHang> getDonDatHangList() {
        return donDatHangList;
    }

    public void setDonDatHangList(List<DonDatHang> donDatHangList) {
        this.donDatHangList = donDatHangList;
    }

    public List<traicay> getTraicayList() {
        return traicayList;
    }

    public void setTraicayList(List<traicay> traicayList) {
        this.traicayList = traicayList;
    }

    public List<nguoidung> getNguoidungList() {
        return nguoidungList;
    }

    public void setNguoidungList(List<nguoidung> nguoidungList) {
        this.nguoidungList = nguoidungList;
    }

}
