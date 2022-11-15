package fhss.sjp.thesis.dyslexiaapp.arobject;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.ux.ArFragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ARScreen extends AppCompatActivity {

    private ArFragment arFragment;
    private Button btnRemove;
    AnchorNode anchorNode;

    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_screen);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        removeButton = (Button) findViewById(R.id.remove);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this, Uri.parse(Common.model))
                    .build()
                    .thenAccept(modelRenderable -> addModelToScene(anchor, modelRenderable));
        });

        removeButton.setOnClickListener(view -> removeAnchorNode(anchorNode));
    }

    private void getImages() {

        imagesPath.add(R.drawable.bat);
        imagesPath.add(R.drawable.bear);

        namesPath.add("Bat");
        namesPath.add("Bear");

        modelNames.add("bat.sfb");
        modelNames.add("bear.sfb");

        initaiteRecyclerview();
    }


    private void initaiteRecyclerview() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerviewAdapter rVadapter = new RecyclerviewAdapter(this, namesPath, imagesPath, modelNames);
        recyclerView.setAdapter(rVadapter);

    }

    private void addModelToScene(Anchor anchorr, ModelRenderable modRenderable) {

        anchorNode = new AnchorNode(anchorr);
        TransformableNode tfnode = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(modRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        tfnode.select();
    }

    public void removeAnchorNode(AnchorNode removeNode) {
        if (removeNode != null) {
            arFragment.getArSceneView().getScene().removeChild(removeNode);
            removeNode.getAnchor().detach();
            removeNode.setParent(null);
            removeNode = null;
        }
    }
}
