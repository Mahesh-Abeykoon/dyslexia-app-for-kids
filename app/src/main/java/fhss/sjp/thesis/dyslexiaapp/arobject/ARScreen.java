package fhss.sjp.thesis.dyslexiaapp.arobject;

import fhss.sjp.thesis.dyslexiaapp.Function;
import fhss.sjp.thesis.dyslexiaapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;

public class ARScreen extends AppCompatActivity {

    private ArFragment fragmentAR;
    private Button buttonRemove;

    private ArrayList<Integer> imagePath = new ArrayList<Integer>();
    private ArrayList<String> namePath = new ArrayList<>();
    private ArrayList<String> modelName = new ArrayList<>();
    AnchorNode anchorNode;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_screen);

        fragmentAR = (ArFragment)getSupportFragmentManager().findFragmentById(R.id.fragment);
        buttonRemove = (Button)findViewById(R.id.remove);
        getImgObject();

        fragmentAR.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            ModelRenderable.builder()
                    .setSource(this,Uri.parse(Function.modelsfb))
                    .build()
                    .thenAccept(modelRenderable -> addModelsToScene(anchor,modelRenderable));
        });
        buttonRemove.setOnClickListener(view -> removeAnchorNode(anchorNode));
    }

    private void getImgObject() {

        //animals icon
        imagePath.add(R.drawable.bat);
        imagePath.add(R.drawable.bear);
        imagePath.add(R.drawable.lion);
        imagePath.add(R.drawable.cat);
        //items icon
        imagePath.add(R.drawable.table);
        imagePath.add(R.drawable.lamp);
        imagePath.add(R.drawable.odltv);
        imagePath.add(R.drawable.chair);

        //animal
        namePath.add("Bat");
        namePath.add("Bear");
        namePath.add("Lion");
        namePath.add("Cat");

        //items
        namePath.add("Table");
        namePath.add("Lamp");
        namePath.add("Old Tv");
        namePath.add("Chair");

        //animal sfb
        modelName.add("bat.sfb");
        modelName.add("bear.sfb");
        modelName.add("lion.sfb");
        modelName.add("cat.sfb");
        //items sfb
        modelName.add("table.sfb");
        modelName.add("lamp.sfb");
        modelName.add("tv.sfb");
        modelName.add("chair.sfb");

        initiateRecyclerview();
    }

    private void initiateRecyclerview() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(linearLayoutManager);

        RecyclerviewAdapter recyclerviewAdapter = new RecyclerviewAdapter(this,namePath,imagePath,modelName);
        recyclerView.setAdapter(recyclerviewAdapter);

    }

    private void addModelsToScene(Anchor anchor, ModelRenderable modelRenderable) {

        anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(fragmentAR.getTransformationSystem());

        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        fragmentAR.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }

    public void removeAnchorNode(AnchorNode nodeToRemove) {
        if (nodeToRemove != null) {
            fragmentAR.getArSceneView().getScene().removeChild(nodeToRemove);
            nodeToRemove.getAnchor().detach();
            nodeToRemove.setParent(null);
            nodeToRemove = null;
        }
    }
}
