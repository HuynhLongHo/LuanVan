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

import longho.nienluan.traicayngoainhap.Model.ObjectClass.KhuyenMai;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.HienThiTraiCayTheoDanhMuc.HienThiTraiCayKhuyenMaiActivity;

public class AdapterKhuyenMai extends RecyclerView.Adapter<AdapterKhuyenMai.RecyclerViewHolder> {

    Context context;
    List<KhuyenMai> khuyenMaiList;

    public AdapterKhuyenMai(Context context, List<KhuyenMai> khuyenMais) {
        this.context = context;
        this.khuyenMaiList = khuyenMais;
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        TextView txtTenKhuyenMai;
        TextView txtThoiGianKM;
        ImageView imgHinhKM;
        ProgressBar pgbKhuyenMai;
        LinearLayout lnKhuyenMai;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtTenKhuyenMai = itemView.findViewById(R.id.txtTenKhuyenMai);
            txtThoiGianKM = itemView.findViewById(R.id.txtThoiGianKM);
            imgHinhKM = itemView.findViewById(R.id.imgHinhKM);
            pgbKhuyenMai = itemView.findViewById(R.id.pgbKhuyenMai);
            lnKhuyenMai = itemView.findViewById(R.id.lnKhuyenMai);

        }
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom_item_khuyenmai, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder holder, int position) {
        final KhuyenMai khuyenMai = khuyenMaiList.get(position);
        holder.txtTenKhuyenMai.setText(khuyenMai.getTenKM());
        holder.txtThoiGianKM.setText("Từ " + khuyenMai.getNgayBatDau() + " đến " + khuyenMai.getNgayKetThuc());
        Picasso.with(context).load(khuyenMai.getHinhKM()).resize(350,250).into(holder.imgHinhKM, new Callback() {
            @Override
            public void onSuccess() {
                holder.pgbKhuyenMai.setVisibility(View.GONE);
            }
            @Override
            public void onError() {

            }
        });

        holder.lnKhuyenMai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iHienThiTraiCayKhuyenMai = new Intent(context,HienThiTraiCayKhuyenMaiActivity.class);
                iHienThiTraiCayKhuyenMai.putExtra("MaKM", khuyenMai.getMaKM());
                iHienThiTraiCayKhuyenMai.putExtra("TenKM", khuyenMai.getTenKM());
                iHienThiTraiCayKhuyenMai.putExtra("NgayBatDau", khuyenMai.getNgayBatDau());
                iHienThiTraiCayKhuyenMai.putExtra("NgayKetThuc", khuyenMai.getNgayKetThuc());
                context.startActivity(iHienThiTraiCayKhuyenMai);
            }
        });
    }


    @Override
    public int getItemCount() {
        return khuyenMaiList.size();
    }
}
