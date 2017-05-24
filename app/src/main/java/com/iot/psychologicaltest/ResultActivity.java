package com.iot.psychologicaltest;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.iot.psychologicaltest.QuestionActivity.REQUEST_CODE_MENU;

/**
 * Provides UI for the Detail page with Collapsing Toolbar.
 */
public class ResultActivity extends AppCompatActivity {

    ImageButton BackButton;
    ImageButton TwitButton;
    ImageButton HomeButton;


    public static final String EXTRA_POSITION = "position";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        HomeButton=(ImageButton)findViewById(R.id.home);
        BackButton=(ImageButton)findViewById(R.id.BackButton);
        TwitButton=(ImageButton)findViewById(R.id.TwitButton);




        // Set Collapsing Toolbar layout to the screen
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        // Set title of Detail page
        //  collapsingToolbar.setTitle(getString(R.array.titleset));


        /// ok


        int postion = getIntent().getIntExtra(EXTRA_POSITION, 0);
        Resources resources = getResources();

        String[] places = resources.getStringArray(R.array.titleset);
        collapsingToolbar.setTitle(places[postion % places.length]);

        String[] placeDetails = resources.getStringArray(R.array.TestContents);
        TextView placeDetail = (TextView) findViewById(R.id.place_detail);
        placeDetail.setText(placeDetails[postion % placeDetails.length]);

        TypedArray placePictures = resources.obtainTypedArray(R.array.picture);
        ImageView placePicutre = (ImageView) findViewById(R.id.image);
        placePicutre.setImageDrawable(placePictures.getDrawable(postion % placePictures.length()));

        placePictures.recycle();
    }

    public void home(View v){
        Intent intent = new Intent(
                getApplicationContext(),
                ListViewMain.class
        );
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }

    public void backClicked(View v){
        Intent intent = new Intent(
                getApplicationContext(),
                QuestionActivity.class
        );
        startActivityForResult(intent, REQUEST_CODE_MENU);
    }


    public void twit (View v) {
        String strLink = null;
        try {
            strLink = String.format("http://twitter.com/intent/tweet?text=%s",
                    URLEncoder.encode("공유하고픈 심리테스트~ ", "utf-8"));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(strLink));
        startActivity(intent);

    }
}