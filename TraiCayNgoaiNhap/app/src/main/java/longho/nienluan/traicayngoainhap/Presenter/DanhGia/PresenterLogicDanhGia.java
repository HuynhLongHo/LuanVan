package longho.nienluan.traicayngoainhap.Presenter.DanhGia;

import longho.nienluan.traicayngoainhap.Model.DanhGia.ModelDanhGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.View.DanhGia.ViewDanhGia;

public class PresenterLogicDanhGia implements IPresenterDanhGia {

    ViewDanhGia viewDanhGia;
    ModelDanhGia modelDanhGia;

    public PresenterLogicDanhGia (ViewDanhGia viewDanhGia){
        this.viewDanhGia=viewDanhGia;
        modelDanhGia = new ModelDanhGia();
    }

    @Override
    public void ThemDanhGia(DanhGia danhGia) {
        boolean kiemtra = modelDanhGia.ThemDanhGia(danhGia);
        if (kiemtra){
            viewDanhGia.DanhGiaThanhCong();
        }else{
            viewDanhGia.DanhGiaThatBai();
        }
    }
}
