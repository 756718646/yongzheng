package com.interest.iWen.view;

import com.interest.iWen.R;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

/**
 * ÕÀ≥ˆÃ· æ
 * @author yongzheng
 *
 */
public class ExitDialog extends DialogFragment implements OnClickListener{

	
	
	private TextView title;
	private TextView content;
	private Button but1;
	private Button but2;
	private Callback callback;
	
	public interface Callback{
		public void but1Event(View v);
		public void but2Event(View v);
		
	}
	
	public ExitDialog(Callback callback){
		this.callback = callback;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, 
			@Nullable Bundle savedInstanceState) {
		getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
		View view = inflater.inflate(R.layout.pop_answer_exit, container);
		
		title = (TextView)view.findViewById(R.id.title);
		content = (TextView)view.findViewById(R.id.content);
		but1 = (Button)view.findViewById(R.id.but1);
		but2 = (Button)view.findViewById(R.id.but2);
		
		but1.setOnClickListener(this);
		but2.setOnClickListener(this);
		return view;
	}
	
	public void setTitle(String title){
		this.title.setText(title);
	}
	
	public void setContent(String content){
		this.content.setText(content);
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.but1:
			callback.but1Event(v);
			break;
		case R.id.but2:
			callback.but2Event(v);
			break;
		}
	}




}
