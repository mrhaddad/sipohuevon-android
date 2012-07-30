package com.ludditetechnology.sipohuevon;

import org.apache.http.ProtocolVersion;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/30/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Vote {
    private String id;
    private String modismo_id;
    private String user_id;
    private String type;

    public Vote(JSONObject json) {
        try {
            this.id = json.getString("id");
            this.modismo_id = json.getString("modismo_id");
            this.user_id = json.getString("user_id");
            this.type = json.getString("type");
        } catch (JSONException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Vote(String modismo_id, String user_id, String type) {
        this.modismo_id = modismo_id;
        this.user_id = user_id;
        this.type = type;
    }

    public String getUserId() {
        return user_id;
    }

    public String getType() {
        return type;
    }
}
