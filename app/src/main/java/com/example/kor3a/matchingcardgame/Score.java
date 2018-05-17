package com.example.kor3a.matchingcardgame;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Score implements Serializable {
    private int score;
    private String name;

    public Score(int score, String name){
        this.score = score;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public JSONObject toJSON() throws JSONException{
        JSONObject json = new JSONObject();
        json.put("id", name);
        json.put("score", score);
        return json;
    }
}
