package longho.nienluan.traicayngoainhap.Presenter.DatHang;

import android.content.Context;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.GioHang.ModelGioHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Model.DatHang.ModelDatHang;
import longho.nienluan.traicayngoainhap.View.DatHang.ViewDatHang;

public class PresenterLogicDatHang implements IPresenterDatHang {
    ViewDatHang viewDatHang;
    ModelDatHang modelDatHang;
    ModelGioHang modelGioHang;
    List<traicay> traicayList;

    public PresenterLogicDatHang(ViewDatHang viewDatHang, Context context){
        this.viewDatHang = viewDatHang;
        modelDatHang = new ModelDatHang();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }
    @Override
    public void ThemDDH(DonDatHang donDatHang) {
        boolean kiemtra = modelDatHang.ThemDonDatHang(donDatHang);
        if(kiemtra){
            viewDatHang.DatHangThanhCong();

            int dem = traicayList.size();
            for(int i = 0; i<dem ;i++){
                modelGioHang.XoaSanPhamTrongGioHang(traicayList.get(i).getMaTraiCay());
            }

        }else{
            viewDatHang.DatHangThatBai();
        }

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {
        traicayList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewDatHang.LayDanhSachSanPhamTrongGioHang(traicayList);
    }
}
