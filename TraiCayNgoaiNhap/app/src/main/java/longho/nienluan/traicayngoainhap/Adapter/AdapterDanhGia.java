package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DanhGia;
import longho.nienluan.traicayngoainhap.R;

public class AdapterDanhGia extends RecyclerView.Adapter<AdapterDanhGia.ViewHolderDanhGia>{

    List<DanhGia> danhGiaList;
    int limit;
    Context context;

    public AdapterDanhGia(Context context, List<DanhGia> danhGiaList, int limit){
        this.danhGiaList = danhGiaList;
        this.limit = limit;
        this.context = context;
    }

    public class ViewHolderDanhGia extends RecyclerView.ViewHolder {
        TextView txtTieuDeDanhGia,txtNoiDungDanhGia,txtDuocDanhGiaBoi;
        RatingBar rbDanhGia;

        public ViewHolderDanhGia(View itemView) {
            super(itemView);

            txtDuocDanhGiaBoi = itemView.findViewById(R.id.txtDuocDangBoi);
            txtNoiDungDanhGia = itemView.findViewById(R.id.txtNoiDungDanhGia);
            txtTieuDeDanhGia = itemView.findViewById(R.id.txtTieuDeDanhGia);
            rbDanhGia = itemView.findViewById(R.id.rbDanhGia);
        }
    }

    @NonNull
    @Override
    public ViewHolderDanhGia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_danhgia,parent,false);

        ViewHolderDanhGia viewHolderDanhGia = new ViewHolderDanhGia(view);

        return viewHolderDanhGia;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDanhGia holder, int position) {
        DanhGia danhGia = danhGiaList.get(position);

        holder.txtTieuDeDanhGia.setText(danhGia.getTieuDe());
        holder.txtNoiDungDanhGia.setText(danhGia.getNoiDungDG());
        holder.txtDuocDanhGiaBoi.setText("Được đánh giả bởi " + danhGia.getTenThietBi() + " ngày " + danhGia.getNgayDG());
        holder.rbDanhGia.setRating(danhGia.getSoSaoDG());
    }

    @Override
    public int getItemCount() {
        int dong = 0;
        if(danhGiaList.size() < limit){
            dong = danhGiaList.size();
        }else{
            if(limit !=0){
                dong = limit;
            }else{
                dong = danhGiaList.size();
            }
        }

        return dong;
    }

}

