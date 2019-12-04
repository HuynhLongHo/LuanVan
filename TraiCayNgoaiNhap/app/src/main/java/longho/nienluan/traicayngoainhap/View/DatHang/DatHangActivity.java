package longho.nienluan.traicayngoainhap.View.DatHang;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.ButterKnife;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.DatHang.PresenterLogicDatHang;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.MoMo.MoMoConstants;
import longho.nienluan.traicayngoainhap.View.MoMo.PaymentActivity;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;
import vn.momo.momo_partner.AppMoMoLib;
import vn.momo.momo_partner.MoMoParameterNamePayment;

public class DatHangActivity extends AppCompatActivity implements View.OnClickListener,ViewDatHang {
    Toolbar toolbar;
    EditText edTenNguoiNhan, edDiaChi, edSoDT, edMoTa;
    ImageButton imNhanTienKhiGiaoHang, imChuyenKhoan, imMoMo;
    TextView txtNhanTienKhiGiaoHang, txtChuyenKhoan, txtTongTien, tvMessage;
    String strMessage="";
    Button btnThanhToan;
    CheckBox cbThoaThuan;
    PresenterLogicDatHang presenterLogicDatHang;
    List<ChiTietDDH> chiTietDDHList = new ArrayList<>();
    int MaNguoiDung;
    int environment = 1;
    int tongtien = 0;
    int chonHinhThuc = 0;

    private String amount = "10000";
    private String fee = "0";
//    int environment = 0;//developer default
    private String merchantName = "LongHoFruit";
    private String merchantCode = "MOMOFHAL20191023";
    private String merchantNameLabel = "Nhà cung cấp";
    private String description = "Mua trái cây";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_dathang);

        MaNguoiDung = Integer.parseInt(getIntent().getStringExtra("MaNguoiDung"));
        toolbar = findViewById(R.id.toolbar);
        edTenNguoiNhan = findViewById(R.id.edTenNguoiNhan);
        edDiaChi = findViewById(R.id.edDiaChi);
        edSoDT = findViewById(R.id.edSoDT);
        edMoTa = findViewById(R.id.edMoTa);
        imNhanTienKhiGiaoHang = findViewById(R.id.imNhanTienKhiGiaoHang);
        imChuyenKhoan = findViewById(R.id.imChuyenKhoan);
        imMoMo = findViewById(R.id.imMoMo);
        btnThanhToan = findViewById(R.id.btnDatHang);
        txtNhanTienKhiGiaoHang = findViewById(R.id.txtNhanTienKhiGiaoHang);
        txtChuyenKhoan = findViewById(R.id.txtChuyenKhoan);
        txtTongTien = findViewById(R.id.txtTongTien);
        tvMessage = findViewById(R.id.tvMessage);
