package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Presenter.Admin.PresenterLogicDuyetDonHang;
import longho.nienluan.traicayngoainhap.R;

public class AdapterDuyetDonHang extends RecyclerView.Adapter<AdapterDuyetDonHang.ViewHolderDuyetDonHang> {

    List<DonDatHang> donDatHangList;
    Context context;
    PresenterLogicDuyetDonHang presenterLogicDuyetDonHang;

    public AdapterDuyetDonHang(Context context, List<DonDatHang> donDatHangList){
        this.donDatHangList = donDatHangList;
        this.context = context;
        presenterLogicDuyetDonHang = new PresenterLogicDuyetDonHang();
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
    public void onBindViewHolder(@NonNull final ViewHolderDuyetDonHang holder, int position) {

        final DonDatHang donDatHang = donDatHangList.get(position);

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

        AdapterDuyetDonHangSanPham adapterDuyetDonHangSanPham =new AdapterDuyetDonHangSanPham(context,donDatHang.getChiTietDDHList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.rcvDanhSachTraiCayHD.setLayoutManager(layoutManager);
        holder.rcvDanhSachTraiCayHD.setAdapter(adapterDuyetDonHangSanPham);

        holder.chkDuyetDonHang.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    donDatHang.setTrangThaiGiao("Đã duyệt");
                    presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                }else{
                    donDatHang.setTrangThaiGiao("Chờ kiểm duyệt");
                    presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return donDatHangList.size();
    }

}
