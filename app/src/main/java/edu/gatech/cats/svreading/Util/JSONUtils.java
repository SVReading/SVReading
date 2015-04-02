package edu.gatech.cats.svreading.Util;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

/**
 * Created by kurt on 4/1/15.
 */
public class JSONUtils {
    /**
     * The JSON object parsed from the JSON file
     */
    private static JSONObject Books;

    /**
     * The first part of the youtube deep link
     */
    private static final String deepLink = "android://com.google.youtube/http/";

    /**
     * This method parses a validated JSON file into a JSONObject. The method expects that the file
     * is located within the assets folder (hence the need for Context). It also expects that the
     * file name includes the .json extension and matches the appropriate path/is named correctly.
     * @param fileName The name of the JSON file to parse
     * @param mContext The activity context to get access to the assets of the application
     * @return True if the file was successfully parsed, False if not
     */
    public static boolean loadJSONFile(String fileName, Context mContext){
        try{
            InputStream is = mContext.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

           Books = new JSONObject(new String(buffer, "UTF-8"));

        } catch (Exception ex){
            Log.e("JSONUtils", "Failed to parse JSON file!");
            return false;
        }
        return true;
    }

    public static Uri getYoutubeDeeplink(String bookName, int pageNumber){
        if(isJSONFileLoad()){
            try{
                JSONArray pages = (JSONArray) Books.get(bookName);
                String link = pages.getString(pageNumber);

                if(link.contains("www")){
                    link = link.replace("https://", "");
                } else {
                    link = link.replace("https://", "www");
                }

                return Uri.parse(deepLink + link);
            } catch (Exception ex){
                Log.e("JSONUtils", "Failed to get link for the given book and page number!");
                return null;
            }
        } else {
            return null;
        }
    }

    public static boolean isJSONFileLoad(){ return Books != null; }
}
