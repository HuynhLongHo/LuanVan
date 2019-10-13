package longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.HienThiTraiCayTheoDanhMuc.ModelHienThiTraiCayKhuyenMai;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.ViewHienThiTraiCayKhuyenMai;

public class PresenterLogicHienThiTraiCayKhuyenMai implements IPresenterHienThiTraiCayKhuyenMai {
    ViewHienThiTraiCayKhuyenMai viewHienThiTraiCayKhuyenMai;
    ModelHienThiTraiCayKhuyenMai modelHienThiTraiCayKhuyenMai;

    public PresenterLogicHienThiTraiCayKhuyenMai(ViewHienThiTraiCayKhuyenMai viewHienThiTraiCayKhuyenMai){
        this.viewHienThiTraiCayKhuyenMai = viewHienThiTraiCayKhuyenMai;
        modelHienThiTraiCayKhuyenMai = new ModelHienThiTraiCayKhuyenMai();
    }
    @Override
    public void LayDanhSachTraiCayKhuyenMai(int MaKM) {
        List<traicay> traicayList = modelHienThiTraiCayKhuyenMai.LayDanhSachTraiCayKhuyenMaiTheoMa(MaKM);
        if(traicayList.size()>0){
            viewHienThiTraiCayKhuyenMai.HienThiDanhSachTraiCayKhuyenMai(traicayList);
        }
    }
}
