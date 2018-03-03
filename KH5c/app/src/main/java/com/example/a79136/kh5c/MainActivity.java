package com.example.a79136.kh5c;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * @author yangyu
 *	������������Activity�࣬����������
 */
public class MainActivity extends Activity implements OnClickListener {
	//���尴ť
	private Button mainBtn01,mainBtn02;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
						
		initView();		
	}

	/**
	 * ��ʼ�����
	 */
	private void initView(){
		//�õ���ť�����ü����¼�
		mainBtn01 = (Button)findViewById(R.id.main_btn01);
		mainBtn02 = (Button)findViewById(R.id.main_btn02);		
		
		mainBtn01.setOnClickListener(this);
		mainBtn02.setOnClickListener(this);
	}	
		
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.main_btn01:
			startActivity(new Intent(MainActivity.this,CustomTitleActivity01.class));
			break;
		case R.id.main_btn02:
			startActivity(new Intent(MainActivity.this,CustomTitleActivity02.class));
			break;				
		default:
			break;
		}		
	}
	
}
