package longho.nienluan.traicayngoainhap.Presenter.Admin;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;

public interface IPresenterDuyetDonHang {
    void LayDanhSachDonHangChuaDuyet();
    void DuyetDonHang(DonDatHang donDatHang);
    void GiaoDonHang(DonDatHang donDatHang);
    void HuyDonHang(DonDatHang donDatHang);
    void LayDanhSachDonHangChuaGiao();
}
