package longho.nienluan.traicayngoainhap.Presenter.ThongTinNguoiDung;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ThongTinNguoiDung.ModelThongTinNguoiDung;
import longho.nienluan.traicayngoainhap.View.ThongTinNguoiDung.ViewThongTinNguoiDung;

public class PresenterLogicThongTinNguoiDung implements IPresenterThongTinNguoiDung {

    ModelThongTinNguoiDung modelThongTinNguoiDung;
    ViewThongTinNguoiDung viewThongTinNguoiDung;

    public PresenterLogicThongTinNguoiDung(ViewThongTinNguoiDung viewThongTinNguoiDung){
        this.viewThongTinNguoiDung = viewThongTinNguoiDung;
        modelThongTinNguoiDung = new ModelThongTinNguoiDung();
    }

    @Override
    public void LayThongTinNguoiDung(int manguoidung) {
        nguoidung nguoidung = modelThongTinNguoiDung.LayThongTinNguoiDung(manguoidung);
        viewThongTinNguoiDung.HienThiThongTinNguoiDung(nguoidung);
    }
}
