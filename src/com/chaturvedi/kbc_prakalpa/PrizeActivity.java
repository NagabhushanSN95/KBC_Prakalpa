package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import custom_views.QuitDialog;
import custom_views.SelectButton;
import custom_views.SelectButtonMini;

public class PrizeActivity extends Activity
{
	private SelectButton playButton;
	private SelectButtonMini quitButton;
	private QuitDialog quitDialog;
	
	private Intent splashIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prize);
		
		MediaPlayerService kbcPlayer = new MediaPlayerService(getApplicationContext(), "startup");
		kbcPlayer.playMusic();
		
		buildLayout();
		splashIntent = new Intent(this, SplashActivity.class);
	}
	
	private void buildLayout()
	{
		playButton = (SelectButton) findViewById(R.id.button_play);
		playButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(splashIntent);
				finish();
			}
		});
		
		quitButton = (SelectButtonMini) findViewById(R.id.button_quit);
		quitButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				onBackPressed();
			}
		});
		
		// Make the buttons invisible for 10 seconds
		playButton.setVisibility(View.GONE);
		quitButton.setVisibility(View.GONE);
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				playButton.setVisibility(View.VISIBLE);
				quitButton.setVisibility(View.VISIBLE);
			}
		}, 10000);
	}
	
	public void onBackPressed()
	{
		// Set The Onclick Listeners For The quitDialog
		quitDialog = new QuitDialog(this);
		quitDialog.setOnPlayListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				quitDialog.dismiss();
			}
		});
		quitDialog.setOnQuitListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				quitDialog.dismiss();
				finish();
			}
		});
		quitDialog.show();
	}
}
