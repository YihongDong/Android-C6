package com.example.a79136.kh5c;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

/**
 * @author yangyu
 *	�����������ڶ���ʵ�ַ�ʽ,Activityʵ�ַ�ʽ
 */
public class CustomTitleActivity02 extends Activity {
	//����������ϵİ�ť
	private ImageButton titleBtn;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_title);
		
		initView();			
	}
	
	/**
	 * ��ʼ�����
	 */
	private void initView(){
		//ʵ������������ť�����ü���
		titleBtn = (ImageButton) findViewById(R.id.title_btn);
		titleBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(CustomTitleActivity02.this,DialogActivity.class));
			}
		});						
	}	
		
}
