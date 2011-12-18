package com.slimejumper.world.attacks;

import com.slimejumper.gameframework.math.UnitCircle;
import com.slimejumper.world.Hero;

public class MusicNote extends Attack{
	
	public static final float MUSIC_NOTE_WIDTH = 0.1625f;
	public static final float MUSIC_NOTE_HEIGHT = 0.425f;

	public static final float MUSIC_NOTE_LIFESPAN = 4.8f;
	
	public static final float MUISC_NOTE_FRAME_TIMER_LIMIT = 0.14f;
	
	public static final int MUSIC_NOTE_STATE_CIRCULAR = 0;
	public static final int MUSIC_NOTE_STATE_BLAST = 1;
	
	public static final float MUSIC_NOTE_CIRCULAR_STATE_TIMER_BOUND = 1.8f;
	
	public float frame_timer;
	public int frame_counter;
	
	public float state_timer;
	public int state;
	
	public MusicNote() {
		this(3,3);
		
		frame_timer = 0;
		frame_counter = 0;
		
		state_timer = 0;
		state = MUSIC_NOTE_STATE_CIRCULAR;
	}
	
	public MusicNote(float x, float y) {
		super(x, y, MUSIC_NOTE_WIDTH, MUSIC_NOTE_HEIGHT);
	}

	public void update(Hero hero, float deltaTime){
		super.update(deltaTime);
		
		switch(state){
		case MUSIC_NOTE_STATE_CIRCULAR:
			updateCircularState(hero, deltaTime);
			break;
		case MUSIC_NOTE_STATE_BLAST:
			break;
		}
	}
	
	public void updateCircularState(Hero hero, float deltaTime){
		state_timer += deltaTime;
		frame_timer += deltaTime;
		
		if(frame_timer >= MUISC_NOTE_FRAME_TIMER_LIMIT){
			frame_counter++;
			frame_timer = 0;
			if(frame_counter == UnitCircle.UNIT_CIRCLE_SIZE)
				frame_counter = 0;
		}
		
		resetPositionCenter(hero.center.x + UnitCircle.unit_circle_x[frame_counter], 
				hero.center.y + UnitCircle.unit_circle_y[frame_counter]);
		
		
		if(state_timer > MUSIC_NOTE_CIRCULAR_STATE_TIMER_BOUND)
			changeToBlastState(hero);
	}

	public void changeToBlastState(Hero hero) {
		state = MUSIC_NOTE_STATE_BLAST;
		velocity.set(6 * (position.x - hero.center.x), 6 * (position.y - hero.center.y));
	}

	public void reset() {
		super.reset();
		
		frame_timer = 0;
		frame_counter = 0;
		
		state_timer = 0;
		state = MUSIC_NOTE_STATE_CIRCULAR;
	}

	public void reset(int frame_counter) {
		this.reset();
		this.frame_counter = frame_counter;
	}
/*	
	public static void activateMusicalCircularBurst(Hero hero) {
		for(int frame_counter_starter=0; frame_counter_starter<UnitCircle.UNIT_CIRCLE_SIZE; frame_counter_starter+=3){
			MusicNote music_note = GameScreen.pool_manager.music_note_pool.newObject();
			music_note.reset(frame_counter_starter);
			
			SpriteContainer.music_notes.add(music_note);
		}
	}
*/
}
