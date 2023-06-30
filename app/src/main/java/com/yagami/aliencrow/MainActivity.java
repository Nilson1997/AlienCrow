package com.yagami.aliencrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    RecyclerView recyclerView;

    ListaProyecto listaProyecto;

    Retrofit retrofit;

    private final String TAG="tttapi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById((R.id.recycler1));

        listaProyecto=new ListaProyecto(this);
        recyclerView.setAdapter(listaProyecto);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, RecyclerView.VERTICAL, true);
        recyclerView.setLayoutManager(linearLayoutManager);

        retrofit=new Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/api/character")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        imageView=findViewById(R.id.imagenGlide);
        obtenerDatos();

    }
    private void obtenerDatos(){
        Api service=retrofit.create(Api.class);
        Call<ActividadRespuesta>actividadRespuestaCall=service.obtenerLista();

        actividadRespuestaCall.enqueue(new Callback<ActividadRespuesta>(){
            @Override

            public void onResponse(Call<ActividadRespuesta> call, Response<ActividadRespuesta> response){
                if (response.isSuccessful()){
                    ActividadRespuesta actividadRespuesta=response.body();
                    List<Proyecto> listaproyecto=actividadRespuesta.getResult();
                    for (int i=0; i<listaproyecto.size(); i++){
                        Proyecto p=listaproyecto.get(i);
                        Log.e(TAG, "proyecto" + p.getNombre());
                    }
                    listaproyecto.add((ArrayList<Proyecto>) listaproyecto);

                }
                else {
                    Log.e(TAG, "onResponse" + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<ActividadRespuesta> call, Throwable t){
                Log.e(TAG, "onFailure" + t.getMessage());

            }
        });
    }

    private void setImageView(){
        String url="https://rickandmortyapi.com/api/character";
        Glide.with(this)
                .load(url)
                .into(imageView);
    }
}