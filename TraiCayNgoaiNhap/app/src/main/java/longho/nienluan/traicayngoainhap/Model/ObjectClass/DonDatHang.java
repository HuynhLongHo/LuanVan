package longho.nienluan.traicayngoainhap.Model.ObjectClass;

import java.util.List;

public class DonDatHang {

    int MaDDH, MaNguoiDung;
    String TenNguoiDatHang, SoDienThoaiDatHang, DiaChiDatHang, NgayDat, NgayGiao, TrangThaiGiao, MoTa;
    List<ChiTietDDH> ChiTietDDHList;
    int SoLuongDonHang;//Số lượng dựa vào trạng thái

    public int getSoLuongDonHang() {
        return SoLuongDonHang;
    }

    public void setSoLuongDonHang(int soLuongDonHang) {
        SoLuongDonHang = soLuongDonHang;
    }

    public int getChuyenKhoan() {
        return ChuyenKhoan;
    }

    public void setChuyenKhoan(int chuyenKhoan) {
        ChuyenKhoan = chuyenKhoan;
    }

    int ChuyenKhoan;

    public List<ChiTietDDH> getChiTietDDHList() {
        return ChiTietDDHList;
    }

    public void setChiTietDDHList(List<ChiTietDDH> chiTietDDHList) {
        ChiTietDDHList = chiTietDDHList;
    }

    public int getMaDDH() {
        return MaDDH;
    }

    public void setMaDDH(int maDDH) {
        MaDDH = maDDH;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }

    public String getTenNguoiDatHang() {
        return TenNguoiDatHang;
    }

    public void setTenNguoiDatHang(String tenNguoiDatHang) {
        TenNguoiDatHang = tenNguoiDatHang;
    }

    public String getSoDienThoaiDatHang() {
        return SoDienThoaiDatHang;
    }

    public void setSoDienThoaiDatHang(String soDienThoaiDatHang) {
        SoDienThoaiDatHang = soDienThoaiDatHang;
    }

    public String getDiaChiDatHang() {
        return DiaChiDatHang;
    }

    public void setDiaChiDatHang(String diaChiDatHang) {
        DiaChiDatHang = diaChiDatHang;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }

    public String getNgayGiao() {
        return NgayGiao;
    }

    public void setNgayGiao(String ngayGiao) {
        NgayGiao = ngayGiao;
    }

    public String getTrangThaiGiao() {
        return TrangThaiGiao;
    }

    public void setTrangThaiGiao(String trangThaiGiao) {
        TrangThaiGiao = trangThaiGiao;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }
}
