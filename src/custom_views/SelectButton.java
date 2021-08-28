package custom_views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.chaturvedi.kbc_prakalpa.R;

public class SelectButton extends Button
{
	private int BUTTON_WIDTH = 250;
	private int BUTTON_HEIGHT = 40;
	
	public SelectButton(Context context)
	{
		super(context);
		customizeButton();
	}

	public SelectButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		customizeButton();
	}
	
	private void customizeButton()
	{
		super.setWidth(BUTTON_WIDTH);
		super.setHeight(BUTTON_HEIGHT);
		super.setTextColor(Color.WHITE);
		super.setBackgroundResource(R.drawable.button_select);
		
		ScaleAnimation scale = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f);
		scale.setFillAfter(true);
		scale.setDuration(1000);
		scale.setRepeatCount(ScaleAnimation.INFINITE);
		scale.setRepeatMode(ScaleAnimation.REVERSE);
		super.startAnimation(scale);
		
		/*super.setOnTouchListener(new View.OnTouchListener()
		{
			
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				SelectButton.this.setWidth(150);
				SelectButton.this.setHeight(25);
				return false;
			}
		});*/
	}
}
