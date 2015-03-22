package edu.gatech.cats.svreading.View;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wikitude.architect.ArchitectView;

import java.io.IOException;

import edu.gatech.cats.svreading.R;

/**
 * Created by Kurt on 2/4/2015
 */
public class CameraFragment extends Fragment {

    /**
     * Holds the Wikitude SDK ArchitectView (where the camera, markers, etc. are rendered)
     */
    private ArchitectView architectView;

    private static final String WIKITUDE_SDK_KEY = "duca3B2Ea9WNrZsme5Q02LS6Md/3KwcrsoPzyBbVrzMjIQpx7uX2wMm0c/zD899TKMoOQaGHDW4EUqqERCAUxNjJkyDTZfF3QkbReB1v9VldNQX9JnoTwuyOYBBf5s9G6oPKQljZpZEXrcM7LFcV/iPAgDTPzyllGsrUBHQ38m9TYWx0ZWRfX+p4MJuZXKms9+sFFqgeCVflRI4P1mSUoeWD8W2/akWfzwAGkKfc+wH68/J4lEjY8IWBJ0Emm2lKuo7qViImhmIYV3HvTi8Ff6GtMu1elyGCLfWFZCj9K7BDHNmdkdGbSFuAONrcCQrY9jhZJO9Ht/Fc4jDjCoK7ldahs9RyIQM3wRSKMe2gO5rcNPPH3zDfuDXaKdWf0J+vsqS9n88OjIx26QWZtxRlxsqeFlaDjZf56smNLL6qnMWP0Yb9+tjHWpvWUV8ZiB1+RZqAk8yWoUu1bvreB9vyz5LP1dpQxAxN8KLnfpj61/iPHyFHDInR2wUIwrwHB8SrJVlOYtIPoGsUKhYYEbRapOYe/ZYSkZs95KgKCeVDfLFwCavt7b5FboJb6h+Kr1d3lh4AyAA8Pj3f13Wi0WLOedd7+mhvg+Eb/jsu8NbxIfYJLG3zPzXf3Qt5NqE13e7PAsazkbxkQGQ7/UrlvL0FaoteoHX/rLwF5AFY6AwA2cw=";

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle bundle){
        return inflater.inflate(this.getContentViewId(), container, false);
    }

    @Override
    public void onActivityCreated(final Bundle bundle){
        super.onActivityCreated(bundle);

        //Set the ArchitectView
        this.architectView = (ArchitectView) this.getView().findViewById(getARchitectViewID());

        //Pass the license key to the ArchitectView
        final ArchitectView.ArchitectConfig config = new ArchitectView.ArchitectConfig(this.getWikitudSDKLicenseKey());

        //Try to create the ArchitectView
        try{
            this.architectView.onCreate(config);
            this.architectView.onPostCreate();
        } catch (RuntimeException e){
            this.architectView = null;
            Log.e(this.getClass().getName(), "Exception in ArchitectView.onCreate()", e);
        }

        //Load the architect world view
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

    @Override
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

    public int getContentViewId() { return R.layout.fragment_camera; }

    public String getWikitudSDKLicenseKey() { return WIKITUDE_SDK_KEY; }

    public int getARchitectViewID() { return R.id.architectView; }
}
