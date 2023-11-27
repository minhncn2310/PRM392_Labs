package nhannt.com.se160792_nguyenthanhnhan.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;

import nhannt.com.se160792_nguyenthanhnhan.R;
import nhannt.com.se160792_nguyenthanhnhan.model.Trainee;

public class TraineeAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private List<Trainee> trainees;

    public TraineeAdapter(Context context, int layout, List<Trainee> trainees) {
        this.context = context;
        this.layout = layout;
        this.trainees = trainees;
    }

    @Override
    public int getCount() {
        return trainees.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(layout, null);

        TextView name = (TextView) convertView.findViewById(R.id.textViewName);
        TextView date = (TextView) convertView.findViewById(R.id.textViewDate);
        TextView salary = (TextView) convertView.findViewById(R.id.textViewSalary);
        TextView gender = (TextView) convertView.findViewById(R.id.textViewGender);

        Trainee trainee = trainees.get(position);

        name.setText(trainee.getName());
        date.setText(trainee.getDate());
        salary.setText(trainee.getSalary());
        gender.setText(trainee.getGender());

        return convertView;
    }
}