package kelompok10pam.develops.pedulicovid19;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CekresikoDao {
    @Insert
    void insert(Cekresiko cekresiko);
    @Update
    void update (Cekresiko cekresiko);
    @Delete
    void delete (Cekresiko cekresiko);

    @Query("DELETE FROM cekresiko_table")
    void deleteAllCekresiko();

    @Query("SELECT * FROM cekresiko_table")
    LiveData<List<Cekresiko>> getAllCekresiko();

}
