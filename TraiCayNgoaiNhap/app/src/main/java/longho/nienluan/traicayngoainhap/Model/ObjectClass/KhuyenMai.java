package longho.nienluan.traicayngoainhap.Model.ObjectClass;

import java.util.Date;

public class KhuyenMai {

    int MaKM;
    String TenKM, HinhKM, NgayBatDau, NgayKetThuc;

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public int getMaKM() {
        return MaKM;
    }

    public void setMaKM(int maKM) {
        MaKM = maKM;
    }

    public String getTenKM() {
        return TenKM;
    }

    public void setTenKM(String tenKM) {
        TenKM = tenKM;
    }

    public String getHinhKM() {
        return HinhKM;
    }

    public void setHinhKM(String hinhKM) {
        HinhKM = hinhKM;
    }

}
