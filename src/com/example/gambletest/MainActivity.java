package com.example.gambletest;


import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {
	
//	final static boolean isDebug = true;
	final static boolean isDebug = false;
	
	public static String logString;
	
	public TextView mTextView;
	public ListView listView;
	public ArrayList<String> list;
//	public String list[];
	public ArrayAdapter<String> adapter;
	
	public boolean isNextOK;
	public static boolean Token;
	
	public Spinner spinner_max_bet;
	public Spinner spinner_max_round;
	public Spinner spinner_contSameToBet;
	
	public static int maxRound;
	public static int maxBet;
	public static int contSameToBet;
	
	public boolean isSimulate;
	
	public ProgressDialog mProgressDialog;
	
	public static SharedPreferences sharedPrefs;
	public static SharedPreferences.Editor sharedPrefsEditor;

	Handler handler = new Handler() {  
        @Override  
        public void handleMessage(Message msg) {
        	
           	switch (msg.what) {
    			case 0:
    				isSimulate = false;
    				mProgressDialog.dismiss();
					mProgressDialog = null;
//    				mTextView.setText(logString);
    				
					adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, list);
					listView.setAdapter(adapter);
					
    				break;
    			case 1:
    				
    				listView.setAdapter(null);
    				
    				break;
           	}
        }
	};
    				
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.e("gray", "MainActivity.java:onCreate, " + "");
		
