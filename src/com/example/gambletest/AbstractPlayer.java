package com.example.gambletest;

import android.util.Log;

public abstract class AbstractPlayer {
	
	
//		final static boolean isDebug = true;
		final static boolean isDebug = false;
		
		final static int NA = 0;
		final static int LOSE = -1;
		final static int WIN = 1;
		
		final static int ALL_KILL = 0;
		final static int SMALL = -1;
		final static int BIG = 1;
		
		/**
		 * 設定連續開幾次就下注
		 */
		public  int continuousNumberToBet;
		/**
		 * 最大下注金額
		 */
		public  int maxBetMoney;
		/**
		 * 開始本金
		 */
		public  int startCash = 20000;
		/**
		 * 目前本金
		 */
		public  int totalCash;
		/**
		 * 下注金額
		 */
		public  int betMoney = 50;
		/**
		 * 下注結果
		 */
		public  int betResult = NA;
		/**
		 * 前次下注結果
		 */
		public  int previousBetResult = NA;
		
		/**
		 * 是否下注
		 */
		public  boolean isBet = false;
		/**
		 * 是否押大
		 */
		public  boolean isBetBig = false;
		/**
		 * 是否押小
		 */
		public  boolean isBetSmall = false;
		/**
		 * 是否出局
		 */
		public  boolean isGameOver = false;
		
		/**
		 * 下注者
		 */
		public String playerName;
		
		
		public  void init(){
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
		
		public  int getBetResult() {
			return betResult;
		}

		public  void setBetResult(int betResult) {
			this.betResult = betResult;
		}

		public  int getTotalCash() {
			return totalCash;
		}

		public  void setTotalCash(int totalCash) {
			this.totalCash = totalCash;
			if (isDebug) {
				Log.e("gray", playerName+".java:setTotalCash, totalCash:" + this.totalCash);
			}
		}

		public  int getBetMoney() {
			return betMoney;
		}

		public  void setBetMoney(int betMoney) {
			this.betMoney = betMoney;
		}
		
		public  void resetVars() {
			isBetSmall = false;
			isBetBig = false;
			isBet = false;
			betMoney = 50;
			betResult = NA;
			previousBetResult = NA;
		}
		
		public abstract void bet();
		
		public  void roundFinish(){
			previousBetResult = betResult;
		}
		
		public boolean isBetMoneyExceed(){
			//下注金額大於設定下注最大金額
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
