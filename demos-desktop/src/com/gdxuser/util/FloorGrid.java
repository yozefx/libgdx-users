package com.gdxuser.util;

// rendering a floorgrid on x, z - NOT x,y

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.loaders.ModelLoaderOld;

public class FloorGrid {
	int xcount, zcount;
	private Mesh path;

	public FloorGrid(int tx, int tz) {
		Mesh oneTile = oneTile(1, 0, 1);
		xcount = tx;
		zcount = tz;
	}

	public Mesh oneTile(int x, int y, int z) {
		path = new Mesh(true, 4, 5, new VertexAttribute(Usage.Position, 3,
				"a_position"));
		path.setVertices(new float[] { 0, 0, 0, x, 0, 0, x, 0, z, 0, 0, z });
		path.setIndices(new short[] { 0, 1, 2, 3, 0 });
		return path;
	}
	
	public void render(GL10 gl, int renderType) {
		gl.glPushMatrix();
		for(int x=0; x<xcount; x++) {
			for(int z=0; z<zcount; z++) {
				gl.glPushMatrix();
				gl.glTranslatef(x, 0, z);
				path.render(renderType);
				gl.glPopMatrix();
			}
		}
	}


}
