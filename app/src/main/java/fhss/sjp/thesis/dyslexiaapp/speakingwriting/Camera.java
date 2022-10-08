package fhss.sjp.thesis.dyslexiaapp.speakingwriting;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.tts.TextToSpeech;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import fhss.sjp.thesis.dyslexiaapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.io.IOException;

import static android.Manifest.permission.CAMERA;

public class Camera extends AppCompatActivity {

    private TextView tView;
    private SurfaceView surfView;

    private CameraSource cameraSource;
    private TextRecognizer textRecognizer;

    private TextToSpeech ttSpeech;
    private String stringResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_screen);
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, PackageManager.PERMISSION_GRANTED);
        ttSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cameraSource.release();
    }

    private void textRecognizer() {
        textRecognizer = new TextRecognizer.Builder(getApplicationContext()).build();
        cameraSource = new CameraSource.Builder(getApplicationContext(), textRecognizer)
                .setRequestedPreviewSize(1280, 1024)
                .build();

        surfView = findViewById(R.id.surfaceView);

        surfView.getHolder().addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    cameraSource.start(surfView.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
            @Override
            public void release() {
            }

            @Override
            public void receiveDetections(Detector.Detections<TextBlock> detections) {

                SparseArray<TextBlock> sparseArray = detections.getDetectedItems();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0; i<sparseArray.size(); ++i){
                    TextBlock textBlock = sparseArray.valueAt(i);
                    if (textBlock != null && textBlock.getValue() !=null){
                        stringBuilder.append(textBlock.getValue() + " ");
                    }
                }

                final String stringText = stringBuilder.toString();

                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        stringResult = stringText;
                        resultObtained();
                    }
                });
            }
        });
    }

    private void resultObtained(){
        setContentView(R.layout.camera_screen);
        tView = findViewById(R.id.ocrTextView);
        tView.setText(stringResult);
        ttSpeech.speak(stringResult, TextToSpeech.QUEUE_FLUSH, null, null);
    }

    public void cameraButtonStart(View view){
        setContentView(R.layout.surfaceview_screen);
        textRecognizer();
    }
}