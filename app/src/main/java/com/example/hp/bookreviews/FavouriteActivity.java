package com.example.hp.bookreviews;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hp.bookreviews.Adapters.GenreListAdapter;
import com.example.hp.bookreviews.Models.Book;
import com.example.hp.bookreviews.Models.GenreListItemClass;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends android.support.v4.app.Fragment {

    ListView favLV;
    public GenreListAdapter Fadapter;
    public static FavouriteActivity newInstance() {

        Bundle args = new Bundle();

        FavouriteActivity fragment = new FavouriteActivity();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_genre,container,false);
        favLV = (ListView) v.findViewById(R.id.genreListView);

        List<Book> allFavBooks = Book.getAllFavs();
        ArrayList<GenreListItemClass> objects = new ArrayList<>();
        for(int i=0; i<allFavBooks.size(); i++)
        {
            GenreListItemClass temp = new GenreListItemClass();
            temp.aName = allFavBooks.get(i).author;
            temp.bName = allFavBooks.get(i).title;
            objects.add(temp);
        }

        Fadapter = new GenreListAdapter(getActivity(), objects);
        favLV.setAdapter(Fadapter);


        return v;
    }
}