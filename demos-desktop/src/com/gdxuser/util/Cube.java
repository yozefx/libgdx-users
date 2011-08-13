package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.loaders.ModelLoaderOld;
import com.badlogic.gdx.math.Vector3;

public class Cube {

	private Mesh mesh;
	private Vector3 size;
	private float scale;
	private Vector3 pos;

	public Cube(Vector3 sz) {
		loadMesh();
		
		//TODO - add scale for rendering
		size = sz;
		Log.out("cube bounds: " + mesh.calculateBoundingBox());

		// Texture texture = new
		// Texture(Gdx.files.internal("data/badlogic.jpg"), true);
		// texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
	}
	
	public Cube() {
		loadMesh();
	}

	private void loadMesh() {
		// TODO Auto-generated method stub
		mesh = ModelLoaderOld.loadObj(Gdx.files.internal(
				"data/3d/cube.obj").read());		
	}

	public Cube scale(float sc) {
		scale = sc;
		mesh.scale(sc, sc, sc);
		return this;
	}
	
	public Cube pos(Vector3 p) {
		pos(p.x, p.y, p.z);
		return this;
	}
	public Cube pos(float x, float y, float z) {
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
