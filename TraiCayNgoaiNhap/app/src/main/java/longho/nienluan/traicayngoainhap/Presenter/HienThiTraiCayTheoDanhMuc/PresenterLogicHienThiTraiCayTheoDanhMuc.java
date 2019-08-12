package longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.HienThiTraiCayTheoDanhMuc.ModelHienThiTraiCayTheoDanhMuc;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.ViewHienThiTraiCayTheoDanhMuc;

public class PresenterLogicHienThiTraiCayTheoDanhMuc implements IPresenterHienThiSanPhamTheoDanhMuc {

    ViewHienThiTraiCayTheoDanhMuc viewHienThiTraiCayTheoDanhMuc;
    ModelHienThiTraiCayTheoDanhMuc modelHienThiTraiCayTheoDanhMuc;

    public PresenterLogicHienThiTraiCayTheoDanhMuc(ViewHienThiTraiCayTheoDanhMuc viewHienThiTraiCayTheoDanhMuc){
        this.viewHienThiTraiCayTheoDanhMuc = viewHienThiTraiCayTheoDanhMuc;
        modelHienThiTraiCayTheoDanhMuc = new ModelHienThiTraiCayTheoDanhMuc();
    }

    @Override
    public void LayDanhSachTraiCayTheoMaLoai(int MaLoai) {
        List<traicay> traicayList = modelHienThiTraiCayTheoDanhMuc.LayDanhSachTraiCayTheoMaLoai(MaLoai);
        if(traicayList.size()>0){
            viewHienThiTraiCayTheoDanhMuc.HienThiDanhSachTraiCayTheoMaLoai(traicayList);
        }
    }
}
