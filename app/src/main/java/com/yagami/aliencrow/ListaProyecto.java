package com.yagami.aliencrow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ListaProyecto extends RecyclerView.Adapter<ListaProyecto.ViewHolder> {
  private RecyclerView recyclerView;

  private ArrayList<Proyecto> dataset;

  private ListaProyecto listaProyecto;

  private Context context;

  public class ViewHolder extends RecyclerView{
      private ImageView imageView;

      private TextView textView;

      public ViewHolder(@NonNull View itemView) {
          super(itemView);
          textView=itemView.findViewById(R.id.textView2);
          imageView=itemView.findViewById(R.id.imageView);
      }
    }

    public ListaProyecto(Context context) {
      this.context=context;
      dataset=new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
      View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_proyecto, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {return  dataset.size();}

    @Override
       public void onBindViewHolder(ViewHolder holder, int position) {
          Proyecto p=dataset.get(position);
          holder.textView.setText(p.getNombre());

        Glide.with(context)
                .load("https://rickandmortyapi.com/api/character")
                .into(holder.imageView);
    }

    public void add(ArrayList<Proyecto> listaProyecto){
      dataset.addAll(listaProyecto);
      notifyDataSetChanged();
    }
}
