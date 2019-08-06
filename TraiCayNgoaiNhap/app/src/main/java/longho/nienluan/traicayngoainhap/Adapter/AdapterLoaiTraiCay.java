package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DSloaitraicay;
import longho.nienluan.traicayngoainhap.R;

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
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTenLTC = itemView.findViewById(R.id.txtTieuDeLoaiTraiCay);
            imgHinhLTC = itemView.findViewById(R.id.imHinhLoaiTraiCay);

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
        DSloaitraicay dSloaitraicay = dSloaitraicayList.get(position);
        holder.txtTenLTC.setText(dSloaitraicay.getTenLTC());
        Picasso.with(context).load(dSloaitraicay.getHinhLTC()).resize(120,120).into(holder.imgHinhLTC, new Callback() {
            @Override
            public void onSuccess() {
            }
            @Override
            public void onError() {

            }
        });
    }

    @Override
    public int getItemCount() {
        return dSloaitraicayList.size();
    }


}
