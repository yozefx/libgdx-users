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

public class Cube extends MeshHelper {

	public Cube(Vector3 sz) {
		super("data/3d/cube.obj");
		
		//TODO - add scale for rendering
		size = sz;
		Log.out("cube bounds: " + mesh.calculateBoundingBox());

		// Texture texture = new
		// Texture(Gdx.files.internal("data/badlogic.jpg"), true);
		// texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
	}
	
	public Cube() {
		super("data/3d/cube.obj");
	}


}
