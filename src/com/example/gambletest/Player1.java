package com.example.gambletest;

import android.util.Log;

public class Player1 {
	// Continuous x big or small, Bet reverse.
	
//	final static boolean isDebug = true;
	final static boolean isDebug = false;
	
	final static int NA = 0;
	final static int LOSE = -1;
	final static int WIN = 1;
	
	final static int ALL_KILL = 0;
	final static int SMALL = -1;
	final static int BIG = 1;
	
	public static int continuousNumberToBet;
	public static int maxBetMoney;
	public static int startCash = 20000;
	public static int totalCash;
	public static int betMoney = 50;
	public static int betResult = NA;
	public static int previousBetResult = NA;
	
	public static boolean isBet = false;
	public static boolean isBetBig = false;
	public static boolean isBetSmall = false;
	public static boolean isGameOver = false;
	
	public Player1() {
		if (isDebug) {
			Log.e("gray", "Player1.java:Player1, " + "");
		}
	}
	
	public static void init(){
		continuousNumberToBet = MainActivity.contSameToBet;
		maxBetMoney = MainActivity.maxBet;
		totalCash = startCash;
		betMoney = 50;
		betResult = NA;
		previousBetResult = NA;
		isBet = false;
		isBetBig = false;
		isBetSmall = false;
		isGameOver = false;
	}
	
	public static int getBetResult() {
		return betResult;
	}

	public static void setBetResult(int betResult) {
		Player1.betResult = betResult;
	}

	public static int getTotalCash() {
		return totalCash;
	}

	public static void setTotalCash(int totalCash) {
		Player1.totalCash = totalCash;
		if (isDebug) {
			Log.e("gray", "Player1.java:setTotalCash, totalCash:" + Player1.totalCash);
		}
	}

	public static int getBetMoney() {
		return betMoney;
	}

	public static void setBetMoney(int betMoney) {
		Player1.betMoney = betMoney;
	}
	
	public static void resetVars() {
		isBetSmall = false;
		isBetBig = false;
		isBet = false;
		betMoney = 50;
		betResult = NA;
		previousBetResult = NA;
	}

	public static void bet(){
		
		if (Banker.continuousBigCounter >= continuousNumberToBet || isBetSmall) {
			
			if (previousBetResult == LOSE) {
				betMoney = betMoney*2;
				
				if (isBetMoneyExceed()) {
					isBetSmall = false;
					isBet = isBetSmall || isBetBig;
					return;
				}
			} else {
				betMoney = 50;
			}
			
			if (isDebug) {
				Log.e("gray", "Player1.java:bet, " + "start to bet small, bet:" + betMoney);
			}
			// start to bet small
			MainActivity.logString += "Player start to bet small,  bet:" + betMoney + "\n";
			isBetSmall = true;
			totalCash = totalCash - betMoney;

		} else if (Banker.continuousSmallCounter >= continuousNumberToBet || isBetBig) {

			if (previousBetResult == LOSE) {
				betMoney = betMoney * 2;
				if (isBetMoneyExceed()) {
					isBetBig = false;
					isBet = isBetSmall || isBetBig;
					return;
				}
			} else {
				betMoney = 50;
			}
			
			if (isDebug) {
				Log.e("gray", "Player1.java:bet, " + "start to bet big, bet:" + betMoney);
			}
			// start to bet big
			MainActivity.logString += "Player start to bet big, bet:" + betMoney + "\n";
			isBetBig = true;
			totalCash = totalCash - betMoney;

		} else {
			// wait...
			if (isDebug) {
				Log.e("gray", "Player1.java:bet, " + "wait for bet...");
			}
			MainActivity.logString += "Player is waiting for bet...\n";
			isBetSmall = false;
			isBetBig = false;
		}
		
		isBet = isBetSmall || isBetBig;
		if (isDebug) {
			Log.e("gray", "Player1.java:bet, isBet:" + isBet + ", totalCash:" + totalCash);
		}
		
		if (totalCash < 0) {
			if (isDebug) {
				Log.e("gray", "Player1.java:bet, Game Over, totalCash:" + totalCash);
			}
			MainActivity.logString += "<<<<<<<< Game Over >>>>>>>>\n";
			isGameOver = true;
		}
	}
	
	public static void roundFinish(){
		previousBetResult = betResult;
	}
	
	public static boolean isBetMoneyExceed(){
		if (betMoney > maxBetMoney) {
			if (isDebug) {
				Log.e("gray", "Player1.java:isBetMoneyExceed, betMoney:" + betMoney + ", over maxBetMoney:" + maxBetMoney);
				Log.e("gray", "Player1.java:isBetMoneyExceed, give up, totalCash:" + totalCash);
			}
			isBetSmall = false;
			isBetBig = false;
			isBet = false;
			betMoney = 50;
			betResult = NA;
			previousBetResult = NA;
			Banker.continuousBigCounter = 0;
			Banker.continuousSmallCounter = 0;
			Banker.loseCounter++;
			
			Banker.checkRound += "lose round:" + (Banker.gameCounter-1) + "\n";
			
			return true;
		} 
		
		return false;
	}
	
}
