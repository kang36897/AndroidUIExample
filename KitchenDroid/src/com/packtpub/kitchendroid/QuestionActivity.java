package com.packtpub.kitchendroid;

import android.app.Activity;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QuestionActivity extends Activity implements OnItemClickListener{
	/** Called when the activity is first created. */
	private final static String TAG = "QuestionActivity";
	private Button[] buttons;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);
		// initQuestionScreen();

		setContentView(R.layout.main_hero);
		initQuestionScreenWithListView();
	}

	private int getQuestionID(final Resources res, final int index) {
		String[] questions = res.getStringArray(R.array.questions);
		// return
		// res.getIdentifier(questions[index],"array","com.packtpub.kitchendroid");

		return res.getIdentifier("com.packtpub.kitchendroid:array/"
				+ questions[index], null, null);

	}

	private int getQuestionIndex() {
		return getIntent().getIntExtra("KitchenDroid.Question", 0);
	}

	private void initQuestionScreen() {
		TextView question = (TextView) findViewById(R.id.question);
		ViewGroup answers = (ViewGroup) findViewById(R.id.answers);
		int questionID = getQuestionID(getResources(), getQuestionIndex());
		String[] questionData = getResources().getStringArray(questionID);
		question.setText(questionData[0]);
		int answerCount = questionData.length - 1;
		buttons = new Button[answerCount];

		for (int i = 0; i < answerCount; i++) {
			final String answer = questionData[i + 1];
			buttons[i] = new Button(this);
			buttons[i].setText(answer);
			buttons[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(QuestionActivity.this, "you say " + answer,
							Toast.LENGTH_LONG).show();
				}

			});
			answers.addView(buttons[i]);

		}
	}

	private void initQuestionScreenWithListView() {
		TextView question = (TextView) findViewById(R.id.question);
		ListView listView = (ListView) findViewById(R.id.answers);
		int questionID = getQuestionID(getResources(), getQuestionIndex());
		String[] questionData = getResources().getStringArray(questionID);
		question.setText(questionData[0]);
		final String[] answers = new String[questionData.length - 1];
		System.arraycopy(questionData, 1, answers, 0, questionData.length - 1);
		listView.setAdapter(new ArrayAdapter<String>(this,R.layout.list_button,R.id.button_item,answers){

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				// TODO Auto-generated method stub
				Button button= (Button) super.getView(position, convertView, parent);
				final String message="you say " + answers[position];
				button.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Log.v(TAG,"onClick()");
						Toast.makeText(QuestionActivity.this, message,
								Toast.LENGTH_LONG).show();
					}
				});
				return button;
			}
			
			
		});
        listView.setOnItemClickListener(this);

	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
		// TODO Auto-generated method stub
		Button button=(Button) adapterView.getItemAtPosition(index);
		Log.v(TAG,"onItemClick()");
		Toast.makeText(this, "you say " + button.getText(), Toast.LENGTH_SHORT).show();
	}
}
