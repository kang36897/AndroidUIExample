package com.nano.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	// Operation Buttons
	private ImageView mDelete;
	private Button mPlus;
	private Button mSubtract;
	private Button mMultiply;
	private Button mDevide;
	private Button mMod;
	private Button mEqual;

	// Number Buttons
	private Button mZero;
	private Button mOne;
	private Button mTwo;
	private Button mThree;
	private Button mFour;
	private Button mFive;
	private Button mSix;
	private Button mSeven;
	private Button mEight;
	private Button mNigh;

	private TextView displayArea;
	private StringBuilder displayContent = new StringBuilder("");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initUIResource();
	}

	private TextWatcher textLengthWatcher = new TextWatcher() {

		@Override
		public void afterTextChanged(Editable s) {

             switch(s.length()){
             case 0:
            	 displayArea.setTextSize(45);
            	 break;
             case 10:
            	 displayArea.setTextSize(36);
            	 break;
             case 12:
            	 displayArea.setTextSize(28);
            	 break;
             case 16:
            	 displayArea.setTextSize(20);
            	 break;
            default:
            	break;
             }

		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {
			// TODO Auto-generated method stub

		}
	};

	private void initUIResource() {
		displayArea = (TextView) findViewById(R.id.display);
		displayArea.addTextChangedListener(textLengthWatcher);

		mDelete = (ImageView) findViewById(R.id.delete);
		mDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!TextUtils.isEmpty(displayContent)) {
					displayContent.deleteCharAt(displayContent.length() - 1);
					displayArea.setText(displayContent);
				}
			}

		});

		mDelete.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				if (!TextUtils.isEmpty(displayContent)) {
					displayContent.delete(0, displayContent.length());
					displayArea.setText(displayContent);
					return true;
				}
				return false;
			}

		});

		mPlus = (Button) findViewById(R.id.plus);
		mPlus.setOnClickListener(this);

		mSubtract = (Button) findViewById(R.id.subtract);
		mSubtract.setOnClickListener(this);

		mMultiply = (Button) findViewById(R.id.multiply);
		mMultiply.setOnClickListener(this);

		mDevide = (Button) findViewById(R.id.devide);
		mDevide.setOnClickListener(this);

		mMod = (Button) findViewById(R.id.mod);
		mMod.setOnClickListener(this);

		mEqual = (Button) findViewById(R.id.equal);
		mEqual.setOnClickListener(this);

		mZero = (Button) findViewById(R.id.zero);
		mZero.setOnClickListener(this);

		mOne = (Button) findViewById(R.id.one);
		mOne.setOnClickListener(this);

		mTwo = (Button) findViewById(R.id.two);
		mTwo.setOnClickListener(this);

		mThree = (Button) findViewById(R.id.three);
		mThree.setOnClickListener(this);

		mFour = (Button) findViewById(R.id.four);
		mFour.setOnClickListener(this);

		mFive = (Button) findViewById(R.id.five);
		mFive.setOnClickListener(this);

		mSix = (Button) findViewById(R.id.six);
		mSix.setOnClickListener(this);

		mSeven = (Button) findViewById(R.id.seven);
		mSeven.setOnClickListener(this);

		mEight = (Button) findViewById(R.id.eight);
		mEight.setOnClickListener(this);

		mNigh = (Button) findViewById(R.id.nigh);
		mNigh.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		// Toast.makeText(this, "fuck you", Toast.LENGTH_SHORT).show();
		switch (view.getId()) {
		case R.id.zero:
		case R.id.one:
		case R.id.two:
		case R.id.three:
		case R.id.four:
		case R.id.five:
		case R.id.six:
		case R.id.seven:
		case R.id.eight:
		case R.id.nigh:

		case R.id.plus:
		case R.id.subtract:
		case R.id.multiply:
		case R.id.devide:
		case R.id.mod:
			displayContent.append(((Button) view).getText());
			displayArea.setText(displayContent);
			break;

		case R.id.equal:
			Toast.makeText(this, "fuck you", Toast.LENGTH_SHORT).show();
			break;
		}

	}
}
