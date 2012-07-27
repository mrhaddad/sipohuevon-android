package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        new GetModismosTask().execute();
    }

    class GetModismosTask extends AsyncTask<Void, Void, List<Modismo>> {

        @Override
        protected List<Modismo> doInBackground(Void... voids) {
            return new SipoHuevonApi().getModismos();
        }

        @Override
        protected void onPostExecute(List<Modismo> modismos) {
            ListView modismosListView = (ListView) findViewById(R.id.modismos_list_view);
            modismosListView.setAdapter(new ModismosAdapter(MyActivity.this, modismos));
        }
    }
}
