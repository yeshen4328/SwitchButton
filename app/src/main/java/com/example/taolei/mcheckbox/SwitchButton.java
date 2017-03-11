package com.example.taolei.mcheckbox;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import org.w3c.dom.Attr;

/**
 * Created by Tao Lei on 2017/3/10.
 */

public class SwitchButton extends McheckBox {
    public SwitchButton(Context context, AttributeSet attr)
    {
        super(context, attr);
        this.setOnClickListener(new SwitchButtonOnclick());
    }
    class SwitchButtonOnclick implements OnClickListener{
        public void onClick(View view)
        {
            SwitchButton.this.triger();
        }
    }
}
