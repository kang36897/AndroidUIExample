package com.gulu.image;

import java.io.IOException;

import android.app.Activity;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MediaGalleryActivity extends Activity {

	public static final int DISPLAYWIDTH = 200;
	public static final int DISPLAYHEIGHT = 200;

	private TextView title;
	private ImageButton imageButton;

	private Cursor cursor;
	private String[] columnNames = new String[] { Media.DATA, Media._ID,
			Media.TITLE, Media.DATE_ADDED };

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_media_gallery);

		title = (TextView) findViewById(R.id.title);
		imageButton = (ImageButton) findViewById(R.id.imageButton);

		cursor = managedQuery(Media.EXTERNAL_CONTENT_URI, columnNames, null,
				null, Media.DATE_ADDED + " DESC");
		if (cursor != null && cursor.moveToNext()) {
			title.setText(cursor.getString(cursor
					.getColumnIndexOrThrow(Media.TITLE)));
			imageButton.setImageBitmap(getBitmap(cursor.getString(cursor
					.getColumnIndexOrThrow(Media.DATA))));

		}

		imageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (cursor != null && cursor.moveToNext()) {
					title.setText(cursor.getString(cursor
							.getColumnIndexOrThrow(Media.TITLE)));
					imageButton.setImageBitmap(getBitmap(cursor
							.getString(cursor.getColumnIndexOrThrow(Media.DATA))));

				}
			}
		});

	}

	private Bitmap getBitmap(String imagePath) {
		// TODO Auto-generated method stub
		BitmapFactory.Options opts = new BitmapFactory.Options();
		opts.inJustDecodeBounds = true;
		try {
			ExifInterface exif = new ExifInterface(imagePath);
			Log.v("kang", "kang-exif.hasThumbnail()=" + exif.hasThumbnail());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Bitmap bm = BitmapFactory.decodeFile(imagePath, opts);

		int heightRatio = (int) Math.ceil(opts.outHeight / DISPLAYHEIGHT);
		int widthRatio = (int) Math.ceil(opts.outWidth / DISPLAYWIDTH);

		if (heightRatio > 1 && widthRatio > 1) {
			opts.inSampleSize = heightRatio > widthRatio ? heightRatio
					: widthRatio;
		}

		opts.inJustDecodeBounds = false;
		bm = BitmapFactory.decodeFile(imagePath, opts);
		return bm;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_media_gallery, menu);
		return true;
	}
}
