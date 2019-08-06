package longho.nienluan.traicayngoainhap.Presenter.TrangChu.XuLyMenu;

import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import longho.nienluan.traicayngoainhap.DownloadInternet.DownloadJSON;
import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.LoaiTraiCay;
import longho.nienluan.traicayngoainhap.Model.TrangChu.XuLyJSONMenu.XuLyJSONMenu;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.XuLyMenu.IPresenterXuLyMenu;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewXuLyMenu;

public class PresenterLogicXuLyMenu implements IPresenterXuLyMenu {
    ViewXuLyMenu viewXuLyMenu;

    public PresenterLogicXuLyMenu(ViewXuLyMenu viewXuLyMenu){
        this.viewXuLyMenu = viewXuLyMenu;
    }
    @Override
    public void LayDanhSachMenu() {

        List<LoaiTraiCay> loaiTraiCayList;
        String dataJSON = "";
        List<HashMap<String, String>> atts = new ArrayList<>();
//        start lấy bằng GET
//        String duongdan = "http://10.2.57.122:80/NienLuan_LongHo/loaitraicay_traicay.php";
//        DownloadJSON downloadJSON = new DownloadJSON(duongdan);
//        end phương thức GET

//        start POST
        String duongdan = TrangChuActivity.SERVER_NAME;
        //String duongdan = "http://192.168.43.93:80/NienLuan_LongHo/loaitraicay_traicay.php";//Itel
        HashMap<String, String> hmHam = new HashMap<>();
        hmHam.put("ham","LayDanhSachMenu");
        HashMap<String, String> hmMaLTC = new HashMap<>();
        hmMaLTC.put("MaLTC","1");

        atts.add(hmHam);
        atts.add(hmMaLTC);

        DownloadJSON downloadJSON = new DownloadJSON(duongdan,atts);
//        end POST
        downloadJSON.execute();
        try {
            dataJSON = downloadJSON.get();
            XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
            loaiTraiCayList = xuLyJSONMenu.ParseJSONMenu(dataJSON);
            viewXuLyMenu.HienThiDanhSachMenu(loaiTraiCayList);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AccessToken LayTokenNguoiDungFB() {
        ModelDangNhap modelDangNhap = new ModelDangNhap();
        AccessToken accessToken = modelDangNhap.LayTokenFacebookHienTai();

        return accessToken;
    }


}
