package longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_KhuyenMai;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.KhuyenMai;
import longho.nienluan.traicayngoainhap.Model.TrangChu_Khuyenmai.ModelKhuyenMai;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewKhuyenMai;

public class PresenterLogicKhuyenMai implements IPresenterKhuyenmai {

    ViewKhuyenMai viewKhuyenMai;
    ModelKhuyenMai modelKhuyenMai;

    public PresenterLogicKhuyenMai(ViewKhuyenMai viewKhuyenMai){
        this.viewKhuyenMai = viewKhuyenMai;
        modelKhuyenMai = new ModelKhuyenMai();
    }
    @Override
    public void LayDanhSachKhuyenMai() {
        List<KhuyenMai> khuyenMaiList = modelKhuyenMai.LayDanhSachDangKhuyenMai();
        if(khuyenMaiList.size()>0){
            viewKhuyenMai.HienThiDanhSachKhuyenMai(khuyenMaiList);
        }
    }
}
