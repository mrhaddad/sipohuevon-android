package com.ludditetechnology.sipohuevon;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/27/12
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModismoListItem extends LinearLayout {
    private MyActivity context;
    private Modismo modismo;

    public ModismoListItem(MyActivity context, Modismo modismo) {
        super(context);
        this.context = context;
        this.modismo = modismo;
        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate.inflate(R.layout.modismo_list_item, this, true);

        setText();
    }

    private void setText() {
        ((ImageView) findViewById(R.id.user_image)).setImageBitmap(modismo.getUser().getImage());
        ((TextView) findViewById(R.id.frase)).setText(modismo.getFrase());
        ((TextView) findViewById(R.id.definicion)).setText(modismo.getDefinicion());
        ((TextView) findViewById(R.id.ejemplo)).setText(modismo.getEjemplo());
    }

    public Modismo getModismo() {
        return modismo;
    }
}
