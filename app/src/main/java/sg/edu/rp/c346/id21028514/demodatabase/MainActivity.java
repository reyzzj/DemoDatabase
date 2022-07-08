package sg.edu.rp.c346.id21028514.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    TextView tvResults;
    ListView listView;
    EditText editTextdescription,editTextdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        tvResults = findViewById(R.id.tvResults);
        listView = findViewById(R.id.lv);
        editTextdescription = findViewById(R.id.editTextDescription);
        editTextdate = findViewById(R.id.editTextdate);

        btnInsert.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                String description = editTextdescription.getText().toString();
                String date = editTextdate.getText().toString();
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                db.insertTask(description,date);

            }
        });
        btnGetTasks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getTaskContent();
                db.close();

                String txt = "";
                for (int i = 0; i < data.size(); i++) {
                    Log.d("Database Content", i +". "+data.get(i));
                    txt += i + ". " + data.get(i) + "\n";
                }
                tvResults.setText(txt);
                DBHelper db2 = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<Task> al= db2.getTasks();
                db2.close();

                ArrayAdapter adapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, al);

                listView.setAdapter(adapter);

            }
        });
    }
}