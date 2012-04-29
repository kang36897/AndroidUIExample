package com.packtpub.deliverydroid;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FruitListAdapter extends BaseAdapter {

	private List<FruitItem> list;

	public FruitListAdapter(List<FruitItem> l) {
		this.list = l;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
					R.layout.fruit_item, null);
		}
		ImageView icon = (ImageView) vg.findViewById(R.id.icon);
		TextView text = (TextView) vg.findViewById(R.id.text);
		FruitItem item = (FruitItem) getItem(position);
		icon.setImageResource(item.image);
		text.setText(item.name);
		return vg;

	}

}

class FruitItem {
	public final String name;
	public final int image;

	FruitItem(String name, int image) {
		this.name = name;
		this.image = image;
	}
}
