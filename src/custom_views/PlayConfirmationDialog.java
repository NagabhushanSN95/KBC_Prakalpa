package custom_views;

import com.chaturvedi.kbc_prakalpa.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

public class PlayConfirmationDialog extends Dialog
{
	public PlayConfirmationDialog(Context context)
	{
		super(context);
		customizeDialog();
	}
	
	private void customizeDialog()
	{
		super.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.setContentView(R.layout.dialog_play_confirmation);
		super.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
		super.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
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
