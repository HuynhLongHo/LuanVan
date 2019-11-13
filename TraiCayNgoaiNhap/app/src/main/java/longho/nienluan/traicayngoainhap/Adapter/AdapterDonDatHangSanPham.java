package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.DanhGia.ThemDanhGiaActivity;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class AdapterDonDatHangSanPham extends RecyclerView.Adapter<AdapterDonDatHangSanPham.ViewHolderDonDatHangSanPham> {

    List<ChiTietDDH> chiTietDDHList;
    Context context;
    String MaDDH;

    public AdapterDonDatHangSanPham(Context context,String MaDDH, List<ChiTietDDH> chiTietDDHList){
        this.chiTietDDHList = chiTietDDHList;
        this.context = context;
        this.MaDDH = MaDDH;
    }

    public class ViewHolderDonDatHangSanPham extends RecyclerView.ViewHolder {
        TextView txtGiaBan, txtSoLuong, txtTenTraicay;
        ImageView imTraiCay;
        LinearLayout lnTraiCay;
        public ViewHolderDonDatHangSanPham(View itemView) {
            super(itemView);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtTenTraicay = itemView.findViewById(R.id.txtTenTraiCay);
            txtGiaBan = itemView.findViewById(R.id.txtGiaBan);
            imTraiCay = itemView.findViewById(R.id.imTraiCay);
            lnTraiCay = itemView.findViewById(R.id.lnTraiCay);
        }
    }

    @NonNull
    @Override
    public ViewHolderDonDatHangSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_dondathang_sanpham,parent,false);

        ViewHolderDonDatHangSanPham viewHolderDonDatHangSanPham = new ViewHolderDonDatHangSanPham(view);

        return viewHolderDonDatHangSanPham;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDonDatHangSanPham holder, int position) {
        ChiTietDDH chiTietDDH = chiTietDDHList.get(position);
        final traicay traicay = chiTietDDH.getTraicay();
        DecimalFormat formatter = new DecimalFormat("###,###");//định dạng tiền tệ
        String sotien = formatter.format(chiTietDDH.getGiaBanHD()) + "đ";

        holder.txtTenTraicay.setText(String.valueOf(traicay.getTenTraiCay()));
        holder.txtSoLuong.setText("Số lượng: " + String.valueOf(chiTietDDH.getSoLuongDat()));
        holder.txtGiaBan.setText("Giá: " + sotien);
        Picasso.with(context).load(traicay.getHinhTraiCay()).resize(120,120).into(holder.imTraiCay);
        holder.lnTraiCay.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent iThemDanhGia = new Intent(context, ThemDanhGiaActivity.class);
                iThemDanhGia.putExtra("matraicay", traicay.getMaTraiCay());
                iThemDanhGia.putExtra("tentraicay", traicay.getTenTraiCay());
                iThemDanhGia.putExtra("maddh", MaDDH);
                context.startActivity(iThemDanhGia);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return chiTietDDHList.size();
    }

}
