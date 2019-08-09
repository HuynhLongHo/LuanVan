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

import longho.nienluan.traicayngoainhap.Adapter.AdapterKhuyenMai;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.KhuyenMai;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_KhuyenMai.PresenterLogicKhuyenMai;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewKhuyenMai;

public class FragmentChươngTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {
    RecyclerView recyclerView;
    AdapterKhuyenMai adapterKhuyenMai;
    List<KhuyenMai> khuyenMais;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_chuongtrinhkhuyenmai, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_khuyenmai);
        khuyenMais = new ArrayList<>();
        PresenterLogicKhuyenMai presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachKhuyenMai();
        return view ;
    }

    @Override
    public void HienThiDanhSachKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        khuyenMais = khuyenMaiList;
        AdapterKhuyenMai recyclerViewAdapter = new AdapterKhuyenMai(getContext(),khuyenMais);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
