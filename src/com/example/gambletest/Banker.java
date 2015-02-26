package com.example.gambletest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

public class Banker {
//test
//	final static boolean isDebug = true;
	final static boolean isDebug = false;
	
	final static int NA = 0;
	final static int LOSE = -1;
	final static int WIN = 1;
	
	final static int ALL_KILL = 0;
	final static int SMALL = -1;
	final static int BIG = 1;
	
	public static Random generator;
	
	public static int dice1 = 0;
	public static int dice2 = 0;
	public static int dice3 = 0;
	
	public static int dice1_1counter = 0;
	public static int dice1_2counter = 0;
	public static int dice1_3counter = 0;
	public static int dice1_4counter = 0;
	public static int dice1_5counter = 0;
	public static int dice1_6counter = 0;
	public static int dice2_1counter = 0;
	public static int dice2_2counter = 0;
	public static int dice2_3counter = 0;
	public static int dice2_4counter = 0;
	public static int dice2_5counter = 0;
	public static int dice2_6counter = 0;
	public static int dice3_1counter = 0;
	public static int dice3_2counter = 0;
	public static int dice3_3counter = 0;
	public static int dice3_4counter = 0;
	public static int dice3_5counter = 0;
	public static int dice3_6counter = 0;
	public static int gameCounter = 0;
	public static int bigCounter = 0;
	public static int smallCounter = 0;
	public static int allSameCounter = 0;
	public static int continuousBigCounter = 0;
	public static int continuousSmallCounter = 0;
	public static int continuousBigCounterReal = 0;
	public static int continuousSmallCounterReal = 0;
	public static int maxContinuousBigCounter = 0;
	public static int maxContinuousSmallCounter = 0;
	public static int result = NA;
	public static int previousResult = NA;
	public static String resultS;
	
	public static int sameContinuesCounter_2;
	public static int sameContinuesCounter_3;
	public static int sameContinuesCounter_4;
	public static int sameContinuesCounter_5;
	public static int sameContinuesCounter_6;
	public static int sameContinuesCounter_7;
	public static int sameContinuesCounter_8;
	public static int sameContinuesCounter_9;
	public static int sameContinuesCounter_10;
	public static int sameContinuesCounter_11;
	public static int sameContinuesCounter_12;
	public static int sameContinuesCounter_13;
	public static int sameContinuesCounter_14;
	public static int sameContinuesCounter_15;
	public static int sameContinuesCounter_16;
	public static int sameContinuesCounter_17;
	public static int sameContinuesCounter_18;
	public static int sameContinuesCounter_19;
	public static int sameContinuesCounter_20;
	public static int sameContinuesCounter_others;
	
	public static int winCounter;
	public static int loseCounter;
	
	public static String checkRound;
	public static List<AbstractPlayer> players = new ArrayList<AbstractPlayer>();
	public Banker() {
		if (isDebug) {
			Log.e("gray", "Banker.java:Banker, " + "");
		}
	}
	
