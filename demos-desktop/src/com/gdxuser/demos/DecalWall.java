package com.gdxuser.demos;

import java.util.LinkedList;

import com.gdxuser.util.CamUtil;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.GroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.SimpleOrthoGroupStrategy;
import com.badlogic.gdx.math.WindowedMean;
import com.gdxuser.util.CamUtil;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.MeshUtil;

public class DecalWall extends DemoWrapper implements InputProcessor {
	float w;
	float h;
	private Camera oCam;
	private Mesh cube, floor;

	@Override
	public void create() {
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);
		
		w = Gdx.graphics.getWidth() / 0.8f;
		h = Gdx.graphics.getHeight() / 0.8f;

		oCam = CamUtil.orthoCam(w, h);
		
		floor = new MeshUtil().floorGrid(5,5);
		cube = new MeshUtil().cube();
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		// gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();

		gl.glTranslatef(0f, 0.5f, 0f);
		gl.glScalef(0.5f, 0.5f, 0.5f);

		oCam.update();
		oCam.apply(gl);
		
		gl.glColor4f(1, 0, 0, 1f);
		cube.render(GL10.GL_LINE_STRIP);
		floor.render(GL10.GL_LINE_STRIP);
		
	}

}