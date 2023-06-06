package sg.edu.rp.c346.id22023910.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner spnAddRemove;
    EditText ETask;
    Button btnAdd;
    Button btnClear;
    Button btnRemove;
    ListView lvTask;
    ArrayList<String> alTasks;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spnAddRemove = findViewById(R.id.spinner);
        ETask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.buttonAddItem);
        btnClear = findViewById(R.id.buttonClearItem);
        btnRemove = findViewById(R.id.buttonRemoveItem);
        lvTask = findViewById(R.id.listViewTask);

        alTasks =new ArrayList<String>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alTasks);

        lvTask.setAdapter(adapter);

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        ETask.setHint("Type in a new task here");
                        btnAdd.setEnabled(true);
                        btnRemove.setEnabled(false);
                        btnClear.setEnabled(true);
                        break;
                    case 1:
                        ETask.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnRemove.setEnabled(true);
                        btnRemove.setEnabled(true);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String colour = ETask.getText().toString();

                alTasks.add(colour);
                adapter.notifyDataSetChanged();
            }
        });

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if ((ETask.getText().toString()).isEmpty() && alTasks.isEmpty()) {
                    Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_SHORT).show();
                }
                else {
                    int pos = Integer.parseInt(ETask.getText().toString());
                    if ((pos < 0 || pos > (alTasks.size())-1)) {
                        Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        alTasks.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTasks.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }


}