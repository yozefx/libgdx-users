package com.wiztoybox.examplelauncher;

/* Data structure for examples list */
public class Demo
{
	public String title;
	public Class<?> classname;
	public int orientation;

	public Demo(String title, Class<?> classname)
	{
		this.title = title;
		this.classname = classname;
	}

	public Demo(String title, Class<?> classname, int orientation)
	{
		this.title = title;
		this.classname = classname;
		this.orientation = orientation;
	}
}	