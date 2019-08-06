package longho.nienluan.traicayngoainhap.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;

import longho.nienluan.traicayngoainhap.Model.ObjectClass.LoaiTraiCay;
import longho.nienluan.traicayngoainhap.Model.TrangChu.XuLyJSONMenu.XuLyJSONMenu;
import longho.nienluan.traicayngoainhap.R;

public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    List<LoaiTraiCay> loaiTraiCays;

    public ExpandAdapter(Context context, List<LoaiTraiCay> loaiTraiCays){
        this.context = context;
        this.loaiTraiCays = loaiTraiCays;
        XuLyJSONMenu xuLyJSONMenu = new XuLyJSONMenu();
        int count = loaiTraiCays.size();
        for(int i=0;i<count;i++){
            int maloaitraicay = loaiTraiCays.get(i).getMaLTC();
            loaiTraiCays.get(i).setListCon(xuLyJSONMenu.LayTraiCayTheoMaLoai(maloaitraicay));
        }


    }
    @Override
    public int getGroupCount() {
        return loaiTraiCays.size();
    }

    @Override
    public int getChildrenCount(int vitricha) {
        return loaiTraiCays.get(vitricha).getListCon().size();
    }

    @Override
    public Object getGroup(int vitricha) {
        return loaiTraiCays.get(vitricha);
    }

    @Override
    public Object getChild(int vitricha, int vitricon) {
        return loaiTraiCays.get(vitricha).getListCon().get(vitricon);
    }

    @Override
    public long getGroupId(int vitricha) {
        return loaiTraiCays.get(vitricha).getMaLTC();
    }

    @Override
    public long getChildId(int vitricha, int vitricon) {
        return loaiTraiCays.get(vitricha).getListCon().get(vitricon).getMaTraiCay();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int vitricha, boolean isExpanded, View View, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewgroupcha = layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup,false);
        TextView txt_tenloaitraicay = viewgroupcha.findViewById(R.id.txt_tenloaitraicay);
        txt_tenloaitraicay.setText(loaiTraiCays.get(vitricha).getTenLTC());
        return viewgroupcha;
    }

    @Override
    public View getChildView(int vitricha, int vitricon, boolean isExpanded, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewgroupcha = layoutInflater.inflate(R.layout.custom_layout_group_cha,viewGroup,false);
        TextView txt_tenloaitraicay = viewgroupcha.findViewById(R.id.txt_tenloaitraicay);
        txt_tenloaitraicay.setText(loaiTraiCays.get(vitricha).getListCon().get(vitricon).getTenTraiCay());
        return viewgroupcha;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
