package longho.nienluan.traicayngoainhap.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment.FragmentDangKy;
import longho.nienluan.traicayngoainhap.View.DangNhap_DangKy.Fragment.FragmentDangNhap;

public class ViewPagerAdapterDangNhap extends FragmentPagerAdapter {
    public ViewPagerAdapterDangNhap(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 :
                FragmentDangNhap fragmentDangNhap = new FragmentDangNhap();
                return fragmentDangNhap;
            case 1 :
                FragmentDangKy fragmentDangKy = new FragmentDangKy();
                return fragmentDangKy;

            default: return null;
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Đăng nhập";
            case 1 :
                return "Đăng ký";

            default: return null;
        }

    }
}
