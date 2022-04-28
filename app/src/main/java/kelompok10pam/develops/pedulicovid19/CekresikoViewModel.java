package kelompok10pam.develops.pedulicovid19;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CekresikoViewModel extends AndroidViewModel {
    private CekresikoRepository repository;
    private LiveData<List<Cekresiko>> allCekresiko;

    public CekresikoViewModel(@NonNull Application application) {
        super(application);
        repository = new CekresikoRepository(application);
        allCekresiko = repository.getAllCekresiko();
    }

    public void insert(Cekresiko cekresiko) {
        repository.insert( cekresiko);
    }

    public void update(Cekresiko cekresiko) {
        repository.update( cekresiko);
    }

    public void delete(Cekresiko cekresiko) {
        repository.delete( cekresiko);
    }

    public void deleteAllCekresiko() {
        repository.deleteAllCekresiko();
    }

    public LiveData<List<Cekresiko>> getAllCekresiko() {
        return allCekresiko;
    }

}
