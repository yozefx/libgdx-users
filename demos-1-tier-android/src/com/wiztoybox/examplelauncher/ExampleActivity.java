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

package com.wiztoybox.examplelauncher;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

public class ExampleActivity extends AndroidApplication {

	public void onCreate (Bundle bundle) {
		super.onCreate(bundle);

		Bundle extras = getIntent().getExtras();
		String exampleName = (String)extras.get("Example");
		Example example = newExample(exampleName);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useGL20 = example.needsGL20();
		config.numSamples = 2;
		initialize(example, config);
	}
	
	public static Example newExample(String exampleName)
	{
		try {
			Class exampleClass = Class.forName("com.wiztoybox.examplelauncher.examples." + exampleName);
			return (Example)exampleClass.newInstance();
		} catch (Exception ex) {
//TODO: This is not needed if all tests reside in examples folder...			
//			try {
//				Class exampleClass = Class.forName("com.wiztoybox.examplelauncher.examples." + exampleName);
//				return (Example)exampleClass.newInstance();
//			} catch (Exception e) {
//				ex.printStackTrace();
//				return null;
//			}
			ex.printStackTrace();
			return null;
		}
	}
}