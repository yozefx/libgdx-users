package com.gdxuser.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class GuOrthoCam extends OrthographicCamera {

	private static Vector3 campos;
	private static float aspect;
	private Matrix4 comb;

	// viewsize should be related to the size of your scene	
	public GuOrthoCam (float w, float h, float viewSize) {
			super(viewSize, viewSize * (h/w));
			campos = new Vector3(15, 15, 8);
			position.set(campos);
			// direction.set(-1, -1, -1);
			lookAt(w/2, 0, h/2);
			near = 0.1f;
			far = 10000;
		}
	
	public void push(){
		campos = position;
		comb = this.combined;
	}
	public void pop() {
		position.set(campos);
		this.combined.set(comb);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

//		Camera camera = new OrthographicCamera(w, h);
//		campos = new Vector3(w/2, h/2, 5f);
//		camera.position.set(campos);
//		camera.lookAt(w/3, w/3, 0);
//		return camera;
	
}
