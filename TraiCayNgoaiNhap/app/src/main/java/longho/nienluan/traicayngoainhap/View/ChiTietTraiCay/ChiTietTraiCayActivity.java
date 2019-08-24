package longho.nienluan.traicayngoainhap.View.ChiTietTraiCay;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterViewPagerSlider;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.ChiTietTraiCay.PresenterLogicChiTietTraiCay;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ChiTietTraiCayActivity extends AppCompatActivity implements ViewChiTietTraiCay,ViewPager.OnPageChangeListener {

    ViewPager viewPager;
    PresenterLogicChiTietTraiCay presenterLogicChiTietTraiCay;
    List<Fragment> fragmentList;
    TextView txtDots[];
    LinearLayout layoutDots;
    TextView txtTenTraiCay, txtGiaBan, txtTenNCC, txtDiaChiNCC;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_chitiettraicay);
        viewPager = findViewById(R.id.viewpagerSlider);
        layoutDots = findViewById(R.id.layoutDots);
        txtTenTraiCay = findViewById(R.id.txtTenTraiCay);
        txtGiaBan = findViewById(R.id.txtGiaBan);
        txtTenNCC = findViewById(R.id.txtTenNCC);
        txtDiaChiNCC = findViewById(R.id.txtDiaChiNCC);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        int matraicay = getIntent().getIntExtra("matraicay", 0);

        presenterLogicChiTietTraiCay = new PresenterLogicChiTietTraiCay(this);
        presenterLogicChiTietTraiCay.LayChiTietTraiCay(matraicay);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        return true;
    }

    @Override
    public void HienThiChiTietTraiCay(traicay traicay) {
        txtTenTraiCay.setText(traicay.getTenTraiCay());
        DecimalFormat formatter = new DecimalFormat("###,###");//định dạng tiền tệ
        String giaban = "Giá: " + formatter.format(traicay.getGiaBan()) + " VNĐ";
        txtGiaBan.setText(giaban);
        txtTenNCC.setText(traicay.getTenNCC());
        txtDiaChiNCC.setText(traicay.getDiaChiNCC());
    }

    @Override
    public void HienSliderTraiCay(String[] linkhinhtraicay) {

        fragmentList = new ArrayList<>();
        for(int i=0; i<linkhinhtraicay.length; i++){
            FragmentSliderChiTietTraiCay fragmentSliderChiTietTraiCay = new FragmentSliderChiTietTraiCay();
            Bundle bundle = new Bundle();
            bundle.putString("linkhinh",TrangChuActivity.SERVER + linkhinhtraicay[i]);
            fragmentSliderChiTietTraiCay.setArguments(bundle);

            fragmentList.add(fragmentSliderChiTietTraiCay);
        }
        AdapterViewPagerSlider adapterViewPagerSlider = new AdapterViewPagerSlider(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapterViewPagerSlider);
        adapterViewPagerSlider.notifyDataSetChanged();
        ThemDotSlider(0);
        viewPager.addOnPageChangeListener(this);
    }

    private void ThemDotSlider(int vitrihientai){
        txtDots = new TextView[fragmentList.size()];

        layoutDots.removeAllViews();
        for (int i=0 ; i<fragmentList.size(); i++){
            txtDots[i] = new TextView(this);
            txtDots[i].setText(Html.fromHtml("&#8226;"));
            txtDots[i].setTextSize(40);
            txtDots[i].setTextColor(getIdColor(R.color.colorSliderInActive));

            layoutDots.addView(txtDots[i]);
        }

        txtDots[vitrihientai].setTextColor(getIdColor(R.color.bgToolbar));
    }

    private int getIdColor(int idcolor){

        int color =0;
        if(Build.VERSION.SDK_INT > 21){
            color = ContextCompat.getColor(this,idcolor);
        }else{
            color = getResources().getColor(idcolor);
        }

        return color;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ThemDotSlider(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
