package longho.nienluan.traicayngoainhap.Adapter.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.R;

public class AdapterThongKeTheoNamTraiCay extends RecyclerView.Adapter<AdapterThongKeTheoNamTraiCay.ViewHolderThongKeTheoNamTraiCay>{

    List<traicay> traicayList;
    Context context;
    public AdapterThongKeTheoNamTraiCay(Context context, List<traicay> traicayList){
        this.context = context;
        this.traicayList = traicayList;
    }
    public class ViewHolderThongKeTheoNamTraiCay extends RecyclerView.ViewHolder {

        TextView txtMaTraiCay, txtTenTraiCay, txtSoLuongBan, txtTongTienBan;
        ImageView imTraiCay;
        public ViewHolderThongKeTheoNamTraiCay(View itemView) {
            super(itemView);
            txtMaTraiCay = itemView.findViewById(R.id.txtMaTraiCay);
            txtTenTraiCay = itemView.findViewById(R.id.txtTenTraiCay);
            txtSoLuongBan = itemView.findViewById(R.id.txtSoLuongBan);
            txtTongTienBan = itemView.findViewById(R.id.txtTongTienBan);
            imTraiCay = itemView.findViewById(R.id.imTraiCay);
        }
    }

    @NonNull
    @Override
    public ViewHolderThongKeTheoNamTraiCay onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_thongketheonam_traicay,parent,false);
        ViewHolderThongKeTheoNamTraiCay viewHolderThongKeTheoNamTraiCay = new ViewHolderThongKeTheoNamTraiCay(view);
        return viewHolderThongKeTheoNamTraiCay;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderThongKeTheoNamTraiCay holder, int position) {

        traicay traicay = traicayList.get(position);
        holder.txtMaTraiCay.setText("Mã trái cây: " + traicay.getMaTraiCay());
        holder.txtTenTraiCay.setText(traicay.getTenTraiCay());
        holder.txtSoLuongBan.setText("Số lượng bán: " + traicay.getSoLuongBan());
        holder.txtTongTienBan.setText("Tổng tiền: " + traicay.getSoTienBan());
        Picasso.with(context).load(traicay.getHinhTraiCay()).resize(170,120).into(holder.imTraiCay);

    }

    @Override
    public int getItemCount() {
        return traicayList.size();
    }

}
