package com.ludditetechnology.sipohuevon;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
    private List<Vote> votes;

    public Modismo(JSONObject json) {
        try {
            this.user = User.for_json(json.getJSONObject("user"));
            this.id = json.getString("id");
            this.frase = json.getString("frase");
            this.definicion = json.getString("definicion");
            this.ejemplo = json.getString("ejemplo");
            this.votes = new ArrayList<Vote>();
            JSONArray votesJSON = json.getJSONArray("votes");
            for (int i = 0; i < votesJSON.length(); i++) {
                votes.add(new Vote(votesJSON.getJSONObject(i)));
            }
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

    public String getId() {
        return id;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public List<Vote> getUpVotes() {
        List<Vote> upVotes = new ArrayList<Vote>();
        for (Vote vote : getVotes()) {
            if(vote.getType().equals("UpVote")) {
                upVotes.add(vote);
            }
        }

        return upVotes;
    }

    public List<Vote> getDownVotes() {
        List<Vote> downVotes = new ArrayList<Vote>();
        for (Vote vote : getVotes()) {
            if(vote.getType().equals("DownVote")) {
                downVotes.add(vote);
            }
        }

        return downVotes;
    }

    public void addVote(String user_id, String type) {
        votes.add(new Vote(id, user_id, type));
    }

    public void removeVote(String user_id, String type) {
        for (Vote vote : votes) {
            if(vote.getUserId().equals(user_id) && vote.getType().equals(type)) {
                votes.remove(vote);
            }
        }
    }
}
