package com.example.anil.listofcountries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by anil on 5/19/2016.
 */
public class CustomAdapter extends BaseAdapter {

    ArrayList<Object> list;
    Context context;

    public CustomAdapter(Context context, ArrayList<Object> countryList) {
        this.context = context;
        this.list = countryList;

    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.country_list_contents, null);
            final MyViewHolder viewHolder = new MyViewHolder(view);
            view.setTag(viewHolder);

        } else {
            view = convertView;
        }

        final MyViewHolder viewHolder = (MyViewHolder) view.getTag();
        Data data = (Data) list.get(position);
        viewHolder.country.setText("Country: " + data.getName());
        viewHolder.capital.setText("Capital: " + data.getCapital());
        viewHolder.population.setText("Population: " + data.getPopulation());
        viewHolder.country.setTag(data.getLang());


        return view;
    }

    class MyViewHolder {
        TextView country, capital, population;

        public MyViewHolder(View v) {
            country = (TextView) v.findViewById(R.id.countrytv);
            capital = (TextView) v.findViewById(R.id.capitaltv);
            population = (TextView) v.findViewById(R.id.populationtv);
        }

    }
}


