package edu.virginia.Ghost_Story;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
/*import android.view.InputDevice;
import android.view.InputEvent;
import android.view.KeyEvent;*/
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
//import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;


@SuppressLint("WrongCall")
public class TutorialOne extends Activity implements /*OnKeyListener,*/ OnTouchListener {

	OurView v;
	Bitmap player;
	float x, y;
	Sprite sprite;
	/*Dpad mDpad = new Dpad();*/

	@SuppressLint("ClickableViewAccessibility")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		v = new OurView(this);
		v.setOnTouchListener(this);
		player = BitmapFactory.decodeResource(getResources(), R.drawable.player_sprite);
		x = y = 0;
		setContentView(v);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		v.resume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		v.pause();
	}

	public class OurView extends SurfaceView implements Runnable{

		Thread t = null;
		SurfaceHolder holder;
		boolean isItOK = false;

		public OurView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
			holder = getHolder();

		}

		public void run() {
			// TODO Auto-generated method stub
			while ( isItOK == true ){
				//perform canvas drawing
				if (!holder.getSurface().isValid()){
					continue;
				}

				//sprite = new Sprite( OurView.this, player);

				Canvas c = holder.lockCanvas();
				onDraw(c);
				
			}
		}
		@SuppressLint("WrongCall")
		protected void onDraw(Canvas canvas){
			canvas.drawARGB(255, 150, 150, 10);
			canvas.drawBitmap(player, x - (player.getWidth()/2), y - (player.getHeight()/2), null);
		    holder.unlockCanvasAndPost(canvas);
		}
		public void pause(){
			isItOK = false;
			while(true){
				try{
					t.join();
				}
				catch( InterruptedException e ){
					e.printStackTrace();
				}
				break;
			}
			t = null;
		}

		@SuppressLint("ClickableViewAccessibility")
		public void resume(){
			isItOK = true;
			t = new Thread(this);
			t.start();
		}

	}

	/*@SuppressLint("RtlHardcoded")
	public static class Dpad {
		final static int UP       = 0;
		final static int LEFT     = 1;
		final static int RIGHT    = 2;
		final static int DOWN     = 3;
		final static int CENTER   = 4;
		Dpad mDpad = new Dpad();

		int directionPressed = -1; // initialized to -1

		public int getDirectionPressed(InputEvent event) {
			if (!isDpadDevice(event)) {
				return -1;
			}

			// If the input event is a MotionEvent, check its hat axis values.
			if (event instanceof MotionEvent) {

				// Use the hat axis value to find the D-pad direction
				MotionEvent motionEvent = (MotionEvent) event;
				float xaxis = motionEvent.getAxisValue(MotionEvent.AXIS_HAT_X);
				float yaxis = motionEvent.getAxisValue(MotionEvent.AXIS_HAT_Y);

				// Check if the AXIS_HAT_X value is -1 or 1, and set the D-pad
				// LEFT and RIGHT direction accordingly.
				if (Float.compare(xaxis, -1.0f) == 0) {
					directionPressed =  Dpad.LEFT;
				} else if (Float.compare(xaxis, 1.0f) == 0) {
					directionPressed =  Dpad.RIGHT;
				}
				// Check if the AXIS_HAT_Y value is -1 or 1, and set the D-pad
				// UP and DOWN direction accordingly.
				else if (Float.compare(yaxis, -1.0f) == 0) {
					directionPressed =  Dpad.UP;
				} else if (Float.compare(yaxis, 1.0f) == 0) {
					directionPressed =  Dpad.DOWN;
				}
			}

			// If the input event is a KeyEvent, check its key code.
			else if (event instanceof KeyEvent) {

				// Use the key code to find the D-pad direction.
				KeyEvent keyEvent = (KeyEvent) event;
				if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
					directionPressed = Dpad.LEFT;
				} else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
					directionPressed = Dpad.RIGHT;
				} else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
					directionPressed = Dpad.UP;
				} else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
					directionPressed = Dpad.DOWN;
				} else if (keyEvent.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER) {
					directionPressed = Dpad.CENTER;
				}
			}
			return directionPressed;
		}

		public static boolean isDpadDevice(InputEvent event) {
			// Check that input comes from a device with directional pads.
			if ((event.getSource() & InputDevice.SOURCE_DPAD)
					!= InputDevice.SOURCE_DPAD) {
				return true;
			} else {
				return false;
			}
		}

	}

	public boolean onGenericMotionEvent(MotionEvent event) {
		
		// Check if this event if from a D-pad and process accordingly.
		if (Dpad.isDpadDevice(event)) {

			int press = mDpad.getDirectionPressed(event);
			switch (press) {
			case 0:
				// Do something for UP direction press
				y = event.getY() - 1;
				return true;
			case 1:
				// Do something for LEFT direction press
				x = event.getX() - 1;

				return true;
			case 2:
				// Do something for RIGHT direction press
				x = event.getX() + 1;

				return true;
			case 3:
				// Do something for DOWN direction press
				y = event.getY() + 1;

				return true;

			}
		}
		return false;

	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return true;
	}*/

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent me) {
		// TODO Auto-generated method stub
		switch(me.getAction()){
		case MotionEvent.ACTION_DOWN:
			x = me.getX();
			y = me.getY();
			break;
		case MotionEvent.ACTION_UP:
			x = me.getX();
			y = me.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			x = me.getX();
			y = me.getY();
			break;
		}
		return true;
	}

}
