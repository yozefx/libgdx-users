package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.loaders.ModelLoaderOld;

public class MeshUtil {

	public MeshUtil() {

	}

	public Mesh floorGrid(int x, int y) {
		Mesh oneTile = oneTile(1, 1);
		return oneTile;
	}

	public Mesh oneTile(int x, int y) {
		Mesh path = new Mesh(true, 4, 5, new VertexAttribute(Usage.Position, 3,
				"a_position"));

		path.setVertices(new float[] { 0, 0, x, 0, x, y, y, 0 });
		path.setIndices(new short[] { 0, 1, 2, 3, 0 });
		return path;
	}

	public Mesh cube() {
		Mesh mesh = ModelLoaderOld.loadObj(Gdx.files.internal(
				"data/3d/cube.obj").read());
		Log.out("cube bounds: " + mesh.calculateBoundingBox());
		// Texture texture = new
		// Texture(Gdx.files.internal("data/badlogic.jpg"), true);
		// texture.setFilter(TextureFilter.MipMap, TextureFilter.Linear);
		return mesh;
	}

}
