package longho.nienluan.traicayngoainhap.Presenter.Admin;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.Admin.ModelThongKe;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ThongKe;
import longho.nienluan.traicayngoainhap.View.Admin.ThongKe.ViewThongKe;

public class PresenterLogicThongKe implements IPresenterThongKe {

    ModelThongKe modelThongKe;
    ViewThongKe viewThongKe;
    public PresenterLogicThongKe(ViewThongKe viewThongKe){
        this.viewThongKe = viewThongKe;
        modelThongKe = new ModelThongKe();
    }
    @Override
    public void LayDanhSachDonDatHangTheoNam() {
        List<ThongKe> thongKeList = modelThongKe.LayDanhSachDonDatHangTheoNam();
        if(thongKeList.size()>0){
            viewThongKe.HienThiThongKe(thongKeList);
        }
    }
}
