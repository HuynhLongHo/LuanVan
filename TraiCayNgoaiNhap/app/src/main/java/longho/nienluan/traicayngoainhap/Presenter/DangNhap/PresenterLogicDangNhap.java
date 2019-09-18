package longho.nienluan.traicayngoainhap.Presenter.DangNhap;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment.ViewDangNhap;

public class PresenterLogicDangNhap implements IPresenterDangNhap {

    ViewDangNhap viewDangNhap;
    ModelDangNhap modelDangNhap;

    public PresenterLogicDangNhap(ViewDangNhap viewDangNhap){
        this.viewDangNhap = viewDangNhap;
        modelDangNhap = new ModelDangNhap();
    }
    @Override
    public void LayThongTinNguoiDungBangEmail(String Email) {
        nguoidung nguoidung = modelDangNhap.LayThongTinNguoiDung(Email);
        if(nguoidung.getMaNguoiDung() >= 0){
            viewDangNhap.LayThongTinNguoiDungBangEmail(nguoidung);
        }

    }
}
