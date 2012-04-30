package com.packtpub.roboticreview;

import java.net.Proxy.Type;

import android.R.integer;
import android.app.TabActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class ReviewActivity extends TabActivity implements ViewFactory,
		Runnable, OnItemSelectedListener {

	private final Handler switcherCommentsHandler = new Handler();
	private TextSwitcher switcher;
	private String[] comments;
	private int commentIndex = 0;
	// private ImageView photo;
	private ImageSwitcher photo;
	private final int[] images = new int[] { R.drawable.curry_view,
			R.drawable.jai, R.drawable.curry_view2 };
	private int imageIndex = 0;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		TabHost tabHost = getTabHost();
		getLayoutInflater().inflate(R.layout.main, tabHost.getTabContentView(),
				true);

		Resources resources = getResources();
		TabHost.TabSpec details = tabHost
				.newTabSpec("review")
				.setContent(R.id.review)
				.setIndicator(getString(R.string.review),
						resources.getDrawable(R.drawable.review));

		TabHost.TabSpec gallery = tabHost
				.newTabSpec("photos")
				.setContent(R.id.photos)
				.setIndicator(getString(R.string.photo),
						resources.getDrawable(R.drawable.photos));

		TabHost.TabSpec reservation = tabHost
				.newTabSpec("reservation")
				.setContent(R.id.reservation)
				.setIndicator(getString(R.string.reservation),
						resources.getDrawable(R.drawable.reservation));

		tabHost.addTab(details);
		tabHost.addTab(gallery);
		tabHost.addTab(reservation);

		TextView titleTextView = (TextView) findViewById(R.id.name);
		titleTextView.setText("Jail\'s Highrise Curry Den");

		comments = getResources().getStringArray(R.array.comments);
		switcher = (TextSwitcher) findViewById(R.id.reviews);
		switcher.setFactory(this);

		// photo=(ImageView) findViewById(R.id.photo);
		photo = (ImageSwitcher) findViewById(R.id.photo);
		photo.setFactory(new ViewFactory() {

			@Override
			public View makeView() {
				// TODO Auto-generated method stub
				ImageView imageView = new ImageView(ReviewActivity.this);
				imageView.setScaleType(ScaleType.CENTER_INSIDE);
				return imageView;
			}
		});
		Gallery photos = (Gallery) findViewById(R.id.gallery);
		photos.setAdapter(new GalleryAdapter());
		photos.setOnItemSelectedListener(this);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		switcherCommentsHandler.postDelayed(this, 5 * 1000);
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		switcherCommentsHandler.removeCallbacks(this);
	}

	@Override
	public View makeView() {
		// TODO Auto-generated method stub
		return getLayoutInflater().inflate(R.layout.review_comment, null);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			switcher.setText(comments[commentIndex++]);
			if (commentIndex >= comments.length) {
				commentIndex = 0;
			}
		} finally {
			switcherCommentsHandler.postDelayed(this, 5 * 1000);
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub

		photo.setImageResource((int) id);

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}
}