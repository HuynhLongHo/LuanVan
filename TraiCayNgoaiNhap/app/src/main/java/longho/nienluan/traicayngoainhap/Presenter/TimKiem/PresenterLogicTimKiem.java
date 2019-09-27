package longho.nienluan.traicayngoainhap.Presenter.TimKiem;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Model.TimKiem.ModelTimKiem;
import longho.nienluan.traicayngoainhap.View.TimKiem.ViewTimKiem;

public class PresenterLogicTimKiem implements IPresenterTimKiem {

    ModelTimKiem modelTimKiem;
    ViewTimKiem viewTimKiem;
    public PresenterLogicTimKiem(ViewTimKiem viewTimKiem){
        modelTimKiem = new ModelTimKiem();
        this.viewTimKiem = viewTimKiem;
    }
    @Override
    public void TimKiemSanPhamTheoTenSP(String tensp) {
        List<traicay> traicayList = modelTimKiem.TimKiemSanPhamTheoTenSP(tensp);
        if(traicayList.size()>0){
            viewTimKiem.TimKiemThanhCong(traicayList);
        }
        else{
            viewTimKiem.TimKiemThatBai();
        }
    }
}
