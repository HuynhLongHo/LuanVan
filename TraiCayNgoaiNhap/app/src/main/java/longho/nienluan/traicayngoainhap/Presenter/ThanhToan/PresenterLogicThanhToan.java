package longho.nienluan.traicayngoainhap.Presenter.ThanhToan;

import android.content.Context;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.GioHang.ModelGioHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Model.ThanhToan.ModelThanhToan;
import longho.nienluan.traicayngoainhap.View.ThanhToan.ViewThanhToan;

public class PresenterLogicThanhToan implements IPresenterThanhToan {
    ViewThanhToan viewThanhToan;
    ModelThanhToan modelThanhToan;
    ModelGioHang modelGioHang;
    List<traicay> traicayList;

    public PresenterLogicThanhToan(ViewThanhToan viewThanhToan, Context context){
        this.viewThanhToan = viewThanhToan;
        modelThanhToan = new ModelThanhToan();
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }
    @Override
    public void ThemDDH(DonDatHang donDatHang) {
        boolean kiemtra = modelThanhToan.ThemDonDatHang(donDatHang);
        if(kiemtra){
            viewThanhToan.DatHangThanhCong();

            int dem = traicayList.size();
            for(int i = 0; i<dem ;i++){
                modelGioHang.XoaSanPhamTrongGioHang(traicayList.get(i).getMaTraiCay());
            }

        }else{
            viewThanhToan.DatHangThatBai();
        }

    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang() {
        traicayList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        viewThanhToan.LayDanhSachSanPhamTrongGioHang(traicayList);
    }
}
