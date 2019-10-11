package longho.nienluan.traicayngoainhap.View.TrangChu;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.ExpandAdapter;
import longho.nienluan.traicayngoainhap.Adapter.ViewPaperAdapter;
import longho.nienluan.traicayngoainhap.Presenter.ChiTietTraiCay.PresenterLogicChiTietTraiCay;
import longho.nienluan.traicayngoainhap.View.Admin.AdminActivity;
import longho.nienluan.traicayngoainhap.View.CaiDat.CaiDatActivity;
import longho.nienluan.traicayngoainhap.View.DonDatHang.DonDatHangActivity;
import longho.nienluan.traicayngoainhap.View.GioHang.GioHangActivity;
import longho.nienluan.traicayngoainhap.View.GuiEmail.EmailActivity;
import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.LoaiTraiCay;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.DangNhapActivity;
import longho.nienluan.traicayngoainhap.View.ThongTinNguoiDung.ThongTinNguoiDungActivity;
import longho.nienluan.traicayngoainhap.View.TimKiem.TimKiemActivity;

public class TrangChuActivity extends AppCompatActivity implements ViewXuLyMenu,AppBarLayout.OnOffsetChangedListener {

    public static final String SERVER_NAME = "http://10.2.56.155:80/NienLuan_LongHo/traicay.php";//B21_P4
    public static final String SERVER = "http://10.2.56.155:80/NienLuan_LongHo/Image/TraiCay/";//B21_P4
    
//    public static final String SERVER_NAME = "https://longho-traicay.000webhostapp.com/NienLuan_LongHo/traicay.php";
//    public static final String SERVER = "https://longho-traicay.000webhostapp.com/NienLuan_LongHo/Image/TraiCay/";


