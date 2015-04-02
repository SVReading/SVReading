package edu.gatech.cats.svreading.View;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wikitude.architect.ArchitectView;

import java.io.IOException;

import edu.gatech.cats.svreading.R;

public class MainActivity extends ActionBarActivity {

    /**
     * The nav draw toggle that handles listening to/opening and closing the nav drawer
     */
    private ActionBarDrawerToggle drawerToggle;

    /**
     * The draw layout holding the nav drawer
     */
    private DrawerLayout drawerLayout;

    /**
     * Holds the Wikitude SDK ArchitectView (where the camera, markers, etc. are rendered)
     */
    private ArchitectView architectView;

    private static final String WIKITUDE_SDK_KEY = "YJYA8XUwIpY/HiBUAwQXRL6Y9YE3NoEudB7JS1hr9sHSJajA5aTeSk0UIgUMnbSCglgkTVeJVa2osAcO/C2B4vAQF/EHYpE6+lEKef4Hupmz38VhBcRmOkzqRLtJBu1c/6flA0Adlced4YiH91bsImSPGQd3qpvSqte65bsyLnBTYWx0ZWRfX5d3zzmuBKWJT1f3CV0sl83h6z5Hharg/OY8X5QPkOruX+zSJwCbE4zHvVY6pTqONmKQmzzCvIPvwVlOqub2VLtjJh+xUYahbdliTw4LCAL4AgZt0ErFfAMlYfIsfEyYlOuZqvG4TZoK5tSrs4Cits4TO9GzlMweFbPQRdI53zGg4lDxW2rvdBucRIB9Frfnud7I7QTPH2SExu+YCqWCXibj6sLLT/oS5fuupYv3/eZxFqBqh4XxT3gev4CroSRCXelA9E24jrPyVVeo+2v7jW6loxeOFTTXAlYRd8Wau6GnjvU2GIFy9PSp5kKPz9KGYyuKqsdawIS4lHeJcww8Kf5TOjwPfaPYY6qOvgilDdW/jbYYepftCkPCH+0UrhFU3QluviS199DBt8yQSAYv7cxzBZE9b6Wp8lHeY7RYs44sPlZD1OqFk+svXRKUa5NskOtOcHjr0iBLwbLIED7ZbFIbnDDiCvBYv+PjEGZRhc8L2GzzP+Bm1BqzFqb2U5tcoBuQdawjRnt7";



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

        //Set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Set up the nav drawer
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.nav_drawer_open, R.string.nav_drawer_closed
        );
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");
        drawerToggle.syncState();

        ListView navDrawerListView = (ListView) findViewById(R.id.nav_drawer);
        String[] books = {"The Cat in the Hat", "Clifford the Big Red Dog"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, books);
        navDrawerListView.setAdapter(adapter);
        navDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                callYoutube("https://www.youtube.com/watch?v=Iu0VTI-ZSHQ");
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();

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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        //Handle pressing back when the drawer is open
        if(drawerLayout.isDrawerOpen(Gravity.START)){
            drawerLayout.closeDrawers();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        if(this.architectView != null){
            this.architectView.onLowMemory();
        }
    }

    public void callYoutube(String url){
        try{
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}