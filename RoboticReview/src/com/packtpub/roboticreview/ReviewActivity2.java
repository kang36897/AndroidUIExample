package com.packtpub.roboticreview;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.TabActivity;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Gallery;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TabHost;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ImageView.ScaleType;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.ViewSwitcher.ViewFactory;

public class ReviewActivity2 extends TabActivity implements ViewFactory,
		Runnable, OnItemSelectedListener, OnSeekBarChangeListener,
		OnClickListener, OnDateSetListener, OnTimeSetListener,
		OnTabChangeListener {
	private TabHost tabHost;
	private final Handler switcherCommentsHandler = new Handler();
	private TextSwitcher switcher;
	private String[] comments;
	private int commentIndex = 0;
	// private ImageView photo;
	private ImageSwitcher photo;

	private String peopleLabelFormatString;
	private TextView peopleLabel;

	private SimpleDateFormat dateFormat;
	private Button date;

	private SimpleDateFormat timeFormat;
	private Button time;

	private boolean reviewInit = false;
	private boolean photoInit = false;
	private boolean reservationInit = false;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		tabHost = getTabHost();
		getLayoutInflater().inflate(R.layout.main2,
				tabHost.getTabContentView(), true);

		Resources resources = getResources();
		TabHost.TabSpec details = tabHost.newTabSpec("review").setContent(
				R.id.review).setIndicator(getString(R.string.review),
				resources.getDrawable(R.drawable.review));

		TabHost.TabSpec gallery = tabHost.newTabSpec("photos").setContent(
				R.id.photos).setIndicator(getString(R.string.photo),
				resources.getDrawable(R.drawable.photos));

		TabHost.TabSpec reservation = tabHost.newTabSpec("reservation")
				.setContent(R.id.reservation).setIndicator(
						getString(R.string.reservation),
						resources.getDrawable(R.drawable.reservation));

		tabHost.addTab(details);
		tabHost.addTab(gallery);
		tabHost.addTab(reservation);

		tabHost.setCurrentTabByTag("photos");//calling this method will trigger onTabChanged() method
		tabHost.setOnTabChangedListener(this);
		init(tabHost.getCurrentTabTag());

	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.v("kang", "kang-currentTab=" + tabHost.getCurrentTabTag());
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		peopleLabel.setText(String
				.format(peopleLabelFormatString, progress + 1));

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	private Calendar parseCalendar(CharSequence text, SimpleDateFormat format) {
		try {
			Date date = format.parse(text.toString());
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (date == v) {
			Calendar calendar = parseCalendar(date.getText(), dateFormat);
			new DatePickerDialog(this, this, calendar.get(Calendar.YEAR),
					calendar.get(Calendar.MONTH), calendar
							.get(Calendar.DAY_OF_MONTH)).show();
		}
		if (time == v) {
			Calendar calendar = parseCalendar(time.getText(), timeFormat);
			new TimePickerDialog(this, this,
					calendar.get(Calendar.HOUR_OF_DAY), calendar
							.get(Calendar.MINUTE), false).show();
		}
	}

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, monthOfYear);
		calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		date.setText(dateFormat.format(calendar.getTime()));
	}

	@Override
	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// TODO Auto-generated method stub
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
		calendar.set(Calendar.MINUTE, minute);
		time.setText(timeFormat.format(calendar.getTime()));
	}

	@Override
	public void onTabChanged(String tabId) {
		// TODO Auto-generated method stub
		Log.v("kang", "kang-tabId=" + tabId);
		init(tabId);
	}

	public void init(String tabId) {
		if (tabId.equals("reservation") && !reservationInit) {
			Log.v("kang", "kang-tabId=" + tabId + ",reservationInit="
					+ reservationInit);
			reservationInit = true;
			peopleLabel = (TextView) findViewById(R.id.people_label);
			peopleLabelFormatString = peopleLabel.getText().toString();

			date = (Button) findViewById(R.id.date);
			dateFormat = new SimpleDateFormat(date.getText().toString());

			time = (Button) findViewById(R.id.time);
			timeFormat = new SimpleDateFormat(time.getText().toString());

			Calendar calendar = Calendar.getInstance();
			if (calendar.get(Calendar.HOUR_OF_DAY) >= 16) {
				calendar.add(Calendar.DATE, 1);
			}

			calendar.set(Calendar.HOUR_OF_DAY, 18);
			calendar.clear(Calendar.MINUTE);
			calendar.clear(Calendar.SECOND);
			calendar.clear(Calendar.MILLISECOND);

			Date reservationDate = calendar.getTime();
			date.setText(dateFormat.format(reservationDate));
			time.setText(timeFormat.format(reservationDate));
			date.setOnClickListener(this);
			time.setOnClickListener(this);

			SeekBar people = (SeekBar) findViewById(R.id.people);
			people.setOnSeekBarChangeListener(this);
			peopleLabel.setText(String.format(peopleLabelFormatString, people
					.getProgress() + 1));
		} else if (tabId.equals("photos") && !photoInit) {
			Log.v("kang", "kang-tabId=" + tabId + ",photoInit=" + photoInit);
			photoInit = true;
			// photo=(ImageView) findViewById(R.id.photo);
			photo = (ImageSwitcher) findViewById(R.id.photo);
			photo.setFactory(new ViewFactory() {

				@Override
				public View makeView() {
					// TODO Auto-generated method stub
					ImageView imageView = new ImageView(ReviewActivity2.this);
					imageView.setScaleType(ScaleType.CENTER_INSIDE);
					return imageView;
				}
			});
			Gallery photos = (Gallery) findViewById(R.id.gallery);
			photos.setAdapter(new GalleryAdapter());
			photos.setOnItemSelectedListener(this);
		} else if (tabId.equals("review") && !reviewInit) {
			Log.v("kang", "kang-tabId=" + tabId + ",reviewInit=" + reviewInit);
			reviewInit=true;
			TextView titleTextView = (TextView) findViewById(R.id.name);
			titleTextView.setText("Jail\'s Highrise Curry Den");

			comments = getResources().getStringArray(R.array.comments);
			switcher = (TextSwitcher) findViewById(R.id.reviews);
			switcher.setFactory(this);
			switcherCommentsHandler.postDelayed(this, 5 * 1000);
		}
	}
}