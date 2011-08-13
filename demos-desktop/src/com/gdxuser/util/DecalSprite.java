package com.gdxuser.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.decals.Decal;
import com.badlogic.gdx.graphics.g3d.decals.DecalBatch;

public class DecalSprite {

	private Decal sprite;

	public DecalSprite(String imgPath) {
		Texture image = new Texture(
				Gdx.files.internal("data/badges/128/badge0.png"));
		image.setFilter(Texture.TextureFilter.Linear,
				Texture.TextureFilter.Linear);
		image.setWrap(Texture.TextureWrap.ClampToEdge,
				Texture.TextureWrap.ClampToEdge);

		float w = image.getWidth();
		float h = image.getHeight();
		sprite = Decal.newDecal(w, h, new TextureRegion(image), false);
	}

}
