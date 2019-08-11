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

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterNoiBat;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.traicay;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_NoiBat.PresenterLogicNoiBat;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewNoiBat;

public class FragmentNoiBat extends Fragment implements ViewNoiBat {
    RecyclerView recyclerView;
    AdapterNoiBat adapterNoiBat;
    List<traicay> traicays;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_noibat, container, false);
        recyclerView = view.findViewById(R.id.recyleNoiBat);
        traicays = new ArrayList<>();
        PresenterLogicNoiBat presenterLogicNoiBat = new PresenterLogicNoiBat(this);
        presenterLogicNoiBat.LayDanhSachTopTraiCay();
        return view;
    }

    @Override
    public void HienThiDanhSachTopTraiCay(List<traicay> traicayList) {
        traicays = traicayList;
        adapterNoiBat = new AdapterNoiBat(getContext(),traicays);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterNoiBat);
        adapterNoiBat.notifyDataSetChanged();
    }
}
