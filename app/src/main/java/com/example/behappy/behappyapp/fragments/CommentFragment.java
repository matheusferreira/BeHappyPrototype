package com.example.behappy.behappyapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.behappy.behappyapp.R;
import com.example.behappy.behappyapp.adapters.CommentListAdapter;
import com.example.behappy.behappyapp.classes.Comment;



import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matheus on 30/06/2016.
 */
public class CommentFragment extends Fragment {
    private List<Comment> commentList = new ArrayList<Comment>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_comments, container, false);
        populateCommentList();
        populateCommentView(root);

        return root;
    }

    private void populateCommentView(View root) {
        ArrayAdapter<Comment> adapter = new CommentListAdapter(getActivity(), R.layout.listview_comments, commentList);
        ListView list = (ListView) root.findViewById(R.id.commentListView);
        list.setAdapter(adapter);
    }

    private void populateCommentList() {
        //int id, String username, int avatarResourceID, String date, String comment, int rating
        commentList.add(new Comment(1, "João Felipe", 0, "", "01/07/2016", "Alguem pode me recomendar um substituto para refrigerante? Eu meio que sou viciado.", 2 ));
        commentList.add(new Comment(2, "Maria Claudia", 0, "", "01/07/2016", "Eu vi em algum lugar que suco de saquinho pode ser pior do que o proprio refrigerante", 5 ));
        commentList.add(new Comment(3, "João Felipe", 0, "", "01/07/2016", "@Maria Claudia, Sim tbm vi, essa nóticia está aqui no BeHappy, salvei até nos favoritos.", 6 ));
        commentList.add(new Comment(4, "Maria Claudia", 0, "", "01/07/2016", "Bom saber! Vou procura-la.", 8 ));
    }
}
