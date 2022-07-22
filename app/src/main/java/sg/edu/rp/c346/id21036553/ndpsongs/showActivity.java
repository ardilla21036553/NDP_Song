package sg.edu.rp.c346.id21036553.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class showActivity extends AppCompatActivity {

    Button btn5Star;
    ListView lvDisplay;
    ArrayList<Song> al;
    ArrayAdapter aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        btn5Star = findViewById(R.id.btn5Star);
        lvDisplay = findViewById(R.id.lvDisplay);
        al = new ArrayList<Song>();
        aa = new ArrayAdapter<Song>(this,
                android.R.layout.simple_list_item_1, al);
        lvDisplay.setAdapter(aa);

        DBHelper dbh = new DBHelper(showActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();

        btn5Star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(showActivity.this);
                al.clear();
                // al.addAll(dbh.getAllNotes());
                int filterText = 5;
                al.addAll(dbh.getStarSongs(filterText));

                aa.notifyDataSetChanged();
            }
        });

        lvDisplay.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song target = al.get(position);

                Intent i = new Intent(showActivity.this,
                        updateActivity.class);
                i.putExtra("data", target);
                startActivity(i);
            }


//            public void onItemClick(AdapterView<?> parent, View view, int
//                   pposition, long identity) {



        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        DBHelper dbh = new DBHelper(showActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        aa.notifyDataSetChanged();
    }

}