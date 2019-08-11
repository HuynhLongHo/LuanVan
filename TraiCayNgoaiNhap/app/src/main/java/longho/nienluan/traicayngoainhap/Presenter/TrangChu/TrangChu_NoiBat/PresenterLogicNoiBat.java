package longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_NoiBat;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Model.TrangChu_NoiBat.ModelTraiCay;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewNoiBat;

public class PresenterLogicNoiBat implements IPresenterNoiBat {

    ViewNoiBat viewNoiBat;
    ModelTraiCay modelTraiCay;

    public PresenterLogicNoiBat(ViewNoiBat viewNoiBat){
        this.viewNoiBat = viewNoiBat;
        modelTraiCay = new ModelTraiCay();
    }
    @Override
    public void LayDanhSachTopTraiCay() {
        List<traicay> traicayList = modelTraiCay.LayDanhSachTopTraiCay();
        if(traicayList.size()>0){
            viewNoiBat.HienThiDanhSachTopTraiCay(traicayList);
        }
    }
}
