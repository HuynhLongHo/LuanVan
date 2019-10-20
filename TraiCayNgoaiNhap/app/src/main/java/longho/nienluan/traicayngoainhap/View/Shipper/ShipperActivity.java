package longho.nienluan.traicayngoainhap.View.Shipper;

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

public class ShipperActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    TextView txtXacNhanDaGiao;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_shipper);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtXacNhanDaGiao = findViewById(R.id.txtXacNhanDaGiao);

        txtXacNhanDaGiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtXacNhanDaGiao:
                Intent intent = new Intent(this, DuyetDonHangActivity.class);
                startActivity(intent);
        }
    }
}
