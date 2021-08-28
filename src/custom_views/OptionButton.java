// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package custom_views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;

import com.chaturvedi.kbc_prakalpa.R;

public class OptionButton extends Button
{
	private int BUTTON_WIDTH = 200;
	private int BUTTON_HEIGHT = 20;
	
	public OptionButton(Context context)
	{
		super(context);
		customizeButton();
	}
	
	public OptionButton(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		customizeButton();
	}
	
	private void customizeButton()
	{
		super.setWidth(BUTTON_WIDTH);
		//super.setHeight(BUTTON_HEIGHT);
		super.setBackgroundResource(R.drawable.button_option);
		super.setTextColor(Color.WHITE);
		// Disable Default Sound Effects
		super.setSoundEffectsEnabled(false);
		
		// Animation
		ScaleAnimation scale = new ScaleAnimation(1.0f, 1.05f, 1.0f, 1.05f);
		scale.setFillAfter(true);
		scale.setDuration(1000);
		scale.setRepeatCount(ScaleAnimation.INFINITE);
		scale.setRepeatMode(ScaleAnimation.REVERSE);
		super.startAnimation(scale);
	}
	
	public void lockOption()
	{
		super.setBackgroundResource(R.drawable.button_option_locked);
	}
	
	public void displayCorrect()
	{
		super.setBackgroundResource(R.drawable.button_option_correct);
	}
	
	public void displayIncorrect()
	{
		super.setBackgroundResource(R.drawable.button_option_wrong);
	}
}
