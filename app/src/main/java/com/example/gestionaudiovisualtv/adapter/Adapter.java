package com.example.gestionaudiovisualtv.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.gestionaudiovisualtv.R;
import com.example.gestionaudiovisualtv.model.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context mCtxt;
    private List<Proyecto> proyectoList;
    private List<Proyecto> origlinalItems;

    public Adapter(Context mCtxt,List<Proyecto> proyectoList) {
        this.mCtxt = mCtxt;
        this.proyectoList = proyectoList;
        this.origlinalItems = new ArrayList<>();
        origlinalItems.addAll(proyectoList);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        LayoutInflater inflater = LayoutInflater.from(mCtxt);
        View view = inflater.inflate(R.layout.lista_latout, null);
        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Proyecto proyecto = proyectoList.get(position);
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        holder.textViewNombre.setText(proyecto.getNombre());//y resto despues.
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return proyectoList.size();
    }


    public void filterName(String strSearch){
        proyectoList.clear();
        if (strSearch.length() == 0){
            proyectoList.addAll(origlinalItems);
        }
        else{
            for (Proyecto i: origlinalItems){
                if (i.getNombre().toLowerCase().contains(strSearch)){
                    proyectoList.add(i);
                }
            }
        }
        notifyDataSetChanged();
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNombre;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textViewNombre = (TextView) view.findViewById(R.id.textViewNombre);
        }
    }
}

