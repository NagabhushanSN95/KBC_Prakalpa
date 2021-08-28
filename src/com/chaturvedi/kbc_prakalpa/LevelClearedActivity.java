package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import custom_views.SelectButton;

public class LevelClearedActivity extends Activity
{
	private int numLevelCleared;
	
	private TextView[] levelViews;
	private SelectButton nextButton;
	
	private Intent previousActivityIntent;
	private Intent nextActivityIntent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_level_cleared);
		
		previousActivityIntent = getIntent();
		numLevelCleared = previousActivityIntent.getIntExtra("NUM LEVEL CLEARED", 1);
		
		buildLayout();
	}
	
	private void buildLayout()
	{
		levelViews = new TextView[6];
		levelViews[0] = (TextView) findViewById(R.id.question01);
		levelViews[1] = (TextView) findViewById(R.id.question02);
		levelViews[2] = (TextView) findViewById(R.id.question03);
		levelViews[3] = (TextView) findViewById(R.id.question04);
		levelViews[4] = (TextView) findViewById(R.id.question05);
		levelViews[5] = (TextView) findViewById(R.id.question06);
		
		nextButton = (SelectButton)findViewById(R.id.button_next);
		nextButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				startActivity(nextActivityIntent);
				finish();
			}
		});
		
		for(int i=0; i<numLevelCleared; i++)
		{
			levelViews[i].setTextColor(Color.YELLOW);
		}
		
		switch(numLevelCleared)
		{
			case 1:
				nextActivityIntent = new Intent(this, Level01Activity.class);
				break;
				
			case 2:
				nextActivityIntent = new Intent(this, Level02Activity.class);
				break;
				
			case 3:
				nextActivityIntent = new Intent(this, Level02Activity.class);
				break;
				
			case 4:
				nextActivityIntent = new Intent(this, Level03Activity.class);
				break;
				
			case 5:
				nextActivityIntent = new Intent(this, Level03Activity.class);
				break;
				
			case 6:
				nextActivityIntent = new Intent(this, PrizeActivity.class);
				nextButton.setText("View Prize");
				break;
		}
		nextActivityIntent.putExtra("LEVEL NO", numLevelCleared+1);
	}
	
	// Disable Back Button
	public void onBackPressed()
	{
		
	}
}
