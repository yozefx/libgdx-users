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
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.gdxuser.demos.DecalWall;
import com.gdxuser.demos.ModelViewer;
import com.gdxuser.demos.QuadRenderTest;
import com.gdxuser.demos.SubMeshColorTest;
import com.gdxuser.demos.SubMeshRandomColorTest;
import com.gdxuser.util.DemoWrapper;
import com.gdxuser.util.Log;

public class TestCollection implements ApplicationListener, InputProcessor {
	private final DemoWrapper[] tests = {
			new DecalWall(),
			new StageTest(),
			new ParticleEmitterTest(),
			new SubMeshColorTest(),
			new SubMeshRandomColorTest(),
			new ModelViewer(),
			new PhysicsTest(),
			new DecalTest(),
			new HelloWorld(),
			new QuadRenderTest(),
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
			app.getInput().setCatchMenuKey(true);
			
			//Create first test 
			DemoWrapper test = tests[testIndex];
			test.create();
		}
		Gdx.input.setInputProcessor(this); //This class gets now all the input
	}

	@Override
	public boolean keyDown(int keycode) {
		//Log.out("TestCollection handling keyInput...");
		if ((keycode == Keys.PERIOD) || (keycode == Keys.MENU)) {
			setNewTest(testIndex + 1);
			return true;
		} else if ( (keycode == Keys.BACK) || (keycode == Keys.COMMA) ){
			setNewTest(testIndex - 1);
			return true;
		} else {
			return tests[testIndex].keyDown(keycode);
		}
	}

	@Override
	public boolean keyTyped(char character) {
		return tests[testIndex].keyTyped(character);
	}

	@Override
	public boolean keyUp(int keycode) {
		return tests[testIndex].keyUp(keycode);
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		return tests[testIndex].touchDown( x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return tests[testIndex].touchDragged(x, y, pointer);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		return tests[testIndex].touchUp(x, y, pointer, button);
	}

	// TODO: removed @Override notation... maybe not correct?
	public boolean needsGL20() {
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		return tests[testIndex].touchMoved(x, y);
	}

	@Override
	public boolean scrolled(int amount) {
		return tests[testIndex].scrolled(amount);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Switches to the next test given
	 */
	private void setNewTest(int nextTest)
	{
		//Dispose current test
		app.log("TestCollection", "disposing test #" + testIndex + " '" + tests[testIndex].getClass().getName() + "'");
		tests[testIndex].dispose();
		
		testIndex = nextTest;
		if (testIndex >= tests.length) testIndex = 0; //If out of bounds, simply start at beginning
		if (testIndex < 0 ) testIndex = tests.length -1; //If out of bounds, simply start at end
		
		//Create new test
		tests[testIndex].create();
		app.log("TestCollection", "created test #" + testIndex + " '" + tests[testIndex].getClass().getName() + "'");
	}
}