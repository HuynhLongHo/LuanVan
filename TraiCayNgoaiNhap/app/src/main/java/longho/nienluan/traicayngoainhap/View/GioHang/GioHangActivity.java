package longho.nienluan.traicayngoainhap.View.GioHang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterGioHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.GioHang.PresenterLogicGioHang;
import longho.nienluan.traicayngoainhap.R;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang {
    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_giohang);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerGioHang);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        btnMuaNgay = (Button) findViewById(R.id.btnMuaNgay);

        setSupportActionBar(toolbar);

        presenterLogicGioHang = new PresenterLogicGioHang(this);
        presenterLogicGioHang.LayDanhSachSanPhamTrongGioHang(this);

//        btnMuaNgay.setOnClickListener(this);
    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<traicay> traicayList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,traicayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
    }
}
