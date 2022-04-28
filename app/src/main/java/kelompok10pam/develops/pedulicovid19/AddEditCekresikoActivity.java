package kelompok10pam.develops.pedulicovid19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

public class AddEditCekresikoActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "pribadi11418007.develops.aplikasibeasiswaunggulitdel.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "package kelompok10pam.develops.pedulicovid19.EXTRA_TITLE";
    public static final String EXTRA_PILIHAN =
            "package kelompok10pam.develops.pedulicovid19.EXTRA_PRIORITY";

    private EditText editTextTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cekresiko);
        editTextTitle = findViewById(R.id.edit_text_title);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.close);
        setTitle("Close Cek Resiko");
        Intent intent = getIntent();


        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Cek Resiko");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
        } else {
            setTitle("Tambah Cek Resiko");
        }
    }

    private void saveBeasiswa() {
        String title = editTextTitle.getText().toString();
        if (title.trim().isEmpty()) {

            Toast.makeText(this, "Tolong Tambahkan isi", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

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
