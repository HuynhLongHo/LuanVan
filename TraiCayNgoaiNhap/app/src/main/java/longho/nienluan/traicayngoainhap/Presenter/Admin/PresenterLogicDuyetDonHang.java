package longho.nienluan.traicayngoainhap.Presenter.Admin;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.Admin.ModelDuyetDonHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.View.Admin.DuyetDonHang.ViewDuyetDonHang;

public class PresenterLogicDuyetDonHang implements IPresenterDuyetDonHang{

    ModelDuyetDonHang modelDuyetDonHang;
    ViewDuyetDonHang viewDuyetDonHang;
    public PresenterLogicDuyetDonHang (ViewDuyetDonHang viewDuyetDonHang){
        this.viewDuyetDonHang = viewDuyetDonHang;
        modelDuyetDonHang = new ModelDuyetDonHang();
    }
    public PresenterLogicDuyetDonHang (){
        modelDuyetDonHang = new ModelDuyetDonHang();
    }

    @Override
    public void LayDanhSachDonHangChuaDuyet() {
        List<DonDatHang> donDatHangList = modelDuyetDonHang.AdminLayDanhSachDonDatHangChuaDuyet();
        if(donDatHangList.size()>0){
            viewDuyetDonHang.HienThiDonHangChuaDuyet(donDatHangList);
        }
    }

    @Override
    public void DuyetDonHang(DonDatHang donDatHang) {
        boolean duyetdonhang = modelDuyetDonHang.AdminDuyetDonHang(donDatHang);
    }
}
