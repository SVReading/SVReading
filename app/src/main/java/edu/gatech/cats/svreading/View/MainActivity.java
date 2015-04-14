package edu.gatech.cats.svreading.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;

import java.io.IOException;

import edu.gatech.cats.svreading.R;
import edu.gatech.cats.svreading.Util.JSONUtils;


public class MainActivity extends Activity {
    /**
     * Holds the Wikitude SDK ArchitectView (where the camera, markers, etc. are rendered)
     */
    private ArchitectView architectView;
    private TextView bookBeingTracked;

    private static final String WIKITUDE_SDK_KEY = "YJYA8XUwIpY/HiBUAwQXRL6Y9YE3NoEudB7JS1hr9sHSJajA5aTeSk0UIgUMnbSCglgkTVeJVa2osAcO/C2B4vAQF/EHYpE6+lEKef4Hupmz38VhBcRmOkzqRLtJBu1c/6flA0Adlced4YiH91bsImSPGQd3qpvSqte65bsyLnBTYWx0ZWRfX5d3zzmuBKWJT1f3CV0sl83h6z5Hharg/OY8X5QPkOruX+zSJwCbE4zHvVY6pTqONmKQmzzCvIPvwVlOqub2VLtjJh+xUYahbdliTw4LCAL4AgZt0ErFfAMlYfIsfEyYlOuZqvG4TZoK5tSrs4Cits4TO9GzlMweFbPQRdI53zGg4lDxW2rvdBucRIB9Frfnud7I7QTPH2SExu+YCqWCXibj6sLLT/oS5fuupYv3/eZxFqBqh4XxT3gev4CroSRCXelA9E24jrPyVVeo+2v7jW6loxeOFTTXAlYRd8Wau6GnjvU2GIFy9PSp5kKPz9KGYyuKqsdawIS4lHeJcww8Kf5TOjwPfaPYY6qOvgilDdW/jbYYepftCkPCH+0UrhFU3QluviS199DBt8yQSAYv7cxzBZE9b6Wp8lHeY7RYs44sPlZD1OqFk+svXRKUa5NskOtOcHjr0iBLwbLIED7ZbFIbnDDiCvBYv+PjEGZRhc8L2GzzP+Bm1BqzFqb2U5tcoBuQdawjRnt7";



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Set the ArchitectView
        this.architectView = (ArchitectView) findViewById(R.id.architectView);

        //Pass the license key to the ArchitectView
        final ArchitectView.ArchitectConfig config = new ArchitectView.ArchitectConfig(WIKITUDE_SDK_KEY);

        //Try to create the ArchitectView
        try
        {
            this.architectView.onCreate(config);
        } catch (RuntimeException e)
        {
            this.architectView = null;
            Log.e(this.getClass().getName(), "Exception in ArchitectView.onCreate()", e);
        }

        //Load the JSON file of all the books and their pages and youtube videos
        JSONUtils.loadJSONFile("Books.json",this);


        architectView.registerUrlListener(new ArchitectView.ArchitectUrlListener()
        {
            @Override
            /**
             * A listener used to receive a string sent from the javascript code. The string comes in
             * the format: "architectsdk://" + bookName + "," + pageNum. Page number is used as a
             * String and therefore does not have to be purely a number (ex. Clifford1, Clifford2, etc).
             * @param arg0 A string containing the corresponding book name and page number recognized.
             */
            public boolean urlWasInvoked(String arg0) {
                //Remove the leading URI scheme
                String linkInfo = arg0.replace("architectsdk://", "");

                //Split the book name and page number
                String delims = "[,]";
                String[] tokens = linkInfo.split(delims);
                String bookName = tokens[0].replace("_"," ");
                String pageNum = tokens[1];

                callYoutube(JSONUtils.getYoutubeDeeplink(bookName, pageNum));

                //Not sure why we pass back false
                return false;
            }
        });

        //Opens up the dialog notifying user to scan a book cover
        DialogFragment newFragment = new scanBookDialogFragment();
        newFragment.show(getFragmentManager(), "scanBookDiag");

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

    /**
     * Method that opens up the youtube link corresponding to the page that was recognized by
     * ArchitectView. If no youtube video was found the Uri will be null and will throw an exception.
     * @param uri youtube link details the activity intent needs
     */
    public void callYoutube(Uri uri)
    {
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, uri));
        } catch (Exception ex){
            //Notify the user no video for the page was found
            Toast.makeText(getApplicationContext(), "No video was found for the page!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Creates the tutorial dialog box that opens when the user launches the app. Notifies the
     * user that a book cover needs to be scanned before user can start reading a specific book
     */
    public static class scanBookDialogFragment extends DialogFragment
    {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("Scan the book cover of the book you want to read").setPositiveButton("OK", null);
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}