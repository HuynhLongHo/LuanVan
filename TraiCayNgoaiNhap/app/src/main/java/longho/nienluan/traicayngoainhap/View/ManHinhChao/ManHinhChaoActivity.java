package longho.nienluan.traicayngoainhap.View.ManHinhChao;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import longho.nienluan.traicayngoainhap.R;
import longho.nienluan.traicayngoainhap.View.TrangChu.TrangChuActivity;

public class ManHinhChaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ẩn statusbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.layout_manhinhchao);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2500);
                }catch (Exception e){

                }finally {
                    Intent intent = new Intent(ManHinhChaoActivity.this,TrangChuActivity.class);
                    startActivity(intent);
                }
            }
        });
        thread.start();
    }
    //    sau khi vào MainActivity tắt activity này để ngăn trở về activity này
    protected void onPause(){
        super.onPause();
        finish();
    }
}
