package com.example.andro3lesson5.data.models;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.andro3lesson5.R;
import com.example.andro3lesson5.data.dao.App;
import com.example.andro3lesson5.ui.FavoriteFilmActivity;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmHolder> {
    private List<FilmModel> filmList = new ArrayList<>();
    private OnClickListener listener;
    private Context context;

    public FilmAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public FilmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_film, parent,false);
        return new FilmHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmHolder holder, int position) {
        holder.onBind(filmList.get(position));

    }

    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public void setClickListener(OnClickListener listener){
        this.listener = listener;
    }

    public void setFilmList(List<FilmModel> body){
        filmList.addAll(body);
        notifyDataSetChanged();
    }

    public class FilmHolder extends RecyclerView.ViewHolder{
        private final TextView textTitle;
        private final TextView textDate;
        private final ImageView imageView;

        public FilmHolder(@NonNull View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.film_title);
            textDate = itemView.findViewById(R.id.film_release_date);
            imageView = itemView.findViewById(R.id.btn_add_favorite);
            if (context.getClass().getSimpleName().equals(FavoriteFilmActivity.class.getSimpleName()))
                imageView.setVisibility(View.INVISIBLE);
            imageView.setOnClickListener(v -> {
                addFilmFovarite(filmList.get(getAdapterPosition()));
            });
        }

        private void addFilmFovarite(FilmModel filmModel) {
            if (App.getAppDataBase().filmDao().getById(filmModel.getId()))
                Toast.makeText(context, "?????? ??????????????????", Toast.LENGTH_SHORT).show();
            else {
                App.getAppDataBase().filmDao().insertAll(filmModel);
                Toast.makeText(context, "?????????????????? ?? ??????????????????", Toast.LENGTH_SHORT).show();
            }

        }

        public void onBind(FilmModel film) {
            textTitle.setText(film.getTitle());
            textDate.setText(film.getReleaseDate());
            itemView.setOnClickListener(v -> listener.onClick(filmList.get(getAdapterPosition())));
        }
    }
    public interface OnClickListener {
        void onClick(FilmModel film);
    }
}
