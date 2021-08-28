// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package com.chaturvedi.kbc_prakalpa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import custom_views.SelectButton;

public class MainActivity extends Activity
{
	private DisplayMetrics displayMetrics;
	private int screenWidth;
	private int screenHeight;
	private int WIDTH_BUTTONS;
	private int HEIGHT_BUTTONS;

	private SelectButton playButton;
	
	Intent level01Intent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		displayMetrics=new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		screenWidth=displayMetrics.widthPixels;
		screenHeight=displayMetrics.heightPixels;
		WIDTH_BUTTONS = 100;
		HEIGHT_BUTTONS = 50;
		
		playButton = (SelectButton)findViewById(R.id.button_play);
		playButton.setOnClickListener(new View.OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				playButton.clickButton();
				AlertDialog.Builder playDialog = new AlertDialog.Builder(MainActivity.this);
				playDialog.setTitle("Play");
				playDialog.setMessage("Are You Sure To Take Up The Challenge");
				playDialog.setPositiveButton("Proceed", new DialogInterface.OnClickListener()
				{
					@Override
					public void onClick(DialogInterface dialog, int which)
					{
						level01Intent = new Intent(MainActivity.this, Level01Activity.class);
						level01Intent.putExtra("LEVEL NO", 1);
						startActivity(level01Intent);
					}
				});
				playDialog.setNegativeButton("Nope", null);
				playDialog.setCancelable(false);
				playDialog.create();
				playDialog.show();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
