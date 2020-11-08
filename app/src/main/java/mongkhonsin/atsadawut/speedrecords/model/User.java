package mongkhonsin.atsadawut.speedrecords.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users")
public class User {

    @PrimaryKey(autoGenerate = true)
    public final int id;

    @ColumnInfo(name = "distance")
    public final double distance;

    @ColumnInfo(name = "duration")
    public final double duration;

    public User(int id, double distance, double duration) {
        this.id = id;
        this.distance = distance;
        this.duration = duration;
    }
}
