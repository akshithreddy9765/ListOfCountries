package com.example.anil.listofcountries;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        init();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    public void init() {

        String url = "https://restcountries.eu/rest/v1/region/americas";
        String[] urlArray = {url};
        CountryTask countryTask = new CountryTask(countryTaskCallback);
        countryTask.execute(urlArray);
        pd = ProgressDialog.show(MainActivity.this, "Loading...", "Please Wait...");

    }

    CountryTaskCallback countryTaskCallback = new CountryTaskCallback() {
        @Override
        public void countryResult(ArrayList<Object> arrayList) {
            if (pd.isShowing()) {
                pd.dismiss();
            }
            CustomAdapter adapter = new CustomAdapter(MainActivity.this, arrayList);
            list.setAdapter(adapter);
            list.setOnItemClickListener(onItemClickListener);

        }
    };

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {



        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            TextView countrytv = (TextView) findViewById(R.id.countrytv);
            ArrayList<String> languagesArray = (ArrayList<String>) countrytv.getTag();

            String languages = "";
            for (int k = 0; k < languagesArray.size(); k++) {
                languages = languages + languagesArray.get(k) + " , ";
            }

            if (languages.length() > 0) {
                languages = languages.substring(0, languages.length() - 2);
                Toast.makeText(getApplicationContext(), "Languages: " + languages, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "No languages available", Toast.LENGTH_SHORT).show();
            }

        }
    };


}
