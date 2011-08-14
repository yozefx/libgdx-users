package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureWrap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalSprite{

	public Decal sprite;

	public DecalSprite() {
		// constructor
	}
	
	public DecalSprite build(String imgPath) {
		TextureWrap texWrap = Texture.TextureWrap.ClampToEdge;	// default
		return build(imgPath, texWrap);
	}

	public DecalSprite build(String imgPath, TextureWrap texWrap) {
		Texture image = new Texture(
				Gdx.files.internal(imgPath));
		image.setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);
		image.setWrap(Texture.TextureWrap.Repeat,
				Texture.TextureWrap.Repeat);

		float w = image.getWidth();
		float h = image.getHeight();
		sprite = Decal.newDecal(w, h, new TextureRegion(image), true);
		return this;
	}

	//TODO
	public void faceCamera(Camera oCam) {
		// sprite.direction.set(x, y, z).sub(position).nor();
	}

	public void update(float delta) {
		// sprite.setRotation(dir, up)
	}
}
