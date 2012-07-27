package com.ludditetechnology.sipohuevon;

import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mrhaddad
 * Date: 7/9/12
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
public class ModismosAdapter extends BaseAdapter {
    private List<Modismo> modismos;
    private MyActivity activity;

    public ModismosAdapter(MyActivity myActivity, List<Modismo> modismos) {
        this.activity = myActivity;
        this.modismos = modismos;
    }

    public int getCount() {
        return modismos.size();
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ModismoListItem modismoListItem = new ModismoListItem(activity, modismos.get(i));
//        modismoListItemView.setClickable(true);
//        modismoListItemView.setFocusable(true);
//        modismoListItemView.setBackgroundResource(android.R.drawable.menuitem_background);
//        final SimpleGame game = games.get(i);
//        modismoListItemView.setGame(game);
//
        modismoListItem.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ModismoListItem listItem = (ModismoListItem) view;
                Intent intent = new Intent(activity, ModismoScreen.class);
                intent.putExtra("id", listItem.getModismo().getId());
                activity.startActivity(intent);
            }
        });

        return modismoListItem;
    }
}
