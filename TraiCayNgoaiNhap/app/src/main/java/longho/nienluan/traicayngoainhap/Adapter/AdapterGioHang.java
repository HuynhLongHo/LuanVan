package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
        TextView txtTenTieuDeGioHang,txtGiaTienGioHang,txtSoLuongSanPham;
        ImageView imHinhGioHang,imXoaSanPhamGioHang;
        ImageButton imTangSoLuongSPGioHang,imGiamSoLuongSPGioHang;

        public ViewHolderGioHang(View itemView) {
            super(itemView);

            txtTenTieuDeGioHang = (TextView) itemView.findViewById(R.id.txtTieuDeGioHang);
            txtGiaTienGioHang = (TextView) itemView.findViewById(R.id.txtGiaGioHang);
            txtSoLuongSanPham = (TextView) itemView.findViewById(R.id.txtSoLuongSanPham);
            imHinhGioHang = (ImageView) itemView.findViewById(R.id.imHinhGioHang);
            imXoaSanPhamGioHang = (ImageView) itemView.findViewById(R.id.imXoaSanPhamGioHang);
            imGiamSoLuongSPGioHang = (ImageButton) itemView.findViewById(R.id.imGiamSoLuongSPTrongGioHang);
            imTangSoLuongSPGioHang = (ImageButton) itemView.findViewById(R.id.imTangSoLuongSPTrongGioHang);
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
        holder.txtGiaTienGioHang.setText(gia + " VNĐ ");

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

//        holder.txtSoLuongSanPham.setText(String.valueOf(traicay.getSOLUONG()));
//
//        holder.imTangSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
//                int soluongtonkho = sanPham.getSOLUONGTONKHO();
//
//                if(soluong < soluongtonkho){
//                    soluong++;
//                }else{
//                    Toast.makeText(context,"Số lượng sản phẩm bạn mua quá số lượng có trong kho của cửa hàng !",Toast.LENGTH_SHORT).show();
//                }
//
//                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);
//                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
//            }
//        });
//
//        holder.imGiamSoLuongSPGioHang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int soluong = Integer.parseInt(holder.txtSoLuongSanPham.getText().toString());
//
//                if(soluong > 1){
//                    soluong--;
//                }
//                modelGioHang.CapNhatSoLuongSanPhamGioHang(sanPham.getMASP(),soluong);
//                holder.txtSoLuongSanPham.setText(String.valueOf(soluong));
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return traicayList.size();
    }
}
