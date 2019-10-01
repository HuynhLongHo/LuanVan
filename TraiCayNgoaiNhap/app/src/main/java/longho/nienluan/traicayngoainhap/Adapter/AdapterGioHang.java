package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.GioHang.ModelGioHang;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.R;

public class AdapterGioHang extends RecyclerView.Adapter<AdapterGioHang.ViewHolderGioHang> {
    Context context;
    List<traicay> traicayList;
    ModelGioHang modelGioHang;

    public AdapterGioHang(Context context, List<traicay> traicayList){
        this.context = context;
        this.traicayList = traicayList;
        modelGioHang = new ModelGioHang();
        modelGioHang.MoKetNoiSQL(context);
    }

    public class ViewHolderGioHang extends RecyclerView.ViewHolder {
        TextView txtTenTieuDeGioHang,txtGiaTienGioHang,txtSoLuongSanPham, txtGiamGiaGioHang;
        ImageView imHinhGioHang,imXoaSanPhamGioHang;
        ImageButton imTangSoLuongSPGioHang,imGiamSoLuongSPGioHang;

        public ViewHolderGioHang(View itemView) {
            super(itemView);

            txtTenTieuDeGioHang = itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaTienGioHang = itemView.findViewById(R.id.txtGiaGioHang);
            txtGiamGiaGioHang = itemView.findViewById(R.id.txtGiamGiaGioHang);
            txtSoLuongSanPham = itemView.findViewById(R.id.txtSoLuongSanPham);
            imHinhGioHang = itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = itemView.findViewById(R.id.imXoaSanPhamGioHang);
            imGiamSoLuongSPGioHang = itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            imTangSoLuongSPGioHang = itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
        }
    }

    @Override
    public ViewHolderGioHang onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.custom_layout_giohang,parent,false);

        ViewHolderGioHang viewHolderGioHang = new ViewHolderGioHang(view);

        return viewHolderGioHang;
    }

    @Override
    public void onBindViewHolder(final ViewHolderGioHang holder, final int position) {
        final traicay traicay = traicayList.get(position);

        holder.txtTenTieuDeGioHang.setText(traicay.getTenTraiCay());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String gia = numberFormat.format(traicay.getGiaBan()).toString();
        String giakm = numberFormat.format(traicay.getGiaKM());
        holder.txtGiaTienGioHang.setText(gia + " VNĐ ");

        if(traicay.getGiaKM()!=0){
            holder.txtGiaTienGioHang.setPaintFlags(holder.txtGiaTienGioHang.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtGiamGiaGioHang.setVisibility(View.VISIBLE);
            holder.txtGiamGiaGioHang.setText(giakm + " VNĐ ");
        }

        byte[] hinhsanpham = traicay.getHinhGioHang();
        Bitmap bitmapHinhGioHang = BitmapFactory.decodeByteArray(hinhsanpham,0,hinhsanpham.length);
        holder.imHinhGioHang.setImageBitmap(bitmapHinhGioHang);

        holder.imXoaSanPhamGioHang.setTag(traicay.getMaTraiCay());

        holder.imXoaSanPhamGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModelGioHang modelGioHang = new ModelGioHang();
                modelGioHang.MoKetNoiSQL(context);
                modelGioHang.XoaSanPhamTrongGioHang((int)v.getTag());
                traicayList.remove(position);
                notifyDataSetChanged();
            }
        });

        holder.txtSoLuongSanPham.setText(String.valueOf(traicay.getSoLuong()));

        holder.imTangSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
                int soluongtonkho = traicay.getSoLuongTon();

                if(soluong < soluongtonkho){
                    soluong++;
                }else{
                    Toast.makeText(context,"Số lượng sản phẩm bạn mua quá số lượng có trong kho của cửa hàng !",Toast.LENGTH_SHORT).show();
                }

                modelGioHang.CapNhatSoLuongSanPhamGioHang(traicay.getMaTraiCay(),soluong);
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
            }
        });

        holder.imGiamSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());

                if(soluong > 1){
                    soluong--;
                }
                modelGioHang.CapNhatSoLuongSanPhamGioHang(traicay.getMaTraiCay(),soluong);
                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
            }
        });

    }

    @Override
    public int getItemCount() {
        return traicayList.size();
    }
}
