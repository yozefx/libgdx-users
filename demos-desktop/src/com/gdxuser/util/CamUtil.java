package com.gdxuser.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CamUtil {

	private static Vector3 campos;
	private static float aspect;

	public static Camera orthoCam(float w, float h) {
			float viewsize = 20;  // this should be related to the size of your scene	
			aspect = h * 1f / w;
			OrthographicCamera camera = new OrthographicCamera(viewsize, (viewsize * aspect));
			campos = new Vector3(15, 15, 8);
			camera.position.set(campos);
			// camera.direction.set(-1, -1, -1);
			camera.lookAt(0, 0, 0);
			camera.near = 0.1f;
			camera.far = 10000;
			return camera;
		}

//		Camera camera = new OrthographicCamera(w, h);
//		campos = new Vector3(w/2, h/2, 5f);
//		camera.position.set(campos);
//		camera.lookAt(w/3, w/3, 0);
//		return camera;
	
}
