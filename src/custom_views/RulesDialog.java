package custom_views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.chaturvedi.kbc_prakalpa.R;

public class RulesDialog extends Dialog
{
	private String rules = "01) There are 3-levels, 2-questions in each level.\n" +
			"02) You have 30seconds to answer in 1st level, 60 in 2nd and 90 in 3rd.\n" +
			"03) If you answer all the questions correctly within time, you'll get a Melody Chocolate.\n" +
			"04) Once an option is touched, it'll be locked. So, Think Before You Ink.\n" +
			"05) The decision of the organizers is final and cannot be questioned.\n" +
			"So, Hurray, Start The Game.\n";
	
	public RulesDialog(Context context)
	{
		super(context);
		customizeDialog();
	}

	public RulesDialog(Context context, int theme)
	{
		super(context, theme);
		customizeDialog();
	}

	public RulesDialog(Context context, boolean cancelable, OnCancelListener cancelListener)
	{
		super(context, cancelable, cancelListener);
		customizeDialog();
	}
	
	private void customizeDialog()
	{
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.dialog_rules);
		super.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		super.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
		
		TextView rulesView = (TextView)findViewById(R.id.view_rules);
		rulesView.setText(rules);
	}
	
	public void setOnPlayListener(View.OnClickListener playListener)
	{
		SelectButton playButton = (SelectButton) findViewById(R.id.button_play);
		playButton.setOnClickListener(playListener);
	}

}
