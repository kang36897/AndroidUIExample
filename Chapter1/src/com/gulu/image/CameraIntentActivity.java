package com.gulu.image;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class CameraIntentActivity extends Activity implements OnClickListener {
	public static final int CAPTURE_OK = 0;

	private Button captureButton;
	private Button displayButton;
	private Button galleryButton;
	private ImageView picture;
	private Uri imageFileUri;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_camera_intent);
		captureButton = (Button) findViewById(R.id.capture);
		captureButton.setOnClickListener(this);

		displayButton = (Button) findViewById(R.id.display);
		displayButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("display_a_large_image").setClassName(
						CameraIntentActivity.this,
						"com.gulu.image.DisplayLargeImageActivity").putExtra(
						MediaStore.EXTRA_SCREEN_ORIENTATION,
						ActivityInfo.SCREEN_ORIENTATION_PORTRAIT));
			}
		});

		galleryButton = (Button) findViewById(R.id.gallery);
		galleryButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent("go_to_the_gallery").setClassName(
						CameraIntentActivity.this,
						"com.gulu.image.MediaGalleryActivity"));
			}
		});

		picture = (ImageView) findViewById(R.id.picture);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_camera_intent, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		/*
		 * version one get the thumbnail Intent goToCapture=new
		 * Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		 * startActivityForResult(goToCapture,CAPTURE_OK);
		 */

		/*
		 * version two String imageFilePath =
		 * Environment.getExternalStorageDirectory() .getAbsolutePath() +
		 * "/myFavoritePicture.jpg"; File imageFile = new File(imageFilePath);
		 * imageFileUri = Uri.fromFile(imageFile);
		 */

		ContentValues metadata = new ContentValues(3);
		metadata.put(Media.DISPLAY_NAME, "Wona");
		metadata.put(Media.DESCRIPTION, "this is a test picture!");
		metadata.put(Media.MIME_TYPE, "image/jpeg");

		imageFileUri = getContentResolver().insert(Media.EXTERNAL_CONTENT_URI,
				metadata);
		Intent goToCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		goToCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
		goToCapture.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION,
				ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		startActivityForResult(goToCapture, CAPTURE_OK);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CAPTURE_OK) {
			/*
			 * version one get the thumbnail Bundle extra=data.getExtras();
			 * Bitmap bm=(Bitmap) extra.get("data"); picture.setImageBitmap(bm);
			 */
			/*
			 * version two picture.setImageURI(imageFileUri);
			 */

			BitmapFactory.Options opts = new BitmapFactory.Options();
			try {
				Bitmap bm = BitmapFactory.decodeStream(getContentResolver()
						.openInputStream(imageFileUri), null, opts);
				picture.setImageBitmap(bm);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			Log.v("kang", "kang-" + imageFileUri);
		}
	}

}
