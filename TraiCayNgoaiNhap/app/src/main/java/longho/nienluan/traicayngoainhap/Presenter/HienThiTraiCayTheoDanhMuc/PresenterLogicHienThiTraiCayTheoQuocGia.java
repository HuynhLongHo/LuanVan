package longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.HienThiTraiCayTheoDanhMuc.ModelHienThiTraiCayTheoQuocGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.ViewHienThiTraiCayTheoQuocGia;

public class PresenterLogicHienThiTraiCayTheoQuocGia implements IPresenterHienThiTraiCayTheoQuocGia{
    ViewHienThiTraiCayTheoQuocGia viewHienThiTraiCayTheoQuocGia;
    ModelHienThiTraiCayTheoQuocGia modelHienThiTraiCayTheoQuocGia;

    public PresenterLogicHienThiTraiCayTheoQuocGia(ViewHienThiTraiCayTheoQuocGia viewHienThiTraiCayTheoQuocGia){
        this.viewHienThiTraiCayTheoQuocGia = viewHienThiTraiCayTheoQuocGia;
        modelHienThiTraiCayTheoQuocGia = new ModelHienThiTraiCayTheoQuocGia();
    }

    @Override
    public void LayDanhSachTraiCayTheoQuocGia(int MaQG) {
        List<traicay> traicayList = modelHienThiTraiCayTheoQuocGia.LayDanhSachTraiCayTheoQuocGia(MaQG);
        if(traicayList.size()>0){
            viewHienThiTraiCayTheoQuocGia.HienThiDanhSachTraiCayTheoQuocGia(traicayList);
        }
    }
}
