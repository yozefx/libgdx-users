package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class GuOrthoCam extends OrthographicCamera {

	private static final float DEG = (float)(Math.PI / 180f);
	private static final int CAMSPEED = 40;
	private static Vector3 campos;
	private static float aspect;
	private Matrix4 comb;
	private Vector3 dir;
	
	private static int VP_WIDTH;
	private static int VP_HEIGHT;
	private float VP_ASPECT_RATIO;
	private static final Vector3 CAM_POS_INITIAL = new Vector3(15f, 15f, 8f);
	private static final float CAM_NEAR_INITIAL = 0.1f;
	private static final float CAM_FAR_INITIAL = 200f;
	private static Vector3 CAM_LOOKAT_INITIAL;
	private static Vector3 CAM_UP_INITIAL = new Vector3(0.0f, 1.0f, 0.0f);
	
	// viewsize should be related to the size of your scene
	public GuOrthoCam(float VP_WIDTH, float VP_HEIGHT, float viewSize) {
		super(viewSize, viewSize * (VP_HEIGHT / VP_WIDTH));
		VP_ASPECT_RATIO = (float) VP_WIDTH / (float) VP_HEIGHT;
//		super(viewSize, viewSize * (h / w));

     	Log.out("Cam-Up:        " + up.x + ", " + up.y + ", " + up.z);
     	Log.out("Cam-Position:  " + position.x + ", " + position.y + ", " + position.z);
     	Log.out("Cam-Direction: " + direction.x + ", " + direction.y + ", " + direction.z);

		position.set(CAM_POS_INITIAL);
		up.set(CAM_UP_INITIAL);
		CAM_LOOKAT_INITIAL = new Vector3(VP_WIDTH / 2, 0, VP_HEIGHT / 2);

		
//		Log.out("Cam-Up: " + up.x + up.y + up.z);
		// direction.set(-1, -1, -1);
		lookAt(CAM_LOOKAT_INITIAL.x, CAM_LOOKAT_INITIAL.y, CAM_LOOKAT_INITIAL.z);
	}

	public void push() {
		campos = position;
		dir = this.direction;
	}

	public void pop() {
		position.set(campos);
		this.direction.set(dir);
	}

	@Override
	public void update() {
		super.update();
	}

	public void handleKeys() {
		float amt = CAMSPEED * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.A)) {
			translate(-amt, 0, 0);
			// rotate(40 * Gdx.graphics.getDeltaTime(), 0, 1, 0);
			// player.sprite.rotateY(40 * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Keys.D)) {
			translate(amt, 0, 0);
			// rotate(-40 * Gdx.graphics.getDeltaTime(), 0, 1, 0);
			// player.sprite.rotateY(-40 * Gdx.graphics.getDeltaTime());
		}
		if (Gdx.input.isKeyPressed(Keys.W)) {
			translate(0, 0, -amt);
			// rotate(amt, 1, 0, 0);
			// player.sprite.rotateX(-amt);
		}
		if (Gdx.input.isKeyPressed(Keys.S)) {
			translate(0, 0, amt);
			// rotate(-amt, 1, 0, 0);
			// player.sprite.rotateX(amt);
		}
		if (Gdx.input.isKeyPressed(Keys.E)) {
			rotate(-amt, 0, 1, 0);
			// player.sprite.rotateX(amt);
		}
		if (Gdx.input.isKeyPressed(Keys.Q)) {
			rotate(amt, 0, 1, 0);
			// player.sprite.rotateX(amt);
		}
		if (Gdx.input.isKeyPressed(Keys.U)) {
			rotate(-amt, 1, 0, 0);
			// player.sprite.rotateX(amt);
		}
		if (Gdx.input.isKeyPressed(Keys.J)) {
			rotate(amt, 1, 0, 0);
			// player.sprite.rotateX(amt);
		}

        // moves NEAR away from cam
		if (Gdx.input.isKeyPressed(Keys.PLUS)) {
			near += amt;
			if (near > CAM_FAR_INITIAL) {
				near = CAM_FAR_INITIAL;
			}
			Log.out("NEAR:" + near);
		}
        // moves NEAR closer to cam
		if (Gdx.input.isKeyPressed(Keys.MINUS)) {
			near -= amt;
			if (near < CAM_NEAR_INITIAL) {
				near = CAM_NEAR_INITIAL;
			}
			Log.out("NEAR:" + near);
		}

		// Zoom in
		if (Gdx.input.isKeyPressed(Keys.T)) {
			zoom(amt);
		}
		// Zoom out
		if (Gdx.input.isKeyPressed(Keys.G)) {
			zoom(-amt);
		}

		// Orbit around lookAt left
		if (Gdx.input.isKeyPressed(Keys.N)) {
			orbit(amt, new Vector3(0,0,0));
		}
		// Orbit around lookAt right
		if (Gdx.input.isKeyPressed(Keys.M)) {
			orbit(-amt, new Vector3(0,0,0));
		}
		
		// Reset scene / reload starting values
		if (Gdx.input.isKeyPressed(Keys.R)) {
			init();
		}
		update();
	}
	
	public void init()
	{
		//TODO NOT WORKING YET :-(
//		position.set(CAM_POS_INITIAL);
//		up.set(CAM_UP_INITIAL);
//		lookAt(CAM_LOOKAT_INITIAL.x, CAM_LOOKAT_INITIAL.y, CAM_LOOKAT_INITIAL.z);
//		direction.set(5, 0, 5);
position.set(0, 0, 0);
up.set(0, 1.0f, 0);
direction.set(0, 0, -1.0f);
		update();
		Log.out("Reset Camera:");
     	Log.out("Cam-Up:        " + up.x + ", " + up.y + ", " + up.z);
     	Log.out("Cam-Position:  " + position.x + ", " + position.y + ", " + position.z);
     	Log.out("Cam-Direction: " + direction.x + ", " + direction.y + ", " + direction.z);
     	Log.out("Cam-LookAt: " + CAM_LOOKAT_INITIAL.x + ", " + CAM_LOOKAT_INITIAL.y + ", " + CAM_LOOKAT_INITIAL.z);
//		GuOrthoCam oCam = new GuOrthoCam(w, h, viewSize);
//		oCam.position.set(-GRID_SIZE, GRID_SIZE, GRID_SIZE * 2);
//		oCam.lookAt(GRID_SIZE / 2, 0, GRID_SIZE / 2);
	}

	public void orbit(float amt_rotate, Vector3 target)
	{
		//TODO NOT WORKING YET :-(
		/**
		 * Rotate camera around a target, cam looks always at target
		 */
//		amt_rotate *= Gdx.graphics.getDeltaTime();
		float x = (float) Math.sin(amt_rotate * DEG);
		float z = (float) Math.cos(amt_rotate * DEG);
     	Log.out("orbit values: " + x + ", " + z);
     	if( (x>0) && (z>0) )
     	{
//     		position.set(position.x+x, position.y, position.z+z);
     		position.set(target.x+x, 5f, position.z+z);     		
     	}
     	if( (x>0) && (z<0) )
     	{
     		position.set(target.x+x, 5f, position.z+z);     		
     	}
     	if( (x<0) && (z>0) )
     	{
     		position.set(target.x+x, 5f, position.z+z);     		
     	}
     	if( (x<0) && (z<0) )
     	{
     		position.set(target.x+x, 5f, position.z+z);     		
     	}
		lookAt(target.x, target.y, target.z);
		update();
	}

    public void zoom(float amt_zoom)
    {
		//TODO NOT WORKING YET :-(
		// When touchscreen is used vertically, change camera's frustum
		// "shortSideLength" property
		// This effectively changes field of view. (??)

		// "shortSideLength" property defines the length of the shorter side of
		// the horizontal and vertical dimensions.
		// (The longer side will be automatically adjusted to preserve pixel
		// aspect ratio)
	 
/* crap yet
    	amt_zoom *= Gdx.graphics.getDeltaTime();
		float len = frustum.shortSideLength() + amt_zoom;
		if (len < 5f)
			len = 5f;
		if (len > 30f)
			len = 30f;
		frustum.shortSideLength(len);
*/
    	update();
	}

	// Camera camera = new OrthographicCamera(w, h);
	// campos = new Vector3(w/2, h/2, 5f);
	// camera.position.set(campos);
	// camera.lookAt(w/3, w/3, 0);
	// return camera;

}
