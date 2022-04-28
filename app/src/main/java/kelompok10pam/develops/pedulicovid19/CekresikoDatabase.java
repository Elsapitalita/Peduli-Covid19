package kelompok10pam.develops.pedulicovid19;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;


@Database(entities = {Cekresiko.class}, version = 1)
public abstract class CekresikoDatabase extends RoomDatabase {
    private static CekresikoDatabase instance;

    public abstract CekresikoDao cekresikoDao();

    public static synchronized CekresikoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),CekresikoDatabase.class, "cekresiko_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulatedDbAsyncTask(instance).execute();
        }
    };
    private static class PopulatedDbAsyncTask extends AsyncTask <Void, Void, Void>{
        private CekresikoDao beasiswaDao;
        private PopulatedDbAsyncTask(CekresikoDatabase db){
            beasiswaDao = db.cekresikoDao();
        }
        @Override
        protected Void doInBackground(Void... voids){
            beasiswaDao.insert(new Cekresiko("Title 1", "Description 1"));
            beasiswaDao.insert(new Cekresiko("Title 2", "Description 2"));
            beasiswaDao.insert(new Cekresiko("Title 3", "Description 3"));
            return null;
        }
    }
}

