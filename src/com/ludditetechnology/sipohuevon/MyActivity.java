package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class MyActivity extends Activity {
    Facebook facebook = new Facebook("380614202004546");
    private Facebook mAsyncRunner;
    public static String PREFS_NAME = "SipoHuevonPreferences";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        facebook.authorize(this, new String[]{"email"}, new Facebook.DialogListener() {
            @Override
            public void onComplete(Bundle values) {
                new FacebookRequestMeTask().execute();
            }

            @Override
            public void onFacebookError(FacebookError error) {
            }

            @Override
            public void onError(DialogError e) {
            }

            @Override
            public void onCancel() {
            }
        });

        new GetModismosTask().execute();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);
    }

    class GetModismosTask extends AsyncTask<Void, Void, List<Modismo>> {
        @Override
        protected List<Modismo> doInBackground(Void... voids) {
            return new SipoHuevonApi().getModismos();
        }

        @Override
        protected void onPostExecute(List<Modismo> modismos) {
            MyApp.addModismos(modismos);
            ListView modismosListView = (ListView) findViewById(R.id.modismos_list_view);
            modismosListView.setAdapter(new ModismosAdapter(MyActivity.this, modismos));
        }
    }

    class FacebookRequestMeTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                JSONObject json = Util.parseJson(facebook.request("me"));

                String uid = json.getString("id");
                String email = json.getString("email");
                String first_name = json.getString("first_name");
                String last_name = json.getString("last_name");

                SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit();
                editor.putString("uid", uid);
                editor.putString("first_name", first_name);
                editor.putString("last_name", last_name);
                editor.putString("email", email);
                editor.commit();

                new SipoHuevonApi().createUser(uid, email, first_name, last_name);
            } catch (JSONException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (MalformedURLException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            return null;
        }
    }
}
