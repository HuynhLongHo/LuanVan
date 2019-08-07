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

import longho.nienluan.traicayngoainhap.Adapter.AdapterQuocGia;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.QuocGia;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_QuocGia.PresenterLogicQuocGia;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewQuocGia;

public class FragmentQuocGia extends Fragment implements ViewQuocGia {
    RecyclerView recyclerView;
    AdapterQuocGia adapterQuocGia;
    List<QuocGia> quocGias;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_quocgia, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_quocgia);
        quocGias = new ArrayList<>();
        PresenterLogicQuocGia presenterLogicQuocGia = new PresenterLogicQuocGia(this);
        presenterLogicQuocGia.LayDanhSachQuocGia();
        return view ;
    }

    @Override
    public void HienThiDanhSachQuocGia(List<QuocGia> quocGiaList) {
        quocGias = quocGiaList;
        AdapterQuocGia recyclerViewAdapter = new AdapterQuocGia(getContext(),quocGias);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
