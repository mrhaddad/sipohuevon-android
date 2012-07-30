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
    private String facebook_uid;
    private String image_url;
    private Bitmap image;
    private String id;

    public User(JSONObject json) {
        try {
            this.id = json.getString("id");
            this.facebook_uid = json.getString("facebook_uid");
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

    public static User for_json(JSONObject json) throws JSONException {
        for (User user : MyApp.getUsers()) {
            if(user.getId().equals(json.getString("id"))) {
                return user;
            }
        }

        User user = new User(json);
        MyApp.addUser(user);
        return user;
    }

    public String getId() {
        return id;
    }

    public boolean hasUpVoted(Modismo modismo) {
        for (Vote vote : modismo.getVotes()) {
            if(vote.getUserId().equals(id) && vote.getType().equals("UpVote")) {
                return true;
            }
        }

        return false;
    }

    public boolean hasDownVoted(Modismo modismo) {
        for (Vote vote : modismo.getVotes()) {
            if(vote.getUserId().equals(id) && vote.getType().equals("DownVote")) {
                return true;
            }
        }

        return false;
    }
}
