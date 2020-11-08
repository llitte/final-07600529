package mongkhonsin.atsadawut.speedrecords.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import mongkhonsin.atsadawut.speedrecords.R;
import mongkhonsin.atsadawut.speedrecords.model.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {

    private Context mContext;
    private User[] mUsers;

    public UserAdapter(Context context, User[] users) {
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.limit_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = mUsers[position];

        double distenceKilometer = user.distance/1000;
        double timeHour = (user.duration/60)/60;
        double answer = distenceKilometer/timeHour;

        String speedCal = String.format(Locale.getDefault(), "%.1f", answer);
        String distanceOnePointFloat = String.format(Locale.getDefault(), "%.1f", user.distance);
        String durationOnePointFloat = String.format(Locale.getDefault(), "%.1f", user.duration);
        holder.distanceTextView.setText(speedCal + " KM/H");
        holder.durationTextView.setText(distanceOnePointFloat +"METERS, " + durationOnePointFloat + " SECONDS");
        if(answer > 80){
            holder.overLimitImg.setImageResource(R.drawable.red_cow);
        }
    }

    @Override
    public int getItemCount() {
        return mUsers.length;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView distanceTextView;
        TextView durationTextView;
        ImageView overLimitImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            this.distanceTextView = itemView.findViewById(R.id.speed_text);
            this.durationTextView = itemView.findViewById(R.id.distance_text);
            this.overLimitImg = itemView.findViewById(R.id.over_limit_imageView);
        }
    }
}
