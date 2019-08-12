package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DSloaitraicay;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.HienThiTraiCayTheoDanhMucActivity;

public class AdapterLoaiTraiCay extends RecyclerView.Adapter<AdapterLoaiTraiCay.RecyclerViewHolder> {
    Context context;
    List<DSloaitraicay> dSloaitraicayList ;

    public AdapterLoaiTraiCay(Context context, List<DSloaitraicay> dSloaitraicays) {
        this.context = context;
        this.dSloaitraicayList = dSloaitraicays;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenLTC;
        ImageView imgHinhLTC;
        ProgressBar pgbLTC;
        LinearLayout lnLoaiTraiCay;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTenLTC = itemView.findViewById(R.id.txtTieuDeLoaiTraiCay);
            imgHinhLTC = itemView.findViewById(R.id.imHinhLoaiTraiCay);
            pgbLTC = itemView.findViewById(R.id.pgbLoaiTraiCay);
            lnLoaiTraiCay = itemView.findViewById(R.id.lnLoaiTraiCay);

        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_loaitraicay, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        final DSloaitraicay dSloaitraicay = dSloaitraicayList.get(position);
        holder.txtTenLTC.setText(dSloaitraicay.getTenLTC());
        Picasso.with(context).load(dSloaitraicay.getHinhLTC()).resize(120,120).into(holder.imgHinhLTC, new Callback() {
            @Override
            public void onSuccess() {
                holder.pgbLTC.setVisibility(View.GONE);
            }
            @Override
            public void onError() {

            }
        });

        holder.lnLoaiTraiCay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiTraiCayTheoDanhMuc = new Intent(context,HienThiTraiCayTheoDanhMucActivity.class);
                iHienThiTraiCayTheoDanhMuc.putExtra("MaLTC", dSloaitraicay.getMaLTC());
                iHienThiTraiCayTheoDanhMuc.putExtra("TenLTC", dSloaitraicay.getTenLTC());
                context.startActivity(iHienThiTraiCayTheoDanhMuc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dSloaitraicayList.size();
    }


}
