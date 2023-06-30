package com.yagami.aliencrow;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {

    @GET("character")
    Call<ActividadRespuesta> obtenerLista();

}
