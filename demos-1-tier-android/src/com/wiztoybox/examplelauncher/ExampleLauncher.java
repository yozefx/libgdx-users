package com.wiztoybox.examplelauncher;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ExampleLauncher extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		ArrayList<String> exampleNames = new ArrayList<String>();
		for (String exampleName : Example.getNames()) {
			exampleNames.add(exampleName);
		}

		// this is a second option of adding examples
		// besides adding them to array in Example.java!
//		exampleNames.add(ColoredBackground.class.getSimpleName());
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, exampleNames.toArray(new String[0])));		
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