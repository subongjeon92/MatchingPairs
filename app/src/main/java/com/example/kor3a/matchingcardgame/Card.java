package com.example.kor3a.matchingcardgame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Button;
import android.widget.GridLayout;

@SuppressLint({"AppCompatCustomView", "ViewConstructor"})
public class Card extends Button {

    private int row;
    private int column;
    private int card_id;
    private int numofElements;

    private boolean isFlipped = false;
    private boolean isMatched = false;

    protected Drawable cardfront;
    protected Drawable cardback;
    protected GridLayout.LayoutParams tempParams;

    public Card(Context context, int r, int c, int id){
        super(context);

        row = r;
        column = c;
        card_id = id;


        cardfront = ResourcesCompat.getDrawable(getResources(), card_id, null);
        cardback = ResourcesCompat.getDrawable(getResources(), R.drawable.cardback, null);

        //sets card to the cardback
        setBackground(cardback);
        tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        tempParams.width = (int) getResources().getDisplayMetrics().density * 75;
        tempParams.height = (int) getResources().getDisplayMetrics().density * 125;

        setLayoutParams(tempParams);
    }

    public void changetoLandscape(int r, int c){
        tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        tempParams.width = (int) getResources().getDisplayMetrics().density * 50;
        tempParams.width = (int) getResources().getDisplayMetrics().density * 75;
        setLayoutParams(tempParams);
    }

    public void changetoPortrait(int r, int c){
        tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));
        tempParams.width = (int) getResources().getDisplayMetrics().density * 75;
        tempParams.width = (int) getResources().getDisplayMetrics().density * 100;
        setLayoutParams(tempParams);
    }

    public boolean isMatched(){
        return isMatched;
    }

    public void setMatch(boolean match){
        isMatched = match;
    }

    public int getCard_Id(){
        return card_id;
    }

    public void flip(){
        if(isMatched){
            return;
        }
        if(isFlipped){
            setBackground(cardback);
            isFlipped = false;
        }else{
            setBackground(cardfront);
            isFlipped = true;
        }
    }
}
