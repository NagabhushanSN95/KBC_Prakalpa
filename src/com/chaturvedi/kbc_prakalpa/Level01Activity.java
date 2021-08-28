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
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import custom_views.CountdownClock;
import custom_views.OptionButton;

public class Level01Activity extends Activity
{
	/*private DisplayMetrics displayMetrics;
	private int screenWidth;
	private int screenHeight;
	private int WIDTH_OPTION_BUTTONS;
	private int HEIGHT_OPTION_BUTTONS;*/

	private String SHARED_PREFERENCES_QUESTION_NOS = "questionNos";
	private String KEY_QUESTION = "question0";							// Append 1 or 2 later depending on the level
	
	private int levelNo;
	private int questionNo;
	private String question;
	private String[] options;
	private int answer;
	
	private LinearLayout questionLayout;
	private TextView questionView;
	private RelativeLayout optionsLayout;
	private OptionButton[] optionButtons;
	private CountdownClock countdownClock;

	private TranslateAnimation questionEnterAnimation;
	private TranslateAnimation questionExitAnimation;
	private TranslateAnimation optionsEnterAnimation;
	private TranslateAnimation optionsExitAnimation;
	private AlphaAnimation countdownClockAnimation;
	
	private MediaPlayerService tickPlayer;
	private MediaPlayerService cheerPlayer;
	
	private long TIME_ALLOTED = 30000;
	private Handler timeoutHandler;
	private Runnable timeoutRunnable;
	private CountdownClock timer;
	
