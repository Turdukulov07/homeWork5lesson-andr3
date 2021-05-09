package com.example.andro3lesson5.data.retrofit;

import com.example.andro3lesson5.data.models.FilmModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GhibliApi {

    @GET("/films")
    Call<List<FilmModel>> getFilms();

    @GET("/films/{id}")
    Call<FilmModel> getFilmById(
            @Path("id") String id
    );

}
