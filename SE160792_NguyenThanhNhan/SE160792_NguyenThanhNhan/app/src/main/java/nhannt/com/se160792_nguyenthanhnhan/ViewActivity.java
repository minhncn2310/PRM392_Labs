package nhannt.com.se160792_nguyenthanhnhan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import nhannt.com.se160792_nguyenthanhnhan.adapter.TraineeAdapter;
import nhannt.com.se160792_nguyenthanhnhan.api.TraineeRepository;
import nhannt.com.se160792_nguyenthanhnhan.api.TraineeService;
import nhannt.com.se160792_nguyenthanhnhan.model.Trainee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewActivity extends AppCompatActivity {


    TraineeService traineeService;
    ArrayList<Trainee> traineeArrayList =  new ArrayList<>();
    TraineeAdapter traineeAdapter;
    ListView traineeListView;
    Button btnBack;
    EditText updateName, updateDate, updateSalary, updateGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        btnBack = findViewById(R.id.btnBack);
        traineeListView = (ListView) findViewById(R.id.list_item);
        updateName = findViewById(R.id.updateName);
        updateDate = findViewById(R.id.updateDate);
        updateSalary = findViewById(R.id.updateSalary);
        updateGender = findViewById(R.id.updateGender);

        traineeService = TraineeRepository.getTraineeService();
        view();

        traineeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trainee trainee = traineeArrayList.get(position);
                update(trainee);
            }
        });

        traineeListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Trainee trainee = traineeArrayList.get(position);
                delete(trainee);
                return true;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void view() {
        try {
            Call<Trainee[]> call = traineeService.getAllTrainees();
            call.enqueue(new Callback<Trainee[]>() {
                @Override
                public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                    Trainee[] trainees = response.body();
                    if (trainees == null) {
                        Toast.makeText(ViewActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    for (Trainee trainee : trainees) {
                        traineeArrayList.add(trainee);
                    }

                    traineeAdapter = new TraineeAdapter(ViewActivity.this, R.layout.item_layout, traineeArrayList);
                    traineeListView.setAdapter(traineeAdapter);
                }

                @Override
                public void onFailure(Call<Trainee[]> call, Throwable t) {
                    Toast.makeText(ViewActivity.this, "Empty data", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.e("Fail!", e.getMessage());
        }
    }

    private void delete(Trainee trainee) {
        try {
            Call<Trainee> call = traineeService.deleteTrainees(trainee.getId());
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(ViewActivity.this, "Delete successfully!", Toast.LENGTH_SHORT).show();
                        traineeArrayList.remove(trainee);
                        traineeAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ViewActivity.this, "Delete failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(ViewActivity.this, "Delete failed!", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            Log.e("Fail!", e.getMessage());
        }
    }

    private void update(Trainee trainee) {
        String name = updateName.getText().toString();
        String date = updateDate.getText().toString();
        String salary = updateSalary.getText().toString();
        String gender = updateGender.getText().toString();

        //Validate
        if (name.isEmpty()) {
            name = trainee.getName();
        }
        if (date.isEmpty()) {
            date = trainee.getDate();
        }
        if (salary.isEmpty()) {
            salary = trainee.getSalary();
        }
        if (gender.isEmpty()) {
            gender = trainee.getGender();
        }

        Trainee updateTrainee = new Trainee(name, date, salary, gender);
        try {
            Call<Trainee> call = traineeService.updateTrainees(trainee.getId(), updateTrainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.isSuccessful()) {
                        Trainee updatedTrainee = response.body();
                        if (updatedTrainee != null) {
                            // Update the trainee in the list
                            int index = traineeArrayList.indexOf(trainee);
                            traineeArrayList.set(index, updatedTrainee);
                            traineeAdapter.notifyDataSetChanged();
                            Toast.makeText(ViewActivity.this, "Update successfully!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ViewActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ViewActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(ViewActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Log.e("Fail!", e.getMessage());
        }
    }
}