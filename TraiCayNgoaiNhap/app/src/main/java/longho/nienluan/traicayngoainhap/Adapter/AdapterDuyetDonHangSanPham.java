package longho.nienluan.traicayngoainhap.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.ChiTietDDH;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.R;

public class AdapterDuyetDonHangSanPham extends RecyclerView.Adapter<AdapterDuyetDonHangSanPham.ViewHolderDuyetDonHangSanPham> {

    List<ChiTietDDH> chiTietDDHList;
    Context context;

    public AdapterDuyetDonHangSanPham(Context context, List<ChiTietDDH> chiTietDDHList){
        this.chiTietDDHList = chiTietDDHList;
        this.context = context;
    }

    public class ViewHolderDuyetDonHangSanPham extends RecyclerView.ViewHolder {
        TextView txtGiaBan, txtSoLuong, txtTenTraicay;
        ImageView imTraiCay;
        public ViewHolderDuyetDonHangSanPham(View itemView) {
            super(itemView);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtTenTraicay = itemView.findViewById(R.id.txtTenTraiCay);
            txtGiaBan = itemView.findViewById(R.id.txtGiaBan);
            imTraiCay = itemView.findViewById(R.id.imTraiCay);
        }
    }

    @NonNull
    @Override
    public AdapterDuyetDonHangSanPham.ViewHolderDuyetDonHangSanPham onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_dondathang_sanpham,parent,false);

        ViewHolderDuyetDonHangSanPham viewHolderDonDatHangSanPham = new ViewHolderDuyetDonHangSanPham(view);

        return viewHolderDonDatHangSanPham;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDuyetDonHangSanPham.ViewHolderDuyetDonHangSanPham holder, int position) {
        ChiTietDDH chiTietDDH = chiTietDDHList.get(position);
        traicay traicay = chiTietDDH.getTraicay();

        holder.txtTenTraicay.setText(String.valueOf(traicay.getTenTraiCay()));
        holder.txtSoLuong.setText("Số lượng: " + String.valueOf(chiTietDDH.getSoLuongDat()));
        holder.txtGiaBan.setText("Tồn kho: " + String.valueOf(traicay.getSoLuongTon()));
        Picasso.with(context).load(traicay.getHinhTraiCay()).resize(120,120).into(holder.imTraiCay);
    }

    @Override
    public int getItemCount() {
        return chiTietDDHList.size();
    }

}