// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import custom_views.PlayConfirmationDialog;
import custom_views.QuitDialog;
import custom_views.RulesDialog;
import custom_views.SelectButton;
import custom_views.SelectButtonMini;

public class MainActivity extends Activity
{
	/*private DisplayMetrics displayMetrics;
	private int screenWidth;
	private int screenHeight;
	private int WIDTH_BUTTONS;
	private int HEIGHT_BUTTONS;*/

	private SelectButton playButton;
	private SelectButtonMini rulesButton;
	
	Intent level01Intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		/*displayMetrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenWidth=displayMetrics.widthPixels;
		screenHeight=displayMetrics.heightPixels;
		WIDTH_BUTTONS = 100;
		HEIGHT_BUTTONS = 50;*/
		
		final PlayConfirmationDialog playDialog = new PlayConfirmationDialog(this);
		playButton = (SelectButton)findViewById(R.id.button_play);
		playButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				playDialog.setOnPlayListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						// Play The Button-Click Sound
						MediaPlayerService.playSound(getApplicationContext(), "button_click");
						
						playDialog.dismiss();
						level01Intent = new Intent(MainActivity.this, Level01Activity.class);
						level01Intent.putExtra("LEVEL NO", 1);
						startActivity(level01Intent);
						finish();
					}
				});
				playDialog.setOnQuitListener(new View.OnClickListener()
				{
					@Override
					public void onClick(View v)
					{
						// Play The Button-Click Sound
						MediaPlayerService.playSound(getApplicationContext(), "button_click");
						
						playDialog.dismiss();
					}
				});
				playDialog.show();
			}
		});
		
		// Set The Rules Dialog
		final RulesDialog rulesDialog = new RulesDialog(this);
		rulesDialog.setOnPlayListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				rulesDialog.dismiss();
				level01Intent = new Intent(MainActivity.this, Level01Activity.class);
				level01Intent.putExtra("LEVEL NO", 1);
				startActivity(level01Intent);
				finish();
			}
		});
		rulesButton = (SelectButtonMini)findViewById(R.id.button_rules);
		rulesButton.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				// Play The Button-Click Sound
				MediaPlayerService.playSound(getApplicationContext(), "button_click");
				
				rulesDialog.show();
			}
		});
	}
	
	@Override
	public void onBackPressed()
	{
		final QuitDialog quitDialog = new QuitDialog(this);
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
