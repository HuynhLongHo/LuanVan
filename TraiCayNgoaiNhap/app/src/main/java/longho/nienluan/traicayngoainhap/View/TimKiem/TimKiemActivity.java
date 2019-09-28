package longho.nienluan.traicayngoainhap.View.TimKiem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterTraiCayKhuyenMai;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.TimKiem.PresenterLogicTimKiem;
import longho.nienluan.traicayngoainhap.R;

public class TimKiemActivity extends AppCompatActivity implements ViewTimKiem, SearchView.OnQueryTextListener {

    Toolbar toolbar;
    RecyclerView recyclerView;
    PresenterLogicTimKiem presenterLogicTimKiem;
    TextView txtKhongTimThay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_timkiem);

        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerTimKiem);
        txtKhongTimThay = findViewById(R.id.txtKhongTimThay);

        setSupportActionBar(toolbar);

        presenterLogicTimKiem = new PresenterLogicTimKiem(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_timkiem,menu);
        MenuItem itSearch = menu.findItem(R.id.itSearch);
        SearchView searchView = (SearchView) itSearch.getActionView();
        searchView.setIconified(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public void TimKiemThanhCong(List<traicay> traicayList) {
        txtKhongTimThay.setVisibility(View.GONE);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        AdapterTraiCayKhuyenMai adapterTraiCayKhuyenMai = new AdapterTraiCayKhuyenMai(this,R.layout.custom_item_traicay_khuyenmai,traicayList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterTraiCayKhuyenMai);
        adapterTraiCayKhuyenMai.notifyDataSetChanged();
    }

    @Override
    public void TimKiemThatBai() {
        txtKhongTimThay.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenterLogicTimKiem.TimKiemSanPhamTheoTenSP(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
