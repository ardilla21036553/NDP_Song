package sg.edu.rp.c346.id21036553.ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class updateActivity extends AppCompatActivity {

    EditText etID, etTitle, etSinger, etYear;
    RadioGroup rgStars;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;
    RadioButton rbStar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgroupStars);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setFocusable(false);
        etID.setText(String.valueOf(data.getId()));
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        //if else for stars radiobutton
        int selected = data.getStars();
        if(selected == 1){
            rbStar = findViewById(R.id.rb1);
            rbStar.setChecked(true);

        }else if(selected == 2){
            rbStar = findViewById(R.id.rb2);
            rbStar.setChecked(true);

        }else if(selected == 3){
            rbStar = findViewById(R.id.rb3);
            rbStar.setChecked(true);

        }else if(selected == 4){
            rbStar = findViewById(R.id.rb4);
            rbStar.setChecked(true);

        }else if(selected == 5){
            rbStar = findViewById(R.id.rb5);
            rbStar.setChecked(true);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(updateActivity.this);
                data.setTitle(etTitle.getText().toString());
                data.setSingers(etSinger.getText().toString());
                data.setYear(Integer.parseInt(etYear.getText().toString()));

                int selected = rgStars.getCheckedRadioButtonId();
                rbStar = findViewById(selected);
                data.setStars(Integer.parseInt(rbStar.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(updateActivity.this);
                dbh.deleteSong(data.getId());
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}