package longho.nienluan.traicayngoainhap.Presenter.GioHang;

import android.content.Context;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.GioHang.ModelGioHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.GioHang.ViewGioHang;

public class PresenterLogicGioHang implements IPresenterGioHang {
    ModelGioHang modelGioHang;
    ViewGioHang viewGioHang;

    public PresenterLogicGioHang(ViewGioHang viewGioHang){
        modelGioHang = new ModelGioHang();
        this.viewGioHang = viewGioHang;
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(Context context) {
        modelGioHang.MoKetNoiSQL(context);
        List<traicay> traicayList = modelGioHang.LayDanhSachSanPhamTrongGioHang();
        if(traicayList.size() > 0){
            viewGioHang.HienThiDanhSachSanPhamTrongGioHang(traicayList);
        }else{
            viewGioHang.GioHangRong();
        }
    }
}
