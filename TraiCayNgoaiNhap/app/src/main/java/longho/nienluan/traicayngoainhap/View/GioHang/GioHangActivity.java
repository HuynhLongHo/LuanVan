package longho.nienluan.traicayngoainhap.View.GioHang;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterGioHang;
import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.GioHang.PresenterLogicGioHang;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.DangNhapActivity;
import longho.nienluan.traicayngoainhap.View.DatHang.DatHangActivity;

public class GioHangActivity extends AppCompatActivity implements ViewGioHang, View.OnClickListener {
    RecyclerView recyclerView;
    PresenterLogicGioHang presenterLogicGioHang;
    Toolbar toolbar;
    Button btnMuaNgay;
    ModelDangNhap modelDangNhap;

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

        btnMuaNgay.setOnClickListener(this);
        modelDangNhap = new ModelDangNhap();
    }

    @Override
    public void HienThiDanhSachSanPhamTrongGioHang(List<traicay> traicayList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterGioHang adapterGioHang = new AdapterGioHang(this,traicayList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterGioHang);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnMuaNgay:
                String MaNguoiDung = modelDangNhap.LayMaNguoiDung(this);
                if(MaNguoiDung!=""){
                    Intent iDatHang = new Intent(GioHangActivity.this, DatHangActivity.class);
                    iDatHang.putExtra("MaNguoiDung",MaNguoiDung);
                    startActivity(iDatHang);
                }
                else{
                    Toast.makeText(this, "Cần đăng nhập trước khi mua", Toast.LENGTH_SHORT).show();
                    Intent iDangNhap = new Intent(GioHangActivity.this,DangNhapActivity.class);
                    startActivity(iDangNhap);
                }

                break;
        }
    }
}
