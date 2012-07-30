package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/29/12
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class SearchScreen extends Activity {
    private String term;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.search_screen);
        term = getIntent().getStringExtra("term");
        new SearchTask().execute(term);
    }

    public void defineClicked(View v) {
        v.setEnabled(false);
        Intent intent = new Intent(SearchScreen.this, NewModismoScreen.class);
        intent.putExtra("frase", term);
        startActivity(intent);
    }

    class SearchTask extends AsyncTask<String, Void, List<Modismo>> {
        @Override
        protected void onPreExecute() {
            findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Modismo> doInBackground(String... strings) {
            return new SipoHuevonApi().search(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Modismo> modismos) {
            findViewById(R.id.progress_bar).setVisibility(View.GONE);
            if(modismos.isEmpty()) {
                TextView no_results = (TextView) findViewById(R.id.no_results);
                no_results.setText(term + " no está definido aún.");
                no_results.setVisibility(View.VISIBLE);
                findViewById(R.id.define_button).setVisibility(View.VISIBLE);
            } else {
                ListView modismosListView = (ListView) findViewById(R.id.modismos_list_view);
                modismosListView.setAdapter(new ModismosAdapter(SearchScreen.this, modismos));
            }
        }
    }
}
