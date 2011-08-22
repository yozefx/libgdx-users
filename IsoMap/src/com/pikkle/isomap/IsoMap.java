package com.pikkle.isomap;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.decals.CameraGroupStrategy;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;
import com.badlogic.gdx.graphics.g3d.decals.GroupStrategy;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.gdxuser.util.Billboard;
import com.gdxuser.util.Cube;
import com.gdxuser.util.DecalSprite;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.FloorGrid;
import com.gdxuser.util.GuOrthoCam;
import com.gdxuser.util.GuPerspCam;
import com.gdxuser.util.Log;
import com.gdxuser.util.MathUtil;
import com.gdxuser.util.MeshHelper;

public class IsoMap extends DemoWrapper implements InputProcessor {
	private static final Vector2 fieldSize = new Vector2(10, 10);
	float w;
	float h;
	private Cube cube;
	FloorGrid floor;
	private DecalBatch decalBatch;
	private SpriteBatch spriteBatch2d;
	private Billboard player;
	private Billboard cloud;
	private Vector3 ppos;
	private int ctr = 0;
	private GroupStrategy strategy;
	// private DecalSprite[] walls = new DecalSprite[5];
	private DecalSprite wall;
	private ArrayList<DecalSprite> walls = new ArrayList<DecalSprite>();
	private ArrayList<DecalSprite> badges = new ArrayList<DecalSprite>();
	private ArrayList<DecalSprite> tiles = new ArrayList<DecalSprite>();
	private MeshHelper plane;
	// camera
	private Camera cam;
	private GuOrthoCam oCam;
	private GuPerspCam pCam;
	private String camType = "ortho";

	@Override
	public void create() {
		Gdx.gl.glEnable(GL10.GL_DEPTH_TEST);
		Gdx.gl10.glDepthFunc(GL10.GL_LESS);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		oCam = new GuOrthoCam(w, h, fieldSize);
		pCam = new GuPerspCam(50, 50, 50);

		cam = oCam;	// so we can switch

		// put some basic furniture in
		floor = new FloorGrid(fieldSize);

		plane = new MeshHelper("data/3d/floorplan.obj");
		plane.setPos(3,2,3);
		plane.setColor(1, 0, 0);

		cube = new Cube();
		cube.scale(0.5f).setPos(0f, 0.5f, 0f).setColor(0, 1, 0);

		addWalls();
		
		// some stuff that should face the camera
		badges = getBadges();
		
		player = Billboard.make("data/players/full/128/avatar1.png");
		player.wpos(2, 0, 2);

		// 2d cloud sprite
		String imgPath = "data/icons/128/thunder.png";
		cloud = Billboard.make(imgPath);
		cloud.setMove(1f,0f,0f);
		cloud.wpos(2f, 1f, 2f);

		// batches
		strategy = new CameraGroupStrategy(cam);		
		decalBatch = new DecalBatch(strategy);
		spriteBatch2d = new SpriteBatch();
		spriteBatch2d.enableBlending();
		
		addTiles();
		
		Gdx.input.setInputProcessor(this);
	}

	private ArrayList<DecalSprite> addWalls() {
		// decals for walls
		wall = new DecalSprite().build("data/decals/256/blueflower.png");
		wall.sprite.setDimensions(6, 6);
		wall.sprite.setPosition(5, 3, 0);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/sakura.png");
		wall.sprite.setDimensions(2, 2);
		wall.sprite.setPosition(0, 1, fieldSize.y);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/sakura.png");
		wall.sprite.setDimensions(2, 2);
		wall.sprite.setPosition(2, 1, fieldSize.y);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/sakura.png");
		wall.sprite.setDimensions(2, 2);
		wall.sprite.setPosition(4, 1, fieldSize.y);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/sakura.png");
		wall.sprite.rotateY(90);
		wall.sprite.setDimensions(2, 2);
		wall.sprite.setPosition(fieldSize.x, 1, 1);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/sakura.png");
		wall.sprite.rotateY(90);
		wall.sprite.setDimensions(2, 2);
		wall.sprite.setPosition(fieldSize.x, 1, 3);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/colormosaic.png");
		wall.sprite.rotateY(90);
		wall.sprite.setDimensions(2, 2);
		wall.sprite.setPosition(fieldSize.x, 3, 3);
		walls.add(wall);

		wall = new DecalSprite().build("data/decals/64/colormosaic.png");
		wall.sprite.rotateY(90);
		wall.sprite.setPosition(fieldSize.x, 2, 6);
		wall.sprite.setDimensions(4, 4);
		walls.add(wall);
		
		return walls;
	}

