package com.course.example.progressdialog;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.view.KeyEvent;
import android.widget.TextView;

public class ProgressDialogExample extends Activity implements Runnable {

	private String pi_string;
	private TextView tv;
	private ProgressBar progress;

	private Handler handler = new Handler(Looper.getMainLooper()) {
		@Override
		public void handleMessage(Message msg) {

			tv.setText(pi_string);
			progress.setVisibility(View.INVISIBLE);
		}
	};

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.main);

		progress = (ProgressBar) findViewById(R.id.progress);
		tv = (TextView) findViewById(R.id.TextView01);
		tv.setText("Press any key to start calculation" + "\n");
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		Thread thread = new Thread(this);
		thread.start();

		return super.onKeyDown(keyCode, event);
	}

	public void run() {
		pi_string = Pi.computePi(1500).toString();
		handler.sendEmptyMessage(0);
	}
}