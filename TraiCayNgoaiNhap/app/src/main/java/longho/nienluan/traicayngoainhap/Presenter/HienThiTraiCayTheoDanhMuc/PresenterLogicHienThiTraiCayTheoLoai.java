package longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.HienThiTraiCayTheoDanhMuc.ModelHienThiTraiCayTheoLoai;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.ViewHienThiTraiCayTheoLoai;

public class PresenterLogicHienThiTraiCayTheoLoai implements IPresenterHienThiTraiCayTheoLoai {

    ViewHienThiTraiCayTheoLoai viewHienThiTraiCayTheoLoai;
    ModelHienThiTraiCayTheoLoai modelHienThiTraiCayTheoLoai;

    public PresenterLogicHienThiTraiCayTheoLoai(ViewHienThiTraiCayTheoLoai viewHienThiTraiCayTheoLoai){
        this.viewHienThiTraiCayTheoLoai = viewHienThiTraiCayTheoLoai;
        modelHienThiTraiCayTheoLoai = new ModelHienThiTraiCayTheoLoai();
    }

    @Override
    public void LayDanhSachTraiCayTheoMaLoai(int MaLoai) {
        List<traicay> traicayList = modelHienThiTraiCayTheoLoai.LayDanhSachTraiCayTheoMaLoai(MaLoai);
        if(traicayList.size()>0){
            viewHienThiTraiCayTheoLoai.HienThiDanhSachTraiCayTheoLoai(traicayList);
        }
    }
}
