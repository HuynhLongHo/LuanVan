package longho.nienluan.traicayngoainhap.View.TrangChu;

import android.util.Log;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.LoaiTraiCay;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public interface ViewXuLyMenu {
     void HienThiDanhSachMenu(List<LoaiTraiCay> loaiTraiCayList);
     void DanhSachImage(List<traicay> traicayList);

}
