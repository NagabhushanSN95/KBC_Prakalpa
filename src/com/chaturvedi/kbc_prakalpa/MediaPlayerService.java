package com.chaturvedi.kbc_prakalpa;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class MediaPlayerService
{
	private Context context;
	private String fileName;
	private MediaPlayer mediaPlayer;
	
	public MediaPlayerService(Context cxt, String fName)
	{
		context = cxt;
		fileName = "sound/"+fName+".mp3";
	}
	
	public void playMusic()
	{
		try
		{
			mediaPlayer = new MediaPlayer();
			AssetFileDescriptor afd = context.getAssets().openFd(fileName);
			mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
			mediaPlayer.prepare();
			mediaPlayer.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void stopMusic()
	{
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
	}
}
