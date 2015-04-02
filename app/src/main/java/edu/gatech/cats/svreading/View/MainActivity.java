package edu.gatech.cats.svreading.View;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.wikitude.architect.ArchitectView;

import java.io.IOException;

import edu.gatech.cats.svreading.R;

public class MainActivity extends Activity {
    /**
     * Holds the Wikitude SDK ArchitectView (where the camera, markers, etc. are rendered)
     */
    private ArchitectView architectView;

    private static final String WIKITUDE_SDK_KEY = "CSU+/8HIRGJh0bAqaYTpkp6qcnyfV5yEnOIFVEE/5T1N8k84kMJz8puHjhA+V+r7xGnizLw1CVt5/qYuvexwfmZeFwsOyuxetU5AC9F1s5/hzLKDNJOn0GBq8OhfpLnd3Ne3GO+b0ZOJDLmA75x/wWZXvmVtRBMblI94O2oBWmdTYWx0ZWRfX78Y6eenrSsW2xQDO7MkObj6INrJiZOQduDwZ0LtT5Ry85jU0FqBo+SkRvQOJYwYHzDBL00ca4zbDTGeqwgzDTG52hbWDCjvcZ6AH/BIZPxQUm4hNaRV6HRpLXs7AgWfEIpZ9W9r7o3oQq7gxGvkVhEkggE3XMEvFrWai9NXDgCHSeSx3MjVUxjIvHRj81ZsqEhrzq8jlDsO4525BIK+/zJOQfZc+M91apCtlrenLo7+Md1zK/+bgweB9pPXODj88aCA0anDzzUyurxPYMeLo7q6UDx90mLrxYW+AbL4F1PBTQAPaYiSyr+DUrqgGq5e832Bf5dIz/lhSNasE8rrUAe0hX0AbXq2fbk3ED8Ehz2YqCyiN+BW1RJGjbIl8ksYWhtTQ9z12Vkczwdd2dXBPk31B6EBHRFhpbVBiTLgyzxmv4HD9yRfdEGOjTSvCEECmb9yeAle9NnV1eH7i23O44kr30DHk6jBOGERaPT7vgrNMAxiGErTIBE=";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set the ArchitectView
        this.architectView = (ArchitectView) findViewById(R.id.architectView);

        //Pass the license key to the ArchitectView
        final ArchitectView.ArchitectConfig config = new ArchitectView.ArchitectConfig(WIKITUDE_SDK_KEY);

        //Try to create the ArchitectView
        try{
            this.architectView.onCreate(config);
        } catch (RuntimeException e){
            this.architectView = null;
            Log.e(this.getClass().getName(), "Exception in ArchitectView.onCreate()", e);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        this.architectView.onPostCreate();
        Log.i("LOG", "Attempting to load index.html");
        try {
            this.architectView.load( "index.html" );
            Log.i("LOG", "index.html loaded.");
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("LOG", "Couldn't load index.html");
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        //Resume the ArchitectView if possible
        if(this.architectView != null){
            this.architectView.onResume();
        }
    }

    public void onPause(){
        super.onPause();

        //Pause the ArchitectView if possible
        if(this.architectView != null){
            this.architectView.onPause();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        if(this.architectView != null){
            this.architectView.onDestroy();
        }
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        if(this.architectView != null){
            this.architectView.onLowMemory();
        }
    }

    public void callYoutube(Uri uri){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}