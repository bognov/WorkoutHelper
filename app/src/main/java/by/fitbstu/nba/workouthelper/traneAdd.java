package by.fitbstu.nba.workouthelper;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class traneAdd extends AppCompatActivity {

    dbHelper sqlHelper;
    SQLiteDatabase db;
    EditText Name;
    EditText Day;
    EditText Count;
    EditText Repeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trane_add);

        sqlHelper = new dbHelper(this);
        db = sqlHelper.getWritableDatabase();
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
        ContentValues cv = new ContentValues();
        cv.put(dbHelper.COLUMN_NAME, name);
        cv.put(dbHelper.COLUMN_DAY, day);
        cv.put(dbHelper.COLUMN_COUNT, count);
        cv.put(dbHelper.COLUMN_REPEAT, repeat);
        db.insert(dbHelper.TABLE, null, cv);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
