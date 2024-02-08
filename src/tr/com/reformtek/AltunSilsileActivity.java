package tr.com.reformtek;

import tr.com.reformtek.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import tr.com.reformtek.widget.AccordionView;

public class AltunSilsileActivity extends Activity {
	private AccordionView v = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (AccordionView.lastIndex == -1) {
			SharedPreferences prefGet = getSharedPreferences("SYC", Activity.MODE_PRIVATE);
			AccordionView.lastIndex = prefGet.getInt("syc", -1);
		}
		 v = (AccordionView) findViewById(R.id.accordion_view);
		 setContentView(R.layout.main);
	}

	@Override
	protected void onStop() {
		super.onStop();
		savePrefs();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		savePrefs();
	}
	
	@Override
	protected void onDestroy() {
	    super.onDestroy();
	    exit();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		exit();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater myInf = getMenuInflater();
		myInf.inflate(R.layout.options_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menuHakkinda:
				AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
				alertBuilder.setMessage(R.string.about).setNegativeButton("Tamam",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				});
				AlertDialog alert = alertBuilder.create();
				alert.setTitle(R.string.about_settings);
				alert.show();
				return true;
			case R.id.menuExit:
				this.exit();
				return true;
			case R.id.menuAltunSilsile:
				Intent info = new  Intent(AltunSilsileActivity.this, InfoScreen.class);
				startActivity(info);
				return true;
		}
		return false;
	}

	private void exit(){
		savePrefs();
		finish();
		System.exit(0);
	}
	
	private void savePrefs(){
		SharedPreferences prefPut = getSharedPreferences("SYC", Activity.MODE_PRIVATE);
		Editor prefEditor = prefPut.edit();
		prefEditor.putInt("syc", AccordionView.lastIndex);
		prefEditor.commit();
	}
}