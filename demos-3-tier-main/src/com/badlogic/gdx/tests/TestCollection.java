/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.tests;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.gdxuser.demos.DecalWall;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.Log;

public class TestCollection extends DemoWrapper implements InputProcessor {
	private InputMultiplexer inputMultiplexer = new InputMultiplexer(this);
	private final DemoWrapper[] tests = {
			//TODO There is a test.p asset missing here...
			// new ParticleEmitterTest(),
			new DecalWall(),
			new PhysicsTest(),
			new DecalTest(),
			new IsoCamTest()};

	private int testIndex = 0;

	private Application app = null;

	@Override
	public void render() {
		tests[testIndex].render();
	}

	@Override
	public void create() {

		if (this.app == null) {
			this.app = Gdx.app;
			app.getInput().setCatchBackKey(true);
			DemoWrapper test = tests[testIndex];
			test.create();
			inputMultiplexer.addProcessor(tests[testIndex]);
		}

		// TODO add IsoCamController here, too ?!
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public boolean keyDown(int keycode) {
		if ((keycode == Keys.SPACE) || (keycode == Keys.PERIOD) || (keycode == Keys.MENU)) {
			app.log("TestCollection", "disposing test #" + testIndex + " '"
					+ tests[testIndex].getClass().getName() + "'");
			tests[testIndex].dispose();
			testIndex++;
			if (testIndex >= tests.length) {
				testIndex = 0;
			}
			DemoWrapper test = tests[testIndex];
			test.create();
			app.log("TestCollection", "created test #" + testIndex + " '"
					+ tests[testIndex].getClass().getName() + "'");
		} else if (keycode == Keys.BACK) {
			testIndex--;
			if (testIndex <0) {
				Gdx.app.exit();
			}
			DemoWrapper test = tests[testIndex];
			test.create();
			app.log("TestCollection", "created test #" + testIndex + " '"
					+ tests[testIndex].getClass().getName() + "'");
		} else {
			tests[testIndex].keyDown(keycode);
		}

		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		tests[testIndex].keyTyped(character);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		tests[testIndex].keyUp(keycode);
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		tests[testIndex].touchDown(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		tests[testIndex].touchDragged(x, y, pointer);
		Log.out("touchDragged in TestCollection");
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		tests[testIndex].touchUp(x, y, pointer, button);
		return false;
	}

	@Override
	public boolean needsGL20() {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}