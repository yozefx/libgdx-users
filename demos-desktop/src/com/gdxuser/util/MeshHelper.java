package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.loaders.ModelLoaderOld;
import com.badlogic.gdx.math.Vector3;

public class MeshHelper {

	public Mesh mesh;
	public Vector3 size;
	public float scale;
	public Vector3 pos;

	public MeshHelper(String fp) {
		loadMesh(fp);
		
	}
	
	protected void loadMesh(String fpath) {
		// TODO Auto-generated method stub
		mesh = ModelLoaderOld.loadObj(Gdx.files.internal(
				fpath).read());		
	}

	public MeshHelper scale(float sc) {
		scale = sc;
		mesh.scale(sc, sc, sc);
		return this;
	}
	
	public MeshHelper pos(Vector3 p) {
		pos(p.x, p.y, p.z);
		return this;
	}
	public MeshHelper pos(float x, float y, float z) {
		pos = new Vector3(x,y,z);
		return this;
	}
	
	public Vector3 pos() {
		return pos;
	}
	
	public void render(GL10 gl, int renderType) {
		gl.glPushMatrix();
		gl.glTranslatef(pos.x, pos.y, pos.z);
		mesh.render(renderType);
		gl.glPopMatrix();
	}

}
