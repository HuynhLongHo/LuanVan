package longho.nienluan.traicayngoainhap.View.Admin.DuyetDonHang;

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

import longho.nienluan.traicayngoainhap.Adapter.AdapterDuyetDonHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Presenter.Admin.PresenterLogicDuyetDonHang;
import longho.nienluan.traicayngoainhap.R;

public class DuyetDonHangActivity extends AppCompatActivity implements ViewDuyetDonHang{

    Toolbar toolbar;
    RecyclerView rcvDuyetDonHang;
    PresenterLogicDuyetDonHang presenterLogicDuyetDonHang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_adminduyetdonhang);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        rcvDuyetDonHang = findViewById(R.id.recyclerDuyetDonDatHang);

        presenterLogicDuyetDonHang = new PresenterLogicDuyetDonHang(this);
        presenterLogicDuyetDonHang.LayDanhSachDonHangChuaDuyet();


    }

    @Override
    public void HienThiDonHangChuaDuyet(List<DonDatHang> donDatHangList) {
        AdapterDuyetDonHang adapterDuyetDonHang = new AdapterDuyetDonHang(this,donDatHangList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvDuyetDonHang.setLayoutManager(layoutManager);
        rcvDuyetDonHang.setAdapter(adapterDuyetDonHang);
        adapterDuyetDonHang.notifyDataSetChanged();
    }

}
