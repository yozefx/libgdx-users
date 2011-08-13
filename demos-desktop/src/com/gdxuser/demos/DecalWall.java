package com.gdxuser.demos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.gdxuser.util.CamUtil;
import com.gdxuser.util.Cube;
import com.gdxuser.util.DecalSprite;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.FloorGrid;

public class DecalWall extends DemoWrapper implements InputProcessor {
	float w;
	float h;
	private Camera oCam;
	private Cube cube;
	FloorGrid floor;
	private DecalSprite decal;

	@Override
	public void create() {
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);
		
		w = Gdx.graphics.getWidth() / 0.8f;
		h = Gdx.graphics.getHeight() / 0.8f;

		oCam = CamUtil.orthoCam(w, h);
		
		floor = new FloorGrid(10,10);
		cube = new Cube().scale(0.5f).pos(0f, 0.5f, 0f);
		decal = new DecalSprite("data/decals/256/3d_side.png");
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		// gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();

		gl.glPushMatrix();
		gl.glTranslatef(0f, 0.5f, 0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);
		gl.glPopMatrix();

		oCam.update();
		oCam.apply(gl);
		
		gl.glPushMatrix();
		gl.glColor4f(1, 0, 0, 1f);
		cube.render(gl, GL10.GL_LINE_STRIP);
		gl.glPopMatrix();
		
		gl.glColor4f(0, 1, 0, 1f);
		floor.render(gl, GL10.GL_LINE_STRIP);
		
		
	}

}