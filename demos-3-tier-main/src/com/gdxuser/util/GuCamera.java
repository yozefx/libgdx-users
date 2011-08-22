package com.gdxuser.util;

public interface GuCamera {

	
	public void push();
	public void pop();
	public void handleKeys();
	public void spin(float delta);

}
