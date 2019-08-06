package longho.nienluan.traicayngoainhap.View.TrangChu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterLoaiTraiCay;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.DSloaitraicay;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_LoaiTraiCay.PresenterLogicLoaiTraiCay;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewLoaiTraiCay;

public class FragmentLoaiTraiCay extends Fragment implements ViewLoaiTraiCay{
    RecyclerView recyclerView;
    AdapterLoaiTraiCay recyclerViewAdapter;
    List<DSloaitraicay> dSloaitraicays;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_loaitraicay, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_loaitraicay);
        dSloaitraicays = new ArrayList<>();
        PresenterLogicLoaiTraiCay presenterLogicLoaiTraiCay = new PresenterLogicLoaiTraiCay(this);
        dSloaitraicays= new ArrayList<>();
        presenterLogicLoaiTraiCay.LayDanhSachLoaiTraiCay();
        return view ;
    }

    @Override
    public void HienThiDanhSachLoaiTraiCay(List<DSloaitraicay> dSloaitraicayList) {
        dSloaitraicays = dSloaitraicayList;
        AdapterLoaiTraiCay recyclerViewAdapter = new AdapterLoaiTraiCay(getContext(),dSloaitraicays);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