	private ArrayList<DecalSprite> getBadges() {
		
		for(int n=0; n<6; n++) {
			DecalSprite badge = new DecalSprite().build("data/badges/128/badge" + n + ".png");
			badge.sprite.setDimensions(1, 1);
			float x = MathUtil.random(8) + 1;
			float y = MathUtil.random(8) + 1;
			badge.sprite.setPosition(x, 0.4f, y);

			// make the Badges always facing the camera
			badge.faceCamera(cam);

			badges.add(badge);
		}

		return badges;
	}

	// builds floor into tiles array
	private ArrayList<DecalSprite> addTiles() {
		float offset = 0.5f;
		String[] tilenames = {"blue-mosaic", "grey-mosaic", "rusty-mosaic", "rusty", "hex", "hex"};
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				String tilename = MathUtil.randomElem(tilenames);

				DecalSprite tile = new DecalSprite().build("data/tiles/128/" + tilename + ".jpeg");
				tile.sprite.setDimensions(1, 1);
				tile.sprite.rotateX(90);
				tile.sprite.setPosition(x + offset, -0.01f, y + offset);
				tiles.add(tile);				
			}
		}
		return tiles;
	}
	
	
	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		// gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		float delta = Gdx.graphics.getDeltaTime();

		cam.update();
		cam.apply(gl);

		// the floor grid...
		gl.glPushMatrix();
		gl.glColor4f(0, 1, 0, 1f);
		floor.render(gl, GL10.GL_LINE_STRIP);
		gl.glColor4f(1, 0, 0, 1f);
		cube.render(gl, GL10.GL_LINE_STRIP);
		gl.glPopMatrix();
		
		
		for (DecalSprite oneWall : walls) {
			decalBatch.add(oneWall.sprite);
		}

		for (DecalSprite oneBadge : badges) {
			oneBadge.faceCamera(cam);
			decalBatch.add(oneBadge.sprite);
		}

		for (DecalSprite tile : tiles) {
			decalBatch.add(tile.sprite);
		}

		decalBatch.flush();

//		cam.push();

		cam.update();
		cam.apply(gl);
		// decalBatch.add(player.sprite);
		decalBatch.flush();

//		cam.pop();
		cam.update();
		cam.apply(gl);

		drawClouds(gl, delta);
		
		plane.render(gl, GL10.GL_LINE_STRIP);

		gl.glPopMatrix();
		
		oCam.handleKeys();

	}

	private void drawClouds(GL10 gl, float delta) {
		gl.glPushMatrix();
		cloud.project(cam);
		cloud.update(delta);

		player.project(cam);
		player.update(delta);

		if (ctr++ % 100 == 0) {
			// Log.out("ppos: " + ppos + "  player:");
			// Log.out("cld: " + cld);
			// Log.out("player: " + player.sprite.getPosition() );
		}
		spriteBatch2d.begin();
		cloud.setPosition(cloud.spos.x, cloud.spos.y);
		cloud.draw(spriteBatch2d, 0.8f);

		player.setPosition(player.spos.x, player.spos.y);
		player.draw(spriteBatch2d, 1);

		spriteBatch2d.end();
		gl.glPopMatrix();
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		Log.out("touched:" + x + y);
		return false;
	}

	//TODO - implement proper drag rotation
	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		Log.out("dragged");
		float delta = Gdx.graphics.getDeltaTime();
		spinCam(delta);
		return false;
	}
	
	// FIXME - fix java too. ocam and pcam are subclasses of different types
	// so cannot share the same behavior unless we could monkeypatch 
	// the parent class of both camera is abstrac tho. >,<
	// hence the ugly code below since polymorphism isnt real
	// interfaces dont help either so need some much more complex pattern
	// ruby modules + monkeypatch would fix this
	private void spinCam(float delta) {
		if (camType == "ortho") {
			oCam.spin(delta);
		} else {
			pCam.spin(delta);
		}
	}

	@Override
	public boolean keyDown(int keyCode) {
		switch (keyCode) {

		case Keys.C:
			if(camType  == "ortho") {
				camType = "persp";
				cam = pCam;
			} else {
				camType = "ortho";
				cam = oCam;
			}
			Log.out("set cam to:" + camType);
			break;

		case Keys.SPACE:
			break;
		}
		return (super.keyDown(keyCode));
	}

}