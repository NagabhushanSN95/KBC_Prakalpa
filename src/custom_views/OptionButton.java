// Shree KRISHNAya Namaha
// Author: Nagabhushan S N

package custom_views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.chaturvedi.kbc_prakalpa.R;

public class OptionButton extends Button
{
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
		super.setBackgroundResource(R.drawable.option_button_background);
	}
	
	public void lockOption()
	{
		super.setBackgroundResource(R.drawable.option_button_background_locked);
	}
	
	public void displayCorrect()
	{
		super.setBackgroundResource(R.drawable.option_button_background_correct);
	}
	
	public void displayIncorrect()
	{
		super.setBackgroundResource(R.drawable.option_button_background_incorrect);
	}
}
