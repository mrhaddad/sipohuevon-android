package com.ludditetechnology.sipohuevon;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/27/12
 * Time: 4:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class SipoHuevonApi {
    protected final String API_ENDPOINT = "http://www.sipohuevon.com/api";

    public List<Modismo> getModismos() {
        List<Modismo> modismos = new ArrayList<Modismo>();
        RestClient client = new RestClient(API_ENDPOINT + "/modismos.json");
        try {
            client.Execute(RequestMethod.GET);
            String response = client.getResponse();
            JSONArray array = new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {
                modismos.add(new Modismo(array.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modismos;
    }

    public User createUser(String uid, String email, String first_name, String last_name) {
        User user = null;
        RestClient client = new RestClient(API_ENDPOINT + "/users.json");
        client.AddParam("user[facebook_uid]", uid);
        client.AddParam("user[email]", email);
        client.AddParam("user[first_name]", first_name);
        client.AddParam("user[last_name]", last_name);
        try {
            client.Execute(RequestMethod.POST);
            String response = client.getResponse();
            JSONObject json = new JSONObject(response);
            user = new User(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
