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
    RecyclerView rcvDangKhuyenMai,rcvSapKhuyenMai;
    AdapterKhuyenMai adapterKhuyenMai;
    List<KhuyenMai> dangKhuyenMais, sapKhuyenMais;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_chuongtrinhkhuyenmai, container, false);
        rcvDangKhuyenMai = view.findViewById(R.id.recycler_view_dangkhuyenmai);
        rcvSapKhuyenMai = view.findViewById(R.id.recycler_view_sapkhuyenmai);
        dangKhuyenMais = new ArrayList<>();
        sapKhuyenMais = new ArrayList<>();
        PresenterLogicKhuyenMai presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachDangKhuyenMai();
        presenterLogicKhuyenMai.LayDanhSachSapKhuyenMai();
        return view ;
    }

    @Override
    public void HienThiDanhSachDangKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        dangKhuyenMais = khuyenMaiList;
        AdapterKhuyenMai recyclerViewAdapter = new AdapterKhuyenMai(getContext(),dangKhuyenMais);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        rcvDangKhuyenMai.setLayoutManager(layoutManager);
        rcvDangKhuyenMai.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void HienThiDanhSachSapKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        sapKhuyenMais = khuyenMaiList;
        AdapterKhuyenMai recyclerViewAdapter = new AdapterKhuyenMai(getContext(),sapKhuyenMais);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
        rcvSapKhuyenMai.setLayoutManager(layoutManager);
        rcvSapKhuyenMai.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}
