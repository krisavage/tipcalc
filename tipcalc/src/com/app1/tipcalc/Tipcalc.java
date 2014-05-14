package com.app1.tipcalc;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class Tipcalc extends Activity {
	
	private static final String TOTAL_BILL = "TOTAL_BILL";
	private static final String Current_TIP = "CURRENT_TIP";
	private static final String BILLWITHOUTTIP = "BILLWITHOUTTIP";
	
	private double billBeforeTip;
	private double tipAmount;
	private double finalBill;
	
	
	EditText billBeforeTipET;
	EditText tipAmountET;
	EditText finalBillET;
	
	SeekBar tipSeekBar;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipcalc);
		
		if(savedInstanceState == null){
			billBeforeTip = 0.0;
			tipAmount = .15;
			finalBill = 0.0; 
		}
		else {
			
			billBeforeTip = savedInstanceState.getDouble(BILLWITHOUTTIP);
			tipAmount = savedInstanceState.getDouble(Current_TIP);
			finalBill = savedInstanceState.getDouble(TOTAL_BILL); 
			
		}
		
		billBeforeTipET = (EditText) findViewById(R.id.Billedittext);
		tipAmountET = (EditText) findViewById(R.id.Tipedittext);
	    finalBillET = (EditText) findViewById(R.id.totaledittext);
	
	    tipSeekBar = (SeekBar) findViewById(R.id.seekbaredittext);
	    
	    tipSeekBar.setOnSeekBarChangeListener(tipSeekBarListener);
	    
	    billBeforeTipET.addTextChangedListener(billBeforeTipListener);
	    
	    
	}

	private TextWatcher billBeforeTipListener = new TextWatcher(){

		@Override
		public void afterTextChanged(Editable arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onTextChanged(CharSequence arg0, int arg1, int arg2,
				int arg3) {
			// TODO Auto-generated method stub
			
			try{
				billBeforeTip = Double.parseDouble(arg0.toString());
				
			}
			catch(NumberFormatException e){
				billBeforeTip = 0.0;
			}
			
			updateTipAndFinalBill();
			
			}
			
		
		
	};
	
	private void updateTipAndFinalBill(){
		
		double tipAmount = Double.parseDouble(tipAmountET.getText().toString());
		
		double finalBill = billBeforeTip + (billBeforeTip * tipAmount);
		
		finalBillET.setText(String.format("%.02f", finalBill));
	}
	
	
	protected void onSaveInstanceState(Bundle outState){
		
		super.onSaveInstanceState(outState);
		
		outState.putDouble(TOTAL_BILL, finalBill);
		outState.putDouble(Current_TIP, finalBill);
		outState.putDouble(BILLWITHOUTTIP, finalBill);

	}

	private OnSeekBarChangeListener tipSeekBarListener = new OnSeekBarChangeListener(){

		@Override
		public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			// TODO Auto-generated method stub
			
			tipAmount = (tipSeekBar.getProgress()) *.01;
			tipAmountET.setText(String.format("%.02f", tipAmount));
			
			updateTipAndFinalBill();
		}

		@Override
		public void onStartTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar arg0) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tipcalc, menu);
		return true;
	}

}
