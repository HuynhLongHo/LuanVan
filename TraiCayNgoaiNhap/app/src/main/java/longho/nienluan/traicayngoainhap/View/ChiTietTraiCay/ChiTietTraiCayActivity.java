package longho.nienluan.traicayngoainhap.View.ChiTietTraiCay;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterDanhGia;
import longho.nienluan.traicayngoainhap.Adapter.AdapterViewPagerSlider;
import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.ChiTietTraiCay.PresenterLogicChiTietTraiCay;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.XuLyMenu.PresenterLogicXuLyMenu;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.DangNhapActivity;
import longho.nienluan.traicayngoainhap.View.DanhGia.DanhSachDanhGiaActivity;
import longho.nienluan.traicayngoainhap.View.DanhGia.ThemDanhGiaActivity;
import longho.nienluan.traicayngoainhap.View.DonDatHang.DonDatHangActivity;
import longho.nienluan.traicayngoainhap.View.GioHang.GioHangActivity;
import longho.nienluan.traicayngoainhap.View.DatHang.DatHangActivity;
import longho.nienluan.traicayngoainhap.View.GuiEmail.EmailActivity;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewXuLyMenu;

public class ChiTietTraiCayActivity extends AppCompatActivity implements ViewChiTietTraiCay,ViewPager.OnPageChangeListener,View.OnClickListener {

    ViewPager viewPager;
    PresenterLogicChiTietTraiCay presenterLogicChiTietTraiCay;
    List<Fragment> fragmentList;
    TextView txtDots[];
    LinearLayout layoutDots;
    TextView txtTenTraiCay, txtGiaBan, txtTenNCC, txtDiaChiNCC,txtThongTin,txtVietDanhGia, txtXemTatCaNhanXet, txtGioHang;
    Toolbar toolbar;
    ImageView imXemThemThongTin, imThemGioHang;
    boolean blXemThemThongTin = false;
    int matraicay;
    String tentraicay,tennguoidung;
    RecyclerView recyclerDanhGiaChiTiet;
    traicay traiCayGioHang;
    Button btnMuaNgay;
    MenuItem itemDangNhap;
    AccessToken accessToken;
    PresenterLogicXuLyMenu logicXuLyMenu;
    ModelDangNhap modelDangNhap;
    Menu menu;
    ViewXuLyMenu viewXuLyMenu;

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
        txtThongTin = findViewById(R.id.txtThongTinChiTiet);
        imXemThemThongTin = findViewById(R.id.imXemThemChiTiet);
        toolbar = findViewById(R.id.toolbar);
        txtVietDanhGia = findViewById(R.id.txtVietDanhGia);
        recyclerDanhGiaChiTiet = findViewById(R.id.recyclerDanhGiaChiTiet);
        txtXemTatCaNhanXet = findViewById(R.id.txtXemTatCaNhanXet);
        imThemGioHang = findViewById(R.id.imThemGioHang);
        btnMuaNgay = findViewById(R.id.btnMuaNgay);

        setSupportActionBar(toolbar);
        toolbar.setTitle("Chi tiết cản phẩm");

        matraicay = getIntent().getIntExtra("matraicay", 0);

        presenterLogicChiTietTraiCay = new PresenterLogicChiTietTraiCay(this);
        presenterLogicChiTietTraiCay.LayChiTietTraiCay(matraicay);
        presenterLogicChiTietTraiCay.LayDanhSachDanhGiaTheoMa(matraicay,0);
        txtVietDanhGia.setOnClickListener(this);
        txtXemTatCaNhanXet.setOnClickListener(this);
        imThemGioHang.setOnClickListener(this);
        btnMuaNgay.setOnClickListener(this);
        modelDangNhap = new ModelDangNhap();
        logicXuLyMenu = new PresenterLogicXuLyMenu(viewXuLyMenu);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //khởi tạo OptionMenu
        getMenuInflater().inflate(R.menu.menutrangchu,menu);
        this.menu=menu;

        MenuItem iGioHang = menu.findItem(R.id.itGioHang);
        View giaoDienCustomGioHang = MenuItemCompat.getActionView(iGioHang);
        txtGioHang = giaoDienCustomGioHang.findViewById(R.id.txtSoLuongSanPhamGioHang);

        txtGioHang.setText(String.valueOf(presenterLogicChiTietTraiCay.DemSanPhamCoTrongGioHang(this)));

        giaoDienCustomGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGioHang = new Intent(ChiTietTraiCayActivity.this, GioHangActivity.class);
                startActivity(iGioHang);
            }
        });

        itemDangNhap = menu.findItem(R.id.itDangNhap);
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

        String TenNguoiDung = modelDangNhap.LayCachedDangNhap(this);
        if(!TenNguoiDung.equals("")){
            itemDangNhap.setTitle(TenNguoiDung);
        }

        if(accessToken!=null||!TenNguoiDung.equals("")){
            MenuItem menuITDangXuat = menu.findItem(R.id.itDangXuat);
            menuITDangXuat.setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.itDangNhap:
                if(accessToken==null&&modelDangNhap.LayCachedDangNhap(this).equals("")){
                    Intent intentDangNhap = new Intent(this, DangNhapActivity.class);
                    startActivity(intentDangNhap);
                };
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
        }

        return true;
    }

    @Override
    public void HienThiChiTietTraiCay(traicay traicay) {

        traiCayGioHang = traicay;
        tentraicay = traicay.getTenTraiCay();
        txtTenTraiCay.setText(traicay.getTenTraiCay());
        DecimalFormat formatter = new DecimalFormat("###,###");//định dạng tiền tệ
        String giaban = "Giá: " + formatter.format(traicay.getGiaBan()) + " VNĐ";
        txtGiaBan.setText(giaban);
        txtTenNCC.setText(traicay.getTenNCC());
        txtDiaChiNCC.setText(traicay.getDiaChiNCC());
        final String thongtin = "Miêu tả: " + traicay.getMieuTaTC() + "\nThành phần dinh dưỡng: " + traicay.getThanhPhanDinhDuong() + "\nMôi trường trồng: " + traicay.getMoiTruongTrong();
        txtThongTin.setText(thongtin.substring(0,50));

        if(thongtin.length()<100){
            imXemThemThongTin.setVisibility(View.GONE);
        }
        {
            imXemThemThongTin.setVisibility(View.VISIBLE);
            imXemThemThongTin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    blXemThemThongTin = !blXemThemThongTin;
                    if(blXemThemThongTin){
                        txtThongTin.setText(thongtin);
                        imXemThemThongTin.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_up_black_24dp));
                    }
                    else {
                        txtThongTin.setText(thongtin.substring(0,50));
                        imXemThemThongTin.setImageDrawable(getHinhChiTiet(R.drawable.ic_keyboard_arrow_down_black_24dp));
                    }
                }
            });

        }
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

    @Override
    public void HienThiDanhGia(List<DanhGia> danhGiaList) {
        AdapterDanhGia adapterDanhGia = new AdapterDanhGia(this,danhGiaList,3);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerDanhGiaChiTiet.setLayoutManager(layoutManager);
        recyclerDanhGiaChiTiet.setAdapter(adapterDanhGia);
        adapterDanhGia.notifyDataSetChanged();
    }

    @Override
    public void ThemGioHangThanhCong() {
        Toast.makeText(this, "Giỏ hàng đã được thêm", Toast.LENGTH_SHORT).show();
        txtGioHang.setText(String.valueOf(presenterLogicChiTietTraiCay.DemSanPhamCoTrongGioHang(this)));
    }

    @Override
    public void ThemGioHangThatBai() {
        Toast.makeText(this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
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

    private Drawable getHinhChiTiet(int idDrawable){
        Drawable drawable;
        if(Build.VERSION.SDK_INT > 21){
            drawable = ContextCompat.getDrawable(this,idDrawable);
        }else{
            drawable = getResources().getDrawable(idDrawable);
        }

        return drawable;
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

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.txtVietDanhGia:
                Intent iThemDanhGia = new Intent(this, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("matraicay", matraicay);
                iThemDanhGia.putExtra("tentraicay", tentraicay);
                startActivity(iThemDanhGia);
                break;
            case R.id.txtXemTatCaNhanXet:
                Intent iXemTatCaNhanXet = new Intent(this, DanhSachDanhGiaActivity.class);
                iXemTatCaNhanXet.putExtra("matraicay", matraicay);
                iXemTatCaNhanXet.putExtra("tentraicay", tentraicay);
                startActivity(iXemTatCaNhanXet);
                break;
            case R.id.imThemGioHang:
                Fragment fragment = fragmentList.get(0);
                View view = fragment.getView();
                ImageView imageView = view.findViewById(R.id.imHinhSlider);
                Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[] hinhsanphamgiohang = byteArrayOutputStream.toByteArray();

                traiCayGioHang.setHinhGioHang(hinhsanphamgiohang);
                traiCayGioHang.setSoLuong(1);

                presenterLogicChiTietTraiCay.ThemGioHang(traiCayGioHang,this);
                break;
            case R.id.btnMuaNgay:
                Fragment fragment1 = fragmentList.get(0);
                View view1 = fragment1.getView();
                ImageView imageView1 = (ImageView) view1.findViewById(R.id.imHinhSlider);
                Bitmap bitmap1 = ((BitmapDrawable)imageView1.getDrawable()).getBitmap();

                ByteArrayOutputStream byteArrayOutputStream1 = new ByteArrayOutputStream();
                bitmap1.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream1);
                byte[] hinhsanphamgiohang1 = byteArrayOutputStream1.toByteArray();

                traiCayGioHang.setHinhGioHang(hinhsanphamgiohang1);
                traiCayGioHang.setSoLuong(1);

                presenterLogicChiTietTraiCay.ThemGioHang(traiCayGioHang,this);

                Intent iThanhToan = new Intent(ChiTietTraiCayActivity.this, DatHangActivity.class);
                startActivity(iThanhToan);
                break;
        }
    }
}
