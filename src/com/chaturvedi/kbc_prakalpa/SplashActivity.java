package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity
{
	private int SPLASH_TIME = 12000;		//12 Seconds
	private MediaPlayerService musicPlayer;
	private Intent  mainIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		mainIntent = new Intent(this, MainActivity.class);
		new Handler().postDelayed(new Runnable() 
		{
			@Override
			public void run()
			{
				startActivity(mainIntent);
				finish();
			}
		} ,SPLASH_TIME);
		musicPlayer = new MediaPlayerService(getApplicationContext(), "startup");
		musicPlayer.playMusic();
	}
	
	// Disable Back Button
	public void onBackPressed()
	{
		
	}
}
