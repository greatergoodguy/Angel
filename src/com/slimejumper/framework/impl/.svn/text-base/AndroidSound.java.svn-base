package com.slimejumper.framework.impl;

import android.media.SoundPool;

import com.slimejumper.gameframework.Sound;

public class AndroidSound implements Sound {

	int soundId;
	SoundPool soundPool;
	
	public AndroidSound(SoundPool soundPool, int soundId) {
		// TODO Auto-generated constructor stub
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	public void play(float volume) {
		// TODO Auto-generated method stub
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}

	public void dispose() {
		// TODO Auto-generated method stub
		soundPool.unload(soundId);
	}

}
