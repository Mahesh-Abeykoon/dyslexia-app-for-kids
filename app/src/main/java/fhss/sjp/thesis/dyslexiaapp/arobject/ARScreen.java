package fhss.sjp.thesis.dyslexiaapp.arobject;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ux.ArFragment;

import androidx.appcompat.app.AppCompatActivity;


public class ARScreen extends AppCompatActivity {

    private ArFragment arFragment;
    private Button btnRemove;
    AnchorNode anchorNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_screen);

        arFragment = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        removeButton = (Button)findViewById(R.id.remove);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this,Uri.parse(Common.model))
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor,modelRenderable));
        });

        removeButton.setOnClickListener(view -> removeAnchorNode(anchorNode));
    }


    }

