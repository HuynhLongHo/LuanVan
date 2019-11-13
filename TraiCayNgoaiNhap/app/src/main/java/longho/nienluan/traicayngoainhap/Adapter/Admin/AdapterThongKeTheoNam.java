package longho.nienluan.traicayngoainhap.Adapter.Admin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.ThongKe;
import longho.nienluan.traicayngoainhap.R;

public class AdapterThongKeTheoNam extends RecyclerView.Adapter<AdapterThongKeTheoNam.ViewHolderThongKeTheoNam> {

    List<ThongKe> thongKeList;
    Context context;

    public AdapterThongKeTheoNam(Context context, List<ThongKe> thongKeList){
        this.thongKeList = thongKeList;
        this.context = context;
    }
    public class ViewHolderThongKeTheoNam extends RecyclerView.ViewHolder{
        TextView txtNam, txtTongSoLuongDonDatHang,txtTongDoanhThuNam;
        RecyclerView rcvTrangThaiGiao, rcvTraiCay, rcvNguoiDung;
        public ViewHolderThongKeTheoNam(View itemView) {
            super(itemView);
            txtNam = itemView.findViewById(R.id.txtNam);
            txtTongDoanhThuNam = itemView.findViewById(R.id.txtTongDoanhThuNam);
            txtTongSoLuongDonDatHang = itemView.findViewById(R.id.txtTongSoLuongDonDatHang);
            rcvTrangThaiGiao = itemView.findViewById(R.id.rcvTrangThaiGiao);
            rcvTraiCay = itemView.findViewById(R.id.rcvTraiCay);
            rcvNguoiDung = itemView.findViewById(R.id.rcvNguoiDung);
        }
    }

    @NonNull
    @Override
    public ViewHolderThongKeTheoNam onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_item_thongketheonam,parent,false);

        ViewHolderThongKeTheoNam viewHolderThongKeTheoNam = new ViewHolderThongKeTheoNam(view);

        return viewHolderThongKeTheoNam;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderThongKeTheoNam holder, int position) {
        int giaothanhcong = 0, dahuy = 0, doanhthu = 0;
        ThongKe thongKe = thongKeList.get(position);
        holder.txtNam.setText("Năm: " + String.valueOf(thongKe.getNam()));
        holder.txtTongDoanhThuNam.setText("Tổng doanh thu: " + String.valueOf(thongKe.getTongDoanhThuNam()));
        holder.txtTongSoLuongDonDatHang.setText(String.valueOf(thongKe.getSoLuongDDH() + " đơn đặt hàng bao gồm: "));

        AdapterThongKeTheoNamTrangThaiGiao adapterThongKeTheoNamTrangThaiGiao = new AdapterThongKeTheoNamTrangThaiGiao(context,thongKe.getDonDatHangList());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context,2,LinearLayoutManager.VERTICAL,false);
        holder.rcvTrangThaiGiao.setLayoutManager(layoutManager);
        holder.rcvTrangThaiGiao.setAdapter(adapterThongKeTheoNamTrangThaiGiao);

        AdapterThongKeTheoNamTraiCay adapterThongKeTheoNamTraiCay = new AdapterThongKeTheoNamTraiCay(context,thongKe.getTraicayList());
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.rcvTraiCay.setLayoutManager(layoutManager1);
        holder.rcvTraiCay.setAdapter(adapterThongKeTheoNamTraiCay);

        AdapterThongKeTheoNamNguoiDung adapterThongKeTheoNamNguoiDung = new AdapterThongKeTheoNamNguoiDung(context,thongKe.getNguoidungList());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.rcvNguoiDung.setLayoutManager(layoutManager2);
        holder.rcvNguoiDung.setAdapter(adapterThongKeTheoNamNguoiDung);

    }

    @Override
    public int getItemCount() {
        return thongKeList.size();
    }

}
