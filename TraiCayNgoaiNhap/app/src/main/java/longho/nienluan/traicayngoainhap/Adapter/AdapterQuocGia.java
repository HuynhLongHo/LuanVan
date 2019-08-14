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
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.QuocGia;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.HienThiTraiCayTheoQuocGiaActivity;

public class AdapterQuocGia extends RecyclerView.Adapter<AdapterQuocGia.RecyclerViewHolder> {

    Context context;
    List<QuocGia> quocGiaList;

    public AdapterQuocGia(Context context, List<QuocGia> quocGias) {
        this.context = context;
        this.quocGiaList = quocGias;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenQuocGia;
        ImageView imgQuocKiQG;
        ProgressBar pgbQuocGia;
        LinearLayout lnQuocGia;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTenQuocGia = itemView.findViewById(R.id.txtTenQuocGia);
            imgQuocKiQG = itemView.findViewById(R.id.imgQuocKiQG);
            pgbQuocGia = itemView.findViewById(R.id.pgbQuocGia);
            lnQuocGia = itemView.findViewById(R.id.lnQuocGia);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_quocgia, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        final QuocGia quocGia = quocGiaList.get(position);
        holder.txtTenQuocGia.setText(quocGia.getTenQG());
        Picasso.with(context).load(quocGia.getQuocKiQG()).resize(200,120).into(holder.imgQuocKiQG, new Callback() {
            @Override
            public void onSuccess() {
                holder.pgbQuocGia.setVisibility(View.GONE);
            }
            @Override
            public void onError() {

            }
        });
        holder.lnQuocGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiTraiCayTheoQuocGia = new Intent(context,HienThiTraiCayTheoQuocGiaActivity.class);
                iHienThiTraiCayTheoQuocGia.putExtra("MaQG", quocGia.getMaQG());
                iHienThiTraiCayTheoQuocGia.putExtra("TenQG", quocGia.getTenQG());
                context.startActivity(iHienThiTraiCayTheoQuocGia);
            }
        });
    }


    @Override
    public int getItemCount() {
        return quocGiaList.size();
    }
}
