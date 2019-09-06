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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import longho.nienluan.traicayngoainhap.Adapter.AdapterKhuyenMai;
import longho.nienluan.traicayngoainhap.Model.ObjectClass.KhuyenMai;
import longho.nienluan.traicayngoainhap.Presenter.TrangChu.TrangChu_KhuyenMai.PresenterLogicKhuyenMai;
import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.ViewKhuyenMai;

public class FragmentChươngTrinhKhuyenMai extends Fragment implements ViewKhuyenMai {
    RecyclerView rcvDangKhuyenMai,rcvSapKhuyenMai,rcvTop10KhuyenMai;
    AdapterKhuyenMai adapterKhuyenMai;
    List<KhuyenMai> dangKhuyenMais, sapKhuyenMais,top10KhuyenMais;
    TextView txtSapKhuyenMai,txtDangKhuyenMai,txtTopKhuyenMai;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_khuyenmai, container, false);
        rcvDangKhuyenMai = view.findViewById(R.id.recycler_view_dangkhuyenmai);
        rcvSapKhuyenMai = view.findViewById(R.id.recycler_view_sapkhuyenmai);
        rcvTop10KhuyenMai = view.findViewById(R.id.recycler_view_top10khuyenmai);
        txtSapKhuyenMai = view.findViewById(R.id.txtSapKhuyenMai);
        txtDangKhuyenMai = view.findViewById(R.id.txtDangKhuyenMai);
        txtTopKhuyenMai = view.findViewById(R.id.txtTopKhuyenMai);
        dangKhuyenMais = new ArrayList<>();
        sapKhuyenMais = new ArrayList<>();
        top10KhuyenMais = new ArrayList<>();
        PresenterLogicKhuyenMai presenterLogicKhuyenMai = new PresenterLogicKhuyenMai(this);
        presenterLogicKhuyenMai.LayDanhSachDangKhuyenMai();
        presenterLogicKhuyenMai.LayDanhSachSapKhuyenMai();
        presenterLogicKhuyenMai.LayDanhSachTop10KhuyenMai();
        return view ;
    }

    @Override
    public void HienThiDanhSachDangKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        dangKhuyenMais = khuyenMaiList;
        if(khuyenMaiList.size()>0){
            txtDangKhuyenMai.setVisibility(View.VISIBLE);
            AdapterKhuyenMai recyclerViewAdapter = new AdapterKhuyenMai(getContext(),dangKhuyenMais);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
            rcvDangKhuyenMai.setLayoutManager(layoutManager);
            rcvDangKhuyenMai.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }
        else {
            txtDangKhuyenMai.setVisibility(View.GONE);
        }

    }

    @Override
    public void HienThiDanhSachSapKhuyenMai(List<KhuyenMai> khuyenMaiList) {
        sapKhuyenMais = khuyenMaiList;
        if(khuyenMaiList.size()>0){
            txtSapKhuyenMai.setVisibility(View.VISIBLE);
            AdapterKhuyenMai recyclerViewAdapter = new AdapterKhuyenMai(getContext(),sapKhuyenMais);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
            rcvSapKhuyenMai.setLayoutManager(layoutManager);
            rcvSapKhuyenMai.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }
        else{
            txtSapKhuyenMai.setVisibility(View.GONE);
        }

    }

    @Override
    public void HienThiDanhSachTop10KhuyenMai(List<KhuyenMai> khuyenMaiList) {
        top10KhuyenMais = khuyenMaiList;
        if(khuyenMaiList.size()>0){
            txtTopKhuyenMai.setVisibility(View.VISIBLE);
            AdapterKhuyenMai recyclerViewAdapter = new AdapterKhuyenMai(getContext(),top10KhuyenMais);
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),1,GridLayoutManager.HORIZONTAL,false);
            rcvTop10KhuyenMai.setLayoutManager(layoutManager);
            rcvTop10KhuyenMai.setAdapter(recyclerViewAdapter);
            recyclerViewAdapter.notifyDataSetChanged();
        }else {
            txtTopKhuyenMai.setVisibility(View.GONE);
        }

    }
}
