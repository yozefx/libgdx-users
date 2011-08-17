package com.gdxuser.util;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;

public class DemoWrapper implements ApplicationListener, InputProcessor {

	@Override
	public void create() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void render() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void dispose() {
		Gdx.app.log("gdxdemo", "application destroyed");
	}

	public boolean keyDown(int keyCode) {
		switch (keyCode) {
		case Keys.ESCAPE:
			Gdx.app.exit();
		}
		return true;
	}

	public boolean keyTyped(char arg0) {
		return false;
	}

	public boolean keyUp(int arg0) {
		return false;
	}

	public boolean scrolled(int arg0) {
		return false;
	}

	public boolean touchDown(int x, int y, int pointer, int button) {
		return false;
	}

	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}

	// TODO - maybe not correct
	public boolean needsGL20() {
		return false;
	}

	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

}