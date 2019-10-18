package longho.nienluan.traicayngoainhap.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterNoiBat;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_NoiBat.PresenterLogicNoiBat;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewNoiBat;

public class FragmentNoiBat extends Fragment implements ViewNoiBat {
    RecyclerView rcvTopLuotMua,rcvTraiCayGiaRe,rcvTraiCayNgauNhien,rcvTraiCayKhuyenMai;
    AdapterNoiBat adapterTopLuotMua,adapterTraiCayGiaRe,adapterTraiCayNgauNhien,adapterKhuyenMai;
    List<traicay> topLuotMuas,traicaygiares,traiCayNgauNhiens, traiCayKMs;
    TextView txtTieuDeKhuyenMai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_noibat, container, false);
        rcvTraiCayKhuyenMai = view.findViewById(R.id.rcvTraiCayKhuyenMai);
        rcvTopLuotMua = view.findViewById(R.id.rcvTopLuotMua);
        rcvTraiCayGiaRe = view.findViewById(R.id.rcvTraiCayGiaRe);
        rcvTraiCayNgauNhien = view.findViewById(R.id.rcvTraiCayNgauNhien);
        txtTieuDeKhuyenMai = view.findViewById(R.id.txtTieuDeKhuyenMai);
        topLuotMuas = new ArrayList<>();
        traicaygiares = new ArrayList<>();
        traiCayNgauNhiens = new ArrayList<>();
        traiCayKMs = new ArrayList<>();
        PresenterLogicNoiBat presenterLogicNoiBat = new PresenterLogicNoiBat(this);
        presenterLogicNoiBat.LayDanhSachTraiCayKhuyenMai();
        presenterLogicNoiBat.LayDanhSachTopTraiCayTheoLuotMua();
        presenterLogicNoiBat.LayDanhSachTraiCayGiaRe();
        presenterLogicNoiBat.LayDanhSachTraiCayNgauNhien();
        return view;
    }

    @Override
    public void HienThiDanhSachTraiCayKhuyenMai(List<traicay> traicayList) {
        traiCayKMs = traicayList;
        txtTieuDeKhuyenMai.setVisibility(View.VISIBLE);
        adapterKhuyenMai = new AdapterNoiBat(getContext(),R.layout.custom_item_noibat, traiCayKMs);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcvTraiCayKhuyenMai.setLayoutManager(layoutManager);
        rcvTraiCayKhuyenMai.setAdapter(adapterKhuyenMai);
        adapterKhuyenMai.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachTopTraiCayTheoLuotMua(List<traicay> traicayList) {
        topLuotMuas = traicayList;
        adapterTopLuotMua = new AdapterNoiBat(getContext(),R.layout.custom_item_noibat,topLuotMuas);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.HORIZONTAL,false);
        rcvTopLuotMua.setLayoutManager(layoutManager);
        rcvTopLuotMua.setAdapter(adapterTopLuotMua);
        adapterTopLuotMua.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachTraiCayGiaRe(List<traicay> traicayList) {
        traicaygiares = traicayList;
        adapterTraiCayGiaRe = new AdapterNoiBat(getContext(),R.layout.custom_item_noibat,traicaygiares);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.HORIZONTAL,false);
        rcvTraiCayGiaRe.setLayoutManager(layoutManager);
        rcvTraiCayGiaRe.setAdapter(adapterTraiCayGiaRe);
        adapterTraiCayGiaRe.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachTraiCayNgauNhien(List<traicay> traicayList) {
        traiCayNgauNhiens = traicayList;
        adapterTraiCayNgauNhien = new AdapterNoiBat(getContext(),R.layout.custom_item_noibat,traiCayNgauNhiens);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),2,LinearLayoutManager.HORIZONTAL,false);
        rcvTraiCayNgauNhien.setLayoutManager(layoutManager);
        rcvTraiCayNgauNhien.setAdapter(adapterTraiCayNgauNhien);
        adapterTraiCayNgauNhien.notifyDataSetChanged();
    }
}