	public static void init(){
		
		gameCounter = 0;
		dice1 = 0;
		dice2 = 0;
		dice3 = 0;
		dice1_1counter = 0;
		dice1_2counter = 0;
		dice1_3counter = 0;
		dice1_4counter = 0;
		dice1_5counter = 0;
		dice1_6counter = 0;
		dice2_1counter = 0;
		dice2_2counter = 0;
		dice2_3counter = 0;
		dice2_4counter = 0;
		dice2_5counter = 0;
		dice2_6counter = 0;
		dice3_1counter = 0;
		dice3_2counter = 0;
		dice3_3counter = 0;
		dice3_4counter = 0;
		dice3_5counter = 0;
		dice3_6counter = 0;
		bigCounter = 0;
		smallCounter = 0;
		allSameCounter = 0;
		continuousBigCounter = 0;					// continuous bit counter
		continuousSmallCounter = 0;					// continuous small counter
		continuousBigCounterReal = 0;
		continuousSmallCounterReal = 0;
		maxContinuousBigCounter = 0;
		maxContinuousSmallCounter = 0;
		result = NA;
		previousResult = NA;
		
		sameContinuesCounter_2 = 0;
		sameContinuesCounter_3 = 0;
		sameContinuesCounter_4 = 0;
		sameContinuesCounter_5 = 0;
		sameContinuesCounter_6 = 0;
		sameContinuesCounter_7 = 0;
		sameContinuesCounter_8 = 0;
		sameContinuesCounter_9 = 0;
		sameContinuesCounter_10 = 0;
		sameContinuesCounter_11 = 0;
		sameContinuesCounter_12 = 0;
		sameContinuesCounter_13 = 0;
		sameContinuesCounter_14 = 0;
		sameContinuesCounter_15 = 0;
		sameContinuesCounter_16 = 0;
		sameContinuesCounter_17 = 0;
		sameContinuesCounter_18 = 0;
		sameContinuesCounter_19 = 0;
		sameContinuesCounter_20 = 0;
		sameContinuesCounter_others = 0;
		
		winCounter = 0;
		loseCounter = 0;
		
		checkRound = "";
		
		generator = new Random(System.currentTimeMillis());
		players.clear();
	}
	
