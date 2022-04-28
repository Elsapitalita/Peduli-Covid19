package kelompok10pam.develops.pedulicovid19;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CekresikoRepository {
    private CekresikoDao cekresikoDao;
    private LiveData<List<Cekresiko>> allCekresiko;

    public CekresikoRepository(Application application) {
        CekresikoDatabase database = CekresikoDatabase.getInstance(application);
        cekresikoDao = database.cekresikoDao();
        allCekresiko= cekresikoDao.getAllCekresiko();
    }

    public void insert(Cekresiko cekresiko) {
        new InsertCekresikoAsyncTask(cekresikoDao).execute(cekresiko);

    }

    public void update(Cekresiko cekresiko) {
        new UpdateCekresikoAsyncTask(cekresikoDao).execute(cekresiko);


    }

    public void delete(Cekresiko cekresiko) {
        new DeleteCekresikoAsyncTask(cekresikoDao).execute(cekresiko);
    }

    public void deleteAllCekresiko() {
        new DeleteAllCekresikoAsyncTask(cekresikoDao).execute();
    }

    public LiveData<List<Cekresiko>> getAllCekresiko() {
        return allCekresiko;
    }



    private static class InsertCekresikoAsyncTask extends AsyncTask<Cekresiko, Void, Void> {
        private CekresikoDao cekresikoDao;

        private InsertCekresikoAsyncTask(CekresikoDao cekresikoDao){
            this.cekresikoDao = cekresikoDao;
        }

        @Override
        protected Void doInBackground(Cekresiko... cekresiko) {
            cekresikoDao.insert(cekresiko[0]);
            return null;
        }
    }



    private static class UpdateCekresikoAsyncTask extends AsyncTask<Cekresiko, Void, Void> {
        private CekresikoDao cekresikoDao;

        private UpdateCekresikoAsyncTask(CekresikoDao cekresikoDao){
            this.cekresikoDao = cekresikoDao;
        }

        @Override
        protected Void doInBackground(Cekresiko... cekresiko) {
            cekresikoDao.update(cekresiko[0]);
            return null;
        }
    }

    private static class DeleteCekresikoAsyncTask extends AsyncTask<Cekresiko, Void, Void> {
        private CekresikoDao cekresikoDao;

        private DeleteCekresikoAsyncTask(CekresikoDao cekresikoDao){
            this.cekresikoDao = cekresikoDao;
        }

        @Override
        protected Void doInBackground(Cekresiko... cekresiko) {
            cekresikoDao.delete(cekresiko[0]);
            return null;
        }
    }
    private static class DeleteAllCekresikoAsyncTask extends AsyncTask<Void, Void, Void> {
        private CekresikoDao cekresikoDao;

        private DeleteAllCekresikoAsyncTask(CekresikoDao cekresikoDao){
            this.cekresikoDao = cekresikoDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            cekresikoDao.deleteAllCekresiko();
            return null;
        }
    }




}
