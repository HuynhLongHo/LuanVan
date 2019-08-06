package longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_LoaiTraiCay;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DSloaitraicay;
import longho.nienluan.traicayngoainhap.Model.TrangChu_LoaiTraiCay.ModelLoaiTraiCay;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewLoaiTraiCay;

public class PresenterLogicLoaiTraiCay implements IPresenterLoaiTraiCay {

    ViewLoaiTraiCay viewLoaiTraiCay;
    ModelLoaiTraiCay modelloaiTraiCay;

    public PresenterLogicLoaiTraiCay(ViewLoaiTraiCay viewLoaiTraiCay){
        this.viewLoaiTraiCay = viewLoaiTraiCay;
        modelloaiTraiCay = new ModelLoaiTraiCay();
    }
    @Override
    public void LayDanhSachLoaiTraiCay() {
        List<DSloaitraicay> dSloaitraicayList = modelloaiTraiCay.LayDanhSachLoaiTraiCay();
        if(dSloaitraicayList.size()>0){
            viewLoaiTraiCay.HienThiDanhSachLoaiTraiCay(dSloaitraicayList);
        }

    }
}