	public static void deal(){
		//亂數產生結果
		dice1 = 1+(int)(generator.nextDouble()*6);
		dice2 = 1+(int)(generator.nextDouble()*6);
		dice3 = 1+(int)(generator.nextDouble()*6);
		//豹子通殺
		if (dice1 == dice2 && dice2 == dice3) {
			allSameCounter++;
			result = ALL_KILL;
			resultS = "AK";
			
			if (continuousBigCounterReal == 2 || continuousSmallCounterReal == 2) {
				sameContinuesCounter_2++;
			} else if (continuousBigCounterReal == 3 || continuousSmallCounterReal == 3) {
				sameContinuesCounter_3++;
			} else if (continuousBigCounterReal == 4 || continuousSmallCounterReal == 4) {
				sameContinuesCounter_4++;
			} else if (continuousBigCounterReal == 5 || continuousSmallCounterReal == 5) {
				sameContinuesCounter_5++;
			} else if (continuousBigCounterReal == 6 || continuousSmallCounterReal == 6) {
				sameContinuesCounter_6++;
			} else if (continuousBigCounterReal == 7 || continuousSmallCounterReal == 7) {
				sameContinuesCounter_7++;
			} else if (continuousBigCounterReal == 8 || continuousSmallCounterReal == 8) {
				sameContinuesCounter_8++;
			} else if (continuousBigCounterReal == 9 || continuousSmallCounterReal == 9) {
				sameContinuesCounter_9++;
			} else if (continuousBigCounterReal == 10 || continuousSmallCounterReal == 10) {
				sameContinuesCounter_10++;
			} else if (continuousBigCounterReal == 11 || continuousSmallCounterReal == 11) {
				sameContinuesCounter_11++;
			} else if (continuousBigCounterReal == 12 || continuousSmallCounterReal == 12) {
				sameContinuesCounter_12++;
			} else if (continuousBigCounterReal == 13 || continuousSmallCounterReal == 13) {
				sameContinuesCounter_13++;
			} else if (continuousBigCounterReal == 14 || continuousSmallCounterReal == 14) {
				sameContinuesCounter_14++;
			} else if (continuousBigCounterReal == 15 || continuousSmallCounterReal == 15) {
				sameContinuesCounter_15++;
			} else if (continuousBigCounterReal == 16 || continuousSmallCounterReal == 16) {
				sameContinuesCounter_16++;
			} else if (continuousBigCounterReal == 17 || continuousSmallCounterReal == 17) {
				sameContinuesCounter_17++;
			} else if (continuousBigCounterReal == 18 || continuousSmallCounterReal == 18) {
				sameContinuesCounter_18++;
			} else if (continuousBigCounterReal == 19 || continuousSmallCounterReal == 19) {
				sameContinuesCounter_19++;
			} else if (continuousBigCounterReal == 20 || continuousSmallCounterReal == 20) {
				sameContinuesCounter_20++;
			} else {
				sameContinuesCounter_others++;
			}
			
			continuousBigCounter = 1;
			continuousSmallCounter = 1;
			continuousBigCounterReal = 1;
			continuousSmallCounterReal = 1;
			//開大
		} else if (dice1+dice2+dice3 >= 11) {
			bigCounter++;
			result = BIG;
			resultS = "B";
			
			if (continuousSmallCounterReal == 2) {
				sameContinuesCounter_2++;
			} else if (continuousSmallCounterReal == 3) {
				sameContinuesCounter_3++;
			} else if (continuousSmallCounterReal == 4) {
				sameContinuesCounter_4++;
			} else if (continuousSmallCounterReal == 5) {
				sameContinuesCounter_5++;
			} else if (continuousSmallCounterReal == 6) {
				sameContinuesCounter_6++;
			} else if (continuousSmallCounterReal == 7) {
				sameContinuesCounter_7++;
			} else if (continuousSmallCounterReal == 8) {
				sameContinuesCounter_8++;
			} else if (continuousSmallCounterReal == 9) {
				sameContinuesCounter_9++;
			} else if (continuousSmallCounterReal == 10) {
				sameContinuesCounter_10++;
			} else if (continuousSmallCounterReal == 11) {
				sameContinuesCounter_11++;
			} else if (continuousSmallCounterReal == 12) {
				sameContinuesCounter_12++;
			} else if (continuousSmallCounterReal == 13) {
				sameContinuesCounter_13++;
			} else if (continuousSmallCounterReal == 14) {
				sameContinuesCounter_14++;
			} else if (continuousSmallCounterReal == 15) {
				sameContinuesCounter_15++;
			} else if (continuousSmallCounterReal == 16) {
				sameContinuesCounter_16++;
			} else if (continuousSmallCounterReal == 17) {
				sameContinuesCounter_17++;
			} else if (continuousSmallCounterReal == 18) {
				sameContinuesCounter_18++;
			} else if (continuousSmallCounterReal == 19) {
				sameContinuesCounter_19++;
			} else if (continuousSmallCounterReal == 20) {
				sameContinuesCounter_20++;
			} else {
				sameContinuesCounter_others++;
			}
			continuousSmallCounter = 1;
			continuousSmallCounterReal = 1;

//			if (previousResult == BIG || (continuousBigCounter >= 2 && previousResult == ALL_KILL)) {
			if (previousResult == BIG) {
				continuousBigCounter++;
				continuousBigCounterReal++;
			}
			
			if (continuousBigCounterReal > maxContinuousBigCounter) {
				maxContinuousBigCounter = continuousBigCounterReal;
			}
			
//			if (continuousBigCounterReal >=9) {
//				Log.e("gray", "Banker.java:deal, round:" + gameCounter);
//				checkRound += "continuous same big:" + continuousBigCounterReal + ", round:" + gameCounter + "\n";
//			}
			//開小
		} else if (dice1+dice2+dice3 <= 10) {
			smallCounter++;
			result = SMALL;
			resultS = "S";
			if (continuousBigCounterReal == 2) {
				sameContinuesCounter_2++;
			} else if (continuousBigCounterReal == 3) {
				sameContinuesCounter_3++;
			} else if (continuousBigCounterReal == 4) {
				sameContinuesCounter_4++;
			} else if (continuousBigCounterReal == 5) {
				sameContinuesCounter_5++;
			} else if (continuousBigCounterReal == 6) {
				sameContinuesCounter_6++;
			} else if (continuousBigCounterReal == 7) {
				sameContinuesCounter_7++;
			} else if (continuousBigCounterReal == 8) {
				sameContinuesCounter_8++;
			} else if (continuousBigCounterReal == 9) {
				sameContinuesCounter_9++;
			} else if (continuousBigCounterReal == 10) {
				sameContinuesCounter_10++;
			} else if (continuousBigCounterReal == 11) {
				sameContinuesCounter_11++;
			} else if (continuousBigCounterReal == 12) {
				sameContinuesCounter_12++;
			} else if (continuousBigCounterReal == 13) {
				sameContinuesCounter_13++;
			} else if (continuousBigCounterReal == 14) {
				sameContinuesCounter_14++;
			} else if (continuousBigCounterReal == 15) {
				sameContinuesCounter_15++;
			} else if (continuousBigCounterReal == 16) {
				sameContinuesCounter_16++;
			} else if (continuousBigCounterReal == 17) {
				sameContinuesCounter_17++;
			} else if (continuousBigCounterReal == 18) {
				sameContinuesCounter_18++;
			} else if (continuousBigCounterReal == 19) {
				sameContinuesCounter_19++;
			} else if (continuousBigCounterReal == 20) {
				sameContinuesCounter_20++;
			} else {
				sameContinuesCounter_others++;
			}
			continuousBigCounter = 1;
			continuousBigCounterReal = 1;
			
//			if (previousResult == SMALL	|| (continuousSmallCounter >= 2 && previousResult == ALL_KILL)) {
			if (previousResult == SMALL) {
				continuousSmallCounter++;
				continuousSmallCounterReal++;
			}
			
			if (continuousSmallCounterReal > maxContinuousSmallCounter) {
				maxContinuousSmallCounter = continuousSmallCounterReal;
			}
			
//			if (continuousSmallCounterReal >=9) {
//				Log.e("gray", "Banker.java:deal, round:" + gameCounter);
//				checkRound += "continuous same small:" + continuousSmallCounterReal + ", round:" + gameCounter + "\n";
//			}
		}
		
		if (dice1 == 1) {
			dice1_1counter++;
		} else if (dice1 == 2) {
			dice1_2counter++;
		} else if (dice1 == 3) {
			dice1_3counter++;
		} else if (dice1 == 4) {
			dice1_4counter++;
		} else if (dice1 == 5) {
			dice1_5counter++;
		} else if (dice1 == 6) {
			dice1_6counter++;
		} else {
			Log.e("gray", "some thing wrong2");
		}
		if (dice2 == 1) {
			dice2_1counter++;
		} else if (dice2 == 2) {
			dice2_2counter++;
		} else if (dice2 == 3) {
			dice2_3counter++;
		} else if (dice2 == 4) {
			dice2_4counter++;
		} else if (dice2 == 5) {
			dice2_5counter++;
		} else if (dice2 == 6) {
			dice2_6counter++;
		} else {
			Log.e("gray", "some thing wrong3");
		}
		if (dice3 == 1) {
			dice3_1counter++;
		} else if (dice3 == 2) {
			dice3_2counter++;
		} else if (dice3 == 3) {
			dice3_3counter++;
		} else if (dice3 == 4) {
			dice3_4counter++;
		} else if (dice3 == 5) {
			dice3_5counter++;
		} else if (dice3 == 6) {
			dice3_6counter++;
		} else {
			Log.e("gray", "some thing wrong4");
		}
		
		if (isDebug) {
			Log.e("gray", "Banker.java:deal, " + dice1 + "-"+ dice2 + "-" + dice3 + "-" + resultS);
		}
		MainActivity.logString += "deal >> " + dice1 + "-"+ dice2 + "-" + dice3 + "-" + resultS + "\n";
		
	}
	
