package com.chaturvedi.kbc_prakalpa;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class MediaPlayerService
{
	private Context context;
	private String fileName;
	private MediaPlayer musicPlayer;
	
	public MediaPlayerService(Context cxt, String fName)
	{
		context = cxt;
		fileName = "sound/"+fName+".mp3";
	}
	
	public void playMusic()
	{
		try
		{
			musicPlayer = new MediaPlayer();
			AssetFileDescriptor afd = context.getAssets().openFd(fileName);
			musicPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
			musicPlayer.prepare();
			musicPlayer.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void playSound(Context context, String fileName)
	{
		try
		{
			fileName = "sound/"+fileName+".mp3";
			MediaPlayer soundPlayer = new MediaPlayer();
			AssetFileDescriptor afd = context.getAssets().openFd(fileName);
			soundPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
			afd.close();
			soundPlayer.prepare();
			soundPlayer.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void stopMusic()
	{
		if(musicPlayer.isPlaying())
			musicPlayer.stop();
	}
}
