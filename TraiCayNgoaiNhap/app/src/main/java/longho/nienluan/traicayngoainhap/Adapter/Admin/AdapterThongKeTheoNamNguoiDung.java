package longho.nienluan.traicayngoainhap.Adapter.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.nguoidung;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.R;

public class AdapterThongKeTheoNamNguoiDung extends RecyclerView.Adapter<AdapterThongKeTheoNamNguoiDung.ViewHolderThongKeTheoNamNguoiDung>{

    List<nguoidung> nguoidungList;
    Context context;
    public AdapterThongKeTheoNamNguoiDung(Context context, List<nguoidung> nguoidungList){
        this.context = context;
        this.nguoidungList = nguoidungList;
    }
    public class ViewHolderThongKeTheoNamNguoiDung extends RecyclerView.ViewHolder {

        TextView txtMaguoiDung, txtTenNguoiDung, txtSoLanMua, TongTienMua;
        public ViewHolderThongKeTheoNamNguoiDung(View itemView) {
            super(itemView);
            txtMaguoiDung = itemView.findViewById(R.id.txtMaguoiDung);
            txtTenNguoiDung = itemView.findViewById(R.id.txtTenNguoiDung);
            txtSoLanMua = itemView.findViewById(R.id.txtSoLanMua);
            TongTienMua = itemView.findViewById(R.id.TongTienMua);
        }
    }

    @NonNull
    @Override
    public ViewHolderThongKeTheoNamNguoiDung onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_thongketheonam_nguoidung,parent,false);
        ViewHolderThongKeTheoNamNguoiDung viewHolderThongKeTheoNamNguoiDung = new ViewHolderThongKeTheoNamNguoiDung(view);
        return viewHolderThongKeTheoNamNguoiDung;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterThongKeTheoNamNguoiDung.ViewHolderThongKeTheoNamNguoiDung holder, int position) {

        nguoidung nguoidung = nguoidungList.get(position);
        holder.txtMaguoiDung.setText("Mã người dùng: " + nguoidung.getMaNguoiDung());
        holder.txtTenNguoiDung.setText("Tên : " + nguoidung.getTenNguoiDung());
        holder.txtSoLanMua.setText("Số lần mua: " + nguoidung.getSoLanMua());
        holder.TongTienMua.setText("Tổng tiền mua: " + nguoidung.getTongTienMua());

    }

    @Override
    public int getItemCount() {
        return nguoidungList.size();
    }

}
