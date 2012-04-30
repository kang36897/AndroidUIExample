package com.packtpub.roboticreview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

public class GalleryAdapter extends BaseAdapter {
	private final int[] thumbnails = new int[] { R.drawable.curry_view_thn,
			R.drawable.jai_thn,R.drawable.curry_view2_thn };
	private final int[] images = new int[] { R.drawable.curry_view,
			R.drawable.jai,R.drawable.curry_view2 };

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return thumbnails.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return Integer.valueOf(images[position]);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return images[position];
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView = (ImageView) ((convertView instanceof ImageView) ? convertView
				: LayoutInflater.from(parent.getContext()).inflate(
						R.layout.gallery_thn, null));
		//imageView.setImageResource(thumbnails[position]);
		imageView.setImageResource(images[position]);
		imageView.setMaxWidth(128);
		imageView.setScaleType(ScaleType.CENTER_INSIDE);
		imageView.setAdjustViewBounds(true);
		return imageView;
	}

}
