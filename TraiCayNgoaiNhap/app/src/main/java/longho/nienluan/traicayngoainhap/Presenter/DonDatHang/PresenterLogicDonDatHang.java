package longho.nienluan.traicayngoainhap.Presenter.DonDatHang;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.DonDatHang.ModelDonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.View.DonDatHang.ViewDonDatHang;

public class PresenterLogicDonDatHang implements IPresenterDonDatHang {

    ViewDonDatHang viewDonDatHang;
    ModelDonDatHang modelDonDatHang;

    public PresenterLogicDonDatHang (ViewDonDatHang viewDonDatHang){
        this.viewDonDatHang=viewDonDatHang;
        modelDonDatHang = new ModelDonDatHang();
    }
    @Override
    public void LayDanhSachDonDatHang(String MaNguoiDung) {
        List<DonDatHang> donDatHangList = modelDonDatHang.LayDanhSachDonDatHangTheoMaND(MaNguoiDung);
        if(donDatHangList.size()>0){
            viewDonDatHang.HienThiDanhSachDonDatHang(donDatHangList);
        }
    }
}
