package com.example.gambletest;

import java.util.Random;

import android.util.Log;

public class Player2 extends AbstractPlayer {
    
    public int betBigCounter;
    public int betSmallCounter;
    public Random mRandom;

    public Player2(String name) {
	if (isDebug) {
	    Log.e("gray", name + ".java:" + name + ", ");
	}
	this.playerName = name;
	betBigCounter = 0;
	betSmallCounter = 0;
	mRandom = new Random(System.currentTimeMillis());
    }

    public void bet() {
	if (isGameOver) {
	    isBet = false;
	    return;
	}
	// 前次下注輸了
	if (previousBetResult == LOSE) {
	    betMoney = betMoney * 2;
	    // check 是否超過設定下注金額
	    isBetMoneyExceed();
	} else {
	    betMoney = MainActivity.minBet;
	}
	
	String msg = null;
	if (mRandom.nextBoolean()) {
	    isBetBig = true;
	    isBetSmall = false;
	    msg = playerName + " start to bet big, bet:" + betMoney;
	    betBigCounter++;
	} else {
	    isBetBig = false;
	    isBetSmall = true;
	    msg = playerName + " start to bet small, bet:" + betMoney;
	    betSmallCounter++;
	}
	
	if (isDebug) {
	    Log.e("gray", msg);
	}
	
	if (MainActivity.isDetailLog) {
	    MainActivity.logString += msg + "\n";
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
