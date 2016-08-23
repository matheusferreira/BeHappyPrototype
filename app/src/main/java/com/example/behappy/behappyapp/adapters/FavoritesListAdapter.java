package com.example.behappy.behappyapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.classes.Ingredient;
import com.example.behappy.behappyapp.classes.News;
import com.example.behappy.behappyapp.classes.Nutrient;
import com.example.behappy.behappyapp.classes.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Matheus on 01/07/2016.
 */
public class FavoritesListAdapter extends BaseExpandableListAdapter {

    public ArrayList<String> groupItem;
    public ArrayList<News> tempChildNews;
    public ArrayList<Product> tempChildProducts;
    public ArrayList<Ingredient> tempChildIngredient;
    public ArrayList<Nutrient> tempChildNutrient;
    public ArrayList<Object> childItem = new ArrayList<Object>();
    public LayoutInflater mInflater;
    public Context mContext;

    public FavoritesListAdapter (ArrayList<String> grList, ArrayList<Object> childItem){
        groupItem = grList;
        this.childItem = childItem;
    }

    public void setInflater (LayoutInflater mInflater, Context context){
        this.mInflater = mInflater;
        this.mContext = context;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        switch (groupPosition){ //TODO: quando ficar pronto o layout dos outros grupos precisa diferenciar o inflater...
            case 0:
                tempChildNews = (ArrayList<News>) childItem.get(groupPosition);
                //if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.listview_favorite_news_item, null);
                //}
                TextView newsTitle = (TextView) convertView.findViewById(R.id.fav_news_title);
                newsTitle.setText(tempChildNews.get(childPosition).getTitle());

                TextView subtitleTxt = (TextView) convertView.findViewById(R.id.fav_news_subtitle);
                subtitleTxt.setText(tempChildNews.get(childPosition).getSubtitle());

                TextView ratingTxt = (TextView) convertView.findViewById(R.id.fav_news_rating);
                ratingTxt.setText("" + tempChildNews.get(childPosition).getRating());

                TextView fontTxt = (TextView) convertView.findViewById(R.id.fav_news_source);
                fontTxt.setText(tempChildNews.get(childPosition).getFont());

                TextView dateTxt = (TextView) convertView.findViewById(R.id.fav_news_date);
                dateTxt.setText(tempChildNews.get(childPosition).getData());

                ImageView imageView = (ImageView)convertView.findViewById(R.id.fav_news_img);
                imageView.setImageResource(tempChildNews.get(childPosition).getImgResourceID());


                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, tempChildNews.get(childPosition).getTitle(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 1:
                tempChildProducts = (ArrayList<Product>) childItem.get(groupPosition);
                //if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.listview_favorite_product_item, null);
                //}
                TextView productName = (TextView) convertView.findViewById(R.id.fav_product_name);
                productName.setText(tempChildProducts.get(childPosition).getName());

                TextView productRating = (TextView) convertView.findViewById(R.id.fav_product_rating);
                productRating.setText("" + tempChildProducts.get(childPosition).getRating());

                TextView manufactureName = (TextView) convertView.findViewById(R.id.fav_product_manufacturer_name);
                manufactureName.setText("" + tempChildProducts.get(childPosition).getManufacturer().getName());

                ImageView productImage = (ImageView)convertView.findViewById(R.id.fav_product_img);
                //productImage.setImageResource(tempChildProducts.get(childPosition).getImgResourceID());
                Picasso.with(mContext).load(tempChildProducts.get(childPosition).getImgResourceID()).into(productImage);

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, tempChildProducts.get(childPosition).getName(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 2:
                tempChildIngredient = (ArrayList<Ingredient>) childItem.get(groupPosition);
                convertView = mInflater.inflate(R.layout.listview_favorite_ingredient_item, null);
                TextView ingredientName = (TextView) convertView.findViewById(R.id.fav_ingredient_name);
                ingredientName.setText(tempChildIngredient.get(childPosition).getName());

                TextView ingredientRating = (TextView) convertView.findViewById(R.id.fav_ingredient_rating);
                ingredientRating.setText("" + tempChildIngredient.get(childPosition).getRating());

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, tempChildIngredient.get(childPosition).getName(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case 3:
                tempChildNutrient = (ArrayList<Nutrient>) childItem.get(groupPosition);
                convertView = mInflater.inflate(R.layout.listview_favorite_nutrient_item, null); //por enquanto o display é igual a de um ingrediente.
                TextView nutrientName = (TextView) convertView.findViewById(R.id.fav_nutrient_name);
                nutrientName.setText(tempChildNutrient.get(childPosition).getName());

                TextView nutrientRating = (TextView) convertView.findViewById(R.id.fav_nutrient_rating);
                nutrientRating.setText("" + tempChildNutrient.get(childPosition).getRating());

                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, tempChildNutrient.get(childPosition).getName(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            default:
                //error handler
                break;
        }

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList<String>) childItem.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public int getGroupCount() {
        return groupItem.size();
    }

    @Override
    public void onGroupCollapsed(int groupPosition) {
        super.onGroupCollapsed(groupPosition);
    }

    @Override
    public void onGroupExpanded(int groupPosition) {
        super.onGroupExpanded(groupPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.expandablelistview_favorites_grouprow, null);
        }
        ((CheckedTextView) convertView).setText(groupItem.get(groupPosition));
        ((CheckedTextView) convertView).setChecked(isExpanded);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

}
