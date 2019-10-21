package longho.nienluan.traicayngoainhap.CustomView;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import longho.nienluan.traicayngoainhap.R;

public class PasswordEditText extends android.support.design.widget.TextInputEditText {

   Drawable eye, eyeStrike;
    Boolean visible = false;
    Boolean useStrike = false;
    Boolean useValidate = false;
    Drawable drawable;
    int ALPHA = (int) (255 * .70f);
    String MATCHER_PATTERN = "((?=.*\\d)(?=.*[A-Z])(?=.*[a-z]).{6,20})"; // (?=.*\d)
    Pattern pattern;
    Matcher matcher;
    public PasswordEditText(Context context) {
        super(context);
        khoitao(null);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        khoitao(attrs);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        khoitao(attrs);
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//        khoitao(attrs);
//    }

    private void khoitao(AttributeSet attrs){

        this.pattern = Pattern.compile(MATCHER_PATTERN);
        if(attrs != null){
            TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs,R.styleable.PasswordEditText,0,0);
            this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike,false);
            //this.useValidate = array.getBoolean(R.styleable.PasswordEditText_useValidate,false);
        }
        eye = ContextCompat.getDrawable(getContext(), R.mipmap.ic_visibility_black_24dp).mutate();
        eyeStrike = ContextCompat.getDrawable(getContext(), R.mipmap.ic_visibility_off_black_24dp).mutate();

        if(this.useValidate){
            setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View view, boolean b) {
                    if(!b) {
                        String chuoi = getText().toString();
                        Log.d("somay","1");
                        TextInputLayout textInputLayout = findViewById(R.id.input_edMatKhauDK);
                        Log.d("somay","2");
                        matcher = pattern.matcher(chuoi);
                        Log.d("somay",matcher.toString());
                        Log.d("somay","3");
                        if(!matcher.matches()){

                            Log.d("somay","4");
                            textInputLayout.setErrorEnabled(true);

                            Log.d("somay","5");
                            textInputLayout.setError("Mật khẩu phải bao gồm 6 ký tự và một chữ hoa");

                            Log.d("somay","6");
                        }else{
                            Log.d("somay","7");
                            textInputLayout.setErrorEnabled(false);

                            Log.d("somay","8");
                            textInputLayout.setError("");

                            Log.d("somay","9");
                        }
                    }
                }
            });
        }
        caidat();
    }

    private void caidat(){
        setInputType(InputType.TYPE_CLASS_TEXT |(visible? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));
        Drawable[] drawables = getCompoundDrawables();
        drawable = useStrike && !visible? eyeStrike : eye;
        drawable.setAlpha(ALPHA);
        setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawable,drawables[3]); //đặt hình ảnh lại cho edittext
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - drawable.getBounds().width()) ){
            visible = !visible;
            caidat();
            invalidate();
        }
        return super.onTouchEvent(event);
    }
}
