package com.slimejumper.framework.impl;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.slimejumper.gameframework.Music;

public class AndroidMusic implements Music, OnCompletionListener {

	MediaPlayer mediaPlayer;
	boolean isPrepared = false;
	
	public AndroidMusic(AssetFileDescriptor assetDescriptor) {
		// TODO Auto-generated constructor stub
		mediaPlayer = new MediaPlayer();
		try{
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
					assetDescriptor.getStartOffset(),
					assetDescriptor.getLength());
			mediaPlayer.prepare();
			isPrepared = true;
			mediaPlayer.setOnCompletionListener(this);
		} catch (Exception e){
			throw new RuntimeException("Couldn't load music");
		}
	
	}

	public void play() {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying())
			return;
		
		try{
			synchronized(this){
				if(!isPrepared)
					mediaPlayer.prepare();
				mediaPlayer.start();
			}
		} catch(IllegalStateException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}

	}

	public void stop() {
		// TODO Auto-generated method stub
		mediaPlayer.stop();
		synchronized(this){
			isPrepared = false;
		}

	}

	public void pause() {
		// TODO Auto-generated method stub

	}

	public void setLooping(boolean looping) {
		// TODO Auto-generated method stub
		mediaPlayer.setLooping(looping);

	}

	public void setVolume(float volume) {
		// TODO Auto-generated method stub
		mediaPlayer.setVolume(volume, volume);

	}

	public boolean isPlaying() {
		// TODO Auto-generated method stub
		return mediaPlayer.isPlaying();
	}

	public boolean isStopped() {
		// TODO Auto-generated method stub
		return !isPrepared;
	}

	public boolean isLooping() {
		// TODO Auto-generated method stub
		return mediaPlayer.isLooping();
	}

	public void dispose() {
		// TODO Auto-generated method stub
		if(mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.release();

	}

	public void onCompletion(MediaPlayer mp) {
		// TODO Auto-generated method stub
		synchronized(this){
			isPrepared = false;
		}
		
	}

}
