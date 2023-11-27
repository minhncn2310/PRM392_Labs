package nhannt.com.se160792_nguyenthanhnhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import nhannt.com.se160792_nguyenthanhnhan.api.TraineeRepository;
import nhannt.com.se160792_nguyenthanhnhan.api.TraineeService;
import nhannt.com.se160792_nguyenthanhnhan.model.Trainee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TraineeService traineeService;
    EditText etName, etDate, etSalary, etGender;
    Button button, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etDate = findViewById(R.id.etDate);
        etSalary = findViewById(R.id.etSalary);
        etGender = findViewById(R.id.etGender);
        button = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class );
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OfficeActivity.class);
                startActivity(intent);
            }
        });

        traineeService = TraineeRepository.getTraineeService();
    }
    private void save() {
        String name = etName.getText().toString();
        String date = etDate.getText().toString();
        String salary = etSalary.getText().toString();
        String gender = etGender.getText().toString();

        Trainee trainee = new Trainee(name, date, salary, gender);
        try {
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "Save successfully"
                                , Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save fail"
                            , Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e("Fail!", e.getMessage());
        }
    }
}