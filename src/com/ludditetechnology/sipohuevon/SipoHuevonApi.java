package com.ludditetechnology.sipohuevon;

import android.util.Log;
import org.json.JSONArray;

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
            Log.e("FOO", response);
            JSONArray array = new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {
                modismos.add(new Modismo(array.getJSONObject(i)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modismos;
    }
}
