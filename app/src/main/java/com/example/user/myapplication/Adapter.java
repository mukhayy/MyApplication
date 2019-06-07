package com.example.user.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.myapplication.Models.Weather;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    List<com.example.user.myapplication.Models.List> list = new ArrayList<>();
    private AdapterView.OnItemClickListener onItemClickListener;


    public Adapter(AdapterView.OnItemClickListener onItemClickListener, List<com.example.user.myapplication.Models.List> list){
        this.onItemClickListener=onItemClickListener;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        //inflate layout with cardView
        View view = inflater.inflate(R.layout.card, viewGroup, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        com.example.user.myapplication.Models.List weatherList = list.get(position);
        String UrlIcon = "http://openweathermap.org/img/w/" + weatherList.getWeather().get(0).getIcon() + ".png";

        Picasso.get()
                .load(UrlIcon)
                .fit()
                .centerCrop()
                .into(viewHolder.weatherIcon);

        viewHolder.cityName.setText(weatherList.getName());
        int temp = Integer.valueOf(weatherList.getMain().getTemp().intValue());
        viewHolder.cityTemperature.setText(String.valueOf(temp)+"°");
        viewHolder.weatherCondition.setText(weatherList.getWeather().get(0).getDescription());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public com.example.user.myapplication.Models.List getItem(int position){
        return list.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.name)
        TextView cityName;

        @BindView(R.id.temperature)
        TextView cityTemperature;

        @BindView(R.id.condition)
        TextView weatherCondition;

        @BindView(R.id.icon)
        ImageView weatherIcon;

        @BindView(R.id.mainLayout)
        LinearLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
            mainLayout.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(null, v, getAdapterPosition(), v.getId());
        }
    }


}
