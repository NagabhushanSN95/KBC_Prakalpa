package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import custom_views.QuitDialog;
import custom_views.SelectButton;
import custom_views.SelectButtonMini;

public class TimeoutActivity extends Activity
{
	private QuitDialog quitDialog;
	private SelectButton playButton;
	private SelectButtonMini quitButton;
	private int levelNo;
	private Intent previousActivityIntent;
	private Intent splashIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_timeout);
		
		previousActivityIntent = getIntent();
		levelNo = previousActivityIntent.getIntExtra("NUM LEVEL FAILED", 1);

		MediaPlayerService.playSound(getApplicationContext(), "buzzer");
		buildLayout();
		splashIntent = new Intent(this, SplashActivity.class);
	}
	
	private void buildLayout()
	{
		ImageView chocolateView = (ImageView)findViewById(R.id.chocolate);
		if(levelNo == 1 || levelNo == 2)
			chocolateView.setImageResource(R.drawable.melody);
		else if(levelNo == 3 || levelNo == 4)
			chocolateView.setImageResource(R.drawable.melody);
		else if(levelNo == 5)
			chocolateView.setImageResource(R.drawable.dairymilk_shots);
		
		playButton = (SelectButton) findViewById(R.id.button_play);
		playButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
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
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				onBackPressed();
			}
		});
		
		// Make the buttons invisible for 5 seconds
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
		}, 2000);
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
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				quitDialog.dismiss();
			}
		});
		quitDialog.setOnQuitListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				quitDialog.dismiss();
				finish();
			}
		});
		quitDialog.show();
	}
}
