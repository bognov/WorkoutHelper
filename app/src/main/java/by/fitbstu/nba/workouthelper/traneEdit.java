package by.fitbstu.nba.workouthelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class traneEdit extends AppCompatActivity {

    workouts work;

    EditText Name;
    EditText Day;
    EditText Count;
    EditText Repeat;

    private dbAdapter adapter;

    long traneId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trane_edit);

        Name = (EditText) findViewById(R.id.editText);
        Day = (EditText) findViewById(R.id.editText3);
        Count = (EditText) findViewById(R.id.editText1);
        Repeat = (EditText) findViewById(R.id.editText2);

        Bundle extras = getIntent().getExtras();
        adapter = new dbAdapter(this);
        if (extras != null) {
            traneId = extras.getLong("id");
        }
        adapter.open();
        work = adapter.getWorkout(traneId);

        Name.setText(work.Name);
        Day.setText(String.valueOf(work.Day));
        Count.setText(String.valueOf(work.Count));
        Repeat.setText(String.valueOf(work.Repeat));
    }

    public void Save(View v) {
        Name = (EditText) findViewById(R.id.editText);
        Day = (EditText) findViewById(R.id.editText3);
        Count = (EditText) findViewById(R.id.editText1);
        Repeat = (EditText) findViewById(R.id.editText2);

        String name = Name.getText().toString();
        int day = Integer.parseInt(Day.getText().toString());
        int count = Integer.parseInt(Count.getText().toString());
        int repeat = Integer.parseInt(Repeat.getText().toString());

        work = new workouts(traneId, name, day, count, repeat);
        edAsynkTask task = new edAsynkTask();
        task.execute("");
        Intent intent = new Intent(this, traneAct.class);
        intent.putExtra("id", work.id);
        startActivity(intent);
    }

    private class edAsynkTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... task) {
            adapter.open();
            adapter.update(work);
            adapter.close();
            return null;
        }
    }
}
