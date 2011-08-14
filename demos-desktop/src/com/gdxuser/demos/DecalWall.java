package com.gdxuser.demos;

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

public class DecalWall extends DemoWrapper implements InputProcessor {
	private static final int GRID_SIZE = 10;
	float w;
	float h;
	private GuOrthoCam oCam;
	private Cube cube;
	FloorGrid floor;
	private DecalSprite wall;
	private DecalBatch decalBatch3d;
	private SpriteBatch spriteBatch2d;
	private DecalSprite player;
	private MeshHelper floorMesh;
	private Billboard cloud;
	private Texture cloudTex;
	private Vector3 ppos;
	private int ctr = 0;

	@Override
	public void create() {
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		oCam = new GuOrthoCam(w, h, GRID_SIZE * 2);
		oCam.position.set(-GRID_SIZE, GRID_SIZE, GRID_SIZE * 2);
		oCam.lookAt(GRID_SIZE / 2, 0, GRID_SIZE / 2);
		// oCam.lookAt(0, 0, 0);

		// put some basic furniture in
		floor = new FloorGrid(GRID_SIZE, GRID_SIZE);

		// floorMesh = new MeshHelper("data/3d/floorplan.obj");

		cube = new Cube();
		cube.scale(0.5f).pos(0f, 0.5f, 0f);
		wall = new DecalSprite("data/decals/256/3d_side.png");
		wall.sprite.setDimensions(6, 6);
		wall.sprite.setPosition(5, 3, 0);
		float dh = wall.sprite.getHeight();
		float dw = wall.sprite.getWidth();
		Log.out("decal size:" + dh + "," + dw);

		// 3d sprite batch
		player = new DecalSprite("data/players/full/128/avatar1.png");
		player.sprite.setDimensions(4, 4);
		player.sprite.setPosition(4, 2, 2);

		// 2d cloud sprite
		// cloud = new ImageSprite("cloud", "data/icons/128/thunder.png");
		// cloud.x = 10;
		// cloud.y = 10;

		// 2d sprites
		spriteBatch2d = new SpriteBatch();
		spriteBatch2d.enableBlending();

		// 2d cloud sprite
		String imgPath = "data/icons/128/thunder.png";
		cloud = Billboard.make(imgPath);
		cloud.wpos(2f, 3f, 2f);

		decalBatch3d = new DecalBatch();

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		// gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		oCam.update();
		oCam.apply(gl);

		decalBatch3d.add(wall.sprite);
		decalBatch3d.flush();

		oCam.push();

		ppos = player.sprite.getPosition();
		// Log.out("x=" + ppos.x + "y=" + ppos.y + "z=" + ppos.z);
		// oCam.position.set(5f, 2f, 4f);

		// oCam.position.set(ppos.x, ppos.y, ppos.z+10);
		// oCam.lookAt(ppos.x, ppos.y, ppos.z);
		// player.sprite.rotateY(-45f);

		oCam.update();
		oCam.apply(gl);
		decalBatch3d.add(player.sprite);
		decalBatch3d.flush();

		oCam.pop();
		oCam.update();
		oCam.apply(gl);

		gl.glPushMatrix();
		gl.glColor4f(0, 1, 0, 1f);
		floor.render(gl, GL10.GL_LINE_STRIP);
		gl.glColor4f(1, 0, 0, 1f);
		cube.render(gl, GL10.GL_LINE_STRIP);
		gl.glPopMatrix();

		drawClouds(gl);

		gl.glPopMatrix();

		// Log.out("ppos = " + ppos);
		oCam.handleKeys();

	}

	private void drawClouds(GL10 gl) {
		gl.glPushMatrix();
		cloud.project(oCam);
		cloud.update();

		if (ctr++ % 100 == 0) {
			// Log.out("ppos: " + ppos + "  player:");
			// Log.out("cld: " + cld);
			// Log.out("player: " + player.sprite.getPosition() );
		}
		spriteBatch2d.begin();
		cloud.setPosition(cloud.spos.x, cloud.spos.y);
		cloud.draw(spriteBatch2d, 0.5f);
		spriteBatch2d.end();
		gl.glPopMatrix();
	}

	private void overlay(GL10 gl) {

	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		Log.out("touched:" + x + y);
		return false;
	}

	@Override
	public boolean keyDown(int keyCode) {
		switch (keyCode) {

		case Keys.I:
			Log.out("Key Info (I):");
			Log.out("ESC = quit demo");
			Log.out("----------------------");
			Log.out("A, D = move cam left / right");
			Log.out("W, S = move cam forward / backward");
			Log.out("Q, E = yaw (turn) cam left / right");
			Log.out("U, J = pitch cam up / down");
			Log.out("H, K = roll cam counter-clockwise / clockwise");
			Log.out("N, M = orbit cam around origin (TODO: player) counter-clockwise / clockwise");
			Log.out("C, SPACE = print cam / player position");
			Log.out("----------------------");
			break;
		
		case Keys.C:
			Log.out("cam_pos:  " + oCam.position);
			Log.out("cam_up:   " + oCam.up);
			Log.out("cam_dir:  " + oCam.direction);
			break;

		case Keys.SPACE:
			Log.out("ppos:" + ppos);
			break;
		}
		return (super.keyDown(keyCode));
	}

}