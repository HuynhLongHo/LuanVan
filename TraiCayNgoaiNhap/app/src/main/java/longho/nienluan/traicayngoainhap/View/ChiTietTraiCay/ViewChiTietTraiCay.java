package longho.nienluan.traicayngoainhap.View.ChiTietTraiCay;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public interface ViewChiTietTraiCay {
    void HienThiChiTietTraiCay(traicay traicay);
    void HienSliderTraiCay(String[] linkhinhtraicay);
    void HienThiDanhGia(List<DanhGia> danhGiaList);
    void ThemGioHangThanhCong();
    void ThemGioHangThatBai();
}
