package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.DangNhap_DangKy.ModelDangNhap;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Presenter.Admin.PresenterLogicDuyetDonHang;
import longho.nienluan.traicayngoainhap.R;

public class AdapterDuyetDonHang extends RecyclerView.Adapter<AdapterDuyetDonHang.ViewHolderDuyetDonHang> {

    List<DonDatHang> donDatHangList;
    Context context;
    PresenterLogicDuyetDonHang presenterLogicDuyetDonHang;
    private SparseBooleanArray itemStateArray;
    ModelDangNhap modelDangNhap;
    int manguoiduyet;
    String tenquyen;

    public AdapterDuyetDonHang(Context context, List<DonDatHang> donDatHangList){
        this.donDatHangList = donDatHangList;
        this.context = context;
        presenterLogicDuyetDonHang = new PresenterLogicDuyetDonHang();
        itemStateArray = new SparseBooleanArray();
        modelDangNhap = new ModelDangNhap();
        manguoiduyet = Integer.parseInt(modelDangNhap.LayMaNguoiDung(context));
        tenquyen = modelDangNhap.LayTenQuyenTruyCap(context);
    }


    public class ViewHolderDuyetDonHang extends RecyclerView.ViewHolder {
        TextView txtMaDDH, txtNgayDat, txtNgayGiao, txtTrangThaiGiaoHang, txtTongTien;
        CheckBox chkDuyetDonHang;
        LinearLayout lnDuyetDonHang;
        RecyclerView rcvDanhSachTraiCayHD;
        public ViewHolderDuyetDonHang(View itemView) {
            super(itemView);
            txtMaDDH = itemView.findViewById(R.id.txtMaDDH);
            txtNgayDat = itemView.findViewById(R.id.txtNgayDat);
            txtNgayGiao = itemView.findViewById(R.id.txtNgayGiao);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            txtTrangThaiGiaoHang = itemView.findViewById(R.id.txtTrangThaiGiaoHang);
            chkDuyetDonHang = itemView.findViewById(R.id.chkDuyetDonHang);
            rcvDanhSachTraiCayHD = itemView.findViewById(R.id.rcvDanhSachTraiCayHD);
            lnDuyetDonHang = itemView.findViewById(R.id.lnDuyetDonHang);
        }
        void bind(int position) {
            // use the sparse boolean array to check
            if (!itemStateArray.get(position, false)) {
                chkDuyetDonHang.setChecked(false);}
            else {
                chkDuyetDonHang.setChecked(true);
            }
        }
    }
    @NonNull
    @Override
    public ViewHolderDuyetDonHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_duyetdonhang,parent,false);

        ViewHolderDuyetDonHang viewHolderDuyetDonHang = new ViewHolderDuyetDonHang(view);

        return viewHolderDuyetDonHang;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDuyetDonHang holder, final int position) {

        final DonDatHang donDatHang = donDatHangList.get(position);

        holder.bind(position);

        List<ChiTietDDH> chiTietDDHList = new ArrayList<>();
        chiTietDDHList = donDatHang.getChiTietDDHList();
        int tongHoaDon = 0;
        int sosp = chiTietDDHList.size();
        for (int i = 0; i<sosp; i++){
            tongHoaDon += chiTietDDHList.get(i).getGiaBanHD()*chiTietDDHList.get(i).getSoLuongDat();
        }
        DecimalFormat formatter = new DecimalFormat("###,###");//định dạng tiền tệ
        String sotien = formatter.format(tongHoaDon) + "đ";

        holder.txtTongTien.setText("Tổng Tiền: " + sotien);
        holder.txtMaDDH.setText("Mã đơn hàng: " + String.valueOf(donDatHang.getMaDDH()));
        holder.txtNgayDat.setText("Ngày đặt: " + donDatHang.getNgayDat());
        holder.txtNgayGiao.setText("Dự kiến ngày giao: " + donDatHang.getNgayGiao());
        holder.txtTrangThaiGiaoHang.setText("Trạng thái: " + donDatHang.getTrangThaiGiao());

        if(tenquyen.equals("Admin")){
            AdapterDuyetDonHangSanPham adapterDuyetDonHangSanPham =new AdapterDuyetDonHangSanPham(context,donDatHang.getChiTietDDHList());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            holder.rcvDanhSachTraiCayHD.setLayoutManager(layoutManager);
            holder.rcvDanhSachTraiCayHD.setAdapter(adapterDuyetDonHangSanPham);
        }
        if(tenquyen.equals("Shipper")){
            AdapterDuyetDonHangSanPham adapterDonDatHangSanPham =new AdapterDuyetDonHangSanPham(context,donDatHang.getChiTietDDHList());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
            holder.rcvDanhSachTraiCayHD.setLayoutManager(layoutManager);
            holder.rcvDanhSachTraiCayHD.setAdapter(adapterDonDatHangSanPham);
        }


        holder.chkDuyetDonHang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!itemStateArray.get(position,false)){
                    holder.chkDuyetDonHang.setChecked(true);
                    itemStateArray.put(position,true);
                    if(tenquyen.equals("Admin")){
                        donDatHang.setTrangThaiGiao("Đã duyệt");
                        donDatHang.setMaNguoiDuyet(manguoiduyet);
                        presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                    }
                    if(tenquyen.equals("Shipper")){
                        donDatHang.setTrangThaiGiao("Đã giao");
                        donDatHang.setMaNguoiGiao(manguoiduyet);
                        presenterLogicDuyetDonHang.GiaoDonHang(donDatHang);
                    }
                }
                else{
                    holder.chkDuyetDonHang.setChecked(false);
                    itemStateArray.put(position,false);
                    if(tenquyen.equals("Admin")){
                        donDatHang.setTrangThaiGiao("Chờ kiểm duyệt");
                        donDatHang.setMaNguoiDuyet(-1);
                        presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                    }
                    if(tenquyen.equals("Shipper")){
                        donDatHang.setTrangThaiGiao("Đã duyệt");
                        donDatHang.setMaNguoiGiao(-1);
                        presenterLogicDuyetDonHang.GiaoDonHang(donDatHang);
                    }
                }
            }
        });
        holder.lnDuyetDonHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!itemStateArray.get(position,false)){
                    holder.chkDuyetDonHang.setChecked(true);
                    itemStateArray.put(position,true);
                    if(tenquyen.equals("Admin")){
                        donDatHang.setTrangThaiGiao("Đã duyệt");
                        donDatHang.setMaNguoiDuyet(manguoiduyet);
                        presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                    }
                    if(tenquyen.equals("Shipper")){
                        donDatHang.setTrangThaiGiao("Đã giao");
                        donDatHang.setMaNguoiGiao(manguoiduyet);
                        presenterLogicDuyetDonHang.GiaoDonHang(donDatHang);
                    }
                }
                else{
                    holder.chkDuyetDonHang.setChecked(false);
                    itemStateArray.put(position,false);
                    if(tenquyen.equals("Admin")){
                        donDatHang.setTrangThaiGiao("Chờ kiểm duyệt");
                        donDatHang.setMaNguoiDuyet(-1);
                        presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                    }
                    if(tenquyen.equals("Shipper")){
                        donDatHang.setTrangThaiGiao("Đã duyệt");
                        donDatHang.setMaNguoiGiao(-1);
                        presenterLogicDuyetDonHang.GiaoDonHang(donDatHang);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return donDatHangList.size();
    }

}
