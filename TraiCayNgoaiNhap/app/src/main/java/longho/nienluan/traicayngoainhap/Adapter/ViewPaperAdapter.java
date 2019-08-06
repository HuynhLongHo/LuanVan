package longho.nienluan.traicayngoainhap.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.View.TrangChu.Fragment.FragmentChươngTrinhKhuyenMai;
import longho.nienluan.traicayngoainhap.View.TrangChu.Fragment.FragmentLoaiTraiCay;
import longho.nienluan.traicayngoainhap.View.TrangChu.Fragment.FragmentNoiBat;
import longho.nienluan.traicayngoainhap.View.TrangChu.Fragment.FragmentQuocGia;
import longho.nienluan.traicayngoainhap.View.TrangChu.Fragment.FragmentTrongNuoc;

public class ViewPaperAdapter extends FragmentPagerAdapter {

    List<Fragment> listFragment = new ArrayList<Fragment>();
    List<String> listTitle = new ArrayList<String>();
    public ViewPaperAdapter (FragmentManager fragmentManager){
        super(fragmentManager);
        listFragment.add(new FragmentNoiBat());
        listFragment.add(new FragmentChươngTrinhKhuyenMai());
        listFragment.add(new FragmentLoaiTraiCay());
        listFragment.add(new FragmentQuocGia());
        listFragment.add(new FragmentTrongNuoc());

        listTitle.add("Nổi bật");
        listTitle.add("Khuyến mãi");
        listTitle.add("Loại trái cây");
        listTitle.add("Quốc gia");
        listTitle.add("Trong Nước");
    }
    @Override
    public Fragment getItem(int i) {

        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle.get(position);
    }
}
