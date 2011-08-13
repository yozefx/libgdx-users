package com.gdxuser.util;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

public class CamUtil {

	private static Vector3 campos;
	private static float aspect;

	public static Camera orthoCam(float w, float h) {
			aspect = h * 1f / w;
			OrthographicCamera camera = new OrthographicCamera(10, (10 * aspect));
			campos = new Vector3(8, 2, 8);
			camera.position.set(campos);
			camera.direction.set(-1, -1, -1);
			// cam.lookAt(5, 0, 5);
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
