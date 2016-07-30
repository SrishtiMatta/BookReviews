package com.example.hp.bookreviews;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ReviewListFragment extends android.support.v4.app.Fragment{

    public static ReviewListFragment newInstance() {

        Bundle args = new Bundle();

        ReviewListFragment fragment = new ReviewListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    RadioButton searchByGenre;
    RadioButton searchByTitle;
    EditText searchQuery;
    Button submitButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_review_list,container,false);

        searchByGenre = (RadioButton)v.findViewById(R.id.genreRadioButton);
        searchByTitle = (RadioButton)v.findViewById(R.id.titleRadioButton);
        searchQuery = (EditText)v.findViewById(R.id.searchQueryEditText);
        submitButton = (Button)v.findViewById(R.id.submitButton);
        searchQuery.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);

        searchByGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery.setVisibility(View.VISIBLE);
                searchQuery.setText("");
                submitButton.setVisibility(View.VISIBLE);
            }
        });

        searchByTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchQuery.setVisibility(View.VISIBLE);
                searchQuery.setText("");
                submitButton.setVisibility(View.VISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(searchByGenre.isChecked())
                {
                    Intent i = new Intent();
                    i.setClass(getActivity(),GenreActivity.class);
                    i.putExtra("genreName",searchQuery.getText().toString());
                    startActivity(i);

                }
                else
                {
                    Intent i = new Intent();
                    i.setClass(getActivity(),BookDetails.class);
                    i.putExtra("bookName",searchQuery.getText().toString());
                    startActivity(i);
                }
            }
        });

        return v;
    }
}