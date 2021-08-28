package custom_views;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.chaturvedi.kbc_prakalpa.R;

public class QuestionView extends TextView
{
	private int VIEW_WIDTH = 500;
	private int MIN_VIEW_HEIGHT = 200;
	public QuestionView(Context context)
	{
		super(context);
		customizeView(context);
	}
	
	public QuestionView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		customizeView(context);
	}
	
	private void customizeView(Context context)
	{
		super.setWidth(VIEW_WIDTH);
		super.setMinHeight(MIN_VIEW_HEIGHT);
		super.setBackgroundResource(R.drawable.view_question);
		super.setTextColor(Color.WHITE);
	}
}
