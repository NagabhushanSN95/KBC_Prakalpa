package custom_views;

import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.widget.TextView;

import com.chaturvedi.kbc_prakalpa.R;

public class CountdownClock extends TextView
{
	private int CLOCK_WIDTH = 100;
	private int CLOCK_HEIGHT = 100;
	
	private CountDownTimer timer;
	public CountdownClock(Context context)
	{
		super(context);
		customizeView();
	}

	public CountdownClock(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		customizeView();
	}

	private void customizeView()
	{
		super.setWidth(CLOCK_WIDTH);
		super.setHeight(CLOCK_HEIGHT);
		super.setTextColor(Color.WHITE);
		super.setBackgroundResource(R.drawable.countdown_clock);
	}
	
	public void startTimer(long millis)
	{
		super.setText(""+millis/1000);
		
		timer = new CountDownTimer(millis, 1000)
		{
			public void onTick(long millisUntilFinished)
			{
				CountdownClock.this.setText(""+millisUntilFinished/1000);
			}
			
			public void onFinish()
			{
				CountdownClock.this.setText("0");
			}
		};
		timer.start();
	}
	
	public void stopTimer()
	{
		timer.cancel();
	}
}
