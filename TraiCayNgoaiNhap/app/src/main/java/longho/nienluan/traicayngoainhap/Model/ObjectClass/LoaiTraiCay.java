package longho.nienluan.traicayngoainhap.Model.ObjectClass;

import java.util.List;

public class LoaiTraiCay {

    int MaLTC, MaTraiCay, MaDV;
    String TenLTC,TenTraiCay;
    List<LoaiTraiCay> ListCon; //tạo các biến xong thì nhấn alt + insert để thêm set và get

    public int getMaTraiCay() {
        return MaTraiCay;
    }

    public void setMaTraiCay(int maTraiCay) {
        MaTraiCay = maTraiCay;
    }

    public String getTenTraiCay() {
        return TenTraiCay;
    }

    public void setTenTraiCay(String tenTraiCay) {
        TenTraiCay = tenTraiCay;
    }

    public int getMaDV() {
        return MaDV;
    }

    public void setMaDV(int maDV) {
        MaDV = maDV;
    }

    public int getMaLTC() {
        return MaLTC;
    }

    public void setMaLTC(int maLTC) {
        MaLTC = maLTC;
    }

    public String getTenLTC() {
        return TenLTC;
    }

    public void setTenLTC(String tenLTC) {
        TenLTC = tenLTC;
    }

    public List<LoaiTraiCay> getListCon() {
        return ListCon;
    }

    public void setListCon(List<LoaiTraiCay> listCon) {
        ListCon = listCon;
    }
}
