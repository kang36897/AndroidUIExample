package com.packtpub.deliverydroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

public class PizzaToppingAdapter extends BaseExpandableListAdapter {
	private ToppingCatagory[] toppingCatagories;

	public PizzaToppingAdapter(ToppingCatagory... toppingCatagory) {
		this.toppingCatagories = toppingCatagory;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return toppingCatagories.length;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		if (groupPosition >= 0 && groupPosition <= toppingCatagories.length - 1)
			return toppingCatagories[groupPosition].catagories.length;
		else
			return 0;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return toppingCatagories[groupPosition];
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return toppingCatagories[groupPosition].catagories[childPosition];
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		TextView view = getGroupView(convertView, parent);
		view.setText(toppingCatagories[groupPosition].name);
		// view.setPadding(android.R.attr.expandableListPreferredItemPaddingLeft,
		// 0, 0, 0);
		return view;
	}

	private TextView getGroupView(View convertView, ViewGroup parent) {
		if (convertView instanceof TextView) {
			return (TextView) convertView;
		}
		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View item = inflater.inflate(
				android.R.layout.simple_expandable_list_item_1, null);
		return (TextView) item;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewGroup viewGroup = getViewGroup(convertView, parent);
		TextView flag = (TextView) viewGroup.findViewById(R.id.topping_flag);
		TextView name = (TextView) viewGroup.findViewById(R.id.topping_name);
		PizzaTopping pizzaTopping = (PizzaTopping) getChild(groupPosition,
				childPosition);
		flag.setVisibility(pizzaTopping.flag < 1 ? View.INVISIBLE
				: View.VISIBLE);
		if (pizzaTopping.flag == 1) {
			flag.setText("On");
		} else if (pizzaTopping.flag == 2) {
			flag.setText("Extra");
		}

		name.setText(pizzaTopping.name);
		return viewGroup;
	}

	private ViewGroup getViewGroup(View convertView, ViewGroup parent) {
		if (convertView instanceof ViewGroup)
			return (ViewGroup) convertView;

		Context context = parent.getContext();
		LayoutInflater inflater = LayoutInflater.from(context);
		View item = inflater.inflate(R.layout.topping_item, null);
		return (ViewGroup) item;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return true;
	}

}
