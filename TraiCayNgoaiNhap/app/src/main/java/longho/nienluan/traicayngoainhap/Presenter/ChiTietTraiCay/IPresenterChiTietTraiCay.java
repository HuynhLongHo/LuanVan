package longho.nienluan.traicayngoainhap.Presenter.ChiTietTraiCay;

import android.content.Context;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public interface IPresenterChiTietTraiCay {
    void LayChiTietTraiCay(int matraicay);
    void LayDanhSachDanhGiaTheoMa(int matraicay, int limit);
    void ThemGioHang(traicay traicay, Context context);
}
