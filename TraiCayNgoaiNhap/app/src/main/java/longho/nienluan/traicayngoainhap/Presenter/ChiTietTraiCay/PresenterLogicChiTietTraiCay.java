package longho.nienluan.traicayngoainhap.Presenter.ChiTietTraiCay;

import android.content.Context;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ChiTietTraiCay.ModelChiTietTraiCay;
import longho.nienluan.traicayngoainhap.Model.GioHang.ModelGioHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.ChiTietTraiCay.ViewChiTietTraiCay;

public class PresenterLogicChiTietTraiCay implements IPresenterChiTietTraiCay {

    ViewChiTietTraiCay viewChiTietTraiCay;
    ModelChiTietTraiCay modelChiTietTraiCay;
    ModelGioHang modelGioHang;

    public PresenterLogicChiTietTraiCay(ViewChiTietTraiCay viewChiTietTraiCay){
        this.viewChiTietTraiCay=viewChiTietTraiCay;
        modelChiTietTraiCay = new ModelChiTietTraiCay();
        modelGioHang = new ModelGioHang();

    }

    @Override
    public void LayChiTietTraiCay(int matraicay) {
        traicay traicay = modelChiTietTraiCay.LayChiTietTraiCayTheoMa(matraicay);
        if(traicay.getMaTraiCay()>=0){
            String[] linkhinhanh = traicay.getHinhChiTiet().split(",");
            viewChiTietTraiCay.HienSliderTraiCay(linkhinhanh);
            viewChiTietTraiCay.HienThiChiTietTraiCay(traicay);
        }
    }

    @Override
    public void LayDanhSachDanhGiaTheoMa(int matraicay, int limit) {
        List<DanhGia> danhGias = modelChiTietTraiCay.LayDanhSachDanhGiaCuaTraiCay(matraicay,limit);
        if(danhGias.size()>0){
            viewChiTietTraiCay.HienThiDanhGia(danhGias);
        }
    }

    @Override
    public void ThemGioHang(traicay traicay, Context context) {
        modelGioHang.MoKetNoiSQL(context);
        boolean kiemtra = modelGioHang.ThemGioHang(traicay);
        if (kiemtra){
            viewChiTietTraiCay.ThemGioHangThanhCong();
        }else{
            viewChiTietTraiCay.ThemGiohangThatBai();
        }
    }


    public int DemSanPhamCoTrongGioHang(Context context){
        modelGioHang.MoKetNoiSQL(context);
        List<traicay> traicayList = modelGioHang.LayDanhSachSanPhamTrongGioHang();

        int dem = traicayList.size();

        return dem;
    }
}
