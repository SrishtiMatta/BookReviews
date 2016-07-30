package com.example.hp.bookreviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.bookreviews.Adapters.ReviewListAdapter;
import com.example.hp.bookreviews.Models.Book;
import com.example.hp.bookreviews.Models.BookObject;
import com.example.hp.bookreviews.Network.ApiInterface;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class BookDetails extends AppCompatActivity {

    TextView bookNameTv,authorNameTv;
    ListView reviewsListView;
    ReviewListAdapter adapter;
    ImageView favIcon;
    public static final String access_token = "c7f1424572ccb869f9675eab4e7f24c837f84e86";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Book Reviews");
        setContentView(R.layout.activity_book_details);

        setContentView(R.layout.activity_book_details);

        bookNameTv = (TextView)findViewById(R.id.bookName);
        authorNameTv = (TextView) findViewById(R.id.authorName);
        favIcon = (ImageView)findViewById(R.id.favIcon);
        Picasso.with(BookDetails.this).load("https://image.freepik.com/free-icon/favourite--star--ios-7-interface-symbol_318-36171.jpg").into(favIcon);


        favIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.title = bookNameTv.getText().toString();
                book.author = authorNameTv.getText().toString();
                book.save();
                Picasso.with(BookDetails.this).load("http://icons.iconarchive.com/icons/aha-soft/multimedia/256/favourites-star-SH-icon.png").into(favIcon);

            }
        });

        final String bookName = getIntent().getStringExtra("bookName");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://idreambooks.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface service = retrofit.create(ApiInterface.class);

        Call<BookObject> bookObjectCall = service.getBookObject(bookName,access_token);
        bookObjectCall.enqueue(new Callback<BookObject>() {
            @Override
            public void onResponse(Call<BookObject> call, Response<BookObject> response) {
                BookObject bookObject = response.body();
                bookNameTv.setText(bookObject.book.title);
                authorNameTv.setText(bookObject.book.author);

                adapter = new ReviewListAdapter(BookDetails.this, bookObject.book.criticReviews);
                reviewsListView = (ListView)findViewById(R.id.reviewsListView);
                reviewsListView.setAdapter(adapter);

            /*    reviewsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String url = adapter.getItem(position).reviewLink;
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        i.addCategory(Intent.CATEGORY_BROWSABLE);
                        if(i.resolveActivity(getPackageManager())!=null)
                            startActivity(i);
                        else
                            Log.i("Errr", "No Browser");


                    }
                });*/
               // Toast.makeText(BookDetails.this, bookObject.book.criticReviews.get(1).snippet, Toast.LENGTH_SHORT).show();
            }


            @Override
            public void onFailure(Call<BookObject> call, Throwable t) {
                Log.i("Errr", "No Net");
            }
        });

    }
}

