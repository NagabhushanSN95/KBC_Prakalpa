package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import custom_views.QuitDialog;
import custom_views.SelectButton;
import custom_views.SelectButtonMini;

public class PrizeActivity extends Activity
{
	private SelectButton playButton;
	private SelectButtonMini quitButton;
	private QuitDialog quitDialog;
	
	private ImageView[] stars;
	private AnimationSet[] starAnimation; 
	
	private Intent splashIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prize);
		
		MediaPlayerService kbcPlayer = new MediaPlayerService(getApplicationContext(), "startup");
		kbcPlayer.playMusic();
		
		buildAnimations();
		buildLayout();
		splashIntent = new Intent(this, SplashActivity.class);
	}
	
	private void buildAnimations()
	{
		stars = new ImageView[3];
		stars[0] = (ImageView) findViewById(R.id.star01);
		stars[1] = (ImageView) findViewById(R.id.star02);
		stars[2] = (ImageView) findViewById(R.id.star03);
		
		starAnimation = new AnimationSet[3];
		for(int i=0; i<3; i++)
		{
			Animation alphaAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_star);
			Animation rotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_star);
			starAnimation[i] = new AnimationSet(false);
			starAnimation[i].addAnimation(alphaAnimation);
			starAnimation[i].addAnimation(rotation);
			
			stars[i].setVisibility(View.GONE);
		}
		
		stars[0].setVisibility(View.VISIBLE);
		stars[0].startAnimation(starAnimation[0]);
		
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				stars[1].setVisibility(View.VISIBLE);
				stars[1].startAnimation(starAnimation[1]);
			}
		}, 500);
		
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				stars[2].setVisibility(View.VISIBLE);
				stars[2].startAnimation(starAnimation[2]);
				stars[0].setVisibility(View.GONE);
			}
		}, 1000);
		
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				stars[1].setVisibility(View.GONE);
			}
		}, 1500);
		
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				stars[2].setVisibility(View.GONE);
			}
		}, 2000);
	}
	
	private void buildLayout()
	{
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
