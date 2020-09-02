package com.rujirakongsomran.fancyshowcaseview;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rujirakongsomran.fancyshowcaseview.databinding.ActivityMainBinding;

import me.toptas.fancyshowcase.FancyShowCaseView;
import me.toptas.fancyshowcase.OnViewInflateListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;

    FancyShowCaseView fancyShowCaseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnFocus.setOnClickListener(this);
        binding.btnNoFocus.setOnClickListener(this);
        binding.btnLarger.setOnClickListener(this);
        binding.btnColor.setOnClickListener(this);
        binding.btnLongerText.setOnClickListener(this);
        binding.btnCustomAnim.setOnClickListener(this);
        binding.btnCustomView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnNoFocus:
                new FancyShowCaseView.Builder(this)
                        .title("No Focus")
                        .build()
                        .show();
                break;
            case R.id.btnFocus:
                new FancyShowCaseView.Builder(this)
                        .title("Focus on view")
                        .focusOn(view)
                        .build()
                        .show();
                break;
            case R.id.btnLarger:
                new FancyShowCaseView.Builder(this)
                        .title("Focus on view with larger circle")
                        .focusOn(view)
                        .focusCircleRadiusFactor(2.0)
                        .build()
                        .show();
                break;
            case R.id.btnLongerText:
                new FancyShowCaseView.Builder(this)
                        .title("Focus on view with larger view")
                        .focusOn(view)
                        .titleStyle(0, Gravity.TOP)
                        .build()
                        .show();
                break;
            case R.id.btnColor:
                new FancyShowCaseView.Builder(this)
                        .title("Background Color")
                        .focusOn(view)
                        .backgroundColor(Color.parseColor("#333639"))
                        .titleStyle(R.style.MyTitleStyle, Gravity.TOP | Gravity.CENTER)
                        .build()
                        .show();
                break;

            case R.id.btnCustomAnim:
                showWithAnim(view);
                break;
            case R.id.btnCustomView:
                showWithCustomView(view);
                break;
        }

    }

    private void showWithCustomView(View view) {
        fancyShowCaseView = new FancyShowCaseView.Builder(this)
                .focusOn(view)
                .customView(R.layout.custom_view, new OnViewInflateListener() {
                    @Override
                    public void onViewInflated(View view) {
                        view.findViewById(R.id.btnClose).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                fancyShowCaseView.hide();
                            }
                        });
                    }
                }).closeOnTouch(false)
                .build();
        fancyShowCaseView.show();

    }

    private void showWithAnim(View view) {
        Animation enterAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_in_top);
        Animation exitAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_out_bottom);

        final FancyShowCaseView fancy = new FancyShowCaseView.Builder(this)
                .focusOn(view)
                .title("Custom enter and exit animations")
                .enterAnimation(enterAnimation)
                .exitAnimation(exitAnimation)
                .build();
        fancy.show();
        exitAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                fancy.removeView();
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}