//		mTextView = (TextView) findViewById(R.id.tv_log);
		
		listView = (ListView) findViewById(R.id.list);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list = new ArrayList<String>();
		
		logString = "";
		Token = false;
		isSimulate = false;
		
		// get SharedPreferences instance
		if (sharedPrefs == null) {
			sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
		}
		if (sharedPrefsEditor == null) {
			sharedPrefsEditor = sharedPrefs.edit();  
		}
		
		maxRound = sharedPrefs.getInt("pref_maxRound", 3);
		maxBet = sharedPrefs.getInt("pref_maxBet", 3);
		contSameToBet = sharedPrefs.getInt("pref_contSameToBet", 3);
		
		spinner_max_round = (Spinner) findViewById(R.id.spinner_max_round);
		if (maxRound == 100) {
			spinner_max_round.setSelection(0);
		} else if (maxRound == 500) {
			spinner_max_round.setSelection(1);
		} else if (maxRound == 1000) {
			spinner_max_round.setSelection(2);
		} else if (maxRound == 5000) {
			spinner_max_round.setSelection(3);
		} else if (maxRound == 10000) {
			spinner_max_round.setSelection(4);
		} else if (maxRound == 50000) {
			spinner_max_round.setSelection(5);
		} else if (maxRound == 100000) {
			spinner_max_round.setSelection(6);
		} 
		spinner_max_round.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	        public void onItemSelected(AdapterView<?> parent, 
	                View view, int pos, long id) {
	        	Log.e("gray", "MainActivity.java:onItemSelected, pos:" + pos + ", id" + id);

	        	switch (pos) {
				case 0:
					maxRound = 100;
					break;
				case 1:
					maxRound = 500;
					break;
				case 2:
					maxRound = 1000;
					break;
				case 3:
					maxRound = 5000;
					break;
				case 4:
					maxRound = 10000;
					break;
				case 5:
					maxRound = 50000;
					break;
				case 6:
					maxRound = 100000;
					break;

				default:
					break;
				}
	        	
	        	sharedPrefsEditor.putInt("pref_maxRound", maxRound);
	        	sharedPrefsEditor.commit();
	        }

	        public void onNothingSelected(AdapterView<?> arg0) {
	            // TODO Auto-generated method stub
	        	Log.e("gray", "MainActivity.java:onNothingSelected, " + "");

	        }

	    });
		
		spinner_max_bet = (Spinner) findViewById(R.id.spinner_max_bet);
		if (maxBet == 200) {
			spinner_max_bet.setSelection(0);
		} else if (maxBet == 400) {
			spinner_max_bet.setSelection(1);
		} else if (maxBet == 800) {
			spinner_max_bet.setSelection(2);
		} else if (maxBet == 1600) {
			spinner_max_bet.setSelection(3);
		} else if (maxBet == 3200) {
			spinner_max_bet.setSelection(4);
		} else if (maxBet == 6400) {
			spinner_max_bet.setSelection(5);
		}
		spinner_max_bet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	        public void onItemSelected(AdapterView<?> parent, 
	                View view, int pos, long id) {
	        	Log.e("gray", "MainActivity.java:onItemSelected, pos:" + pos + ", id" + id);
	        	switch (pos) {
	        	case 0:
	        		maxBet = 200;
	        		break;
	        	case 1:
	        		maxBet = 400;
	        		break;
				case 2:
					maxBet = 800;
					break;
				case 3:
					maxBet = 1600;
					break;
				case 4:
					maxBet = 3200;
					break;
				case 5:
					maxBet = 6400;
					break;
				default:
					break;
				}

	        	sharedPrefsEditor.putInt("pref_maxBet", maxBet);
	        	sharedPrefsEditor.commit();
	        }

	        public void onNothingSelected(AdapterView<?> arg0) {
	            // TODO Auto-generated method stub
	        	Log.e("gray", "MainActivity.java:onNothingSelected, " + "");

	        }

	    });
		
		spinner_contSameToBet = (Spinner) findViewById(R.id.spinner_contSameToBet);
		if (contSameToBet == 2) {
			spinner_contSameToBet.setSelection(0);
		} else if (contSameToBet == 3) {
			spinner_contSameToBet.setSelection(1);
		} else if (contSameToBet == 4) {
			spinner_contSameToBet.setSelection(2);
		} else if (contSameToBet == 5) {
			spinner_contSameToBet.setSelection(3);
		} else if (contSameToBet == 6) {
			spinner_contSameToBet.setSelection(4);
		} else if (contSameToBet == 7) {
			spinner_contSameToBet.setSelection(5);
		} else if (contSameToBet == 8) {
			spinner_contSameToBet.setSelection(6);
		}
		spinner_contSameToBet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

	        public void onItemSelected(AdapterView<?> parent, 
	                View view, int pos, long id) {
	        	Log.e("gray", "MainActivity.java:onItemSelected, pos:" + pos + ", id" + id);
	        	switch (pos) {
				case 0:
					contSameToBet = 2;
					break;
				case 1:
					contSameToBet = 3;
					break;
				case 2:
					contSameToBet = 4;
					break;
				case 3:
					contSameToBet = 5;
					break;
				case 4:
					contSameToBet = 6;
					break;
				case 5:
					contSameToBet = 7;
					break;
				case 6:
					contSameToBet = 8;
					break;
				default:
					break;
				}

	        	sharedPrefsEditor.putInt("pref_contSameToBet", contSameToBet);
	        	sharedPrefsEditor.commit();
	        }

	        public void onNothingSelected(AdapterView<?> arg0) {
	            // TODO Auto-generated method stub
	        	Log.e("gray", "MainActivity.java:onNothingSelected, " + "");

	        }

	    });
		
		Button button = (Button) findViewById(R.id.button_simulate);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				if (isSimulate == true) {
					showProcessDialog("Please Wait...", "Simulation is still running...\nRound:" + Banker.gameCounter + "\nmaxRound:"+maxRound + "\nmaxBet:"+maxBet + "\ncontSameToBet:"+contSameToBet);
					Log.e("gray", "MainActivity.java:onClick, " + "Simulation is still running!");
					return;
				}
				
				showProcessDialog("Please Wait...", "Simulation is running..." + "\nmaxRound:"+maxRound + "\nmaxBet:"+maxBet + "\ncontSameToBet:"+contSameToBet);
				isSimulate = true;
				logString = "";
				handler.sendEmptyMessage(1);
				
				new Thread(new Runnable() 
	    		{
	    			@Override
	    			public void run() 
	    			{
	    				try {
	    					Banker.init();
	    					Player1.init();
	        				list.clear();
	    					for (int i = 0; i < maxRound; i++) {
	    						
	    						try {
	    							
	    							logString = "";
	    							Banker.gameCounter++;
	    							if (isDebug) {
	    								Log.e("gray", "Banker.java:deal, ======== gameCounter:" + Banker.gameCounter + " =========");
	    							}
	    							logString += "========= Game:" + Banker.gameCounter + " =========\n";
	    							Player1.bet();
	    							if (Player1.isGameOver) {
	    								list.add(logString);
	    								break;
	    							}
	    							Banker.deal();
	    							Banker.pay();
	    							Player1.roundFinish();
	    							list.add(logString);
	    							
	    							
	    							Thread.sleep(1);
	    						} catch (Exception e) {
	    							// TODO: handle exception
	    						}
	    					}
	    					logString = "";
	    					logString += "========= Game:" + "End" + " =========\n";
	    					Banker.statistics();
	    					list.add(logString);
	    					handler.sendEmptyMessage(0);
	    					
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    			}
	    		}).start();
				
			}
		});
	}
	
    public void showProcessDialog(CharSequence title, CharSequence message){
    	
		mProgressDialog = ProgressDialog.show(MainActivity.this, title, message, true);
		mProgressDialog.setCancelable(true); 
    }
    
	public static void waitToken(){
		
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.e("gray", "MainActivity.java:onDestroy, " + "");
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.e("gray", "MainActivity.java:onPause, " + "");
	}

	@Override
	protected void onResume() {
		super.onResume();
		Log.e("gray", "MainActivity.java:onResume, " + "");
	}
}
