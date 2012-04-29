package com.packtpub.deliverydroid;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class SamSushiActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sam_sushi);

		GridView fishGrid = (GridView) findViewById(R.id.fishes);
		Fish[] fishes = new Fish[8];
		String[] fishNames = getResources().getStringArray(R.array.fishes);
		int[] fishImages = { R.drawable.tuna, R.drawable.yellowtail,
				R.drawable.snapper, R.drawable.salmon, R.drawable.eel,
				R.drawable.sea_urchin, R.drawable.squid, R.drawable.shrimp };
		for (int i = 0; i < fishes.length; i++) {
			String name = fishNames[i];
			fishes[i] = new Fish(name, fishImages[i]);
		}
		fishGrid.setAdapter(new SuShiAdapter(fishes));

	}
}
