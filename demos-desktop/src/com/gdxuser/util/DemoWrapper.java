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
/*
 * Copyright 2010 Mario Zechner (contact@badlogicgames.com), Nathan Sweet (admin@esotericsoftware.com)
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.gdxuser.util;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


public class DemoWrapper implements ApplicationListener
{
	public void create( ) { };
	public void resume( ) { };
	public void render( ) { };
	public void resize(int width, int height) { };
	public void pause( ) { };
	
	

	public boolean keyDown(int keyCode) {
		switch (keyCode) {
		case Keys.Q:
			Gdx.app.exit();
		}
		return false;
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

	
	public boolean touchDown(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		return false;
	}

	
	public boolean touchMoved(int arg0, int arg1) {
		return false;
	}


	//TODO - maybe not correct
	public boolean needsGL20() {
		return false;
	}

	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		return false;
	}

	@Override 
	public void dispose () {
		Gdx.app.log("gdxdemo", "application destroyed");
	}


}
