package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class GuOrthoCam extends OrthographicCamera {

	private static final int CAMSPEED = 40;
	private static Vector3 campos;
	private static float aspect;
	private Matrix4 comb;
	private Vector3 dir;

	// viewsize should be related to the size of your scene
	public GuOrthoCam(float w, float h, float viewSize) {
		super(viewSize, viewSize * (h / w));
		campos = new Vector3(15, 15, 8);
		position.set(campos);
		// direction.set(-1, -1, -1);
		lookAt(w / 2, 0, h / 2);
		near = 0.1f;
		far = 10000;
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
		update();
	}

	// Camera camera = new OrthographicCamera(w, h);
	// campos = new Vector3(w/2, h/2, 5f);
	// camera.position.set(campos);
	// camera.lookAt(w/3, w/3, 0);
	// return camera;

}
