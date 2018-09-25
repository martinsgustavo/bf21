package com.claudebernard.projetbf21.view;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.claudebernard.projetbf21.R;

public class DialogFAQ extends Dialog {

    private Activity _activity;
    private Button _yes, _no;


    //=====
    public DialogFAQ(Activity a) {
        super(a);
        this._activity = a;
    }

    //=====
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dialog_faq);


        this.setCanceledOnTouchOutside(false);
        loadButtonOK();
    }

    //=====
    public void loadButtonOK(){
        Button _ok = (Button) findViewById(R.id.btn_ok);

        _ok.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
