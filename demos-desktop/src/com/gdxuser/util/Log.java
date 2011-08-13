package com.gdxuser.util;

import com.badlogic.gdx.Gdx;

public class Log {
	
	public static void out(String str) {
		Gdx.app.log("gdxdemos", str);
	}

	//FIXME doesnt accept string right way
	static void splog(String s, float... vars) {
		String s2 = String.format(s, vars);
		out(s2);
	}


}
