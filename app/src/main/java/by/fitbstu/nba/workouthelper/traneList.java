package by.fitbstu.nba.workouthelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class traneList extends AppCompatActivity {

    private ListView userList;
    ArrayAdapter<workouts> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trane_list);

        userList = (ListView)findViewById(R.id.List1);
        this.initList(1);

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                workouts workout = arrayAdapter.getItem(position);
                if(workout != null) {
                    Intent intent = new Intent(getApplicationContext(), traneAct.class);
                    intent.putExtra("id", workout.id);
                    startActivity(intent);
                }
            }
        });
    }

    private void initList(int day){
        dbAdapter adapter = new dbAdapter(this);
        adapter.open();
        List<workouts> users = adapter.getWorkouts(day);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        userList.setAdapter(arrayAdapter);
        adapter.close();
    }

    public void Day1Click(View v) {
        this.initList(1);
    }

    public void Day2Click(View v) {
        this.initList(2);
    }

    public void Day3Click(View v) {
        this.initList(3);
    }
}
