package longho.nienluan.traicayngoainhap.Adapter.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.DonDatHang;
import longho.nienluan.traicayngoainhap.R;

public class AdapterThongKeTheoNamTrangThaiGiao extends RecyclerView.Adapter<AdapterThongKeTheoNamTrangThaiGiao.ViewHolderThongKeTheoNamTrangThaiGiao>{

    List<DonDatHang> donDatHangList;
    Context context;
    public AdapterThongKeTheoNamTrangThaiGiao(Context context, List<DonDatHang> donDatHangList){
        this.context = context;
        this.donDatHangList = donDatHangList;
    }
    public class ViewHolderThongKeTheoNamTrangThaiGiao extends RecyclerView.ViewHolder {

        TextView txtTinhTrangGiao;
        public ViewHolderThongKeTheoNamTrangThaiGiao(View itemView) {
            super(itemView);
            txtTinhTrangGiao = itemView.findViewById(R.id.txtTinhTrangGiao);
        }
    }

    @NonNull
    @Override
    public ViewHolderThongKeTheoNamTrangThaiGiao onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_thongketheonam_trangthaigiao,parent,false);
        ViewHolderThongKeTheoNamTrangThaiGiao viewHolderThongKeTheoNamTrangThaiGiao = new ViewHolderThongKeTheoNamTrangThaiGiao(view);
        return viewHolderThongKeTheoNamTrangThaiGiao;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderThongKeTheoNamTrangThaiGiao holder, int position) {

        DonDatHang donDatHang = donDatHangList.get(position);
        holder.txtTinhTrangGiao.setText(donDatHang.getTrangThaiGiao() + ": " + String.valueOf(donDatHang.getSoLuongDonHang()));

    }

    @Override
    public int getItemCount() {
        return donDatHangList.size();
    }

}
