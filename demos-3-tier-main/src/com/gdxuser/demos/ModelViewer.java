/*
 * simple viewer for 3D objects
 */

package com.gdxuser.demos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Plane;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.Ray;
import com.gdxuser.util.Cube;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.FloorGrid;
import com.gdxuser.util.GuOrthoCam;
import com.gdxuser.util.Log;
import com.gdxuser.util.MeshHelper;

public class ModelViewer extends DemoWrapper implements InputProcessor {
	private static final Vector2 FIELD_SIZE = new Vector2(10, 10);
	float w;
	float h;
	private GuOrthoCam cam;
	private Cube cube;
	FloorGrid floor;
	private MeshHelper mesh;

	// for touchDragged input
	final Plane xzPlane = new Plane(new Vector3(0, 1, 0), 0);
	final Vector3 intersection = new Vector3();
	final Vector3 curr = new Vector3();
	final Vector3 last = new Vector3(-1, -1, -1);
	final Vector3 delta = new Vector3();
	
	// for model movement
	Vector3 currentModelPosition;
	Vector3 newModelPosition;
	
	@Override
	public void create() {
		GL10 gl = Gdx.gl10;
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LESS);

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();

		cam = new GuOrthoCam(w, h, FIELD_SIZE);
		cam.setTargetVec(FIELD_SIZE.x/2, 0, FIELD_SIZE.y/2);

		// put some basic furniture in
		floor = new FloorGrid(FIELD_SIZE);
		floor.setColor(0, 1, 0);

		mesh = new MeshHelper("data/3d/plane_tris.obj");
		//mesh.setPos(FIELD_SIZE.x/2, 0, FIELD_SIZE.y/2);
		mesh.setPos(1,1,1);
		currentModelPosition = mesh.getPos();

		// load a 1x1x1 cube for reference
		cube = new Cube();
		cube.scale(0.5f).setPos(0f, 0.5f, 0f).setColor(1,0,0);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render() {
		GL10 gl = Gdx.app.getGraphics().getGL10();
		gl.glClearColor(0.5f, 0.5f, 0.5f, 1);		
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		cam.update();
		cam.apply(gl);
		floor.renderWireframe(gl);
		cube.renderWireframe(gl);

		gl.glColor4f(0, 0, 0.5f, 0);
//		mesh.render(gl, GL10.GL_AMBIENT_AND_DIFFUSE);
		mesh.render(gl, GL10.GL_TRIANGLES);
		
		currentModelPosition = mesh.getPos();
		cam.handleKeys();
		cam.update();
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		Log.out("touched:" + x + ", " + y);
		return false;
	}
	
	public void moveUp(){}
	public void moveDown(){}

	public void moveLeft(){}
	public void moveRight(){}

	public void turnLeft(){}
	public void turnRight(){}

	@Override
	public boolean keyDown(int keyCode) {
		switch (keyCode) {

		// keys for moving the model up and down
		case Keys.U:
			Log.out("touched:" + "U");
//			newModelPosition.set(currentModelPosition.x, currentModelPosition.y+0.01f, currentModelPosition.z);
//			mesh.setPos(newModelPosition);
			break;
		case Keys.J:
			newModelPosition.set(currentModelPosition.x, currentModelPosition.y-0.01f, currentModelPosition.z);
			mesh.setPos(newModelPosition);
			break;

		// keys for moving the model left and right
		case Keys.NUM_4:
			break;
		case Keys.NUM_6:
			break;

		// keys for turning the model left and right
		case Keys.NUM_7:
			break;
		case Keys.NUM_9:
			break;
			
		case Keys.SPACE:
			return false;
		}
		return (super.keyDown(keyCode));
//		return true;
	}

	@Override
	public boolean touchDragged (int x, int y, int pointer) {
		Ray pickRay = cam.getPickRay(x, y);
		Intersector.intersectRayPlane(pickRay, xzPlane, curr);
		
		if (!(last.x == -1 && last.y == -1 && last.z == -1)) {
			pickRay = cam.getPickRay(last.x, last.y);
			Intersector.intersectRayPlane(pickRay, xzPlane, delta);
			delta.sub(curr);
			cam.position.add(delta.x, 0, delta.z);
		}
		last.set(x, y, 0);
		return true;
	}

	@Override
	public boolean touchUp (int x, int y, int pointer, int button) {
		last.set(-1, -1, -1);
		return true;
	}

}