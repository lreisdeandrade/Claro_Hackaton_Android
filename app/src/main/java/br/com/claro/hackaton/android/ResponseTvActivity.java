package br.com.claro.hackaton.android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;

import org.androidannotations.annotations.EActivity;

/**
 * Created by Leandro on 22/02/2018.
 */

@EActivity(R.layout.activity_result_tv)
public class ResponseTvActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupWindowAnimations();
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(5000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(5000);
        getWindow().setReturnTransition(slide);
    }
}
