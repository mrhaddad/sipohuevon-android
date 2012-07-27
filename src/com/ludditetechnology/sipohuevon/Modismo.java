package com.ludditetechnology.sipohuevon;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/27/12
 * Time: 4:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class Modismo {
    private String id;
    private String frase;
    private String definicion;
    private String ejemplo;
    private User user;

    public Modismo(JSONObject json) {
        try {
            this.user = new User(json.getJSONObject("user"));
            this.id = json.getString("id");
            this.frase = json.getString("frase");
            this.definicion = json.getString("definicion");
            this.ejemplo = json.getString("ejemplo");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public String getFrase() {
        return frase;
    }

    public String getDefinicion() {
        return definicion;
    }

    public String getEjemplo() {
        return ejemplo;
    }

    public User getUser() {
        return user;
    }
}