//
        presenterLogicDatHang = new PresenterLogicDatHang(this,this);
        presenterLogicDatHang.LayDanhSachSanPhamTrongGioHang();
        presenterLogicDatHang.LayThongTinNguoiDung(MaNguoiDung);

        setSupportActionBar(toolbar);
        txtTongTien.setText("Tổng tiền: " + tongtien + "đ");

        amount = String.valueOf(tongtien);

        if(environment == 0){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEBUG);
        }else if(environment == 1){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT);
        }else if(environment == 2){
            AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.PRODUCTION);
        }

        btnThanhToan.setOnClickListener(this);
        imNhanTienKhiGiaoHang.setOnClickListener(this);
        imChuyenKhoan.setOnClickListener(this);
        imMoMo.setOnClickListener(this);
        Toast.makeText(this, ""+tongtien, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btnDatHang:
                String tennguoinhan = edTenNguoiNhan.getText().toString();
                String sodt = edSoDT.getText().toString();
                String diachi = edDiaChi.getText().toString();
                String mota = edMoTa.getText().toString();
                if (tennguoinhan.trim().length() > 0 && sodt.trim().length() > 0 && diachi.trim().length() > 0) {
                    DonDatHang donDatHang = new DonDatHang();
                    donDatHang.setMaNguoiDung(MaNguoiDung);
                    donDatHang.setTenNguoiDatHang(tennguoinhan);
                    donDatHang.setSoDienThoaiDatHang(sodt);
                    donDatHang.setDiaChiDatHang(diachi);
                    donDatHang.setMoTa(mota);
                    if(strMessage.equals("Successful")){
                        donDatHang.setChuyenKhoan(1);
                        donDatHang.setTrangThaiGiao("Đã thanh toán");
                    }
                    else{
                        donDatHang.setChuyenKhoan(0);
                        donDatHang.setTrangThaiGiao("Chờ kiểm duyệt");
                    }

                    donDatHang.setChiTietDDHList(chiTietDDHList);
                    presenterLogicDatHang.ThemDDH(donDatHang);

                } else {
                    Toast.makeText(this, "Bạn chưa nhập đầy đủ thông tin !", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.imNhanTienKhiGiaoHang:
                ChonHinhThucGiaoHang(txtNhanTienKhiGiaoHang,txtChuyenKhoan);
                chonHinhThuc = 0;
                break;

            case R.id.imChuyenKhoan:
                ChonHinhThucGiaoHang(txtChuyenKhoan,txtNhanTienKhiGiaoHang);
                chonHinhThuc = 1;
                break;
            case R.id.imMoMo:
                chonHinhThuc = 1;
                requestPayment();
                break;
        }
    }

    private void requestPayment() {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);


        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put(MoMoParameterNamePayment.MERCHANT_NAME, merchantName);
        eventValue.put(MoMoParameterNamePayment.MERCHANT_CODE, merchantCode);
        eventValue.put(MoMoParameterNamePayment.AMOUNT, amount);
        eventValue.put(MoMoParameterNamePayment.DESCRIPTION, description);
        //client Optional
        eventValue.put(MoMoParameterNamePayment.FEE, fee);
        eventValue.put(MoMoParameterNamePayment.MERCHANT_NAME_LABEL, merchantNameLabel);

        eventValue.put(MoMoParameterNamePayment.REQUEST_ID,  merchantCode+"-"+ UUID.randomUUID().toString());
        eventValue.put(MoMoParameterNamePayment.PARTNER_CODE, "MOMOFHAL20191023");

        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "008");
            objExtraData.put("site_name", "CGV Cresent Mall");
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
            objExtraData.put("movie_format", "2D");
            objExtraData.put("ticket", "{\"ticket\":{\"01\":{\"type\":\"std\",\"price\":110000,\"qty\":3}}}");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put(MoMoParameterNamePayment.EXTRA_DATA, objExtraData.toString());
        eventValue.put(MoMoParameterNamePayment.REQUEST_TYPE, "payment");
        eventValue.put(MoMoParameterNamePayment.LANGUAGE, "vi");
        eventValue.put(MoMoParameterNamePayment.EXTRA, "");
        //Request momo app
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        AppMoMoLib.getInstance().trackEventResult(this,data);//request tracking result data
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if(data != null) {
                tvMessage.setVisibility(View.VISIBLE);
                strMessage = data.getStringExtra("message");
                if(data.getIntExtra("status", -1) == 0) {
                    tvMessage.setText("message: " + "Get token " + data.getStringExtra("message"));
//                    tvMessage.setText(R.string.success);

                    if(data.getStringExtra("data") != null && !data.getStringExtra("data").equals("")) {
                        // TODO:

                    } else {
                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                    }
                } else if(data.getIntExtra("status", -1) == 1) {
                    String message = data.getStringExtra("message") != null?data.getStringExtra("message"):"Thất bại";
                    tvMessage.setText("message: " + message);
                } else if(data.getIntExtra("status", -1) == 2) {
                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                } else {
                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                }
            } else {
                tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
            }
        } else {
            tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
        }
    }

    @Override
    public void DatHangThanhCong() {
        Toast.makeText(this, "Thành công!", Toast.LENGTH_SHORT).show();
        Intent iTrangChu = new Intent(DatHangActivity.this, TrangChuActivity.class);
        startActivity(iTrangChu);
    }

    @Override
    public void DatHangThatBai() {
        Toast.makeText(this, "Thất bại!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void LayDanhSachSanPhamTrongGioHang(List<traicay> traicayList) {
        for (int i = 0; i < traicayList.size(); i++) {
            ChiTietDDH chiTietDDH = new ChiTietDDH();
            chiTietDDH.setMaTraiCay(traicayList.get(i).getMaTraiCay());
            if(traicayList.get(i).getGiaKM() != 0){
                chiTietDDH.setGiaBanHD(traicayList.get(i).getGiaKM());
            }else{
                chiTietDDH.setGiaBanHD(traicayList.get(i).getGiaBan());
            }
            chiTietDDH.setSoLuongDat(traicayList.get(i).getSoLuong());
            chiTietDDHList.add(chiTietDDH);

            tongtien += chiTietDDH.getGiaBanHD()*chiTietDDH.getSoLuongDat();
        }
    }

    @Override
    public void HienThiThongTinNguoiDung(nguoidung nguoidung) {
        edTenNguoiNhan.setText(nguoidung.getTenNguoiDung());
        edDiaChi.setText(nguoidung.getDiaChiND());
        edSoDT.setText(nguoidung.getSoDienThoaiND());
    }

    private void ChonHinhThucGiaoHang(TextView txtDuocChon, TextView txtHuyChon){
        txtDuocChon.setTextColor(getIdColor(R.color.colorFacebook));
        txtHuyChon.setTextColor(getIdColor(R.color.colorBlack));
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
}
