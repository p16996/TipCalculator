package com.example.psath.tipcalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.etBillAmount)
    EditText etBillAmount;
    @BindView(R.id.tipPercent)
    TextView tipPercent;
    @BindView(R.id.tipTotal)
    TextView tipTotal;
    @BindView(R.id.billTotalAmount)
    TextView billTotalAmount;

    float percentage = 0;
    float tipAmount = 0;
    float finalAmount=0;
    float totalBillAmount=0;

    float regural_tip_percent = 10;
    float good_tip_percent = 15;
    float excellent_tip_percent = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setTipValues();
    }

    private void setTipValues() {
        tipPercent.setText(getString(R.string.tip_percent, percentage));
        tipTotal.setText(getString(R.string.tip_total, tipAmount));
        billTotalAmount.setText(getString(R.string.totalresult,finalAmount));
    }
    @OnClick({R.id.RegularService, R.id.excellentService, R.id.goodService})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.RegularService:
                percentage = regural_tip_percent;
                break;
            case R.id.excellentService:
                percentage = excellent_tip_percent;
                break;
            case R.id.goodService:
                percentage = good_tip_percent;
                break;
        }

        calculateFinalBill();
        setTipValues();
    }


    @OnTextChanged(R.id.etBillAmount)
    public void onTextChanged() {
        calculateFinalBill();
        setTipValues();
    }

    private void calculateFinalBill(){
        if(percentage==0){
            percentage=good_tip_percent;
        }
        if(!etBillAmount.getText().toString().equals("") && !etBillAmount.getText().toString().equals(".")){
            totalBillAmount = Float.valueOf(etBillAmount.getText().toString());
        }
        else
            totalBillAmount=0;

        tipAmount =(totalBillAmount * percentage)/100;
        finalAmount = totalBillAmount+tipAmount;

    }

}
