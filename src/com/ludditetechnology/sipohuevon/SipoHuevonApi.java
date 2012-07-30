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
        MyApp.addModismos(modismos);
        return modismos;
    }

    public User createUser(String access_token) {
        User user = null;
        RestClient client = new RestClient(API_ENDPOINT + "/users.json");
        client.AddParam("access_token", access_token);
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

    public Modismo createModismo(String frase, String definicion, String ejemplo) {
        Modismo modismo = null;
        RestClient client = new RestClient(API_ENDPOINT + "/modismos.json");
        client.AddParam("access_token", ((MyApp) MyApp.getContext()).getAccessToken());
        client.AddParam("modismo[frase]", frase);
        client.AddParam("modismo[definicion]", definicion);
        client.AddParam("modismo[ejemplo]", ejemplo);
        try {
            client.Execute(RequestMethod.POST);
            String response = client.getResponse();
            JSONObject json = new JSONObject(response);
            modismo = new Modismo(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyApp.addModismo(modismo);
        return modismo;
    }

    public List<Modismo> search(String term) {
        List<Modismo> modismos = new ArrayList<Modismo>();
        RestClient client = new RestClient(API_ENDPOINT + "/search.json");
        client.AddParam("search[term]", term);
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
        MyApp.addModismos(modismos);
        return modismos;
    }

    public void createVote(Modismo modismo, String type) {
        RestClient client = new RestClient(API_ENDPOINT + "/modismos/" + modismo.getId() + "/votes/" + type + ".json");
        client.AddParam("access_token", ((MyApp) MyApp.getContext()).getAccessToken());
        try {
            client.Execute(RequestMethod.POST);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
