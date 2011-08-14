/*
 * Billboard is a 2d sprite that renders its location on screen
 * based on 3D world position. think of it like a 3D sprite that is always
 * facing the user.
 * 
 * author: yatayata
 */

package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actors.Image;

public class Billboard extends Sprite {
	private static final float ANIM_SPEED = 0.1f;
	public Vector3 wpos;	// WORLD position
	public Vector3 spos;	// SCREEN pos
	public Vector3 wmove;

	public static Billboard make(String imgPath) {
		Texture tex = new Texture(Gdx.files.internal(imgPath));
		Billboard sp = new Billboard(tex);
		return sp;
	}
	
	public Billboard(Texture tex) {
		super(tex);
		wmove = new Vector3(ANIM_SPEED, 0, 0);
	}

	public void render() {

	}

	public void wpos(float x, float y, float z) {
		wpos = new Vector3(x, y, z);
	}
	
	public void spos(Camera cam) {
		
	}

	public Vector3 project(Camera cam) {
		spos = new Vector3(wpos);	// dont overwrite worldpos!
		cam.project(spos);
		return spos;
	}
	
	public void update() {
		wpos.add(wmove);
		if (wpos.x > 10 || wpos.x<0 ) {
			wmove.x = -wmove.x;
		}
	}


}
