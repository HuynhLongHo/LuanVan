package longho.nienluan.traicayngoainhap.Presenter.DangKy;

import android.view.View;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangKy;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.ViewDangKy;

public class PresenterLogicDangKy implements IPresenterDangKy {
    ViewDangKy viewDangKy;
    ModelDangKy modelDangKy;

    public PresenterLogicDangKy(ViewDangKy viewDangKy){
        this.viewDangKy = viewDangKy;
        modelDangKy = new ModelDangKy();
    }
    @Override
    public void ThucHienDangKy(nguoidung nguoiDung) {
        boolean kiemtra = modelDangKy.DangKy(nguoiDung);
        if(kiemtra)
            viewDangKy.DangKyThanhCong();
        else viewDangKy.DangKyThatBai();
    }

}
