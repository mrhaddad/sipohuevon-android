package com.ludditetechnology.sipohuevon;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
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
    private Context context;
    private Modismo modismo;

    public ModismoListItem(Context context, Modismo modismo) {
        super(context);
        this.context = context;
        this.modismo = modismo;
        LayoutInflater inflate = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate.inflate(R.layout.modismo_list_item, this, true);

        setText();
        setVotesCount();
        liveVoteButtons();
    }

    private void liveVoteButtons() {
        View upVoteButton = findViewById(R.id.up_vote_button);
        View downVoteButton = findViewById(R.id.down_vote_button);
//        upVoteButton.setEnabled(MyApp.currentUser().canUpVote(modismo) ? true : false);
//        downVoteButton.setEnabled(MyApp.currentUser().canDownVote(modismo) ? true : false);

        upVoteButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MyApp.currentUser().hasUpVoted(modismo)) {
                    modismo.removeVote(MyApp.currentUser().getId(), "UpVote");
                } else {
                    if (MyApp.currentUser().hasDownVoted(modismo)) {
                        modismo.removeVote(MyApp.currentUser().getId(), "DownVote");
                    }
                    modismo.addVote(MyApp.currentUser().getId(), "UpVote");
                }

                setVotesCount();
                new UpVoteTask().execute(modismo);
            }
        });

        downVoteButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (MyApp.currentUser().hasDownVoted(modismo)) {
                    modismo.removeVote(MyApp.currentUser().getId(), "DownVote");
                } else {
                    if (MyApp.currentUser().hasUpVoted(modismo)) {
                        modismo.removeVote(MyApp.currentUser().getId(), "UpVote");
                    }
                    modismo.addVote(MyApp.currentUser().getId(), "DownVote");
                }

                setVotesCount();
                new DownVoteTask().execute(modismo);
            }
        });
    }

    private void setText() {
        ((ImageView) findViewById(R.id.user_image)).setImageBitmap(modismo.getUser().getImage());
        ((TextView) findViewById(R.id.frase)).setText(modismo.getFrase());
        ((TextView) findViewById(R.id.definicion)).setText(modismo.getDefinicion());
        ((TextView) findViewById(R.id.ejemplo)).setText(modismo.getEjemplo());
    }

    private void setVotesCount() {
        ((TextView) findViewById(R.id.votes_count)).setText("+" + modismo.getUpVotes().size() + ", -" + modismo.getDownVotes().size());
    }

    public Modismo getModismo() {
        return modismo;
    }

    class UpVoteTask extends AsyncTask<Modismo, Void, Void> {
        @Override
        protected Void doInBackground(Modismo... modismos) {
            new SipoHuevonApi().createVote(modismos[0], "up");
            return null;
        }
    }

    class DownVoteTask extends AsyncTask<Modismo, Void, Void> {
        @Override
        protected Void doInBackground(Modismo... modismos) {
            new SipoHuevonApi().createVote(modismos[0], "down");
            return null;
        }
    }
}
