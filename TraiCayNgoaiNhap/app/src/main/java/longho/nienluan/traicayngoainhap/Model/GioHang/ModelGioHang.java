package longho.nienluan.traicayngoainhap.Model.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;

public class ModelGioHang {
    SQLiteDatabase database;

    public void MoKetNoiSQL(Context context){
        DataSanPham dataSanPham = new DataSanPham(context);
        database = dataSanPham.getWritableDatabase();
    }

    public boolean XoaSanPhamTrongGioHang(int masp){

        int kiemtra = database.delete(DataSanPham.TB_GIOHANG,DataSanPham.TB_GIOHANG_MASP + " = " + masp,null);
        if(kiemtra > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean CapNhatSoLuongSanPhamGioHang(int masp,int soluong){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,soluong);

        int id = database.update(DataSanPham.TB_GIOHANG,contentValues,DataSanPham.TB_GIOHANG_MASP + " = " + masp,null);
        if(id > 0){
            return true;
        }else{
            return false;
        }
    }

    public boolean ThemGioHang(traicay traicay){

        ContentValues contentValues = new ContentValues();
        contentValues.put(DataSanPham.TB_GIOHANG_MASP,traicay.getMaTraiCay());
        contentValues.put(DataSanPham.TB_GIOHANG_TENSP,traicay.getTenTraiCay());
        contentValues.put(DataSanPham.TB_GIOHANG_GIATIEN,traicay.getGiaBan());
        contentValues.put(DataSanPham.TB_GIOHANG_HINHANH,traicay.getHinhGioHang());
//        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONG,traicay.getSOLUONG());
//        contentValues.put(DataSanPham.TB_GIOHANG_SOLUONGTONKHO,traicay.getSoLuongTon());

        long id = database.insert(DataSanPham.TB_GIOHANG,null,contentValues);
        if(id > 0){
            return true;
        }else{
            return false;
        }

    }

    public List<traicay> LayDanhSachSanPhamTrongGioHang(){
        List<traicay> traicayList = new ArrayList<>();

        String truyvan = "SELECT * FROM " + DataSanPham.TB_GIOHANG;
        Cursor cursor = database.rawQuery(truyvan,null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int masp = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_MASP));
            String tensp = cursor.getString(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_TENSP));
            int giatien = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_GIATIEN));
            byte[] hinhanh = cursor.getBlob(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_HINHANH));
//            int soluong = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONG));
//            int soluongtonkho = cursor.getInt(cursor.getColumnIndex(DataSanPham.TB_GIOHANG_SOLUONGTONKHO));


            traicay traicay = new traicay();
            traicay.setMaTraiCay(masp);
            traicay.setTenTraiCay(tensp);
            traicay.setGiaBan(giatien);
            traicay.setHinhGioHang(hinhanh);
//            traicay.setSOLUONG(soluong);
//            traicay.setSOLUONGTONKHO(soluongtonkho);

            traicayList.add(traicay);
            cursor.moveToNext();
        }

        return traicayList;
    }
}
