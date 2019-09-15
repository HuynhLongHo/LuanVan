package longho.nienluan.traicayngoainhap.View.DatHang;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public interface ViewDatHang {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<traicay> traicayList);
    void HienThiThongTinNguoiDung(nguoidung nguoidung);
}
