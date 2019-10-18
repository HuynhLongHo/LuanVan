package longho.nienluan.traicayngoainhap.View.DonDatHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterDonDatHang;
import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Presenter.DonDatHang.PresenterLogicDonDatHang;
import longho.nienluan.traicayngoainhap.R;

public class DonDatHangActivity extends AppCompatActivity implements ViewDonDatHang{

    PresenterLogicDonDatHang presenterLogicDonDatHang;
    ModelDangNhap modelDangNhap;
    String MaNguoiDung;
    RecyclerView rcvDonDatHang;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_dondathang);
        rcvDonDatHang = findViewById(R.id.recyclerDonDatHang);
        toolbar = findViewById(R.id.toolbar);

        modelDangNhap = new ModelDangNhap();
        MaNguoiDung = modelDangNhap.LayMaNguoiDung(this);

        toolbar.setTitle("Đơn đặt hàng của tôi");
        setSupportActionBar(toolbar);

        presenterLogicDonDatHang = new PresenterLogicDonDatHang(this);
        presenterLogicDonDatHang.LayDanhSachDonDatHang(MaNguoiDung);

    }

    @Override
    public void HienThiDanhSachDonDatHang(List<DonDatHang> donDatHangList) {
        AdapterDonDatHang adapterDonDatHang = new AdapterDonDatHang(this,donDatHangList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvDonDatHang.setLayoutManager(layoutManager);
        rcvDonDatHang.setAdapter(adapterDonDatHang);
        adapterDonDatHang.notifyDataSetChanged();
    }

    @Override
    public void HuyDonDatHang() {

    }
}
