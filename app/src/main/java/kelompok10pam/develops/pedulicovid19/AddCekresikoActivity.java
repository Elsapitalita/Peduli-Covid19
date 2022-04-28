package kelompok10pam.develops.pedulicovid19;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class AddCekresikoActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE =
            "kelompok10pam.develops.pedulicovid19.EXTRA_TITLE";
    public static final String EXTRA_PILIHAN =
            "kelompok10pam.develops.pedulicovid19.EXTRA_PRIORITY";


    private EditText editTextTitle;
    private Button bSimpan, bLihatData;
    private RadioButton yes, tidak;
    private String pilihan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cekresiko);

        editTextTitle = findViewById(R.id.edit_text_title);
        if(yes.isChecked()){
           pilihan = "yes";
        }else if (tidak.isChecked()){
            pilihan = "tidak";
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        setTitle("Add Beasiswa");


    }





    private void saveBeasiswa() {
        String title = editTextTitle.getText().toString();

        if (title.trim().isEmpty()) {

            Toast.makeText(this, "Tolong Tambahkan Judul dan Deskripsi", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        setResult(RESULT_OK, data);
        finish();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_cekresiko, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_cekresiko:
                saveBeasiswa();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }








}