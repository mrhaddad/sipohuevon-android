package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import com.facebook.android.DialogError;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;

import java.util.List;

public class MyActivity extends Activity {
    Facebook facebook = new Facebook("380614202004546");

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
                ((MyApp) getApplicationContext()).setAccessToken(facebook.getAccessToken());
                ((MyApp) getApplicationContext()).setAccessExpires(facebook.getAccessExpires());
                new CreateUserTask().execute(facebook.getAccessToken());
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
    protected void onResume() {
        super.onResume();
        findViewById(R.id.new_modismo_button).setEnabled(true);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        facebook.authorizeCallback(requestCode, resultCode, data);
    }

    public void newModismoClicked(View v) {
        v.setEnabled(false);
        startActivity(new Intent(this, CreateModismoScreen.class));
    }

    class CreateUserTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... strings) {
            return new SipoHuevonApi().createUser(strings[0]);
        }
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
}