    Toolbar toolbar;
    TabLayout tab;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    ExpandableListView expandableListView;
    PresenterLogicXuLyMenu logicXuLyMenu;
    String tennguoidung ="";
    AccessToken accessToken;
    Menu menu;
    MenuItem itemDangNhap, itDonHangCuaToi, itCaiDat, itSearch, itAdmin;
    CollapsingToolbarLayout collapsingToolbarLayout;
    AppBarLayout appBarLayout;
    ModelDangNhap modelDangNhap;
    TextView txtGioHang;
    PresenterLogicChiTietTraiCay presenterLogicChiTietTraiCay;
    boolean onPause = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_trangchu);
        toolbar = findViewById(R.id.toolbar);
        tab = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewpager);
        drawerLayout = findViewById(R.id.drawerlayout);
        expandableListView = findViewById(R.id.epmenu);
        appBarLayout = findViewById(R.id.appbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle.syncState();

        ViewPaperAdapter adapter = new ViewPaperAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tab.setupWithViewPager(viewPager);

        logicXuLyMenu = new PresenterLogicXuLyMenu(this);
        logicXuLyMenu.LayDanhSachMenu();
        appBarLayout.addOnOffsetChangedListener(this);
        modelDangNhap = new ModelDangNhap();
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        //khởi tạo OptionMenu
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu=menu;

        MenuItem iGioHang = this.menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);
        presenterLogicChiTietTraiCay = new PresenterLogicChiTietTraiCay();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietTraiCay.DemSanPhamCoTrongGioHang(this)));
        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(TrangChuActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        itemDangNhap = menu.findItem(R.id.itDangNhap);
        itCaiDat = menu.findItem(R.id.itCaiDat);
        itDonHangCuaToi = menu.findItem(R.id.itDonHangCuaToi);
        itSearch = menu.findItem(R.id.itSearch);
        itAdmin = menu.findItem(R.id.itAdmin);
        accessToken = logicXuLyMenu.LayTokenNguoiDungFB();
        if(accessToken != null){
            GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    try {
                        tennguoidung = object.getString("name");

                        itemDangNhap.setTitle(tennguoidung);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });

            Bundle parameter = new Bundle();
            parameter.putString("fields","name");

            graphRequest.setParameters(parameter);
            graphRequest.executeAsync();

        }

        String tenquyen = modelDangNhap.LayTenQuyenTruyCap(this);
        if(tenquyen.equals("Admin")){
            itAdmin.setVisible(true);
        }

        String TenNguoiDung = modelDangNhap.LayCachedDangNhap(this);
        if(!TenNguoiDung.equals("")){
            itemDangNhap.setTitle(Html.fromHtml("<font color='#ff3824'>" + TenNguoiDung + "</font>"));
            itemDangNhap.setIcon(R.drawable.dangnhap);

            itCaiDat.setVisible(true);
            itDonHangCuaToi.setVisible(true);

        }

        if(accessToken!=null||!TenNguoiDung.equals("")){
            MenuItem menuITDangXuat = menu.findItem(R.id.itDangXuat);
            menuITDangXuat.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))

            return true;

        int id = item.getItemId();
        switch (id){
            case R.id.itDangNhap:
                if(accessToken==null&&modelDangNhap.LayCachedDangNhap(this).equals("")){
                    Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                }
                if(modelDangNhap.LayMaNguoiDung(this)!=""){
                    Intent iThongTinNguoiDung = new Intent(this,ThongTinNguoiDungActivity.class);
                    startActivity(iThongTinNguoiDung);
                }
                break;
            case R.id.itDangXuat:
                if(accessToken!=null){
                    LoginManager.getInstance().logOut();
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                if(!modelDangNhap.LayCachedDangNhap(this).equals("")) {
                    modelDangNhap.CapNhatCachedDangNhap(this, "","");
                    this.menu.clear();
                    this.onCreateOptionsMenu(this.menu);
                }
                break;
            case R.id.itEmailPhanHoi:
                    Intent intent = new Intent(this, EmailActivity.class);
                    startActivity(intent);
                break;
            case R.id.itDonHangCuaToi:
                if(modelDangNhap.LayMaNguoiDung(this)==""){
                    Toast.makeText(this, "Đăng nhập để quản lý đơn hàng", Toast.LENGTH_SHORT).show();
                    Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                }
                else {
                    Intent iDonHang = new Intent(this, DonDatHangActivity.class);
                    startActivity(iDonHang);
                }
                break;
            case R.id.itCaiDat:
                Intent iCaiDat = new Intent(this,CaiDatActivity.class);
                startActivity(iCaiDat);
                break;
            case R.id.itAdmin:
                Intent iAdmin = new Intent(this,AdminActivity.class);
                startActivity(iAdmin);
                break;
            case R.id.itSearch:
                Intent iTimKiem = new Intent(this, TimKiemActivity.class);
                startActivity(iTimKiem);
                break;
        }

        return true;
    }

    @Override
    public void HienThiDanhSachMenu(List<LoaiTraiCay> loaiTraiCayList) {
        Log.d("kiemtra", loaiTraiCayList.get(0).getTenLTC());
        ExpandAdapter expandAdapter = new ExpandAdapter(this,loaiTraiCayList);
        expandableListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
//        if(collapsingToolbarLayout.getHeight() + verticalOffset <=  1.5 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
//            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
//            linearLayout.animate().alpha(0).setDuration(200);
//
//            MenuItem itSearch = menu.findItem(R.id.itSearch);
//            itSearch.setVisible(true);
//
//        }else{
//            LinearLayout linearLayout = appBarLayout.findViewById(R.id.lnSearch);
//            linearLayout.animate().alpha(1).setDuration(200);
//            try{
//                MenuItem itSearch = menu.findItem(R.id.itSearch);
//                itSearch.setVisible(false);
//            }catch (Exception e){
//
//            }
//
//        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if(onPause){
            PresenterLogicChiTietTraiCay presenterLogicChiTietTraiCay = new PresenterLogicChiTietTraiCay();
            txtGioHang.setText(String.valueOf(presenterLogicChiTietTraiCay.DemSanPhamCoTrongGioHang(this)));

            menu.clear();
            onCreateOptionsMenu(menu);
        }

    }
    @Override
    protected void onPause() {
        super.onPause();

        onPause = true;
    }
}
