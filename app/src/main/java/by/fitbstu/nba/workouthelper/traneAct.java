package by.fitbstu.nba.workouthelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class traneAct extends AppCompatActivity {

    workouts work;

    TextView Name;
    TextView Rep;
    TextView Count;
    TextView CurRep;

    private dbAdapter adapter;
    long traneId = 0;
    int curRep = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trane);

        Name = (TextView) findViewById(R.id.textName);
        Rep = (TextView) findViewById(R.id.textRepete);
        Count = (TextView) findViewById(R.id.textCount);
        CurRep = (TextView) findViewById(R.id.curRepete);

        Bundle extras = getIntent().getExtras();
        adapter = new dbAdapter(this);
        if (extras != null) {
            traneId = extras.getLong("id");
        }
        adapter.open();
        work = adapter.getWorkout(traneId);

        Name.setText("Name : " + work.Name);
        Rep.setText("Repeats Number : " + String.valueOf(work.Repeat));
        Count.setText("Count Number : " + String.valueOf(work.Count));
        CurRep.setText("Current Repeats : " + String.valueOf(0));
        adapter.close();
    }

    public void AddRep(View v) {
        curRep++;
        CurRep.setText("Current Repeats : " + String.valueOf(curRep));
        if(curRep == work.Repeat)
        {
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Trane fineshed");
            ad.setMessage("U fineshed ur trane!");
            ad.setPositiveButton("Back to tranes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                    ToList();
                }
            });
            ad.setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int arg1) {
                }
            });
            ad.show();
        }
    }

    public void EditTrane(View v) {
        Intent intent = new Intent(this, traneEdit.class);
        intent.putExtra("id", work.id);
        startActivity(intent);
    }

    public void DelTrane(View v) {
        delAsynkTask task = new delAsynkTask();
        task.execute("");
        this.ToList();
    }

    public void Back(View v) {
        this.ToList();
    }

    public void ToList()
    {
        Intent intent = new Intent(this, traneList.class);
        startActivity(intent);
    }

    private class delAsynkTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... task) {
            adapter.open();
            adapter.delete(traneId);
            adapter.close();
            return null;
        }
    }
}
