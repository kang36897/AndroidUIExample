package com.packtpub.deliverydroid;

import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.GridView;

public class FourBucketsFruitActivity extends Activity {

	
	private int[] fruitsImage = { R.drawable.apple, R.drawable.banana,
			R.drawable.blackberry, R.drawable.cherries, R.drawable.coconut,
			R.drawable.kiwi, R.drawable.lemon, R.drawable.lime,
			R.drawable.orange, R.drawable.peach, R.drawable.strawberry,
			R.drawable.watermelon };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater();

		GridView grid = (GridView) inflater
				.inflate(R.layout.four_buckets, null);
         String[] fruitsName=getResources().getStringArray(R.array.fruits);
         FruitItem[] fruits=new FruitItem[fruitsName.length];
         for(int i=0;i<fruitsName.length;i++){
        	 fruits[i]=new FruitItem(fruitsName[i],fruitsImage[i]);
         }
         
		grid.setAdapter(new FruitListAdapter(Arrays.asList(fruits)));

		setContentView(grid);

	}

}
