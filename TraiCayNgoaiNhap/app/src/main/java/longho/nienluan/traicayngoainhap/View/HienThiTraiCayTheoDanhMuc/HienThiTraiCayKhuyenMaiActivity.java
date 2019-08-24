package longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterTraiCayKhuyenMai;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.HienThiTraiCayTheoDanhMuc.PresenterLogicHienThiTraiCayKhuyenMai;
import longho.nienluan.traicayngoainhap.R;

public class HienThiTraiCayKhuyenMaiActivity extends AppCompatActivity implements ViewHienThiTraiCayKhuyenMai {
    int MaKM;
    String TenKM,NgayBatDau,NgayKetThuc;
    RecyclerView rcvHienThiTraiCayKhuyenMai;
    Button btnThayDoiTrangThaiHienThi;
    Toolbar toolbar;
    PresenterLogicHienThiTraiCayKhuyenMai presenterLogicHienThiTraiCayKhuyenMai;
    AdapterTraiCayKhuyenMai adapterTraiCayKhuyenMai;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_hienthitraicaytheoma);
        Intent intent = getIntent();
        MaKM = intent.getIntExtra("MaKM",0);
        TenKM = intent.getStringExtra("TenKM");
        NgayBatDau = intent.getStringExtra("NgayBatDau");
        NgayKetThuc = intent.getStringExtra("NgayKetThuc");

        rcvHienThiTraiCayKhuyenMai = findViewById(R.id.rcvHienThiTraiCayTheoDanhMuc);
        btnThayDoiTrangThaiHienThi = findViewById(R.id.btnThayDoiTrangThaiRecycler);
        toolbar = findViewById(R.id.toolbar);
        presenterLogicHienThiTraiCayKhuyenMai= new PresenterLogicHienThiTraiCayKhuyenMai(this);
        presenterLogicHienThiTraiCayKhuyenMai.LayDanhSachTraiCayKhuyenMai(MaKM);
        toolbar.setTitle(TenKM);
        setSupportActionBar(toolbar);

    }

    @Override
    public void HienThiDanhSachTraiCayKhuyenMai(List<traicay> traicayList) {
        adapterTraiCayKhuyenMai = new AdapterTraiCayKhuyenMai(HienThiTraiCayKhuyenMaiActivity.this,R.layout.custom_item_traicay_khuyenmai,traicayList);
        layoutManager = new LinearLayoutManager(HienThiTraiCayKhuyenMaiActivity.this,LinearLayoutManager.VERTICAL,false);
        adapterTraiCayKhuyenMai =  new AdapterTraiCayKhuyenMai(HienThiTraiCayKhuyenMaiActivity.this,R.layout.custom_item_traicay_khuyenmai,traicayList);
        rcvHienThiTraiCayKhuyenMai.setLayoutManager(layoutManager);
        rcvHienThiTraiCayKhuyenMai.setAdapter(adapterTraiCayKhuyenMai);
        adapterTraiCayKhuyenMai.notifyDataSetChanged();
    }

    @Override
    public void LoiHienThiDanhSachTraiCay() {

    }
}
