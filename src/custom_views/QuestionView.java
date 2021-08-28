package custom_views;

import com.chaturvedi.kbc_prakalpa.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

public class QuestionView extends TextView
{
	public QuestionView(Context context)
	{
		super(context);
		customizeView();
	}
	
	public QuestionView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		customizeView();
	}
	
	private void customizeView()
	{
		this.setBackgroundResource(R.drawable.question_view_background);
	}
}
