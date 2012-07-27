package com.ludditetechnology.sipohuevon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/27/12
 * Time: 5:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModismoScreen extends Activity {
    private Modismo modismo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.modismo_screen);
        modismo = MyApp.modismoForId(getIntent().getStringExtra("id"));
        ((ImageView) findViewById(R.id.user_image)).setImageBitmap(modismo.getUser().getImage());
        ((TextView) findViewById(R.id.frase)).setText(modismo.getFrase());
        ((TextView) findViewById(R.id.definicion)).setText(modismo.getDefinicion());
        ((TextView) findViewById(R.id.ejemplo)).setText(modismo.getEjemplo());
    }
}