	public  static void pay(){
		for(AbstractPlayer player : players){
			if (isDebug) {
				Log.e("gray", "Banker.java:pay, TotalCash:" + player.getTotalCash());
			}
			
			if ( player.isBet && ((result == SMALL && player.isBetSmall) || (result == BIG && player.isBetBig)) ) {
				//玩家押對贏錢
				if (isDebug) {
					Log.e("gray", "Banker.java:pay, WIN, "+player.playerName+".betMoney:" + player.getBetMoney());
				}
				MainActivity.logString += player.playerName+" WIN\n";
				
				winCounter++;
				player.setBetResult(WIN);
				player.setTotalCash(player.getTotalCash() + player.getBetMoney()*2);
				MainActivity.logString += player.playerName+" totalCash:" + player.totalCash + "\n";
				player.resetVars();
				continuousBigCounter = 0;
				continuousSmallCounter = 0;
	
			} else if (player.isBet && ((result == BIG && player.isBetSmall) || (result == SMALL && player.isBetBig) || result == ALL_KILL) ) {
				//玩家押錯輸錢
				if (isDebug) {
					Log.e("gray", "Banker.java:pay, " + "LOSE");
				}
				MainActivity.logString += player.playerName+" LOSE\n";
				
	//			loseCounter++;
				player.setBetResult(LOSE);
				MainActivity.logString += player.playerName+" totalCash:" + player.totalCash + "\n";
			}
		}
		previousResult = result;
		
	}
	
