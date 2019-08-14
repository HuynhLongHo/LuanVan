package longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterNoiBat;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc.PresenterLogicHienThiTraiCayTheoDanhMuc;
import longho.nienluan.traicayngoainhap.R;

public class HienThiTraiCayTheoDanhMucActivity extends AppCompatActivity implements ViewHienThiTraiCayTheoDanhMuc, View.OnClickListener {

    RecyclerView rcvTraiCayTheoLoai;
    Button btnThayDoiTrangThaiHienThi;
    PresenterLogicHienThiTraiCayTheoDanhMuc presenterLogicHienThiTraiCayTheoDanhMuc;
    boolean danggrid = true;
    int MaLTC;
    RecyclerView.LayoutManager layoutManager;
    AdapterNoiBat adapterNoiBat;
    android.support.v7.widget.Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_hienthitraicaytheoma);
        Intent intent = getIntent();
        MaLTC = intent.getIntExtra("MaLTC",0);
        String TenLTC = intent.getStringExtra("TenLTC");

        rcvTraiCayTheoLoai = findViewById(R.id.rcvHienThiTraiCayTheoDanhMuc);
        btnThayDoiTrangThaiHienThi = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbar);
        presenterLogicHienThiTraiCayTheoDanhMuc = new PresenterLogicHienThiTraiCayTheoDanhMuc(this);
        presenterLogicHienThiTraiCayTheoDanhMuc.LayDanhSachTraiCayTheoMaLoai(MaLTC);

        toolbar.setTitle(TenLTC);
        setSupportActionBar(toolbar);
        btnThayDoiTrangThaiHienThi.setOnClickListener(this);
    }

    @Override
    public void HienThiDanhSachTraiCayTheoMaLoai(List<traicay> traicayList) {
        adapterNoiBat = new AdapterNoiBat(HienThiTraiCayTheoDanhMucActivity.this,R.layout.custom_item_traicay,traicayList);
        if(danggrid){
            layoutManager = new GridLayoutManager(HienThiTraiCayTheoDanhMucActivity.this,2);
            adapterNoiBat =  new AdapterNoiBat(HienThiTraiCayTheoDanhMucActivity.this,R.layout.custom_item_traicay,traicayList);

        }else{
            layoutManager = new LinearLayoutManager(HienThiTraiCayTheoDanhMucActivity.this);
            adapterNoiBat = new AdapterNoiBat(HienThiTraiCayTheoDanhMucActivity.this,R.layout.custom_item_traicay_list,traicayList);

        }

        rcvTraiCayTheoLoai.setLayoutManager(layoutManager);
        rcvTraiCayTheoLoai.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachTraiCay() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnThayDoiTrangThaiRecycler:
                danggrid = !danggrid;
                presenterLogicHienThiTraiCayTheoDanhMuc.LayDanhSachTraiCayTheoMaLoai(MaLTC);
                break;
        }
    }
}
