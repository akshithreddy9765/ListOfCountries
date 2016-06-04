package com.example.anil.listofcountries;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by anil on 5/18/2016.
 */
public class CountryTask extends AsyncTask<String, Void, String> {

    private CountryTaskCallback countryTaskCallback;
    private final String TAG = CountryTask.class.getSimpleName();

    public CountryTask(CountryTaskCallback countryTaskCallback) {
        this.countryTaskCallback = countryTaskCallback;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... params) {
        BufferedReader bufferedReader = null;
        InputStream is = null;
        HttpURLConnection connection = null;
        String url = params[0];
        try {
            URL url1 = new URL(url);
            connection = (HttpURLConnection) url1.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(15000);
            is = connection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String data = null;
            String rawJson = "";
            while ((data = bufferedReader.readLine()) != null) {
                rawJson += data + "\n";

            }
            Log.i(TAG, "Data is" + rawJson);
//            JSONObject object = new JSONObject(rawJson);
//            Gson gson = new Gson();
//            details = gson.fromJson(object.toString(), Data.class);
//            Log.i(CountryTask.class.getSimpleName(), "Parsed data:" + details);


            return rawJson;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        catch (JSONException e) {
//            e.printStackTrace();
//        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            }

        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        ArrayList<Object> countryList = new ArrayList<Object>();
        try {

            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Data data = new Data();
                String countryName = jsonObject.getString("name");
                data.setName(countryName);
                String capital = jsonObject.getString("capital");
                data.setCapital(capital);
                String population = jsonObject.getString("population");
                data.setPopulation(population);

                JSONArray langArray = jsonObject.getJSONArray("languages");
                ArrayList<String> languagesArray = new ArrayList<String>();
                for (int j = 0; j < langArray.length(); j++) {
                    String language = langArray.getString(j);
                    languagesArray.add(language);
                }

                data.setLang(languagesArray);
                countryList.add(data);
                countryTaskCallback.countryResult(countryList);

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
