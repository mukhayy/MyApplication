package com.example.user.myapplication.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.user.myapplication.Adapter.Adapter;
import com.example.user.myapplication.Models.DataResponse;
import com.example.user.myapplication.Network.RetrofitClient;
import com.example.user.myapplication.Network.Service;
import com.example.user.myapplication.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    String appid = "6f341c784e7340375409af9fc6b7680e";
    String id = "1512569,2643743,745042,2964574,2643123,524901,703448,1217662,1850147,6359304,6454307";

    @BindView(R.id.recyclerList)
    RecyclerView recyclerList;

    Adapter adapter;
    List<com.example.user.myapplication.Models.List> list= new ArrayList<>();

    com.example.user.myapplication.Models.List list_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Service service = RetrofitClient.getRetrofitClient().create(Service.class);

        Call<DataResponse> call = service.getData(id,appid);

        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {

                if (response.isSuccessful()) {
                    list = response.body().getList();
                    generateMoviesList(list);

                } else {
                    try {
                       // Log.e("Error",response.errorBody().string());
                        Toast.makeText(MainActivity.this, response.errorBody().string(), Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failure in data response" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void generateMoviesList(List<com.example.user.myapplication.Models.List> list){
        adapter = new Adapter(this, list);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerList.setLayoutManager(layoutManager);
        recyclerList.setAdapter(adapter);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        list_ = adapter.getItem(position);


        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("name", list_.getName());
        intent.putExtra("icon", list_.getWeather().get(0).getIcon());
        intent.putExtra("min", String.valueOf(list_.getMain().getTempMin().intValue()));
        intent.putExtra("max", String.valueOf(list_.getMain().getTempMax().intValue()));
        intent.putExtra("wind", String.valueOf(list_.getWind().getSpeed()));
        intent.putExtra("condition", list_.getWeather().get(0).getDescription());
        intent.putExtra("hum", String.valueOf(list_.getMain().getHumidity()));
        startActivity(intent);

    }

}
