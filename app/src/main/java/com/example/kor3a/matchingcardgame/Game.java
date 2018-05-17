package com.example.kor3a.matchingcardgame;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener{

    private GridLayout cardlayout;

    private Button tryagainButton;
    private Button newgameButton;
    private Button endgameButton;

    private int columnSize;
    private int rowSize;
    private int winCount = 0;

    private int numofElements;
    private Card[] cards;
    private Card[] cardsLayout;
    private ArrayList<Card> cardQ;
    private int[] cardGraphicsLocation;
    private ArrayList<Integer> cardGraphics;

    private Card selectedCard1;
    private Card selectedCard2;

    private int score = 0;

    private boolean isBusy = false;
    protected static boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        cardlayout = findViewById(R.id.gridLayout);
        tryagainButton = findViewById(R.id.retry);
        newgameButton = findViewById(R.id.newgame);
        endgameButton = findViewById(R.id.endgame);
        tryagainButton.setText("Try Again");
        newgameButton.setText("New Game");
        endgameButton.setText("End Game");
        cardQ = new ArrayList<Card>();
        if(flag){
            Log.d("ONCREATE", "flag is true");
        }

        numofElements = getIntent().getIntExtra("numofElements", 0);
        determineRC();
        cardGraphicsLocation = new int[numofElements];
        cards = new Card[numofElements];
        cardsLayout = new Card[numofElements];

        loadCards();
        shuffleCards();

        for(int row = 0; row < rowSize; row++){
            for(int column = 0; column < columnSize; column++){
                int tempIndex = row * columnSize + column;
                if(!(tempIndex >= numofElements)){
                    Card newCard = new Card(this, row, column, cardGraphics.get(cardGraphicsLocation[tempIndex]));
                    cardQ.add(newCard);
                    newCard.setId(View.generateViewId());
                    newCard.setOnClickListener(this);
                    cards[row * columnSize + column] = newCard;
                    cardlayout.addView(newCard);
                }
            }
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        Log.d(" ONCONFIGCHANGE", "CALLED");
        flag = false;
        if(newConfig.orientation ==  Configuration.ORIENTATION_LANDSCAPE){
            if(numofElements > 8){
                cardlayout.removeAllViews();
                for(int row = 0; row < 3; row++){
                    for(int column = 0; column < 6; column++){
                        int tempIndex = row* 6 + column;
                        if(!(tempIndex >= numofElements)){
                            Card tempCard = cardQ.remove(0);
                            tempCard.changetoLandscape(row, column);
                            cardQ.add(tempCard);
                            cardlayout.addView(tempCard);
                        }
                    }
                }
            }
        }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            if(numofElements > 8){
                cardlayout.removeAllViews();
                for(int row = 0; row < rowSize; row++){
                    for(int column = 0; column < columnSize; column++){
                        int tempIndex = row * columnSize + column;
                        if(!(tempIndex >= numofElements)){
                            Card tempCard = cardQ.remove(0);
                            tempCard.changetoPortrait(row, column);
                            cardQ.add(tempCard);
                            cardlayout.addView(tempCard);
                        }
                    }
                }
            }
        }
    }

    private void determineRC(){
        switch(numofElements){
            case 4:
                columnSize = 2;
                rowSize = 2;
                break;
            case 6:
                columnSize = 3;
                rowSize = 2;
                break;
            case 8:
                columnSize = 4;
                rowSize = 2;
                break;
            case 10:
                columnSize = 5;
                rowSize = 2;
                break;
            case 12:
                columnSize = 4;
                rowSize = 3;
                break;
            case 14:
                columnSize = 4;
                rowSize = 4;
                break;
            case 16:
                columnSize = 4;
                rowSize = 4;
                break;
            case 18:
                columnSize = 5;
                rowSize = 4;
                break;
            case 20:
                columnSize = 5;
                rowSize = 4;
                break;
            case 22:
                columnSize = 6;
                rowSize = 4;
                break;
            case 24:
                columnSize = 6;
                rowSize = 4;
                break;
            case 26:
                columnSize = 6;
                rowSize = 5;
                break;
            case 28:
                columnSize = 6;
                rowSize = 5;
                break;
            case 30:
                columnSize = 6;
                rowSize = 5;
                break;
        }
    }

    private void loadCards(){
        cardGraphics = new ArrayList<Integer>();
        cardGraphics.add(R.drawable.card1);
        cardGraphics.add(R.drawable.card2);
        cardGraphics.add(R.drawable.card3);
        cardGraphics.add(R.drawable.card4);
        cardGraphics.add(R.drawable.card5);
        cardGraphics.add(R.drawable.card6);
        cardGraphics.add(R.drawable.card7);
        cardGraphics.add(R.drawable.card8);
        cardGraphics.add(R.drawable.card9);
        cardGraphics.add(R.drawable.card10);
        cardGraphics.add(R.drawable.card11);
        cardGraphics.add(R.drawable.card12);
        cardGraphics.add(R.drawable.card13);
        cardGraphics.add(R.drawable.card14);
        cardGraphics.add(R.drawable.card15);
    }

    private void shuffleCards(){
        Random rng = new Random();

        for(int i = 0; i < numofElements; i++){
            cardGraphicsLocation[i] = i % (numofElements/2);
        }
        for(int i = 0; i < numofElements; i++){
            int temp = cardGraphicsLocation[i];
            int swapIndex = rng.nextInt(numofElements);
            cardGraphicsLocation[i] = cardGraphicsLocation[swapIndex];
            cardGraphicsLocation[swapIndex] = temp;
        }
    }

    public void tryAgain(View view){
        if(selectedCard2 == null && selectedCard1 != null){
            selectedCard1.flip();
            selectedCard1 = null;
        }
        else if(selectedCard1 != null && selectedCard2 != null){
            selectedCard2.flip();
            selectedCard1.flip();
            selectedCard2 = null;
            selectedCard1 = null;
            isBusy = false;
        }else{
            return;
        }
    }

    public void newGame(View view){
        Intent newIntent = new Intent(Game.this, Game.class);
        newIntent.putExtra("numofElements", numofElements);
        startActivity(newIntent);
    }

    public void endGame(View view){
        Intent newIntent = new Intent(Game.this, WinGameActivity.class);
        newIntent.putExtra("Score", score);
        newIntent.putExtra("NumofCards", numofElements);
        startActivity(newIntent);
    }

    @Override
    public void onClick(View view){
        if(view instanceof Card){
            if(isBusy){
                return;
            }
            Card currentCard = (Card) view;
            if(currentCard.isMatched()){
                return;
            }
            if(selectedCard1 == null){
                selectedCard1 = currentCard;
                selectedCard1.flip();
                return;
            }
            if(selectedCard1.getId() == currentCard.getId()){
                return;
            }
            //if user finds the matching pair
            if(selectedCard1.getCard_Id() == currentCard.getCard_Id()) {
                currentCard.flip();
                currentCard.setMatch(true);
                selectedCard1.setMatch(true);
                selectedCard1.setEnabled(false);
                currentCard.setEnabled(false);
                selectedCard1 = null;
                score = score + 2;
                winCount++;
                if (winCount == (numofElements / 2)) {
                    Intent newIntent = new Intent(Game.this, WinGameActivity.class);
                    newIntent.putExtra("Score", score);
                    newIntent.putExtra("NumofCards", numofElements);
                    startActivity(newIntent);
                }
                return;
            }
            //if the user gets the wrong match, show it to the user
            else{
                if(score > 0){
                    score = score - 1;
                }
                selectedCard2 = currentCard;
                selectedCard2.flip();
                isBusy = true;
            }
        }else{
            return;
        }
    }
}
