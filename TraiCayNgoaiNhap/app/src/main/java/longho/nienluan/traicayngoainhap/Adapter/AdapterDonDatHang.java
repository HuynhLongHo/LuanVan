package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.Admin.ModelDuyetDonHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Presenter.Admin.PresenterLogicDuyetDonHang;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DonDatHang.ViewDonDatHang;

public class AdapterDonDatHang extends RecyclerView.Adapter<AdapterDonDatHang.ViewHolderDonDatHang> {

    List<DonDatHang> donDatHangList;
    Context context;
    PresenterLogicDuyetDonHang presenterLogicDuyetDonHang;

    public AdapterDonDatHang(Context context, List<DonDatHang> donDatHangList){
        this.donDatHangList = donDatHangList;
        this.context = context;
        presenterLogicDuyetDonHang = new PresenterLogicDuyetDonHang();
    }

    public class ViewHolderDonDatHang extends RecyclerView.ViewHolder {

        TextView txtMoTa,txtMaDDH, txtNgayDat, txtNgayGiao, txtTrangThaiGiaoHang, txtTongTien;
        RecyclerView rcvDanhSachTraiCayHD;
        Button btnHuyDonDatHang;

        public ViewHolderDonDatHang(View itemView) {
            super(itemView);

            txtMoTa = itemView.findViewById(R.id.txtMoTa);
            txtMaDDH = itemView.findViewById(R.id.txtMaDDH);
            txtNgayDat = itemView.findViewById(R.id.txtNgayDat);
            txtNgayGiao = itemView.findViewById(R.id.txtNgayGiao);
            txtTongTien = itemView.findViewById(R.id.txtTongTien);
            txtTrangThaiGiaoHang = itemView.findViewById(R.id.txtTrangThaiGiaoHang);
            rcvDanhSachTraiCayHD = itemView.findViewById(R.id.rcvDanhSachTraiCayHD);
            btnHuyDonDatHang = itemView.findViewById(R.id.btnHuyDonDatHang);
        }
    }

    @NonNull
    @Override
    public ViewHolderDonDatHang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_dondathang,parent,false);

        ViewHolderDonDatHang viewHolderDonDatHang = new ViewHolderDonDatHang(view);

        return viewHolderDonDatHang;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolderDonDatHang holder, int position) {

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
        holder.txtMoTa.setText("Mô tả: " + donDatHang.getMoTa());
        holder.txtTrangThaiGiaoHang.setText("Trạng thái: " + donDatHang.getTrangThaiGiao());

        AdapterDonDatHangSanPham adapterDonDatHangSanPham =new AdapterDonDatHangSanPham(context,donDatHang.getChiTietDDHList());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.rcvDanhSachTraiCayHD.setLayoutManager(layoutManager);
        holder.rcvDanhSachTraiCayHD.setAdapter(adapterDonDatHangSanPham);
        if(donDatHang.getTrangThaiGiao().equals("Chờ kiểm duyệt")){
            holder.btnHuyDonDatHang.setVisibility(View.VISIBLE);
        } else{
            holder.btnHuyDonDatHang.setVisibility(View.GONE);
        }
        holder.btnHuyDonDatHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                donDatHang.setTrangThaiGiao("Đã hủy");
                presenterLogicDuyetDonHang.DuyetDonHang(donDatHang);
                holder.btnHuyDonDatHang.setText("Đã hủy");
                holder.btnHuyDonDatHang.setEnabled(false);
            }
        });
    }

    @Override
    public int getItemCount() {
        return donDatHangList.size();
    }


}
