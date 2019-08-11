package longho.nienluan.traicayngoainhap.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterNoiBat;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_NoiBat.PresenterLogicNoiBat;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewNoiBat;

public class FragmentNoiBat extends Fragment implements ViewNoiBat {
    RecyclerView rcvTopLuotMua,rcvTraiCayGiaRe,rcvTraiCayNgauNhien;
    AdapterNoiBat adapterTopLuotMua,adapterTraiCayGiaRe,adapterTraiCayNgauNhien;
    List<traicay> topLuotMuas,traicaygiares,traiCayNgauNhiens;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_noibat, container, false);
        rcvTopLuotMua = view.findViewById(R.id.rcvTopLuotMua);
        rcvTraiCayGiaRe = view.findViewById(R.id.rcvTraiCayGiaRe);
        rcvTraiCayNgauNhien = view.findViewById(R.id.rcvTraiCayNgauNhien);
        topLuotMuas = new ArrayList<>();
        traicaygiares = new ArrayList<>();
        traiCayNgauNhiens = new ArrayList<>();
        PresenterLogicNoiBat presenterLogicNoiBat = new PresenterLogicNoiBat(this);
        presenterLogicNoiBat.LayDanhSachTopTraiCayTheoLuotMua();
        presenterLogicNoiBat.LayDanhSachTraiCayGiaRe();
        presenterLogicNoiBat.LayDanhSachTraiCayNgauNhien();
        return view;
    }

    @Override
    public void HienThiDanhSachTopTraiCayTheoLuotMua(List<traicay> traicayList) {
        topLuotMuas = traicayList;
        adapterTopLuotMua = new AdapterNoiBat(getContext(),topLuotMuas);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcvTopLuotMua.setLayoutManager(layoutManager);
        rcvTopLuotMua.setAdapter(adapterTopLuotMua);
        adapterTopLuotMua.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachTraiCayGiaRe(List<traicay> traicayList) {
        traicaygiares = traicayList;
        adapterTraiCayGiaRe = new AdapterNoiBat(getContext(),traicaygiares);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcvTraiCayGiaRe.setLayoutManager(layoutManager);
        rcvTraiCayGiaRe.setAdapter(adapterTraiCayGiaRe);
        adapterTraiCayGiaRe.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachTraiCayNgauNhien(List<traicay> traicayList) {
        traiCayNgauNhiens = traicayList;
        adapterTraiCayNgauNhien = new AdapterNoiBat(getContext(),traiCayNgauNhiens);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        rcvTraiCayNgauNhien.setLayoutManager(layoutManager);
        rcvTraiCayNgauNhien.setAdapter(adapterTraiCayNgauNhien);
        adapterTraiCayNgauNhien.notifyDataSetChanged();
    }
}
