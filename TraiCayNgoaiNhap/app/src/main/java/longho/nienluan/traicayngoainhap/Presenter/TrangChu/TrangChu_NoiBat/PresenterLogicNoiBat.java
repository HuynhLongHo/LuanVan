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
    public void LayDanhSachTraiCayKhuyenMai() {
        List<traicay> traicayList = modelTraiCay.LayDanhSachTraiCayKhuyenMai();
        if(traicayList.size()>0){
            viewNoiBat.HienThiDanhSachTraiCayKhuyenMai(traicayList);
        }
    }

    @Override
    public void LayDanhSachTopTraiCayTheoLuotMua() {
        List<traicay> traicayList = modelTraiCay.LayDanhSachTopTraiCayTheoLuotMua();
        if(traicayList.size()>0){
            viewNoiBat.HienThiDanhSachTopTraiCayTheoLuotMua(traicayList);
        }
    }

    @Override
    public void LayDanhSachTraiCayGiaRe() {
        List<traicay> traicayList = modelTraiCay.LayDanhSachTraiCayGiaRe();
        if(traicayList.size()>0){
            viewNoiBat.HienThiDanhSachTraiCayGiaRe(traicayList);
        }
    }

    @Override
    public void LayDanhSachTraiCayNgauNhien() {
        List<traicay> traicayList = modelTraiCay.LayDanhSachTraiCayNgauNhien();
        if(traicayList.size()>0){
            viewNoiBat.HienThiDanhSachTraiCayNgauNhien(traicayList);
        }
    }
}
