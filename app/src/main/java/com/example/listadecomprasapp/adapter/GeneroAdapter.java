package com.example.listadecomprasapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import com.example.listadecomprasapp.model.Genero;


public class GeneroAdapter extends ArrayAdapter<Genero> {

    private int rId;

    public GeneroAdapter(Context context, int resource, List<Genero> objects) {
        super(context, resource, objects);
        rId = resource;
    }

    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        /*View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(rId,null);
        }*/

        Genero genero = getItem(position);

        TextView textGenero = (TextView)super.getView(position,currentView,parent);
        textGenero.setText(genero.getNome());

        return textGenero;
    }

    @Override
    public View getDropDownView(int position, View currentView, ViewGroup parent) {
        Genero genero = getItem(position);
        TextView label = (TextView) super.getDropDownView(position, currentView, parent);
        label.setText(genero.getNome());

        return label;
    }
}
