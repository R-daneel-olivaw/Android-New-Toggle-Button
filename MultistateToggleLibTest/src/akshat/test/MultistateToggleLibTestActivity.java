package akshat.test;

import java.util.ArrayList;
import java.util.List;

import akshat.multi.toggle.AkshatMutiToggle;
import akshat.multi.toggle.MultiToggleStateChangeListner;
import akshat.multi.toggle.R;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MultistateToggleLibTestActivity extends Activity implements
		MultiToggleStateChangeListner {
	private AkshatMutiToggle mutiToggle;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mutiToggle = (AkshatMutiToggle) findViewById(R.id.b1);

		List<String> titleList = new ArrayList<String>();
		titleList.add("sadfsaf");
		titleList.add("mhpkyhj");
		titleList.add("xcx");

		List<Drawable> bgList = new ArrayList<Drawable>();
		bgList.add(getResources().getDrawable(R.drawable.appwidget_bg));
		bgList.add(getResources().getDrawable(R.drawable.gray_bg));
		bgList.add(getResources().getDrawable(R.drawable.green_button));

		View inflate = View.inflate(this, R.layout.custom_b_1, null);

//		mutiToggle.initializeButtonsForStates(titleList, bgList);

		mutiToggle.initializeButtonsForStates(titleList, bgList, inflate,
				R.id.textView1, R.id.bg_1);
		mutiToggle.addOnStateToggleListner(this);
	}

	@Override
	public void onMultiToogleStateChanged(int newState, String newTitle) {
		Log.i("In Listner", "In Listner - " + newTitle);
	}
}