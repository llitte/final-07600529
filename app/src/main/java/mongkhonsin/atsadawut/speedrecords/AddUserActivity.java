package mongkhonsin.atsadawut.speedrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

import mongkhonsin.atsadawut.speedrecords.db.AppDatabase;
import mongkhonsin.atsadawut.speedrecords.model.User;
import mongkhonsin.atsadawut.speedrecords.util.AppExecutors;

public class AddUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText distanceEditText, durationEditText;
                distanceEditText = findViewById(R.id.ditstance_edit_text);
                durationEditText = findViewById(R.id.duration_edit_text);

                final String distanceStr = distanceEditText.getText().toString();
                final String durationStr = durationEditText.getText().toString();
                double distenceMeter = Double.parseDouble(distanceStr);
                double durationSec = Double.parseDouble(durationStr);

                /*double distenceKilometer = distenceMeter/1000;
                double timeHour = (durationSec/60)/60;
                double answer = distenceKilometer/timeHour;

                String speedCal = String.format(Locale.getDefault(), "%.2f", answer);
*/
                final User user = new User(0, distenceMeter, durationSec);
                AppExecutors executors = new AppExecutors();
                executors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        AppDatabase db = AppDatabase.getInstance(AddUserActivity.this);
                        db.userDao().addUser(user);
                        finish();
                    }
                });
            }
        });

    }
}