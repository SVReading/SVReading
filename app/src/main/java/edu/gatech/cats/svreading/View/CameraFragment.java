package edu.gatech.cats.svreading.View;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wikitude.architect.ArchitectView;

import edu.gatech.cats.svreading.R;

/**
 * Created by Kurt on 2/4/2015
 */
public class CameraFragment extends Fragment {

    /**
     * Holds the Wikitude SDK ArchitectView (where the camera, markers, etc. are rendered)
     */
    private ArchitectView architectView;

    private static final String WIKITUDE_SDK_KEY = "CSU+/8HIRGJh0bAqaYTpkp6qcnyfV5yEnOIFVEE/5T1N8k84kMJz8puHjhA+V+r7xGnizLw1CVt5/qYuvexwfmZeFwsOyuxetU5AC9F1s5/hzLKDNJOn0GBq8OhfpLnd3Ne3GO+b0ZOJDLmA75x/wWZXvmVtRBMblI94O2oBWmdTYWx0ZWRfX78Y6eenrSsW2xQDO7MkObj6INrJiZOQduDwZ0LtT5Ry85jU0FqBo+SkRvQOJYwYHzDBL00ca4zbDTGeqwgzDTG52hbWDCjvcZ6AH/BIZPxQUm4hNaRV6HRpLXs7AgWfEIpZ9W9r7o3oQq7gxGvkVhEkggE3XMEvFrWai9NXDgCHSeSx3MjVUxjIvHRj81ZsqEhrzq8jlDsO4525BIK+/zJOQfZc+M91apCtlrenLo7+Md1zK/+bgweB9pPXODj88aCA0anDzzUyurxPYMeLo7q6UDx90mLrxYW+AbL4F1PBTQAPaYiSyr+DUrqgGq5e832Bf5dIz/lhSNasE8rrUAe0hX0AbXq2fbk3ED8Ehz2YqCyiN+BW1RJGjbIl8ksYWhtTQ9z12Vkczwdd2dXBPk31B6EBHRFhpbVBiTLgyzxmv4HD9yRfdEGOjTSvCEECmb9yeAle9NnV1eH7i23O44kr30DHk6jBOGERaPT7vgrNMAxiGErTIBE=";

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
