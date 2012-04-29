package com.packtpub.deliverydroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SuShiAdapter extends BaseAdapter {
	private Fish[] fishes;

	public SuShiAdapter(Fish... fishes) {
		this.fishes = fishes;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fishes.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return fishes[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Context context = parent.getContext();
		ViewGroup vg = null;
		if (convertView instanceof ViewGroup) {
			vg = (ViewGroup) convertView;
		} else {
			vg = (ViewGroup) LayoutInflater.from(context).inflate(
					R.layout.fish_item, null);
		}
		ImageButton icon = (ImageButton) vg.findViewById(R.id.image);
		TextView text = (TextView) vg.findViewById(R.id.name);
		Fish item = (Fish) getItem(position);
		icon.setImageResource(item.image);
		text.setText(item.name);
		return vg;
	}

}

class Fish {
	final String name;
	final int image;

	public Fish(String name, int image) {
		this.name = name;
		this.image = image;
	}
}