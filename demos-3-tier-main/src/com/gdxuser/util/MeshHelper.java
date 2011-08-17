/*
 * deals with meshes that need transformations to render
 * class keeps its internal transformation matrix
 * and applies to GL before rendering
 * start of a very simple sceneGraph...
 * 
 * TODO - would be nice to delegate methods to internal mesh automagically
 */


package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.g3d.loaders.ModelLoaderOld;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class MeshHelper {

	public Mesh mesh;
	public Vector3 size;
	public float scale;
	private Vector3 pos = new Vector3(0,0,0);
	private BoundingBox bbox;
	private Vector3 color = new Vector3(1,1,1);

	public MeshHelper(String fpath) {
		load(fpath);
	}
	
	protected MeshHelper load(String fpath) {
		mesh = ModelLoaderOld.loadObj(Gdx.files.internal(
				fpath).read());
		return this;
	}

	public MeshHelper scale(float sc) {
		scale = sc;
		mesh.scale(sc, sc, sc);
		return this;
	}
	
	public void render(GL10 gl, int renderType) {
		// Log.out("render at:", pos);
		gl.glPushMatrix();
		gl.glTranslatef(pos.x, pos.y, pos.z);
		mesh.render(renderType);
		gl.glPopMatrix();
	}
	
	public void renderWireframe(GL10 gl) {
		gl.glPushMatrix();
		gl.glColor4f(color.x, color.y, color.z, 1);
		gl.glTranslatef(pos.x, pos.y, pos.z);		
		mesh.render(GL10.GL_LINE_STRIP);
		gl.glPopMatrix();
	}
	
	public  BoundingBox getSize() {
		bbox = mesh.calculateBoundingBox();
		Log.out("bbox:" + bbox);
		return bbox;
	}

	public Vector3 getPos() {
		return pos;
	}

	public MeshHelper setPos(Vector3 pos) {
		this.pos = pos;
		return this;
	}
	public MeshHelper setPos(float x, float y, float z) {
		setPos(new Vector3(x,y,z));
		return this;
	}

	// for line drawing
	public MeshHelper setColor(float r, float g, float b) {
		color  = new Vector3(r,g,b);
		return this;
	}

}
