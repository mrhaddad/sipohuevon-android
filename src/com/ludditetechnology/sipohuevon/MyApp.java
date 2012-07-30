package com.ludditetechnology.sipohuevon;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/8/12
 * Time: 11:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class MyApp extends Application {
    private static Context context;
    private static ArrayList<Modismo> modismos = new ArrayList<Modismo>();
    private static ArrayList<User> users = new ArrayList<User>();
    public static String PREFS_NAME = "SipoHuevonPreferences";
    private static User current_user;

    public static List<User> getUsers() {
        return users;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }

    public static void addModismos(List<Modismo> modismos) {
        for (Modismo modismo : modismos) {
            addModismo(modismo);
        }
    }

    public static void addModismo(Modismo modismo) {
        if (!MyApp.modismos.contains(modismo)) {
            MyApp.modismos.add(modismo);
        }
    }

    public static void addUser(User user) {
        MyApp.users.add(user);
    }

    public static Modismo modismoForId(String id) {
        for (Modismo modismo : modismos) {
            if (modismo.getId().equals(id)) {
                return modismo;
            }
        }
        return null;
    }

    public String getAccessToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        return sharedPreferences.getString("access_token", null);
    }

    public void setAccessToken(String accessToken) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putString("access_token", accessToken);
        editor.commit();
    }

    public void setAccessExpires(long accessExpires) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong("access_expires", accessExpires);
        editor.commit();
    }

    public static User currentUser() {
        return MyApp.current_user;
    }

    public static void setCurrentUser(User user) {
        MyApp.current_user = user;
    }
}
