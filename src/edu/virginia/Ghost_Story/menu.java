package edu.virginia.Ghost_Story;

import edu.virginia.Ghost_Story.R;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menu extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); //sets up background for menu
		
		//button Sound
		final MediaPlayer buttonSound = MediaPlayer.create(menu.this, R.raw.boo);
		
		Button tut1 = (Button) findViewById(R.id.button1); //button1 is a view, find it by the id
		tut1.setOnClickListener(new View.OnClickListener() { //must pass clickListener to button
			public void onClick(View v) {					 //this method will hold code for doing something after button clicked 
				buttonSound.start();						//Starts the boo sound when button clicked 
		
				startActivity(new Intent("edu.virginia.Ghost_Story.TUTORIALONE"));
			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	
}
