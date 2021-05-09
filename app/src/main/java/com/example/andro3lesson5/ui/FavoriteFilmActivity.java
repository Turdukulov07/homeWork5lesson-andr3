package com.example.andro3lesson5.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.andro3lesson5.data.dao.App;
import com.example.andro3lesson5.data.models.FilmAdapter;
import com.example.andro3lesson5.data.models.FilmModel;
import com.example.andro3lesson5.databinding.ActivityFavoriteFilmBinding;

import java.util.List;

public class FavoriteFilmActivity extends AppCompatActivity implements FilmAdapter.OnClickListener {
    private ActivityFavoriteFilmBinding binding;
    private FilmAdapter adapter;
    private List<FilmModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteFilmBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        if (App.getAppDataBase().filmDao().getAllElements() == null)
            Toast.makeText(this, "Нет сохраненных фильмов", Toast.LENGTH_SHORT).show();
        else {
            list = App.getAppDataBase().filmDao().getAllElements();
            load();
        }
    }

    private void load() {
        adapter = new FilmAdapter(this);
        adapter.setFilmList(list);
        binding.recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
    }

    @Override
    public void onClick(FilmModel film) {
        Intent intent = new Intent(this, com.example.andro3lesson5.ui.FilmActivity.class);
        intent.putExtra("ID", film.getId());
        startActivity(intent);
    }
}