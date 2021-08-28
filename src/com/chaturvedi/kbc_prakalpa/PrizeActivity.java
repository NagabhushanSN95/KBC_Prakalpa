package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.os.Bundle;

public class PrizeActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prize);
		
		MediaPlayerService kbcPlayer = new MediaPlayerService(getApplicationContext(), "startup");
		kbcPlayer.playMusic();
	}
}
