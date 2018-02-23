package br.com.claro.hackaton.android;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import br.com.claro.hackaton.nfcservice.model.DiagnosticoResponse;
import timber.log.Timber;

/**
 * Created by Leandro on 22/02/2018.
 */

@EActivity(R.layout.activity_result_tv)
public class ResponseTvActivity extends AppCompatActivity {

    int pStatus = 0;
    private Handler handler = new Handler();

    @ViewById
    ProgressBar progress;

    @ViewById
    TextView percent, statusTvLabel, statusTvSubLabel;

    @ViewById
    ImageView signalStatusImg;

    private DiagnosticoResponse diagnostic;


    @AfterViews
    protected void setupView() {
        setupPercent();
        setupWindowAnimations();
        diagnostic = (DiagnosticoResponse) getIntent().getSerializableExtra("diagnostic");

        Timber.d("diagnostic " + diagnostic.toString());

        setupScreen();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//    }

    private void setupScreen() {
        if (diagnostic.getBody().getCodigo() != 0) {
            setupScreenDecoderProblem();
        } else {
            setupScreenDecoderOK();
        }
    }

    private void setupScreenDecoderOK() {
        signalStatusImg.setBackgroundResource(R.drawable.signal_tv_on);

        statusTvLabel.setText(getString(R.string.tv_status_label_ok));
        statusTvSubLabel.setText(getString(R.string.tv_status_sub_label_ok));

    }

    private void setupScreenDecoderProblem() {
        signalStatusImg.setBackgroundResource(R.drawable.signal_tv_offline);
        statusTvLabel.setText(getString(R.string.tv_status_label_error));
        statusTvSubLabel.setText(getString(R.string.tv_status_sub_label_error));


    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(5000);
        getWindow().setEnterTransition(fade);

        Slide slide = new Slide();
        slide.setDuration(5000);
        getWindow().setReturnTransition(slide);
    }

    private void setupPercent() {
        new Thread(() -> {
            // TODO Auto-generated method stub
            while (pStatus < 100) {
                pStatus += 1;

                handler.post(() -> {
                    // TODO Auto-generated method stub
                    progress.setProgress(pStatus);
                    percent.setText(pStatus + "%");

                });
                try {
                    // Sleep for 200 milliseconds.
                    // Just to display the progress slowly
                    Thread.sleep(16); //thread will take approx 3 seconds to finish
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
