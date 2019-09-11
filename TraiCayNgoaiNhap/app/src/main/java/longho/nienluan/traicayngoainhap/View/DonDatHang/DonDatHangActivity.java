package longho.nienluan.traicayngoainhap.View.DonDatHang;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_dondathang);
        rcvDonDatHang = findViewById(R.id.recyclerDonDatHang);

        modelDangNhap = new ModelDangNhap();
        MaNguoiDung = modelDangNhap.LayMaNguoiDung(this);


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
}
