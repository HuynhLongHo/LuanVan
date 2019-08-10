package longho.nienluan.traicayngoainhap.View.TrangChu;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.KhuyenMai;

public interface ViewKhuyenMai {
    void HienThiDanhSachDangKhuyenMai(List<KhuyenMai> khuyenMaiList);
    void HienThiDanhSachSapKhuyenMai(List<KhuyenMai> khuyenMaiList);
}
