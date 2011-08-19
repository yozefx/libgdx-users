package com.gdxuser;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tests.Box2DTest;
import com.badlogic.gdx.tests.IsoCamTest;
import com.badlogic.gdx.tests.PhysicsTest;
import com.badlogic.gdx.tests.TestCollection;
import com.gdxuser.demos.DecalWall;
import com.gdxuser.demos.ModelViewer;
import com.gdxuser.demos.QuadRenderTest;
import com.gdxuser.demos.SimpleTest;
import com.gdxuser.util.DemoWrapper;

public class DemoWrapperDesktop {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO add menu system to allow user to pick which demo to launch
		// low tech switching method to choose which demo to view! comments!

//		new LwjglApplication(new SimpleTest(),
//				"LibGDX users DemoWrapper Desktop", 800, 600, false);
//		new LwjglApplication(new DecalWall(),
//				"LibGDX users DemoWrapper Desktop", 800, 600, false);
//		new LwjglApplication(new ModelViewer(),
//				"LibGDX users DemoWrapper Desktop", 800, 600, false);
//		new LwjglApplication(new Box2DTest(),
//				"LibGDX users DemoWrapper Desktop", 800, 600, false);
//		new LwjglApplication(new PhysicsTest(),
//				"LibGDX users DemoWrapper Desktop", 800, 600, false);
//		new LwjglApplication(new IsoCamTest(),
//				"LibGDX users DemoWrapper Desktop", 800, 600, false);
//		new LwjglApplication(new QuadRenderTest(),
//				"LibGDX users DemoWrapper Desktop: QuadRenderTest", 800, 600, false);
		new LwjglApplication(new TestCollection(),
				"LibGDX users DemoWrapper Desktop", 800, 600, false);
	}

}
