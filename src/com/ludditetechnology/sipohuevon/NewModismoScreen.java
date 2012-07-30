package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/27/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewModismoScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.new_modismo_screen);
        ((TextView) findViewById(R.id.frase_text)).setText(getIntent().getStringExtra("frase"));
    }

    public void createModismoClicked(View v) {
        if (validFrase()) {
            v.setEnabled(false);
            new CreateModismoTask().execute(fraseText(), definicionText(), ejemploText());
        } else {
            Toast.makeText(getApplicationContext(), R.string.invalid_modismo, Toast.LENGTH_LONG).show();
        }
    }

    private boolean validFrase() {
        return fraseText().length() > 0 && definicionText().length() > 0 && ejemploText().length() > 0;
    }

    private String fraseText() {
        return ((EditText) findViewById(R.id.frase_text)).getText().toString().trim();
    }

    private String definicionText() {
        return ((EditText) findViewById(R.id.definicion_text)).getText().toString().trim();
    }

    private String ejemploText() {
        return ((EditText) findViewById(R.id.ejemplo_text)).getText().toString().trim();
    }

    class CreateModismoTask extends AsyncTask<String, Void, Modismo> {
        @Override
        protected Modismo doInBackground(String... strings) {
            return new SipoHuevonApi().createModismo(strings[0], strings[1], strings[2]);
        }

        @Override
        protected void onPostExecute(Modismo modismo) {
            Intent intent = new Intent(NewModismoScreen.this, ModismoScreen.class);
            intent.putExtra("id", modismo.getId());
            NewModismoScreen.this.startActivity(intent);
        }
    }
}
