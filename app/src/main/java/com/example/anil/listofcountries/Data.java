package com.example.anil.listofcountries;

import java.util.ArrayList;

/**
 * Created by anil on 5/18/2016.
 */
public class Data {
    private String name;
    private String capital;
    private String population;
    private ArrayList<String> languages;

    public ArrayList<String> getLang() {
        return languages;
    }

    public void setLang(ArrayList<String> lang) {
        this.languages = lang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getPopulation() {

        return population;
    }

    public void setPopulation(String population) {

        this.population = population;
    }


}