	private Intent previousActivityIntent;
	private Intent nextActivityIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level01);
		
		previousActivityIntent = getIntent();
		levelNo = previousActivityIntent.getIntExtra("LEVEL NO", 1);
		KEY_QUESTION+=levelNo;
		
		/*displayMetrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenWidth=displayMetrics.widthPixels;
		screenHeight=displayMetrics.heightPixels;
		WIDTH_OPTION_BUTTONS = screenWidth*40/100;
		HEIGHT_OPTION_BUTTONS = screenHeight*20/100;*/

		tickPlayer = new MediaPlayerService(getApplicationContext(), "tick30");
		cheerPlayer = new MediaPlayerService(getApplicationContext(), "cheer");
		
		readPreferences();
		readQuestion();
		buildAnimations();
		buildLayout();
		setTimeout();
	}
	
	private void readPreferences()
	{
		SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_QUESTION_NOS, 0);
		if(preferences.contains(KEY_QUESTION))
		{
			questionNo=preferences.getInt(KEY_QUESTION, 1);
		}
		else
		{
			questionNo = 1;
		}
	}
	
	private void readQuestion()
	{
		int questionsResId;							// ID of the raw file containing the questions 
		switch(levelNo)
		{
			case 1:									// For 1st Level, select the raw file question01
				questionsResId = R.raw.question01;
				break;
				
			case 2:									// For 2nd Level, select the raw file question02
				questionsResId = R.raw.question02;
				break;
				
			default:
				questionsResId = R.raw.question01;
				break;	
		}
		InputStream questionStream = getResources().openRawResource(questionsResId);
		BufferedReader questionReader = new BufferedReader(new InputStreamReader(questionStream));
		try
		{
			int numIgnoreLines = (questionNo-1)*7;
			for(int i=0; i<numIgnoreLines; i++)
			{
				questionReader.readLine();
			}
			
			question = questionReader.readLine();
			options = new String[4];
			for(int i=0; i<4; i++)
			{
				options[i] = questionReader.readLine();
			}
			answer = Integer.parseInt(questionReader.readLine());
			
			// Check if the file has reached the end. If so, set the questionNo=1 to point to first question again
			// Else, increament the questionNo to point to the next question
			if(questionReader.readLine().equals("end"))
			{
				questionNo=1;
				//Toast.makeText(getApplicationContext(), "Check-Point 01: "+questionNo, Toast.LENGTH_SHORT).show();
			}
			else
			{
				questionNo++;
				//Toast.makeText(getApplicationContext(), "Check-Point 02: "+questionNo, Toast.LENGTH_SHORT).show();
			}
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		// Store The Next Question Number In The Shared Preferences
		SharedPreferences preferences = getSharedPreferences(SHARED_PREFERENCES_QUESTION_NOS, 0);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putInt(KEY_QUESTION, questionNo);
		//Toast.makeText(getApplicationContext(), "Check-Point 03: "+questionNo, Toast.LENGTH_SHORT).show();
		editor.commit();
	}
	
	private void buildAnimations()
	{
		// Set Animations 
		
		questionEnterAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -2.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		questionEnterAnimation.setDuration(1000);
		questionEnterAnimation.setFillAfter(true);
		questionEnterAnimation.setFillEnabled(true);
		
		questionExitAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, -2.0f);
		questionExitAnimation.setDuration(1000);
		questionExitAnimation.setFillAfter(true);
		questionExitAnimation.setFillEnabled(true);
		
		optionsEnterAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 3.0f, Animation.RELATIVE_TO_SELF, 0.0f);
		optionsEnterAnimation.setDuration(1000);
		optionsEnterAnimation.setFillAfter(true);
		optionsEnterAnimation.setFillEnabled(true);
		
		optionsExitAnimation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF, 3.0f);
		optionsExitAnimation.setDuration(1000);
		optionsExitAnimation.setFillAfter(true);
		optionsExitAnimation.setFillEnabled(true);
		
		countdownClockAnimation = new AlphaAnimation(0, 1);
		countdownClockAnimation.setDuration(2000);
	}
	
	private void buildLayout()
	{
		// Start Animation
		questionLayout = (LinearLayout) findViewById(R.id.layout_question);
		questionLayout.startAnimation(questionEnterAnimation);
		optionsLayout = (RelativeLayout) findViewById(R.id.layout_options);
		optionsLayout.startAnimation(optionsEnterAnimation);
		countdownClock = (CountdownClock) findViewById(R.id.countdownClock);
		//countdownClock.startAnimation(countdownClockAnimation);
		
		// Set The Question
		questionView = (TextView) findViewById(R.id.questionView);
		questionView.setText(question);
		
		optionButtons = new OptionButton[4];
		LayoutParams[] optionButtonParams = new LayoutParams[4];
		
		optionButtons[0] = (OptionButton) findViewById(R.id.button_optionA);
		optionButtons[1] = (OptionButton) findViewById(R.id.button_optionB);
		optionButtons[2] = (OptionButton) findViewById(R.id.button_optionC);
		optionButtons[3] = (OptionButton) findViewById(R.id.button_optionD);
		for(int i=0; i<4; i++)
		{
			optionButtonParams[i] = (LayoutParams) optionButtons[i].getLayoutParams();
			//optionButtonParams[i].width=WIDTH_OPTION_BUTTONS;
			//optionButtonParams[i].height=HEIGHT_OPTION_BUTTONS;
			optionButtons[i].setLayoutParams(optionButtonParams[i]);
			optionButtons[i].setText(options[i]);
			optionButtons[i].setOnClickListener(new AnswerListener(i+1));
		}
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
			// Play The Button-Click Sound
			MediaPlayerService.playSound(getApplicationContext(), "button_click");
			
			final OptionButton button = (OptionButton) v;
			button.lockOption();
			
			// Stop The Timeout Handler
			timeoutHandler.removeCallbacks(timeoutRunnable);
			// Stop The Tick-Tick Sound
			tickPlayer.stopMusic();
			// Stop The Timer Showing The Number Of Seconds Left
			timer.stopTimer();
			
			// Disable all the buttons and stop the animation
			for(int i=0; i<4; i++)
			{
				optionButtons[i].setClickable(false);
				optionButtons[i].clearAnimation();
			}
			
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
						//Toast.makeText(getApplicationContext(), "Correct Answer!", Toast.LENGTH_SHORT).show();
						button.displayCorrect();
						//cheerPlayer.playMusic();
						MediaPlayerService.playSound(getApplicationContext(), "correct_answer");
						nextActivityIntent = new Intent(getApplicationContext(), LevelClearedActivity.class);
						nextActivityIntent.putExtra("NUM LEVEL CLEARED", levelNo);
					}
					else
					{
						//Toast.makeText(getApplicationContext(), "Incorrect Answer!", Toast.LENGTH_SHORT).show();
						button.displayIncorrect();
						MediaPlayerService.playSound(getApplicationContext(), "wrong_answer");
						nextActivityIntent = new Intent(getApplicationContext(), LevelFailedActivity.class);
						nextActivityIntent.putExtra("NUM LEVEL FAILED", levelNo);
						
						// Display The Correct Answer
						optionButtons[answer-1].displayCorrect();
					}
				}
			} ,2000);
			
			// Send Out The Views By Animation For 1second
			new Handler().postDelayed(new Runnable() 
			{
				@Override
				public void run()
				{
					questionLayout.startAnimation(questionExitAnimation);
					optionsLayout.startAnimation(optionsExitAnimation);
				}
			} ,3000);
			
			// Start The Next Activity After 4seconds i.e. 2seconds after the result is displayed
			new Handler().postDelayed(new Runnable() 
			{
				@Override
				public void run()
				{
					startActivity(nextActivityIntent);
					Level01Activity.this.finish();
				}
			} ,4000);
			
		}
	}
	
	private void setTimeout()
	{
		timer = (CountdownClock) findViewById(R.id.countdownClock);
		timer.startTimer(TIME_ALLOTED);
		timer.bringToFront();
		
		tickPlayer.playMusic();
		nextActivityIntent = new Intent(this, TimeoutActivity.class);
		nextActivityIntent.putExtra("NUM LEVEL TIMEOUT", levelNo);
		timeoutHandler = new Handler();
		timeoutRunnable = new Runnable() 
		{
			@Override
			public void run()
			{
				timer.stopTimer();
				tickPlayer.stopMusic();
				startActivity(nextActivityIntent);
				Level01Activity.this.finish();
			}
		};
		timeoutHandler.postDelayed(timeoutRunnable ,TIME_ALLOTED);
	}
	
	// Disable Back Button
	public void onBackPressed()
	{
		
	}
}
