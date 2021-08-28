// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package com.chaturvedi.kbc_prakalpa;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import custom_views.CountdownClock;
import custom_views.OptionButton;

public class Question01Activity extends Activity
{
	private DisplayMetrics displayMetrics;
	private int screenWidth;
	private int screenHeight;
	private int WIDTH_OPTION_BUTTONS;
	private int HEIGHT_OPTION_BUTTONS;
	
	private static int questionNo;
	private String question;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private int answer;
	
	private TextView questionView;
	private OptionButton optionAButton;
	private OptionButton optionBButton;
	private OptionButton optionCButton;
	private OptionButton optionDButton;
	
	private long TIME_ALLOTED = 10000;
	private Handler timeoutHandler;
	private Runnable timeoutRunnable;
	private CountdownClock timer;
	
	private static final String SHARED_PREFERENCES_QUESTION_NOS = "questionNos";
	private static final String KEY_QUESTION01 = "question01";
	
	private Intent nextActivityIntent;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question01);
		
		displayMetrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenWidth=displayMetrics.widthPixels;
		screenHeight=displayMetrics.heightPixels;
		WIDTH_OPTION_BUTTONS = screenWidth*40/100;
		HEIGHT_OPTION_BUTTONS = screenHeight*20/100;
		
		readPreferences();
		readQuestion();
		buildLayout();
		setTimeout();
	}
	
	private void readPreferences()
	{
		SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_QUESTION_NOS, 0);
		if(preferences.contains(KEY_QUESTION01))
		{
			questionNo=preferences.getInt(KEY_QUESTION01, 1);
		}
		else
		{
			questionNo=1;
		}
	}
	
	private void readQuestion()
	{
		int numIgnoreLines = (questionNo-1)*7;
		Toast.makeText(getApplicationContext(), "NumIgnoreLines= "+numIgnoreLines, Toast.LENGTH_SHORT).show();
		
		InputStream questionStream = getResources().openRawResource(R.raw.question01);
		BufferedReader questionReader = new BufferedReader(new InputStreamReader(questionStream));
		try
		{
			for(int i=0; i<numIgnoreLines; i++)
			{
				questionReader.readLine();
			}
			
			question = questionReader.readLine();
			optionA = questionReader.readLine();
			optionB = questionReader.readLine();
			optionC = questionReader.readLine();
			optionD = questionReader.readLine();
			answer = Integer.parseInt(questionReader.readLine());
			
			// Check if the file has reached the end. If so, set the questionNo=1 to point to first question again
			// Else, increament the questionNo to point to the next question
			if(questionReader.readLine().equals("end"))
			{
				questionNo=1;
			}
			else
			{
				questionNo++;
			}
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		// Store The Next Question Number In The Shared Preferences
		SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_QUESTION_NOS, 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(KEY_QUESTION01, questionNo);
		editor.commit();
	}
	
	private void buildLayout()
	{
		questionView = (TextView) findViewById(R.id.questionView);
		questionView.setText(question);
		
		optionAButton = (OptionButton) findViewById(R.id.button_optionA);
		LayoutParams optionAParams = (LayoutParams) optionAButton.getLayoutParams();
		optionAParams.width=WIDTH_OPTION_BUTTONS;
		optionAParams.height=HEIGHT_OPTION_BUTTONS;
		optionAButton.setLayoutParams(optionAParams);
		optionAButton.setText(optionA);
		optionAButton.setOnClickListener(new AnswerListener(1));
		
		optionBButton = (OptionButton) findViewById(R.id.button_optionB);
		LayoutParams optionBParams = (LayoutParams) optionBButton.getLayoutParams();
		optionBParams.width=WIDTH_OPTION_BUTTONS;
		optionBParams.height=HEIGHT_OPTION_BUTTONS;
		optionBButton.setLayoutParams(optionBParams);
		optionBButton.setText(optionB);
		optionBButton.setOnClickListener(new AnswerListener(2));
		
		optionCButton = (OptionButton) findViewById(R.id.button_optionC);
		LayoutParams optionCParams = (LayoutParams) optionCButton.getLayoutParams();
		optionCParams.width=WIDTH_OPTION_BUTTONS;
		optionCParams.height=HEIGHT_OPTION_BUTTONS;
		optionCButton.setLayoutParams(optionCParams);
		optionCButton.setText(optionC);
		optionCButton.setOnClickListener(new AnswerListener(3));
		
		optionDButton = (OptionButton) findViewById(R.id.button_optionD);
		LayoutParams optionDParams = (LayoutParams) optionDButton.getLayoutParams();
		optionDParams.width=WIDTH_OPTION_BUTTONS;
		optionDParams.height=HEIGHT_OPTION_BUTTONS;
		optionDButton.setLayoutParams(optionDParams);
		optionDButton.setText(optionD);
		optionDButton.setOnClickListener(new AnswerListener(4));
	}
	
	private class AnswerListener implements View.OnClickListener
	{
		private int optionNo;
		
		public AnswerListener(int optNo)
		{
			optionNo = optNo;
		}
		
		@Override
		public void onClick(View v)
		{
			final OptionButton button = (OptionButton) v;
			button.lockOption();
			
			// Stop The Timeout Handler
			timeoutHandler.removeCallbacks(timeoutRunnable);
			// Stop The Tick-Tick Sound
			MediaPlayerService.stopMusic();
			// Stop The Timer Showing The Number Of Seconds Left
			timer.stopTimer();
			
			new Handler().postDelayed(new Runnable() 
			{
				@Override
				public void run()
				{
					// If Answer Is Correct, Display Correct Answer And Make It Green
					// Else, Display Incorrect Answer And Make It Red
					// And Start The Corresponding Next Activities
					if(optionNo==answer)
					{
						Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
						button.displayCorrect();
						MediaPlayerService.playMusic(getApplicationContext(), "cheer");
						nextActivityIntent = new Intent(getApplicationContext(), Question01AnsweredActivity.class);
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT).show();
						button.displayIncorrect();
						nextActivityIntent = new Intent(getApplicationContext(), Question01FailedActivity.class);
					}
				}
			} ,2000);
			
			new Handler().postDelayed(new Runnable() 
			{
				@Override
				public void run()
				{
					startActivity(nextActivityIntent);
					Question01Activity.this.finish();
				}
			} ,4000);
			
		}
	}
	
	private void setTimeout()
	{
		timer = (CountdownClock) findViewById(R.id.countdownClock);
		timer.startTimer(TIME_ALLOTED);
		timer.bringToFront();
		
		MediaPlayerService.playMusic(getApplicationContext(), "tick");
		nextActivityIntent = new Intent(this, Question01TimeoutActivity.class);
		timeoutHandler = new Handler();
		timeoutRunnable = new Runnable() 
		{
			@Override
			public void run()
			{
				timer.stopTimer();
				MediaPlayerService.stopMusic();
				startActivity(nextActivityIntent);
				Question01Activity.this.finish();
			}
		};
		timeoutHandler.postDelayed(timeoutRunnable ,TIME_ALLOTED);
	}
	
	// Disable Back Button
	public void onBackPressed()
	{
		
	}
}
