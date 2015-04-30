package com.example.gambletest;

import java.io.FileDescriptor;
import java.io.PrintWriter;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class SimulationService extends Service {

    final boolean isDebug = true;

    public boolean isStopSimulation;

    final static int NA = 0;
    final static int LOSE = -1;
    final static int WIN = 1;

    private NotificationCompat.Builder mBuilder;
    
    @Override
    public IBinder onBind(Intent arg0) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onBind, " + "");
	}
	return null;
    }

    @Override
    protected void dump(FileDescriptor fd, PrintWriter writer, String[] args) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:dump, " + "");
	}
	super.dump(fd, writer, args);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onConfigurationChanged, " + "");
	}
	super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onCreate, " + "");
	}
	super.onCreate();
    }

    @Override
    public void onDestroy() {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onDestroy, " + "");
	}
	
	isStopSimulation = true;
	
	super.onDestroy();
    }

    @Override
    public void onLowMemory() {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onLowMemory, " + "");
	}
	super.onLowMemory();
    }

    @Override
    public void onRebind(Intent intent) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onRebind, " + "");
	}
	super.onRebind(intent);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onStartCommand, flags:" + flags + ", startId:" + startId);
	}

	Intent updateUIIntent = new Intent();
	updateUIIntent.setAction(MainActivity.UPDATE_UI);
	PendingIntent pendingIntentUpdateStatus = PendingIntent.getBroadcast(this, 0, updateUIIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	
	Intent stopSimulationIntent = new Intent();
	stopSimulationIntent.setAction(MainActivity.STOP_SIMULATION);
        PendingIntent pendingIntentStopSimulation = PendingIntent.getBroadcast(this, 0, stopSimulationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
	
        mBuilder = new NotificationCompat.Builder(this)
        .setSmallIcon(R.drawable.ic_launcher)
        .setContentTitle("GTest")
        .setTicker("Simulation is running")
        .addAction(R.drawable.ic_action_refresh, "Current result", pendingIntentUpdateStatus)
        .addAction(R.drawable.ic_action_stop, "Stop simulation", pendingIntentStopSimulation);
        
//        Intent notificationIntent = new Intent(this, MainActivity.class);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        
        startForeground(1337, mBuilder.build());
        
//	Notification note = new Notification(R.drawable.ic_launcher, "GTest", System.currentTimeMillis());
////	Intent i = new Intent(this, MainActivity.class);
//	Intent i = new Intent();
//	i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//	PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
//	note.setLatestEventInfo(this, "GTest", "Simulation is running", pi);
//	note.flags |= Notification.FLAG_NO_CLEAR;
//	startForeground(1337, note);

	new Thread(new Runnable() {
	    @Override
	    public void run() {
		try {

		    isStopSimulation = false;
		    
		    for (int j = 0; j < MainActivity.simulationTimes; j++) {

			if (isStopSimulation) {
			    if (isDebug) {
				Log.e("gray", "SimulationService.java:run, simulation stop" + "");
			    }
			    break;
			}

			MainActivity.isSimulating = true;
			MainActivity.currentSimulationTimes = j + 1;
			AbstractPlayer player;
			if (MainActivity.playerIndex == 1) {
			    player = new Player1("Player1");
			} else if (MainActivity.playerIndex == 2) {
			    player = new Player2("Player2");
			} else if (MainActivity.playerIndex == 3) {
			    player = new Player3("Player3");
			} else {
			    player = new Player1("Player1");
			}
			Banker.init();
			Banker.players.add(player);
			player.init();

			if (!MainActivity.isDetailLog) {
			    MainActivity.logString = "";
			    MainActivity.logString += "========= Simulation Times:" + (j + 1) + " =========\n";
			}

			for (int i = 0; i < MainActivity.maxRound; i++) {

			    if (isStopSimulation) {
				break;
			    }
			    
			    MainActivity.totalRound++;
			    Banker.gameCounter++;
			    // if (isDebug) {
			    // Log.e("gray",
			    // "Banker.java:deal, ======== gameCounter:" +
			    // Banker.gameCounter + " =========");
			    // }
			    if (MainActivity.isDetailLog) {
				MainActivity.logString = "";
				MainActivity.logString += "========= Game:" + Banker.gameCounter + " =========\n";
			    }
			    player.bet();
			    if (player.isGameOver) {
				if (MainActivity.isDetailLog) {
				    MainActivity.list.add(MainActivity.logString);
				    MainActivity.listColor.add("#d9d9d9");
				}
				break;
			    }
			    Banker.deal();
			    Banker.pay();
			    player.roundFinish();
			    if (MainActivity.isDetailLog) {
				MainActivity.list.add(MainActivity.logString);
				if (player.getBetResult() == WIN) {
				    MainActivity.listColor.add("#7fc65f");
				} else if (player.getBetResult() == LOSE) {
				    MainActivity.listColor.add("#e06666");
				} else {
				    MainActivity.listColor.add("#d9d9d9");
				}
			    }

			    Thread.sleep(1);

			}

			if (MainActivity.isDetailLog) {
			    MainActivity.logString = "";
			    MainActivity.logString += "========= Game:" + "End" + " =========\n";
			    Banker.statistics();
			    MainActivity.list.add(MainActivity.logString);
			    MainActivity.listColor.add("#d9d9d9");
			} else {
			    MainActivity.logString += player.playerName + " start cash:" + player.startCash + "\n";
			    MainActivity.logString += player.playerName + " final cash:" + player.totalCash + "\n";
			    MainActivity.profit += (player.totalCash - player.startCash);
			    MainActivity.list.add(MainActivity.logString);
			    if (j % 2 == 0) {
				MainActivity.listColor.add("#ffd966");
			    } else {
				MainActivity.listColor.add("#8e7cc3");
			    }
			}

		    }
		    
		    MainActivity.isSimulating = false;

		    if (!MainActivity.isDetailLog) {
			MainActivity.logString = "========= Simulation End =========\n";
			MainActivity.logString += "Total Profit:" + MainActivity.profit + "\n";
			MainActivity.logString += "Average Profit:" + ((double) MainActivity.profit) / MainActivity.totalRound + "\n";
			MainActivity.list.add(MainActivity.logString);
			MainActivity.listColor.add("#4a86e8");
		    }

		    if (isDebug) {
			Log.e("gray", "SimulationService.java:onCreate, UPDATE_UI" + "");
		    }
		    Intent intent1 = new Intent();
		    intent1.setAction(MainActivity.UPDATE_UI);
		    sendBroadcast(intent1);
		    
		    stopSelf();
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	}).start();

	return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onTaskRemoved, " + "");
	}
	super.onTaskRemoved(rootIntent);
    }

    @Override
    public void onTrimMemory(int level) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onTrimMemory, level:" + level);
	}
	super.onTrimMemory(level);
    }

    @Override
    public boolean onUnbind(Intent intent) {
	if (isDebug) {
	    Log.e("gray", "SimulationService.java:onUnbind, " + "");
	}
	return super.onUnbind(intent);
    }

}
