package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class Question01FailedActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question01_failed);
		
		// Close The Activity After 5 seconds
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				finish();
			}
			
		}, 5000);
	}
}
