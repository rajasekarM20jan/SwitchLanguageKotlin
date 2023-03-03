package com.example.switchlanguagekotlin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.switchlanguagekotlin.NewRecipePage;
import com.example.switchlanguagekotlin.R;
import com.example.switchlanguagekotlin.model.IngredientModel;

import java.util.ArrayList;
import java.util.List;

public class IngredientAdapter extends ArrayAdapter<IngredientModel> {
    Context context;
    int resource;
    ArrayList<IngredientModel> objects;
    NewRecipePage recipePage;


    public IngredientAdapter(@NonNull Context context, int resource, @NonNull ArrayList<IngredientModel> objects,NewRecipePage recipePage) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
        this.recipePage=recipePage;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(resource,null,false);
        ViewHolder viewHolder=new ViewHolder();
        viewHolder.imageView=view.findViewById(R.id.deleteIngredient);
        viewHolder.textView=view.findViewById(R.id.ingredientData);

        viewHolder.textView.setText(objects.get(position).getIngredientData());

        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objects.remove(position);
                recipePage.resetAdapter();

            }
        });

        return view;
    }

    private static class ViewHolder{
        TextView textView;
        ImageView imageView;
    }
}
