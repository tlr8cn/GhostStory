package edu.virginia.Ghost_Story;

import edu.virginia.Ghost_Story.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {
	MediaPlayer logoSound;
	
	
	//want to load splash screen for ~5 sec and then kill itself and go to new activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);			    //sets the initial view
		
		/*MediaPlayer:
		 * Takes two parameters: Context & reference to actual sound file
		 * 
		 */
		logoSound = MediaPlayer.create(MainActivity.this, R.raw.spooky_zone);
		logoSound.start();
		
		Thread logoTimer = new Thread(){			    //setting up timer for splash screen
										 				//Threads allow you do do multiple things at the same time
			public void run(){						    //running the thread		
				try{					 				//try it in case there are thread collisions
					sleep(5000);						//just rest it on that screen for 5 seconds
					Intent menuIntent = new Intent("edu.virginia.Ghost_Story.MENU");	//New intent of opening menu class
					startActivity(menuIntent);	 		//start activity after that using menu intent as parameter
				} catch (InterruptedException e) {		//Catch exceptions
					e.printStackTrace();
				}
			
				finally{				 //after try block is done doing its thing,
					finish();			 //You want to "finish" the current activity in the finally block
				}
			}
		};
		logoTimer.start();	//Starts thread
	}

	
	
	
	@Override
	protected void onPause() {
		super.onPause();
		logoSound.release(); //done with sound
	}




	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
