package longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterNoiBat;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc.PresenterLogicHienThiTraiCayTheoQuocGia;
import longho.nienluan.traicayngoainhap.R;

public class HienThiTraiCayTheoQuocGiaActivity extends AppCompatActivity implements ViewHienThiTraiCayTheoQuocGia,View.OnClickListener {

    int MaQG;
    RecyclerView rcvTraiCayTheoQuocGia;
    Toolbar toolbar;
    boolean danggrid = true;
    RecyclerView.LayoutManager layoutManager;
    Button btnThayDoiTrangThaiHienThi;
    AdapterNoiBat adapterTraiCay;
    PresenterLogicHienThiTraiCayTheoQuocGia presenterLogicHienThiTraiCayTheoQuocGia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_hienthitraicaytheoma);
        Intent intent = getIntent();
        MaQG = intent.getIntExtra("MaQG",0);
        String TenQG = intent.getStringExtra("TenQG");

        rcvTraiCayTheoQuocGia = findViewById(R.id.rcvHienThiTraiCayTheoDanhMuc);
        btnThayDoiTrangThaiHienThi = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbar);
        presenterLogicHienThiTraiCayTheoQuocGia = new PresenterLogicHienThiTraiCayTheoQuocGia(this);
        presenterLogicHienThiTraiCayTheoQuocGia.LayDanhSachTraiCayTheoQuocGia(MaQG);

        toolbar.setTitle(TenQG);
        setSupportActionBar(toolbar);
        btnThayDoiTrangThaiHienThi.setOnClickListener(this);
    }

    @Override
    public void HienThiDanhSachTraiCayTheoQuocGia(List<traicay> traicayList) {
        adapterTraiCay = new AdapterNoiBat(HienThiTraiCayTheoQuocGiaActivity.this,R.layout.custom_item_traicay,traicayList);
        if(danggrid){
            layoutManager = new GridLayoutManager(HienThiTraiCayTheoQuocGiaActivity.this,2);
            adapterTraiCay =  new AdapterNoiBat(HienThiTraiCayTheoQuocGiaActivity.this,R.layout.custom_item_traicay,traicayList);

        }else{
            layoutManager = new LinearLayoutManager(HienThiTraiCayTheoQuocGiaActivity.this);
            adapterTraiCay = new AdapterNoiBat(HienThiTraiCayTheoQuocGiaActivity.this,R.layout.custom_item_traicay_list,traicayList);

        }

        rcvTraiCayTheoQuocGia.setLayoutManager(layoutManager);
        rcvTraiCayTheoQuocGia.setAdapter(adapterTraiCay);
        adapterTraiCay.notifyDataSetChanged();
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
                presenterLogicHienThiTraiCayTheoQuocGia.LayDanhSachTraiCayTheoQuocGia(MaQG);
                break;
        }
    }
}
