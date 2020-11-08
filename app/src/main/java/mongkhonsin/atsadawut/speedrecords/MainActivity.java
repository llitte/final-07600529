package mongkhonsin.atsadawut.speedrecords;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import mongkhonsin.atsadawut.speedrecords.adapter.UserAdapter;
import mongkhonsin.atsadawut.speedrecords.db.AppDatabase;
import mongkhonsin.atsadawut.speedrecords.model.User;
import mongkhonsin.atsadawut.speedrecords.util.AppExecutors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    private RecyclerView mRecyclerView;
    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final User[] users = db.userDao().getAllUsers();
                TextView total = findViewById(R.id.total_text);
                TextView overLimit = findViewById(R.id.over_limit_text);
                total.setText("TOTAL: " + users.length);
                /*for(int i = 0; i < users.length; i++){
                    User u = db.userDao().getUserById(i);
                    double distenceKilometer = u.distance/1000;
                    double timeHour = (u.duration/60)/60;
                    double answer = distenceKilometer/timeHour;
                }*/

                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        UserAdapter adapter = new UserAdapter(MainActivity.this, users);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.limit_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddUserActivity.class);
                startActivity(i);
            }
        });
    }
}