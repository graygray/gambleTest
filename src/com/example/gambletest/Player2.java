package com.example.gambletest;

import android.util.Log;

public class Player2 extends AbstractPlayer{
	
	
	public Player2(String name) {
		if (isDebug) {
			Log.e("gray", name+".java:"+name+", " );
		}
		this.playerName = name;
	}
	
	public  void bet(){
		if (isGameOver){
			isBet = false;
			return;
		}
		//前次下注輸了
		if (previousBetResult == LOSE) {
			betMoney = betMoney*2;
			//超過設定下注金額
			if (isBetMoneyExceed()) {			
				return;
			}
		} else {
			betMoney = 50;
		}
		int chooseNum = (int)(Math.random()*18+1);
		String msg = null;
		if (chooseNum >= 11){
			isBetBig = true;
			isBetSmall = false;
			msg = playerName+ "start to bet big, bet:" + betMoney;
		}else{
			isBetBig = false;
			isBetSmall = true;
			msg = playerName+ "start to bet small, bet:" + betMoney;
		}
		if (isDebug) {
			Log.e("gray", msg);
		}
		// start to bet small
		MainActivity.logString += msg + "\n";
		
		totalCash = totalCash - betMoney;
			
					
		isBet = true;
		if (isDebug) {
			Log.e("gray", playerName+".java:bet, isBet:" + isBet + ", totalCash:" + totalCash);
		}
		
		if (totalCash < 0) {
			if (isDebug) {
				Log.e("gray", playerName+".java:bet, Game Over, totalCash:" + totalCash);
			}
			MainActivity.logString += "<<<<<<<< Game Over >>>>>>>>\n";
			isGameOver = true;
		}
	}
	
	
}
