package edu.gatech.cats.svreading.View;

/**
 * Created by harrisonobiorah on 3/30/15.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import com.wikitude.architect.ArchitectView;

/**
 * Class to handle all calls from JS & from Java too
 **/
public class JsHandler
{

    Activity activity;
    String TAG = "JsHandler";
    ArchitectView webView;


    public JsHandler(Activity _contxt,ArchitectView architectView) {
        activity = _contxt;
        webView = architectView;
    }

    /**
     * This function handles call from JS
     */
    public void jsFnCall(String jsString) {
        showDialog(jsString);
    }

    /**
     * This function handles call from Android-Java
     */
    public void javaFnCall(String jsString) {

        final String webUrl = "javascript:diplayJavaMsg('"+jsString+"')";
        // Add this to avoid android.view.windowmanager$badtokenexception unable to add window
        if(!activity.isFinishing())
            // loadurl on UI main thread
            activity.runOnUiThread(new Runnable() {

                @Override
                public void run() {
                   // webView.loadUrl(webUrl);
                }
            });
    }

    /**
     * function shows Android-Native Alert Dialog
     */
    public void showDialog(String msg){

        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("test");
        alertDialog.setMessage(msg);
        alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"Bang", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE,"Hey", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}