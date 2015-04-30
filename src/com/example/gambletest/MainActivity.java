package com.example.gambletest;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    final static boolean isDebug = true;
//    final static boolean isDebug = false;

    final static int NA = 0;
    final static int LOSE = -1;
    final static int WIN = 1;
    
    public static final String UPDATE_UI = "UPDATE_UI";
    public static final String STOP_SIMULATION = "STOP_SIMULATION";
    
    public static String logString;

    public TextView mTextView;
    public ListView listView;
    public static ArrayList<String> list;
    public static ArrayList<String> listColor;
    // public String list[];
//    public ArrayAdapter<String> adapter;
    public MyAdapter adapter;

    public boolean isNextOK;
    public static boolean Token;

    public Spinner spinner_max_bet;
    public Spinner spinner_max_round;
    public Spinner spinner_contSameToBet;

    public static int playerIndex;
    public static int simulationTimes;
    public static int maxRound;
    public static int maxBet;
    public static int contSameToBet;
    public static int minBet;
    public static int playerStartCash;
    public static int currentSimulationTimes;

    public static boolean isSimulating;

    public ProgressDialog mProgressDialog;

    public static SharedPreferences sharedPrefs;
    public static SharedPreferences.Editor sharedPrefsEditor;

    public static Context mContext;

    public static boolean isDetailLog;
    public static int profit;
    public static int totalRound;
    
    public Intent serviceIntent;

    Handler handler = new Handler() {
	@Override
	public void handleMessage(Message msg) {

	    switch (msg.what) {
	    case 0:
		
		if (mProgressDialog != null) {
		    mProgressDialog.dismiss();
		    mProgressDialog = null;
		}
		// mTextView.setText(logString);

//		adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
		String[] listArray = Arrays.copyOf(list.toArray(), list.toArray().length, String[].class);
		String[] listCollorArray = Arrays.copyOf(listColor.toArray(), listColor.toArray().length, String[].class);
		adapter = new MyAdapter(MainActivity.this, R.layout.drawer_list_item, listArray, listCollorArray);
		listView.setAdapter(adapter);

		break;
		
	    case 1:

		listView.setAdapter(null);

		break;
		
	    case 2:

		stopService(serviceIntent);

		break;
	    }
	}
    };

    private BroadcastReceiver mBroadcast =  new BroadcastReceiver() {
        @Override
        public void onReceive(Context mContext, Intent mIntent) {
            if (isDebug) {
		Log.e("gray", "MainActivity.java:onReceive, " + mIntent.getAction());
	    }
            
            if(UPDATE_UI.equals(mIntent.getAction())){
        	handler.sendEmptyMessage(0);
            } else if (STOP_SIMULATION.equals(mIntent.getAction())) {
        	handler.sendEmptyMessage(2);
	    }
        }
    };
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);

	Log.e("gray", "MainActivity.java:onCreate, " + "");

	// mTextView = (TextView) findViewById(R.id.tv_log);

	mContext = getApplicationContext();
	listView = (ListView) findViewById(R.id.list);
	listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
	list = new ArrayList<String>();
	listColor = new ArrayList<String>();

	logString = "";
	Token = false;
	isSimulating = false;
	
	IntentFilter filter = new IntentFilter();
	filter.addAction(UPDATE_UI);
	filter.addAction(STOP_SIMULATION);
	registerReceiver(mBroadcast, filter);

	// get SharedPreferences instance
	if (sharedPrefs == null) {
	    sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
	}
	if (sharedPrefsEditor == null) {
	    sharedPrefsEditor = sharedPrefs.edit();
	}

	playerIndex = Integer.parseInt(sharedPrefs.getString("pref_playerIndex", "1"));
	simulationTimes = Integer.parseInt(sharedPrefs.getString("pref_simulationTimes", "1"));
	maxRound = Integer.parseInt(sharedPrefs.getString("pref_maxRound", "100"));
	maxBet = Integer.parseInt(sharedPrefs.getString("pref_maxBet", "400"));
	contSameToBet = Integer.parseInt(sharedPrefs.getString("pref_contSameToBet", "3"));
	minBet = Integer.parseInt(sharedPrefs.getString("pref_minBet", "50"));
	playerStartCash = Integer.parseInt(sharedPrefs.getString("pref_playerStartCash", "10000"));

	Button button = (Button) findViewById(R.id.button_simulate);
	button.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View arg0) {
		
		if (isSimulating == true) {
		    showProcessDialog("Please Wait...", "Simulation is still running..." + "\n家览Ω计:" + simulationTimes + "\n–Ω家览Ы计:" + maxRound + "\nヘ玡材 " + currentSimulationTimes + " Ω 材 " + Banker.gameCounter + " Ы");
		    return;
		}
		
		showProcessDialog("Please Wait...", "Simulation is running..." + "\n家览Ω计:" + simulationTimes + "\n–Ω家览Ы计:" + maxRound);
		handler.sendEmptyMessage(1);
		
		new Thread(new Runnable() {
		    @Override
		    public void run() {
			logString = "";
			profit = 0;
			totalRound = 0;
			list.clear();
			listColor.clear();
			if (simulationTimes == 1) {
			    isDetailLog = true;
			} else {
			    isDetailLog = false;
			}
			serviceIntent = new Intent(MainActivity.this, SimulationService.class);
			startService(serviceIntent);
		    }
		}).start();
		
	    }
	});
    }

    public void showProcessDialog(CharSequence title, CharSequence message) {
	mProgressDialog = ProgressDialog.show(MainActivity.this, title, message, true);
	mProgressDialog.setCancelable(true);
    }

    public static void waitToken() {

	while (Token) {
	    Log.e("gray", "MainActivity.java:waitToken, " + "wait");
	    try {
		Thread.sleep(10);
	    } catch (Exception e) {
		// TODO: handle exception
	    }
	}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	Log.e("gray", "MainActivity.java:onCreateOptionsMenu, " + "");
	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
	// Handle action bar item clicks here. The action bar will
	// automatically handle clicks on the Home/Up button, so long
	// as you specify a parent activity in AndroidManifest.xml.
	Log.e("gray", "MainActivity.java:onOptionsItemSelected, " + "");
	int id = item.getItemId();
	if (id == R.id.action_settings) {

	    Intent i = new Intent(this, SettingsActivity.class);
	    startActivityForResult(i, 0);
	    return true;

	} else if (id == R.id.action_mail_log) {

	    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
	    sharingIntent.setType("text/plain");
	    sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "GTest Log");
	    
	    // >= 1000 round seems exceed memory than crash
	    String tempString = "To much log, try <=500 round.";
	    if (maxRound < 1000) {
		tempString = list.toString().replaceAll("\\[|\\]", "").replaceAll(", ","");
	    }
	    sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, tempString);
	    
	    startActivity(Intent.createChooser(sharingIntent, "Share via ..."));
	}

	return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	super.onActivityResult(requestCode, resultCode, data);
	if (isDebug) {
	    Log.e("gray", "MainActivity.java:onActivityResult, requestCode:" + requestCode + ", resultCode:" + resultCode);
	}
	switch (requestCode) {
	case 0:

	    playerIndex = Integer.parseInt(sharedPrefs.getString("pref_playerIndex", "1"));
	    simulationTimes = Integer.parseInt(sharedPrefs.getString("pref_simulationTimes", "1"));
	    maxRound = Integer.parseInt(sharedPrefs.getString("pref_maxRound", "100"));
	    maxBet = Integer.parseInt(sharedPrefs.getString("pref_maxBet", "400"));
	    contSameToBet = Integer.parseInt(sharedPrefs.getString("pref_contSameToBet", "3"));
	    minBet = Integer.parseInt(sharedPrefs.getString("pref_minBet", "50"));
	    playerStartCash = Integer.parseInt(sharedPrefs.getString("pref_playerStartCash", "10000"));

	    break;

	default:
	    break;
	}

	// super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
	super.onDestroy();
	Log.e("gray", "MainActivity.java:onDestroy, " + "");
	
	unregisterReceiver(mBroadcast);
	
	if (serviceIntent != null) {
	    stopService(serviceIntent);
	}
    }

    @Override
    protected void onPause() {
	super.onPause();
	Log.e("gray", "MainActivity.java:onPause, " + "");
	
	if (mProgressDialog != null) {
	    mProgressDialog.dismiss();
	    mProgressDialog = null;
	}
    }

    @Override
    protected void onResume() {
	super.onResume();
	Log.e("gray", "MainActivity.java:onResume, " + "");
	if (isSimulating == true) {
	    showProcessDialog("Please Wait...", "Simulation is still running..." + "\n家览Ω计:" + simulationTimes + "\n–Ω家览Ы计:" + maxRound + "\nヘ玡材 " + currentSimulationTimes + " Ω 材 " + Banker.gameCounter + " Ы");
	} else {
	    String[] listArray = Arrays.copyOf(list.toArray(), list.toArray().length, String[].class);
	    String[] listCollorArray = Arrays.copyOf(listColor.toArray(), listColor.toArray().length, String[].class);
	    adapter = new MyAdapter(MainActivity.this, R.layout.drawer_list_item, listArray, listCollorArray);
	    listView.setAdapter(adapter);
	}
	
    }
    
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
	if (isDebug) {
	    Log.e("gray", "MainActivity.java:onKeyDown, keyCode:" + keyCode);
	}
	if (keyCode == KeyEvent.KEYCODE_BACK) {
	    if (isDebug) {
		Log.e("gray", "MainActivity.java:onKeyDown, " + "KEYCODE_BACK");
	    }
	    
	    if (isSimulating) {
		moveTaskToBack(true);
		return true;
	    }
	}
	return super.onKeyDown(keyCode, event);
  }
  
}
