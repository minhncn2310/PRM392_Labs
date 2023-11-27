package com.example.game_project;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.DecelerateInterpolator;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static int CURRENT_BALANCE;
    @SuppressLint("SetTextI18n")
    public void setBalance(int newBalance, TextView tvAmount) {
        CURRENT_BALANCE = newBalance;
        tvAmount.setText("$" + CURRENT_BALANCE);
    }

    private void animateProgression(int progress, SeekBar seekBar) {
        final ObjectAnimator animation = ObjectAnimator.ofInt(seekBar, "progress", 0, progress);
        animation.setDuration(4800);
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
        seekBar.clearAnimation();
    }

    private void hideAllBadges(List<Car> pets) {
        for (Car pet : pets) {
            pet.getBadge().setImageDrawable(null);
        }
    }

    private String resultString(String winner, ArrayList<Ammount> list) {
        String result = "";
        result += "Winner is car " + winner + "\n";
        for (Ammount ammount : list) {
            result += ammount.getHint() + ": ";
            if (ammount.getHint().contains(winner)) {
                result += "+";
            }
            else result += "-";
            result += "$" + ammount.getAmmount() + "\n";
        }
        result += "Your balance: $" + CURRENT_BALANCE;
        return result;
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFinishOnTouchOutside(false);

        TextView tvAmount = findViewById(R.id.tvAmount);

        setBalance(100, tvAmount);


        Button btnStart = findViewById(R.id.btnStart);
        Button btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(View -> {
            Intent intent = new Intent(this, SignInActivity.class);
            this.startActivity(intent);
            this.finish();
        });

        ListView lvAmmount = findViewById(R.id.lvAmmount);
        ArrayList<Ammount> listAmmount = new ArrayList<>();
        AmmountAdapter adapter = new AmmountAdapter(listAmmount, this, R.layout.ammount_listview_item);
        lvAmmount.setAdapter(adapter);

        SeekBar seekBarCar1 = findViewById(R.id.seekBarCar1);
        SeekBar seekBarCar2 = findViewById(R.id.seekBarCar2);
        SeekBar seekBarCar3 = findViewById(R.id.seekBarCar3);

        RadioGroup radioGroup = findViewById(R.id.radioGroup);

        CheckBox cbBlue = findViewById(R.id.checkBoxBlue);
        CheckBox cbYellow = findViewById(R.id.checkBoxYellow);
        CheckBox cbRed = findViewById(R.id.checkBoxRed);

        cbBlue.setOnClickListener(view -> {
            if (cbBlue.isChecked()) {
                listAmmount.add(new Ammount("Blue car:", 1));
            }
            else {
                int index = -1;
                for (int i = 0; i < listAmmount.size(); ++i) {
                    if (listAmmount.get(i).getHint().contains("Blue")) {
                        index = i;
                        break;
                    }
                }
                if (index != -1)
                    listAmmount.remove(index);
            }
            adapter.notifyDataSetChanged();
        });

        cbYellow.setOnClickListener(view -> {
            if (cbYellow.isChecked()) {
                listAmmount.add(new Ammount("Yellow car:", 1));
            }
            else {
                int index = -1;
                for (int i = 0; i < listAmmount.size(); ++i) {
                    if (listAmmount.get(i).getHint().contains("Yellow")) {
                        index = i;
                        break;
                    }
                }
                if (index != -1)
                    listAmmount.remove(index);
            }
            adapter.notifyDataSetChanged();
        });

        cbRed.setOnClickListener(view -> {
            if (cbRed.isChecked()) {
                listAmmount.add(new Ammount("Red car:", 1));
            }
            else {
                int index = -1;
                for (int i = 0; i < listAmmount.size(); ++i) {
                    if (listAmmount.get(i).getHint().contains("Red")) {
                        index = i;
                        break;
                    }
                }
                if (index != -1)
                    listAmmount.remove(index);
            }
            adapter.notifyDataSetChanged();
        });

        ImageView car1Badge = findViewById(R.id.pet1Badge);
        ImageView car2Badge = findViewById(R.id.pet2Badge);
        ImageView car3Badge = findViewById(R.id.pet3Badge);


        List<Car> cars = new ArrayList<>();
        cars.add(new Car(seekBarCar1, cbBlue, car1Badge, "Blue"));
        cars.add(new Car(seekBarCar2, cbRed, car2Badge, "Red"));
        cars.add(new Car(seekBarCar3, cbYellow, car3Badge, "Yellow"));

        hideAllBadges(cars);

        seekBarCar1.setOnTouchListener((v, event) -> true);
        seekBarCar2.setOnTouchListener((v, event) -> true);
        seekBarCar3.setOnTouchListener((v, event) -> true);

        btnStart.setOnClickListener(v -> {
            btnStart.setEnabled(false);
            try {

                if (listAmmount.size() == 0) {
                    Toast.makeText(MainActivity.this, "You must select one car", Toast.LENGTH_SHORT).show();
                    btnStart.setEnabled(true);
                    return;
                }

                int total = 0;
                for (int i = 0; i < listAmmount.size(); ++i) {
                    if (listAmmount.get(i).getAmmount() <= 0) {
                        Toast.makeText(MainActivity.this, "Please enter a valid bet amount", Toast.LENGTH_SHORT).show();
                        btnStart.setEnabled(true);
                        return;
                    }
                    total += listAmmount.get(i).getAmmount();
                }

                if (total > CURRENT_BALANCE) {
                    Toast.makeText(MainActivity.this, "You don't have enough money", Toast.LENGTH_SHORT).show();
                    btnStart.setEnabled(true);
                    return;
                }

                Collections.shuffle(cars);

                Car rank1Car = cars.get(0);
                Car rank2Car = cars.get(1);
                Car rank3Car = cars.get(2);

                animateProgression(100, findViewById(rank1Car.getSeekBar().getId()));
                animateProgression(85, findViewById(rank2Car.getSeekBar().getId()));
                animateProgression(70, findViewById(rank3Car.getSeekBar().getId()));

                new Handler().postDelayed(() -> {
                    rank3Car.getBadge().setImageDrawable(getResources().getDrawable(R.drawable.ic_bronze_medal));
                    rank1Car.getBadge().setImageDrawable(getResources().getDrawable(R.drawable.ic_trophy));
                    rank2Car.getBadge().setImageDrawable(getResources().getDrawable(R.drawable.ic_silver_medal));
                }, 5000);

                new Handler().postDelayed(() -> {

                    for (Ammount ammount : listAmmount) {
                        if (ammount.getHint().contains(rank1Car.getName())) {
                            setBalance(CURRENT_BALANCE + ammount.getAmmount(), tvAmount);
                        }
                        else
                            setBalance(CURRENT_BALANCE - ammount.getAmmount(), tvAmount);
                    }

                    new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Result")
                                .setMessage(resultString(rank1Car.getName(), listAmmount))
                                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                                    hideAllBadges(cars);
                                    for (Car car : cars) {
                                        car.getSeekBar().setProgress(0);
                                    }
                                    if (CURRENT_BALANCE <= 0) {
                                        new AlertDialog.Builder(MainActivity.this)
                                                .setTitle("You are out of money!")
                                                .setMessage("Do you want to reset game?")
                                                .setPositiveButton("Reset", (d, w) -> {
                                                    setBalance(100, tvAmount);
                                                    cbBlue.setChecked(false);
                                                    cbRed.setChecked(false);
                                                    cbYellow.setChecked(false);
                                                    listAmmount.removeAll(listAmmount);
                                                    adapter.notifyDataSetChanged();
                                                })
                                                .setNegativeButton("Quit game", (d, w) -> {
                                                    finish();
                                                    System.exit(0);
                                                })
                                                .setCancelable(false)
                                                .show();
                                    }
                                })
                                .setCancelable(false)
                                .show();
                    btnStart.setEnabled(true);
                }, 5400);
            } catch (Exception e) {
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}