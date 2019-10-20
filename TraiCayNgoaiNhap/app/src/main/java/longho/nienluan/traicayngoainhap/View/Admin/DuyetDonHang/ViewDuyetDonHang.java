package longho.nienluan.traicayngoainhap.View.Admin.DuyetDonHang;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;

public interface ViewDuyetDonHang {
    void HienThiDonHangChuaDuyet(List<DonDatHang> donDatHangList);
    void HienThiDonHangChuaGiao(List<DonDatHang> donDatHangList);
}
