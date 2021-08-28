package custom_views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import com.chaturvedi.kbc_prakalpa.R;

public class QuitDialog extends Dialog
{

	public QuitDialog(Context context)
	{
		super(context);
		customizeDialog(context);
	}
	
	private void customizeDialog(Context context)
	{
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.dialog_quit);
		super.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		
		
		//Animation slideUp = AnimationUtils.loadAnimation(context, R.anim.dialog_slide_up);
		//slideUp.start();
	}
	
	@Override
	public void show()
	{
		super.show();
	}
	
	@Override
	public void dismiss()
	{
		super.dismiss();
	}
	
	public void setOnPlayListener(View.OnClickListener playListener)
	{
		SelectButton playButton = (SelectButton) findViewById(R.id.button_play);
		playButton.setOnClickListener(playListener);
	}
	
	public void setOnQuitListener(View.OnClickListener quitListener)
	{
		SelectButtonMini quitButton = (SelectButtonMini) findViewById(R.id.button_quit);
		quitButton.setOnClickListener(quitListener);
	}

}
