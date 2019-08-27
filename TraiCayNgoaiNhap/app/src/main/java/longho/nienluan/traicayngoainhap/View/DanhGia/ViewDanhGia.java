package longho.nienluan.traicayngoainhap.View.DanhGia;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;

public interface ViewDanhGia {
    void DanhGiaThanhCong();
    void DanhGiaThatBai();
    void HienThiDanhSachDanhGiaTheoMa(List<DanhGia> danhGiaList);
}
