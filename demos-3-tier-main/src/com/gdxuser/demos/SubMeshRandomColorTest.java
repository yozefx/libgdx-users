package com.gdxuser.demos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.loaders.wavefront.ObjLoader;
import com.badlogic.gdx.graphics.g3d.model.SubMesh;
import com.badlogic.gdx.graphics.g3d.model.still.StillModel;
import com.gdxuser.util.DemoWrapper;

public class SubMeshRandomColorTest extends DemoWrapper {
	GL10 gl;
	PerspectiveCamera cam;
	ObjLoader model = new ObjLoader();
	StillModel building;

	public static Color[] roomColors;

	
	@Override
	public void create() {

		gl = Gdx.app.getGraphics().getGL10();

		gl.glMatrixMode(GL10.GL_MODELVIEW);
		gl.glLoadIdentity();

		// Enable face culling.
		gl.glEnable(GL10.GL_CULL_FACE);

		// What faces to remove with the face culling.
		gl.glCullFace(GL10.GL_BACK);

		// Enable Z-sorting, depth test
		gl.glEnable(GL10.GL_DEPTH_TEST);

		// load model from file
		building = model.loadObj(Gdx.files.internal("data/3d/building.obj"));

		// set colors for all building floor's submeshes
		roomColors = new Color[building.getSubMeshes().length];
		int i = 0;
		for (SubMesh subMeshName : building.getSubMeshes()) {
			roomColors[i] = new Color();
			roomColors[i].set((float) Math.random(), (float) Math.random(),
					(float) Math.random(), 1f);
			i++;
		}
		
		// Position the camera
		cam = new PerspectiveCamera(60, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		
		// just moving and rotating the cam
		// to fit most of the model's parts into viewport
		cam.rotate(-45, 1, 0, 0);
		cam.rotate(-45, 0, 1, 0);
		cam.translate(-5f, 20f, 20f);

		cam.near = 1f;
		cam.far = 100f;
		cam.update();
		cam.apply(gl);
	}

	@Override
	public void render() {

		// clear screen and set it to white
		// never forget |GL10.GL_DEPTH_BUFFER_BIT
		// because this makes 3D-Meshes flicker
		// and they get some kind of shine-through
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

		// render all floor's submeshes (i.e. the rooms)
		int i = 0;
		for (SubMesh subMeshName : building.getSubMeshes()) {
			gl.glColor4f(roomColors[i].r, roomColors[i].g, roomColors[i].b,
					roomColors[i].a);
			i++;
			building.getSubMesh(subMeshName.name).mesh
					.render(GL10.GL_TRIANGLES);
		}
	}

}
