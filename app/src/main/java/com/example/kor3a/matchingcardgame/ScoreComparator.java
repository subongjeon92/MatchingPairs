package com.example.kor3a.matchingcardgame;

import java.util.Comparator;

public class ScoreComparator implements Comparator<Score> {


    @Override
    public int compare(Score score1, Score score2) {
        int s1 = score1.getScore();
        int s2 = score2.getScore();

        if(s1 > s2){
            return -1;
        }else if(s2 > s1){
            return 1;
        }else{
            return 0;
        }
    }
}
