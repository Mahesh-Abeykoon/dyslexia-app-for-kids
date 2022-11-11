package fhss.sjp.thesis.dyslexiaapp.videolesson.hodiya;

import android.widget.ImageButton;
import android.view.SurfaceHolder;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import fhss.sjp.thesis.dyslexiaapp.R;
import android.view.SurfaceView;

public class Hodiya1 extends AppCompatActivity implements SurfaceHolder.Callback {
    private SurfaceView surfaceV;
    private MediaPlayer videoPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hodiya1_screen);

        videoPlayer = MediaPlayer.create(this, R.raw.hodiya1);

        surfaceV = findViewById(R.id.surfaceView);
        surfaceV.setKeepScreenOn(true);

        SurfaceHolder holder = surfaceV.getHolder();
        holder.addCallback(this);
        holder.setFixedSize(400, 400);

        ImageButton play = findViewById(R.id.buttonPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlayer.start();
            }
        });

        ImageButton pause = findViewById(R.id.buttonPause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoPlayer.pause();
            }
        });

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        videoPlayer.setDisplay(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    protected void onPause() {
        super.onPause();

        if (videoPlayer != null) {
            videoPlayer.pause();
            videoPlayer.release();
            videoPlayer = null;
        }

    }
}