package kelompok10pam.develops.pedulicovid19;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cekresiko_table")
public class Cekresiko {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String pilihan;

    public Cekresiko(String title, String pilihan) {
        this.title = title;
        this.pilihan = pilihan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPilihan() {
        return pilihan;
    }

    public void setPilihan(String pilihan) {
        this.pilihan = pilihan;
    }
}