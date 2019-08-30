package longho.nienluan.traicayngoainhap.View.ThanhToan;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public interface ViewThanhToan {
    void DatHangThanhCong();
    void DatHangThatBai();
    void LayDanhSachSanPhamTrongGioHang(List<traicay> traicayList);
}
