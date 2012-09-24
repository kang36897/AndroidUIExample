package com.gulu.image;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

public class DisplayLargeImageActivity extends Activity {

	public static final int IMAGE_CAPTURE = 1;
	private ImageView largeImage;
	private Uri imageUri;
	private File imageFile;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display_large_image);

		largeImage = (ImageView) findViewById(R.id.largeImage);

		Intent goToCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		String imageName = "papa.jpg";
		imageFile = new File(Environment.getExternalStorageDirectory()
				.getAbsoluteFile() + File.separator + imageName);
		imageUri = Uri.fromFile(imageFile);
		goToCapture.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
		startActivityForResult(goToCapture, IMAGE_CAPTURE);

	}

	@Override	@SuppressWarnings("deprecation")
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == IMAGE_CAPTURE) {

			Display currentDisplay = getWindowManager().getDefaultDisplay();
		
			int dw = currentDisplay.getWidth();
			int dh = currentDisplay.getHeight();

			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;

			Bitmap bm = BitmapFactory.decodeFile(imageUri.getPath(), options);

			int heightRatio = (int) Math.ceil(options.outHeight / (float) dh);
			int widthRatio = (int) Math.ceil(options.outHeight / (float) dw);

			if (heightRatio > 1 && widthRatio > 1) {
				options.inSampleSize = heightRatio > widthRatio ? heightRatio
						: widthRatio;
			}
			
			options.inJustDecodeBounds=false;
			bm = BitmapFactory.decodeFile(imageFile.getAbsolutePath(), options);
			Log.v("kang","kang-imageFile="+imageFile.getAbsolutePath());
			Log.v("kang","kang-imageUri="+imageUri);
			Log.v("kang","kang-imageUri="+imageUri.getPath());
			largeImage.setImageBitmap(bm);
		}

	}

}
