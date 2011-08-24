package com.wiztoybox.examplelauncher;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.wiztoybox.examplelauncher.examples.*;

public class ExampleLauncher extends ListActivity {

	static String[] strings;

	public static final Demo[] demos = {
			// Add another line for each new demo.
			// Parameters: Title, class [, orientation]
			new Demo("Colored Background", ColoredBackground.class),
			new Demo("Colored Lines", ColoredLines.class),
			new Demo("Colored Squares", ColoredSquares.class,
					ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE),
			new Demo("Moving Sprites", MovingSprites.class) };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_launcher);		

		strings = new String[demos.length];
		for (int i = 0; i < demos.length; i++) {
			strings[i] = demos[i].classname.getSimpleName();
		}

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strings));
	}

	@Override
	protected void onListItemClick(ListView parent, View v, int position, long id) {
		super.onListItemClick(parent, v, position, id);

		Object o = this.getListAdapter().getItem(position);
		String exampleName = o.toString();

		Bundle bundle = new Bundle();
		bundle.putString("Example", exampleName);
		Intent intent = new Intent(this, ExampleActivity.class);
		intent.putExtras(bundle);

		startActivity(intent);
	}
}