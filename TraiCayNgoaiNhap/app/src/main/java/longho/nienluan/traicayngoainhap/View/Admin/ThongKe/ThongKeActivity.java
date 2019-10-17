package longho.nienluan.traicayngoainhap.View.Admin.ThongKe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.Admin.AdapterThongKeTheoNam;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ThongKe;
import longho.nienluan.traicayngoainhap.Presenter.Admin.PresenterLogicThongKe;
import longho.nienluan.traicayngoainhap.R;

public class ThongKeActivity extends AppCompatActivity implements ViewThongKe{

    Toolbar toolbar;
    PresenterLogicThongKe presenterLogicThongKe;
    RecyclerView recyclerThongKeTheoNam;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_admin_thongke);

        toolbar = findViewById(R.id.toolbar);
        recyclerThongKeTheoNam = findViewById(R.id.recyclerThongKeTheoNam);

        presenterLogicThongKe = new PresenterLogicThongKe(this);
        presenterLogicThongKe.LayDanhSachDonDatHangTheoNam();

        setSupportActionBar(toolbar);
    }

    @Override
    public void HienThiThongKe(List<ThongKe> thongKeList) {
        AdapterThongKeTheoNam adapterThongKeTheoNam = new AdapterThongKeTheoNam(this,thongKeList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerThongKeTheoNam.setLayoutManager(layoutManager);
        recyclerThongKeTheoNam.setAdapter(adapterThongKeTheoNam);
        adapterThongKeTheoNam.notifyDataSetChanged();
    }
}
