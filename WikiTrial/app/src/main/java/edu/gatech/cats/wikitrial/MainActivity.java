package edu.gatech.cats.wikitrial;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.wikitude.architect.ArchitectView;

import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    private ArchitectView architectView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.architectView = (ArchitectView)this.findViewById( R.id.architectView );
        final ArchitectView.ArchitectConfig config = new ArchitectView.ArchitectConfig( "duca3B2Ea9WNrZsme5Q02LS6Md/3KwcrsoPzyBbVrzMjIQpx7uX2wMm0c/zD899TKMoOQaGHDW4EUqqERCAUxNjJkyDTZfF3QkbReB1v9VldNQX9JnoTwuyOYBBf5s9G6oPKQljZpZEXrcM7LFcV/iPAgDTPzyllGsrUBHQ38m9TYWx0ZWRfX+p4MJuZXKms9+sFFqgeCVflRI4P1mSUoeWD8W2/akWfzwAGkKfc+wH68/J4lEjY8IWBJ0Emm2lKuo7qViImhmIYV3HvTi8Ff6GtMu1elyGCLfWFZCj9K7BDHNmdkdGbSFuAONrcCQrY9jhZJO9Ht/Fc4jDjCoK7ldahs9RyIQM3wRSKMe2gO5rcNPPH3zDfuDXaKdWf0J+vsqS9n88OjIx26QWZtxRlxsqeFlaDjZf56smNLL6qnMWP0Yb9+tjHWpvWUV8ZiB1+RZqAk8yWoUu1bvreB9vyz5LP1dpQxAxN8KLnfpj61/iPHyFHDInR2wUIwrwHB8SrJVlOYtIPoGsUKhYYEbRapOYe/ZYSkZs95KgKCeVDfLFwCavt7b5FboJb6h+Kr1d3lh4AyAA8Pj3f13Wi0WLOedd7+mhvg+Eb/jsu8NbxIfYJLG3zPzXf3Qt5NqE13e7PAsazkbxkQGQ7/UrlvL0FaoteoHX/rLwF5AFY6AwA2cw=" );
        this.architectView.onCreate( config );
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
    protected void onPause() {
        super.onPause();
        this.architectView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.architectView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.architectView.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
