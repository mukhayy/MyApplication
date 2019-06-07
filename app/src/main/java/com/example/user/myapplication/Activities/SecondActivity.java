package com.example.user.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.user.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SecondActivity extends AppCompatActivity {

    @BindView(R.id.name2)
    TextView name;

    @BindView(R.id.icon2)
    ImageView icon;

    @BindView(R.id.minTemperature)
    TextView minTemp;

    @BindView(R.id.maxTemperature)
    TextView maxTemp;

    @BindView(R.id.condition2)
    TextView con;

    @BindView(R.id.windSpeed)
    TextView windSpeed;

    @BindView(R.id.hum)
    TextView hum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ButterKnife.bind(this);

        Intent intent = getIntent();
        String name_ = intent.getStringExtra("name");
        String icon_ = intent.getStringExtra("icon");
        String min = intent.getStringExtra("min");
        String max = intent.getStringExtra("max");
        String wind = intent.getStringExtra("wind");
        String condition = intent.getStringExtra("condition");
        String hum_ = intent.getStringExtra("hum");

        name.setText(name_);

        String UrlIcon = "http://openweathermap.org/img/w/" + icon_ + ".png";

        Glide.with(this)
                .load(UrlIcon)
                .centerCrop()
                .placeholder(R.drawable.sunrise)
                .into(icon);

        minTemp.setText("Min: " + min+"°");
        maxTemp.setText("Max: " + max+"°");
        con.setText(condition);
        windSpeed.setText("Wind Speed: "+wind);
        hum.setText("Humidity: " + hum_ + "%");



    }
}
