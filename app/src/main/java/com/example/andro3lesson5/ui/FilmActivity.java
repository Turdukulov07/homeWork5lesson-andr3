package com.example.andro3lesson5.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andro3lesson5.data.models.FilmModel;
import com.example.andro3lesson5.data.retrofit.RetrofitBuilder;
import com.example.andro3lesson5.databinding.ActivityFilmBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmActivity extends AppCompatActivity {

    private ActivityFilmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFilmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (getIntent() != null) {
            load(getIntent().getStringExtra("ID"));
        }
    }

    private void load(String id) {
        RetrofitBuilder.getInstance().getFilmById(id).enqueue(new Callback<FilmModel>() {
            @Override
            public void onResponse(Call<FilmModel> call, Response<FilmModel> response) {
                if (response.body() != null)
                    setView(response.body());
                else
                    Log.d("tag", "error");
            }

            @Override
            public void onFailure(Call<FilmModel> call, Throwable t) {

            }
        });
    }

    private void setView(FilmModel body) {
        binding.txtTitle.setText(body.getTitle());
        binding.txtDescription.setText(body.getDescription());
        binding.txtDirector.setText(body.getDirector());
        binding.txtProducer.setText(body.getProducer());
        binding.txtReleaseDate.setText(body.getReleaseDate());
    }


}