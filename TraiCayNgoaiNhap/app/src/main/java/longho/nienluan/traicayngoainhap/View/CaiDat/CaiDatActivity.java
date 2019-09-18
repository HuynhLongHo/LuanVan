package longho.nienluan.traicayngoainhap.View.CaiDat;

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
import longho.nienluan.traicayngoainhap.View.DoiMatKhau.DoiMatKhauActivity;
import longho.nienluan.traicayngoainhap.View.GoiYLayMatKhau.GoiYLayMatKhauActivity;

public class CaiDatActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtDoiMatKhau,txtGoiYLayMatKhau;
    Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_caidat);

        txtDoiMatKhau = findViewById(R.id.txtDoiMatKhau);
        txtGoiYLayMatKhau = findViewById(R.id.txtGoiYLayMatKhau);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtDoiMatKhau.setOnClickListener(this);
        txtGoiYLayMatKhau.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.txtDoiMatKhau:
                Intent iDoiMatKhau = new Intent(this,DoiMatKhauActivity.class);
                startActivity(iDoiMatKhau);
            case R.id.txtGoiYLayMatKhau:
                Intent iGoiY = new Intent(this,GoiYLayMatKhauActivity.class);
                startActivity(iGoiY);
        }
    }
}
