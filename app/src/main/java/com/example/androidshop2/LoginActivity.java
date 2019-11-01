package com.example.androidshop2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    private TextView sup,login;
    private FrameLayout loginBtn;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        sup = findViewById(R.id.sup);
        login=findViewById(R.id.login);
        loginBtn=findViewById(R.id.login_btn);
        progressBar=findViewById(R.id.progressbar);
        sup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent it = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(it);
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateButtonWidth();
                fadeOutTextAndSetProgressDialog();
            }
        });
    }


    private void animateButtonWidth(){
        ValueAnimator anim = ValueAnimator.ofInt(loginBtn.getMeasuredWidth(),getFinalWidth());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value= (Integer) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams=loginBtn.getLayoutParams();
                layoutParams.width=value;
                loginBtn.requestLayout();

            }
        });
        anim.setDuration(250);
        anim.start();

    }

    private void fadeOutTextAndSetProgressDialog(){
        login.animate().alpha(0f).setDuration(250).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showProgressDialog();
            }
        }).start();
    }

    private void showProgressDialog() {
        progressBar.getIndeterminateDrawable().setColorFilter(Color.parseColor("#ffffff"), PorterDuff.Mode.SRC_IN);
        progressBar.setVisibility(View.VISIBLE);
    }


    private int getFinalWidth() {
        return (int) loginBtn.getHeight();
    }




}
