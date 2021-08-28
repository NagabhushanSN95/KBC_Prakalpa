package custom_views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.chaturvedi.kbc_prakalpa.R;

public class SelectButtonMini extends Button
{
	private int BUTTON_WIDTH = 200;
	private int BUTTON_HEIGHT = 30;
	
	public SelectButtonMini(Context context)
	{
		super(context);
		customizeButton();
	}

	public SelectButtonMini(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		customizeButton();
	}
	
	private void customizeButton()
	{
		super.setWidth(BUTTON_WIDTH);
		super.setHeight(BUTTON_HEIGHT);
		super.setTextColor(Color.WHITE);
		super.setBackgroundResource(R.drawable.button_select_mini);
		// Disable Default Sound Effects
		super.setSoundEffectsEnabled(false);
		
		ScaleAnimation scale = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f);
		scale.setFillAfter(true);
		scale.setDuration(1000);
		scale.setRepeatCount(ScaleAnimation.INFINITE);
		scale.setRepeatMode(ScaleAnimation.REVERSE);
		super.startAnimation(scale);
	}
}
