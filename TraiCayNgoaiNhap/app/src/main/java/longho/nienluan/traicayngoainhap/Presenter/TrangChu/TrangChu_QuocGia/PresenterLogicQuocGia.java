package longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_QuocGia;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.QuocGia;
import longho.nienluan.traicayngoainhap.Model.TrangChu_QuocGia.ModelQuocGia;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewQuocGia;

public class PresenterLogicQuocGia implements IPresenterQuocGia{
    ViewQuocGia viewQuocGia;
    ModelQuocGia modelQuocGia;

    public PresenterLogicQuocGia(ViewQuocGia viewQuocGia){
        this.viewQuocGia = viewQuocGia;
        modelQuocGia = new ModelQuocGia();
    }

    @Override
    public void LayDanhSachQuocGia() {
        List<QuocGia> quocGiaList = modelQuocGia.LayDanhSachQuocGia();
        if(quocGiaList.size()>0){
            viewQuocGia.HienThiDanhSachQuocGia(quocGiaList);
        }
    }


}
