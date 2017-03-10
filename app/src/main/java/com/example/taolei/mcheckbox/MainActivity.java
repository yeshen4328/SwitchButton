package com.example.taolei.mcheckbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public boolean click = false;
    McheckBox mcheckBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mcheckBox = (McheckBox)findViewById(R.id.cb);
        mcheckBox.setOnClickListener(new OnclickCheckbox());
    }
    public class OnclickCheckbox implements View.OnClickListener
    {
        public void onClick(View view)
        {
            mcheckBox.triger();

            Log.i("toast", Boolean.toString(mcheckBox.getStat()));
        }
    }
}
