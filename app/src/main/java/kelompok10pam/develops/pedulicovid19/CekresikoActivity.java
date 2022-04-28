package kelompok10pam.develops.pedulicovid19;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CekresikoActivity extends AppCompatActivity {
    public static final int ADD_CEKRESIKO_REQUEST = 1;
    public static final int EDIT_CEKRESIKO_REQUEST = 2;
    private CekresikoViewModel cekresikoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekresiko);
        FloatingActionButton buttonAddCekresiko = findViewById(R.id.button_add_cekresiko);
        buttonAddCekresiko.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CekresikoActivity.this, AddCekresikoActivity.class);
                startActivityForResult(intent, ADD_CEKRESIKO_REQUEST);
            }
        });
        RecyclerView recyclerView = findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CekresikoAdapter adapter = new CekresikoAdapter();
        recyclerView.setAdapter(adapter);
        cekresikoViewModel = ViewModelProviders.of(this).get(CekresikoViewModel.class);
        cekresikoViewModel.getAllCekresiko().observe(this, new Observer<List<Cekresiko>>() {
            @Override
            public void onChanged(@Nullable List<Cekresiko> cekresikos) {
                adapter.submitList(cekresikos);
//UPDATE RECYLER VIEW
                Toast.makeText(CekresikoActivity.this, "On Changed", Toast.LENGTH_SHORT).show();
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                cekresikoViewModel.delete(adapter.getCekresikoAt(viewHolder.getAdapterPosition()));
                Toast.makeText(CekresikoActivity.this, "Cekresiko Dihapus", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new CekresikoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Cekresiko cekresiko) {
                Intent intent = new Intent(CekresikoActivity.this, AddEditCekresikoActivity.class);
                intent.putExtra(AddEditCekresikoActivity.EXTRA_ID, cekresiko.getId());
                intent.putExtra(AddEditCekresikoActivity.EXTRA_TITLE, cekresiko.getTitle());
                intent.putExtra(AddEditCekresikoActivity.EXTRA_PILIHAN, cekresiko.getPilihan());
                startActivityForResult(intent, EDIT_CEKRESIKO_REQUEST);


            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CEKRESIKO_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(AddEditCekresikoActivity.EXTRA_TITLE);
            String pilihan = data.getStringExtra(AddEditCekresikoActivity.EXTRA_PILIHAN);

            Cekresiko cekresiko = new Cekresiko(title, pilihan);
            cekresikoViewModel.insert(cekresiko);
            Toast.makeText(this, "Cekresiko di Simpan", Toast.LENGTH_SHORT).show();

        } else if (requestCode == EDIT_CEKRESIKO_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(AddEditCekresikoActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Cekresiko Tidak bisa di Updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String title = data.getStringExtra(AddEditCekresikoActivity.EXTRA_TITLE);
            String pilihan = data.getStringExtra(AddEditCekresikoActivity.EXTRA_PILIHAN);

            Cekresiko cekresiko = new Cekresiko(title, pilihan);
            cekresiko.setId(id);
            cekresikoViewModel.update(cekresiko);
            Toast.makeText(this, "Cekresiko diupdate", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Cekresiko Tidak Tersimpan", Toast.LENGTH_SHORT).show();
        }
    }
}