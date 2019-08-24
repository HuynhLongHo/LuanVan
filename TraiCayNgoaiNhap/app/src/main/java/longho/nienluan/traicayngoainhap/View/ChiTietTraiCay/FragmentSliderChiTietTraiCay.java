package longho.nienluan.traicayngoainhap.View.ChiTietTraiCay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import longho.nienluan.traicayngoainhap.R;

public class FragmentSliderChiTietTraiCay extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_slider_hinhchitiettraicay,container,false);
        Bundle bundle=getArguments();
        String linkhinh = bundle.getString("linkhinh");
        ImageView imageView = view.findViewById(R.id.imHinhSlider);
        Picasso.with(getContext()).load(linkhinh).resize(400,420).into(imageView);
        return view;
    }
}
