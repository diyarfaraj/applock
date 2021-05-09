package com.example.applock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

public class PinLock extends AppCompatActivity {
    PinLockView mPinLockView;
    IndicatorDots mIndicatorDots;
    String _pin = "";
    boolean isPinConfirm = false;
    TextView enter_pin;
    Button close_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_lock);
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mPinLockView.setPinLockListener(mPinLockListener);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);
        enter_pin = (TextView) findViewById(R.id.enter_pin);
        mIndicatorDots.setPinLength(0);
        close_btn = findViewById(R.id.close_btn);
        // mPinLockView.attachIndicatorDots(mIndicatorDots);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);

            }
        });
    }


    private PinLockListener mPinLockListener = new PinLockListener() {
        @Override
        public void onComplete(String pin) {
            Log.d("PinLock", "Pin complete: " + pin);
            if(isPinConfirm){
                if(_pin.equalsIgnoreCase(pin)){
                    setResult(RESULT_OK);
                    finish();
                } else{
                    Toast.makeText(PinLock.this, "Pin is not matching", Toast.LENGTH_SHORT).show();
                    mIndicatorDots.setPinLength(0);
                    isPinConfirm = false;
                    mPinLockView.resetPinLockView();
                    enter_pin.setText("Enter pin");

                }
            } else {
                _pin = pin;
                isPinConfirm = true;
                mIndicatorDots.setPinLength(0);
                mPinLockView.resetPinLockView();
                enter_pin.setText("Confirm Pin");
            }

        }

        @Override
        public void onEmpty() {
            Log.d("PinLock", "Pin empty");
            mIndicatorDots.setPinLength(0);

        }

        @Override
        public void onPinChange(int pinLength, String intermediatePin) {
            Log.d("PinLock", "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            mIndicatorDots.setPinLength(pinLength);

        }
    };
}