package com.example.gambletest;


import android.util.Log;

public class Player3  extends AbstractPlayer{

    boolean isPauseBet;
//    boolean isContinuesDoubleBetMoney;
    
    public Player3(String name) {
	if (isDebug) {
	    Log.e("gray", name + ".java:" + name + ", ");
	}
	this.playerName = name;
	isPauseBet = false;
    }

    @Override
    public void bet() {
	
	if (isGameOver) {
	    isBet = false;
	    return;
	}
	
	if (Banker.isReverse) {
	
	    // decide bet money
	    if (previousBetResult == LOSE || isContinuesDoubleBetMoney) {
		betMoney = betMoney * 2;
		isBetMoneyExceed();
	    } else {
		betMoney = MainActivity.minBet;
		isContinuesDoubleBetMoney = true;
	    }
	    
	    Banker.isReverse = false;
	    
	    if (Banker.firstReverseRoundResult == BIG) {
		isBetBig = true;
		isBetSmall = false;
		if (MainActivity.isDetailLog) {
		    MainActivity.logString += playerName + " start to bet big, bet:" + betMoney + "\n";
		}
	    } else if (Banker.firstReverseRoundResult == SMALL) {
		isBetBig = false;
		isBetSmall = true;
		if (MainActivity.isDetailLog) {
		    MainActivity.logString += playerName + " start to bet small, bet:" + betMoney + "\n";
		}
	    }
	} else {
	    isBetBig = false;
	    isBetSmall = false;
	    isBet = false;
//	    previousBetResult = NA;
//	    betMoney = MainActivity.minBet;
	    return;
	}
	
	totalCash = totalCash - betMoney;
	
	isBet = isBetSmall || isBetBig;
	if (isDebug) {
	    Log.e("gray", playerName + ".java:bet, isBet:" + isBet + ", totalCash:" + totalCash);
	}

	if (totalCash < 0) {
	    if (isDebug) {
		Log.e("gray", playerName + ".java:bet, Game Over, totalCash:" + totalCash);
	    }
	    if (MainActivity.isDetailLog) {
		MainActivity.logString += "<<<<<<<< Game Over >>>>>>>>\n";
	    }
	    isGameOver = true;
	}
	
    }

}
