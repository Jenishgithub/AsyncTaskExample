package com.example.asynctaskexample;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	EditText etsleeptime;
	Button btnrunasynctask;
	TextView tvresult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		etsleeptime = (EditText) findViewById(R.id.etsleeptime);
		btnrunasynctask = (Button) findViewById(R.id.btnRunAsyncTask);
		tvresult = (TextView) findViewById(R.id.tvResult);
		btnrunasynctask.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				String sleeptime = etsleeptime.getText().toString();
				AsyncTaskRunner runner = new AsyncTaskRunner();
				runner.execute(sleeptime);

			}
		});
	}

	class AsyncTaskRunner extends AsyncTask<String, String, String> {

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			String response = null;

			publishProgress("Sleeping....");
			/**
			 * this method actually calls on method onProgressUpdate() and
			 * passes "Sleeping...." to it
			 */

			int sleeptime = Integer.parseInt(params[0]);
			try {
				Thread.sleep(sleeptime);
				response = "Slept for " + sleeptime + " milliseconds...";

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return response;
		}

		@Override
		protected void onPostExecute(String result) {
			/** this method executes right after doinBackground() finishes */
			// TODO Auto-generated method stub
			tvresult.setText(result);
		}

		@Override
		protected void onProgressUpdate(String... values) {
			/**
			 * the code inside this method executes until doInBackground()
			 * finishes its computation
			 */
			// TODO Auto-generated method stub
			tvresult.setText(values[0]);
		}

	}
}