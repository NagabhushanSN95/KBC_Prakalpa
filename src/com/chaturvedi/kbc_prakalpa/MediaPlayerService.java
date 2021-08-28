package com.chaturvedi.kbc_prakalpa;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class MediaPlayerService
{
	private static MediaPlayer mediaPlayer;
	
	public static void playMusic(Context context, String fileName)
	{
		try
		{
			mediaPlayer = new MediaPlayer();
			AssetFileDescriptor afd = context.getAssets().openFd("sound/"+fileName+".mp3");
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
	
	public static void stopMusic()
	{
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
	}
}
