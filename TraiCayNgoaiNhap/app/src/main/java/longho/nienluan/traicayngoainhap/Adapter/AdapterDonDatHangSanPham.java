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
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class AdapterDonDatHangSanPham extends RecyclerView.Adapter<AdapterDonDatHangSanPham.ViewHolderDonDatHangSanPham> {

    List<ChiTietDDH> chiTietDDHList;
    Context context;

    public AdapterDonDatHangSanPham(Context context, List<ChiTietDDH> chiTietDDHList){
        this.chiTietDDHList = chiTietDDHList;
        this.context = context;
    }

    public class ViewHolderDonDatHangSanPham extends RecyclerView.ViewHolder {
        TextView txtGiaBan, txtSoLuong, txtTenTraicay;
        ImageView imTraiCay;
        public ViewHolderDonDatHangSanPham(View itemView) {
            super(itemView);
            txtSoLuong = itemView.findViewById(R.id.txtSoLuong);
            txtTenTraicay = itemView.findViewById(R.id.txtTenTraiCay);
            txtGiaBan = itemView.findViewById(R.id.txtGiaBan);
            imTraiCay = itemView.findViewById(R.id.imTraiCay);
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
        traicay traicay = chiTietDDH.getTraicay();
        DecimalFormat formatter = new DecimalFormat("###,###");//định dạng tiền tệ
        String sotien = formatter.format(traicay.getGiaBan()) + " VNĐ";

        holder.txtTenTraicay.setText(String.valueOf(traicay.getTenTraiCay()));
        holder.txtSoLuong.setText("Số lượng: " + String.valueOf(chiTietDDH.getSoLuongDat()));
        holder.txtGiaBan.setText(sotien);
        Picasso.with(context).load(traicay.getHinhTraiCay()).resize(120,120).into(holder.imTraiCay);
    }

    @Override
    public int getItemCount() {
        return chiTietDDHList.size();
    }

}
