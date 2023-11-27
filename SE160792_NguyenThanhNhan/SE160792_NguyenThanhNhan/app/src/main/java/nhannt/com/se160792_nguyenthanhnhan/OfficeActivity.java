package nhannt.com.se160792_nguyenthanhnhan;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import nhannt.com.se160792_nguyenthanhnhan.api.OfficeRepository;
import nhannt.com.se160792_nguyenthanhnhan.api.OfficeService;
import nhannt.com.se160792_nguyenthanhnhan.model.Office;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfficeActivity extends AppCompatActivity {

    OfficeService officeService;
    Button btnSave, btnBack;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_office);

        name = findViewById(R.id.editTextName);
        btnSave = findViewById(R.id.buttonSave);
        btnBack = findViewById(R.id.buttonBack);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OfficeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        officeService = OfficeRepository.getOfficeService();
    }

    private void save() {
        String officeName = name.getText().toString();

        Office office = new Office(officeName);
        try {
            Call<Office> call = officeService.createOffice(office);
            call.enqueue(new Callback<Office>() {
                @Override
                public void onResponse(Call<Office> call, Response<Office> response) {
                    if (response.body() != null) {
                        Toast.makeText(OfficeActivity.this, "Create successfully"
                                , Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Office> call, Throwable t) {
                    Toast.makeText(OfficeActivity.this, "Create fail"
                            , Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.e("Fail!", e.getMessage());
        }
    }
}