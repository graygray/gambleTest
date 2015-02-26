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
		 * �]�w�s��}�X���N�U�`
		 */
		public  int continuousNumberToBet;
		/**
		 * �̤j�U�`���B
		 */
		public  int maxBetMoney;
		/**
		 * �}�l����
		 */
		public  int startCash = 20000;
		/**
		 * �ثe����
		 */
		public  int totalCash;
		/**
		 * �U�`���B
		 */
		public  int betMoney = 50;
		/**
		 * �U�`���G
		 */
		public  int betResult = NA;
		/**
		 * �e���U�`���G
		 */
		public  int previousBetResult = NA;
		
		/**
		 * �O�_�U�`
		 */
		public  boolean isBet = false;
		/**
		 * �O�_��j
		 */
		public  boolean isBetBig = false;
		/**
		 * �O�_��p
		 */
		public  boolean isBetSmall = false;
		/**
		 * �O�_�X��
		 */
		public  boolean isGameOver = false;
		
		/**
		 * �U�`��
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
			//�U�`���B�j��]�w�U�`�̤j���B
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
