package longho.nienluan.traicayngoainhap.Presenter.DoiMatKhau;

import android.view.View;

import longho.nienluan.traicayngoainhap.Model.DoiMatKhau.ModelDoiMatKhau;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.View.DoiMatKhau.ViewDoiMatKhau;

public class PresenterLogicDoiMatKhau implements IPresenterDoiMatKhau {

    ModelDoiMatKhau modelDoiMatKhau;
    ViewDoiMatKhau viewDoiMatKhau;
    public PresenterLogicDoiMatKhau(ViewDoiMatKhau viewDoiMatKhau){
        this.viewDoiMatKhau = viewDoiMatKhau;
        modelDoiMatKhau = new ModelDoiMatKhau();
    }
    @Override
    public void DoiMatKhau(nguoidung nguoidung) {
        boolean doimatkhau = modelDoiMatKhau.DoiMatKhauNguoiDung(nguoidung);
        if(doimatkhau){
            viewDoiMatKhau.DoiMatKhauThanhCong();
        }

    }

    @Override
    public void LayMatKhauHienTai(String manguoidung) {
        viewDoiMatKhau.LayMatKhau(modelDoiMatKhau.LayThongTinNguoiDung(Integer.parseInt(manguoidung)));
    }
}
