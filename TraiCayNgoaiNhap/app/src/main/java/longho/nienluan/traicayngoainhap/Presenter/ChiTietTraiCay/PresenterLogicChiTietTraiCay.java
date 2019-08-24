package longho.nienluan.traicayngoainhap.Presenter.ChiTietTraiCay;

import longho.nienluan.traicayngoainhap.Model.ChiTietTraiCay.ModelChiTietTraiCay;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.View.ChiTietTraiCay.ViewChiTietTraiCay;

public class PresenterLogicChiTietTraiCay implements IPresenterChiTietTraiCay {

    ViewChiTietTraiCay viewChiTietTraiCay;
    ModelChiTietTraiCay modelChiTietTraiCay;

    public PresenterLogicChiTietTraiCay(ViewChiTietTraiCay viewChiTietTraiCay){
        this.viewChiTietTraiCay=viewChiTietTraiCay;
        modelChiTietTraiCay = new ModelChiTietTraiCay();

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
}
