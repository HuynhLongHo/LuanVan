package longho.nienluan.traicayngoainhap.View.DanhGia;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterDanhGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.Presenter.DanhGia.PresenterLogicDanhGia;
import longho.nienluan.traicayngoainhap.R;

public class DanhSachDanhGiaActivity extends AppCompatActivity implements ViewDanhGia{
    RecyclerView recyclerDanhSachDanhGia;
    ProgressBar progressBar;
    int matraicay = 0;
    String tentraicay;
    PresenterLogicDanhGia presenterLogicDanhGia;
    List<DanhGia> tatcaDanhGia;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_danhsachdanhgia);
        recyclerDanhSachDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        matraicay = getIntent().getIntExtra("matraicay",0);
        tentraicay = getIntent().getStringExtra("tentraicay");

        recyclerDanhSachDanhGia = findViewById(R.id.recyclerDanhSachDanhGia);
        progressBar = findViewById(R.id.progress_bar);

        presenterLogicDanhGia = new PresenterLogicDanhGia(this);
        presenterLogicDanhGia.LayDanhSachDanhGiaTheoMa(matraicay,0);

    }

    @Override
    public void DanhGiaThanhCong() {

    }

    @Override
    public void DanhGiaThatBai() {

    }

    @Override
    public void HienThiDanhSachDanhGiaTheoMa(List<DanhGia> danhGiaList) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,danhGiaList,0);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhSachDanhGia.setLayoutManager(layoutManager);
        recyclerDanhSachDanhGia.setAdapter(adapterDanhGia);
        adapterDanhGia.notifyDataSetChanged();
    }
}
