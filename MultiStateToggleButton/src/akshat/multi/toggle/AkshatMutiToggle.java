package akshat.multi.toggle;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AkshatMutiToggle extends LinearLayout {

	private int presentState;
	private List<String> stateTitle;
	private List<? extends Drawable> bg;
	private TextView buttonText;
	private int bgId;
	private int textId;
	private View bgContainer;

	private final List<MultiToggleStateChangeListner> registeredListners = new ArrayList<MultiToggleStateChangeListner>();

	public AkshatMutiToggle(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		Log.i("Touch", "Touch");
		internalIncrementState();
		sendCallbackToListners();
		return super.onTouchEvent(event);
	}

	public void initializeButtonsForStates(final List<String> stateTitle,
			final List<? extends Drawable> bg) {
		this.stateTitle = stateTitle;
		this.bg = bg;

		setDefaultIds();
		reCaptureUi();

		presentState = 0;
		internalMakeUIChanges();

	}

	private void setDefaultIds() {
		this.textId = R.id.text;
		this.bgId = R.id.b1;
	}

	public void initializeButtonsForStates(final List<String> stateTitle,
			final List<? extends Drawable> bg, View customView, int textId,
			int bgId) {

		this.stateTitle = stateTitle;
		this.bg = bg;

		this.textId = textId;
		this.bgId = bgId;

		useCustomView(customView);
		reCaptureUi();

		presentState = 0;
		internalMakeUIChanges();

	}

	private void reCaptureUi() {
		buttonText = (TextView) this.findViewById(textId);
		bgContainer = this.findViewById(bgId);
	}

	private void useCustomView(View v) {
		this.setBackgroundDrawable(null);
		this.removeAllViews();
		this.addView(v, 0);
	}

	private void internalIncrementState() {
		presentState++;
		if (presentState > bg.size() - 1) {
			presentState = 0;
		}
		internalMakeUIChanges();
	}

	private void internalMakeUIChanges() {
		bgContainer.setBackgroundDrawable(bg.get(presentState));
		buttonText.setText(stateTitle.get(presentState));

		this.requestLayout();
	}

	public String getCurrentTitle() {
		return stateTitle.get(presentState);

	}

	public boolean addOnStateToggleListner(MultiToggleStateChangeListner listner) {
		if (registeredListners.size() < 5
				&& !registeredListners.contains(listner)) {
			registeredListners.add(listner);
			return true;
		}
		return false;
	}

	private void sendCallbackToListners() {
		for (MultiToggleStateChangeListner listner : registeredListners) {
			listner.onMultiToogleStateChanged(presentState,
					stateTitle.get(presentState));
		}
	}
}
