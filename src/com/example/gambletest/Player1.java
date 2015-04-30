package com.example.gambletest;

import android.util.Log;

public class Player1 extends AbstractPlayer {

    public Player1(String name) {
	if (isDebug) {
	    Log.e("gray", name + ".java:" + name + ", ");
	}
	this.playerName = name;
    }

    public void bet() {
	if (isGameOver) {
	    isBet = false;
	    return;
	}
	// ���s�}�j�W�L�]�w�U�`���ƦӥB���a�O��p
	if (Banker.continuousBigCounter >= continuousNumberToBet || isBetSmall) {
	    // �e���U�`��F
	    if (previousBetResult == LOSE) {
		betMoney = betMoney * 2;
		// �W�L�]�w�U�`���B
		if (isBetMoneyExceed()) {
//		    isBetSmall = false;
//		    isBet = isBetSmall || isBetBig;
		    return;
		}
	    } else {
		betMoney = MainActivity.minBet;
	    }

	    if (isDebug) {
		Log.e("gray", playerName + ".java:bet, " + "start to bet small, bet:" + betMoney);
	    }
	    // start to bet small
	    if (MainActivity.isDetailLog) {
		MainActivity.logString += playerName + " start to bet small,  bet:" + betMoney + "\n";
	    }
	    isBetSmall = true;
	    totalCash = totalCash - betMoney;

	// ���s�}�p�W�L�]�w�U�`���ƦӥB���a�O��j
	} else if (Banker.continuousSmallCounter >= continuousNumberToBet || isBetBig) {

	    if (previousBetResult == LOSE) {
		betMoney = betMoney * 2;
		if (isBetMoneyExceed()) {
//		    isBetBig = false;
//		    isBet = isBetSmall || isBetBig;
		    return;
		}
	    } else {
		betMoney = MainActivity.minBet;
	    }

	    if (isDebug) {
		Log.e("gray", playerName + ".java:bet, " + "start to bet big, bet:" + betMoney);
	    }
	    // start to bet big
	    if (MainActivity.isDetailLog) {
		MainActivity.logString += playerName + " start to bet big, bet:" + betMoney + "\n";
	    }
	    isBetBig = true;
	    totalCash = totalCash - betMoney;

	} else {
	    // wait...
	    if (isDebug) {
		Log.e("gray", playerName + ".java:bet, " + "wait for bet...");
	    }
	    if (MainActivity.isDetailLog) {
		MainActivity.logString += playerName + " is waiting for bet...\n";
	    }
	    isBetSmall = false;
	    isBetBig = false;
	}

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
