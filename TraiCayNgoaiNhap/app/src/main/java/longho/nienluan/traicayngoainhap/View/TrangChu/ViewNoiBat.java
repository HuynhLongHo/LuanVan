package longho.nienluan.traicayngoainhap.View.TrangChu;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public interface ViewNoiBat {
    void HienThiDanhSachTraiCayKhuyenMai(List<traicay> traicayList);
    void HienThiDanhSachTopTraiCayTheoLuotMua(List<traicay> traicayList);
    void HienThiDanhSachTraiCayGiaRe(List<traicay> traicayList);
    void HienThiDanhSachTraiCayNgauNhien(List<traicay> traicayList);
}
