package longho.nienluan.traicayngoainhap.Model.ObjectClass;

import java.util.List;

public class loaitraicay_traicay {

    int MaLTC,MaTraiCay, MaDV;
    String TenLTC,TenTraiCay;

    List<loaitraicay_traicay> ListCon;
    public int getMaLTC() {
        return MaLTC;
    }

    public void setMaLTC(int maLTC) {
        MaLTC = maLTC;
    }

    public int getMaTraiCay() {
        return MaTraiCay;
    }

    public void setMaTraiCay(int maTraiCay) {
        MaTraiCay = maTraiCay;
    }

    public int getMaDV() {
        return MaDV;
    }

    public void setMaDV(int maDV) {
        MaDV = maDV;
    }

    public String getTenLTC() {
        return TenLTC;
    }

    public void setTenLTC(String tenLTC) {
        TenLTC = tenLTC;
    }

    public List<loaitraicay_traicay> getListCon() {
        return ListCon;
    }

    public void setListCon(List<loaitraicay_traicay> listCon) {
        ListCon = listCon;
    }


    public String getTenTraiCay() {
        return TenTraiCay;
    }

    public void setTenTraiCay(String tenTraiCay) {
        TenTraiCay = tenTraiCay;
    }


}