	public static void statistics(){
		for(AbstractPlayer player : players){
			Log.e("gray", player.playerName+" start cash:" + player.totalCash);
			Log.e("gray", player.playerName+" final cash:" + player.totalCash);
		}
		Log.e("gray", "total game:" + gameCounter);
		Log.e("gray", "allSameCounter:" + allSameCounter + ", op:" + (double)allSameCounter/gameCounter);
		Log.e("gray", "bigCounter:" + bigCounter + ", op:" + (double)bigCounter/gameCounter);
		Log.e("gray", "smallCounter:" + smallCounter + ", op:" + (double)smallCounter/gameCounter);
		Log.e("gray", "maxContinuousBigCounter:" + maxContinuousBigCounter);
		Log.e("gray", "maxContinuousSmallCounter:" + maxContinuousSmallCounter);
//		Log.e("gray", "dice1, point 1:" + dice1_1counter + ", op:" + (double)dice1_1counter/gameCounter);
//		Log.e("gray", "dice1, point 2:" + dice1_2counter + ", op:" + (double)dice1_2counter/gameCounter);
//		Log.e("gray", "dice1, point 3:" + dice1_3counter + ", op:" + (double)dice1_3counter/gameCounter);
//		Log.e("gray", "dice1, point 4:" + dice1_4counter + ", op:" + (double)dice1_4counter/gameCounter);
//		Log.e("gray", "dice1, point 5:" + dice1_5counter + ", op:" + (double)dice1_5counter/gameCounter);
//		Log.e("gray", "dice1, point 6:" + dice1_6counter + ", op:" + (double)dice1_6counter/gameCounter);
//		Log.e("gray", "dice2, point 1:" + dice2_1counter + ", op:" + (double)dice2_1counter/gameCounter);
//		Log.e("gray", "dice2, point 2:" + dice2_2counter + ", op:" + (double)dice2_2counter/gameCounter);
//		Log.e("gray", "dice2, point 3:" + dice2_3counter + ", op:" + (double)dice2_3counter/gameCounter);
//		Log.e("gray", "dice2, point 4:" + dice2_4counter + ", op:" + (double)dice2_4counter/gameCounter);
//		Log.e("gray", "dice2, point 5:" + dice2_5counter + ", op:" + (double)dice2_5counter/gameCounter);
//		Log.e("gray", "dice2, point 6:" + dice2_6counter + ", op:" + (double)dice2_6counter/gameCounter);
//		Log.e("gray", "dice3, point 1:" + dice3_1counter + ", op:" + (double)dice3_1counter/gameCounter);
//		Log.e("gray", "dice3, point 2:" + dice3_2counter + ", op:" + (double)dice3_2counter/gameCounter);
//		Log.e("gray", "dice3, point 3:" + dice3_3counter + ", op:" + (double)dice3_3counter/gameCounter);
//		Log.e("gray", "dice3, point 4:" + dice3_4counter + ", op:" + (double)dice3_4counter/gameCounter);
//		Log.e("gray", "dice3, point 5:" + dice3_5counter + ", op:" + (double)dice3_5counter/gameCounter);
//		Log.e("gray", "dice3, point 6:" + dice3_6counter + ", op:" + (double)dice3_6counter/gameCounter);
		for(AbstractPlayer player : players){
			MainActivity.logString += player.playerName+"start cash:" + player.startCash + "\n";
			MainActivity.logString += player.playerName+"final cash:" + player.totalCash + "\n";
		}
		
		MainActivity.logString += "total game:" + gameCounter + "\n";
		MainActivity.logString += "win:" + winCounter + "\n";
		MainActivity.logString += "lose:" + loseCounter + "\n";
		MainActivity.logString += "========= statistics result =========\n";
		MainActivity.logString += "allSame:" + allSameCounter + ", op:" + (double)allSameCounter/gameCounter + "\n";
		MainActivity.logString += "big:" + bigCounter + ", op:" + (double)bigCounter/gameCounter + "\n";
		MainActivity.logString += "small:" + smallCounter + ", op:" + (double)smallCounter/gameCounter + "\n";
		MainActivity.logString += "maxContinuous Big:" + maxContinuousBigCounter + "\n";
		MainActivity.logString += "maxContinuous Small:" + maxContinuousSmallCounter + "\n";
		
		MainActivity.logString += "===============================\n";
		MainActivity.logString += "sameContinuous 2:" + sameContinuesCounter_2 + ", op:" + (double)sameContinuesCounter_2/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 3:" + sameContinuesCounter_3 + ", op:" + (double)sameContinuesCounter_3/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 4:" + sameContinuesCounter_4 + ", op:" + (double)sameContinuesCounter_4/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 5:" + sameContinuesCounter_5 + ", op:" + (double)sameContinuesCounter_5/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 6:" + sameContinuesCounter_6 + ", op:" + (double)sameContinuesCounter_6/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 7:" + sameContinuesCounter_7 + ", op:" + (double)sameContinuesCounter_7/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 8:" + sameContinuesCounter_8 + ", op:" + (double)sameContinuesCounter_8/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 9:" + sameContinuesCounter_9 + ", op:" + (double)sameContinuesCounter_9/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 10:" + sameContinuesCounter_10 + ", op:" + (double)sameContinuesCounter_10/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 11:" + sameContinuesCounter_11 + ", op:" + (double)sameContinuesCounter_11/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 12:" + sameContinuesCounter_12 + ", op:" + (double)sameContinuesCounter_12/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 13:" + sameContinuesCounter_13 + ", op:" + (double)sameContinuesCounter_13/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 14:" + sameContinuesCounter_14 + ", op:" + (double)sameContinuesCounter_14/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 15:" + sameContinuesCounter_15 + ", op:" + (double)sameContinuesCounter_15/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 16:" + sameContinuesCounter_16 + ", op:" + (double)sameContinuesCounter_16/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 17:" + sameContinuesCounter_17 + ", op:" + (double)sameContinuesCounter_17/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 18:" + sameContinuesCounter_18 + ", op:" + (double)sameContinuesCounter_18/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 19:" + sameContinuesCounter_19 + ", op:" + (double)sameContinuesCounter_19/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous 20:" + sameContinuesCounter_20 + ", op:" + (double)sameContinuesCounter_20/gameCounter+ "\n";
		MainActivity.logString += "sameContinuous others:" + sameContinuesCounter_others + ", op:" + (double)sameContinuesCounter_others/gameCounter+ "\n";
		MainActivity.logString += "===============================\n";
		
		MainActivity.logString += checkRound;
	}
}
