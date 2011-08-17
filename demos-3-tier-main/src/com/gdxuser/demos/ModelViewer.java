/*
 * simple viewer for 3D objects
 */

package com.gdxuser.demos;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.actors.Image;
import com.gdxuser.util.Billboard;
import com.gdxuser.util.Cube;
import com.gdxuser.util.DecalSprite;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.FloorGrid;
import com.gdxuser.util.GuOrthoCam;
//import com.gdxuser.util.ImageSprite;
import com.gdxuser.util.Log;
import com.gdxuser.util.MeshHelper;

public class ModelViewer extends DemoWrapper implements InputProcessor {
	private static final Vector2 FIELD_SIZE = new Vector2(10, 10);
	float w;
	float h;
	private GuOrthoCam cam;
	private Cube cube;
	FloorGrid floor;
	private int ctr = 0;
	private MeshHelper mesh;

	@Override
	public void create() {
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		cam = new GuOrthoCam(w, h, FIELD_SIZE);
		cam.setTargetVec(FIELD_SIZE.x/2, 0, FIELD_SIZE.y/2);

		// put some basic furniture in
		floor = new FloorGrid(FIELD_SIZE);
		floor.setColor(0, 1, 0);

		mesh = new MeshHelper("data/3d/plane.obj");
		mesh.setPos(FIELD_SIZE.x/2, 0, FIELD_SIZE.y/2);

		// load a 1x1x1 cube for reference
		cube = new Cube();
		cube.scale(0.5f).setPos(0f, 0.5f, 0f).setColor(1,0,0);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		// gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		cam.update();
		cam.apply(gl);
		floor.renderWireframe(gl);
		cube.renderWireframe(gl);
		mesh.render(gl, GL10.GL_AMBIENT_AND_DIFFUSE);
		cam.handleKeys();
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		Log.out("touched:" + x + y);
		return false;
	}

	@Override
	public boolean keyDown(int keyCode) {
		switch (keyCode) {

		case Keys.SPACE:
			break;
		}
		return (super.keyDown(keyCode));
	}

}