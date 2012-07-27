package com.ludditetechnology.sipohuevon;

import android.app.Application;
import android.content.Context;

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
}
