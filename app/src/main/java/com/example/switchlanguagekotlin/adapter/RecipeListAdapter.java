package com.example.switchlanguagekotlin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.switchlanguagekotlin.R;
import com.example.switchlanguagekotlin.model.RecipeListModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends ArrayAdapter<RecipeListModel> {
    Context context;
    int resource;
    ArrayList<RecipeListModel> objects;

    public RecipeListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<RecipeListModel> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(resource,null,false);
        ViewHolder viewHolder=new ViewHolder();
        viewHolder.dietary=view.findViewById(R.id.dietary);
        viewHolder.prepTime=view.findViewById(R.id.prepTime);
        viewHolder.recipeName=view.findViewById(R.id.recipeName);
        viewHolder.moveToNext=view.findViewById(R.id.moveToNext);

        viewHolder.dietary.setText(objects.get(position).getDietary());
        viewHolder.recipeName.setText(objects.get(position).getRecipeName());
        viewHolder.prepTime.setText(objects.get(position).getPreparationTime());
        viewHolder.moveToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String procedure=objects.get(position).getProcedure();
                System.out.println("Procedure: "+procedure);
            }
        });
        return view;
    }

    private static class ViewHolder{
        TextView prepTime,dietary,recipeName,moveToNext;
    }
}
