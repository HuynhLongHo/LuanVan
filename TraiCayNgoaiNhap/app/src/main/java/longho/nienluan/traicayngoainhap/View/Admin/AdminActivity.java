package longho.nienluan.traicayngoainhap.View.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.Admin.DuyetDonHang.DuyetDonHangActivity;
import longho.nienluan.traicayngoainhap.View.Admin.ThongKe.ThongKeActivity;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView txtDuyetDonHang,txtThongKeTheoNam;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_admin);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtDuyetDonHang = findViewById(R.id.txtDuyetDonHang);
        txtThongKeTheoNam = findViewById(R.id.txtThongKeTheoNam);

        txtDuyetDonHang.setOnClickListener(this);
        txtThongKeTheoNam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtDuyetDonHang:
                Intent iDuyetDonHang = new Intent(this,DuyetDonHangActivity.class);
                startActivity(iDuyetDonHang);
                break;
            case R.id.txtThongKeTheoNam:
                Intent iThongKeTheoNam = new Intent(this,ThongKeActivity.class);
                startActivity(iThongKeTheoNam);
                break;
        }

    }
}
