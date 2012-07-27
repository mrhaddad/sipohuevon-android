package com.ludditetechnology.sipohuevon;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/27/12
 * Time: 4:52 PM
 * To change this template use File | Settings | File Templates.
 */
public class User {
    private String first_name;
    private String last_name;
    private String image_url;
    private Bitmap image;

    public User(JSONObject json) {
        try {
            this.first_name = json.getString("first_name");
            this.last_name = json.getString("first_name");
            this.image_url = json.getString("image_url");
            this.image = BitmapFactory.decodeStream((InputStream) new URL(image_url).getContent());
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (MalformedURLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Bitmap getImage() {
        return image;
    }
}
