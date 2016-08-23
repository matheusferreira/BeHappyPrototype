package com.example.behappy.behappyapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.classes.Comment;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by Matheus on 01/07/2016.
 */
public class CommentListAdapter extends ArrayAdapter<Comment> {
    private List<Comment> commentList = new ArrayList<Comment>();
    LayoutInflater mInflater;
    Context mContext;

    public CommentListAdapter(Context context, int resource, List<Comment> objects) {
        super(context, resource, objects);
        this.commentList = objects;
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = mInflater.inflate(R.layout.listview_comments, parent, false);
        }
        Comment currentComment = commentList.get(position);
        //return super.getView(position, convertView, parent);

        ImageView imageView = (ImageView)itemView.findViewById(R.id.comment_useravatar);
        //new DownloadImageTask(imageView).execute(currentNews.getImgPath()); //tá bugando a UI... investigar
        if(currentComment.getAvatarResourceID() != 0) {
            //imageView.setImageResource(currentComment.getAvatarResourceID());
            Picasso.with(mContext)
                    .load(currentComment.getAvatarResourceID())
                    .resize(50, 50)
                    .centerCrop()
                    .into(imageView);

        }
        else{
            if(currentComment.getAvatarURL() != ""){
                Picasso.with(mContext)
                        .load(currentComment.getAvatarURL())
                        .resize(50, 50)
                        .centerCrop()
                        .into(imageView);
            }
        }
        TextView username = (TextView) itemView.findViewById(R.id.comment_title);
        username.setText(currentComment.getUsername());

        TextView ratingTxt = (TextView) itemView.findViewById(R.id.comment_rating);
        ratingTxt.setText("" + currentComment.getRating());

        TextView commentBody = (TextView) itemView.findViewById(R.id.comment_body);
        commentBody.setText(currentComment.getComment());

        TextView dateTxt = (TextView) itemView.findViewById(R.id.comment_data);
        dateTxt.setText(currentComment.getDate());

        //TODO: rating btn logic
        ImageButton upvoteBtn = (ImageButton) itemView.findViewById(R.id.comment_upvote);
        ImageButton downvoteBtn = (ImageButton) itemView.findViewById(R.id.comment_downvote);

        return itemView;

    }
}
