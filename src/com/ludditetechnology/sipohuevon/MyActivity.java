package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
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

        liveSearch();
        authorizeFacebook();
    }

    private void liveSearch() {
        final EditText searchText = (EditText) findViewById(R.id.search_text);
        searchText.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    Intent intent = new Intent(MyActivity.this, SearchScreen.class);
                    intent.putExtra("term", searchText.getText().toString());
                    MyActivity.this.startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }

    private void authorizeFacebook() {
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
        startActivity(new Intent(this, NewModismoScreen.class));
    }

    class CreateUserTask extends AsyncTask<String, Void, User> {
        @Override
        protected User doInBackground(String... strings) {
            return new SipoHuevonApi().createUser(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            MyApp.setCurrentUser(user);
            new GetModismosTask().execute();
        }
    }

    class GetModismosTask extends AsyncTask<Void, Void, List<Modismo>> {
        @Override
        protected void onPreExecute() {
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Modismo> doInBackground(Void... voids) {
            return new SipoHuevonApi().getModismos();
        }

        @Override
        protected void onPostExecute(List<Modismo> modismos) {
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
            ListView modismosListView = (ListView) findViewById(R.id.modismos_list_view);
            modismosListView.setAdapter(new ModismosAdapter(MyActivity.this, modismos));
        }
    }
}
