package com.example.behappy.behappyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.classes.Ingredient;
import com.example.behappy.behappyapp.classes.News;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 11/08/2016.
 */
public class ProductIngredientListAdapter extends ArrayAdapter<Ingredient> {

    private List<Ingredient> ingredients = new ArrayList<Ingredient>();
    LayoutInflater mInflater;
    Context mContext;

    public ProductIngredientListAdapter(Context context, int resource, List<Ingredient> objects) {
        super(context, resource, objects);
        this.ingredients = objects;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = mInflater.inflate(R.layout.listview_product_ingredient, parent, false);
        }
        Ingredient currentIngredient = ingredients.get(position);
        //return super.getView(position, convertView, parent);

        TextView nameTxt = (TextView) itemView.findViewById(R.id.product_ingredient_name);
        nameTxt.setText(currentIngredient.getName());

        TextView categoryTxt = (TextView) itemView.findViewById(R.id.product_ingredient_category);
        categoryTxt.setText(currentIngredient.getCategory());

        TextView quantity = (TextView) itemView.findViewById(R.id.product_ingredient_qtd);
        quantity.setText(currentIngredient.getQuantityNumber() + currentIngredient.getQuantityType());

        return itemView;

    }


}