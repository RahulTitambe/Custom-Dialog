package com.example.customdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

class edttxtpopup extends Dialog {

    private TextView txtEditDialog;
    private RadioButton rdBtnUppercase, rdBtnLowercase, rdBtnInitCap;
    private CheckBox chbxReverseText;
    private Button btnDone;
    private Context context;
    private String Input;


    public interface OnDoneListner{
        void onDone(edttxtpopup EdtPopup);
    }

    private OnDoneListner onDoneListner = null;
    public void setOnDoneListner(OnDoneListner onDoneListner){
        this.onDoneListner = onDoneListner;
    }

    edttxtpopup(Context context, String input){
    super(context);
    this.context = context;
    setContentView(R.layout.activity_edttxt_popup);

    init();

    this.Input=input;

        txtEditDialog.setText(input);

        rdBtnUppercase.setOnClickListener(new RdBtnUppercaseListner());
        rdBtnLowercase.setOnClickListener(new RdBtnLowercaseListner());
        rdBtnInitCap.setOnClickListener(new RdBtnInitcapListner());
        btnDone.setOnClickListener(new BtnDoneListner());

    }

    private class RdBtnUppercaseListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (rdBtnUppercase.isChecked()) {
                rdBtnLowercase.setChecked(false);
                rdBtnInitCap.setChecked(false);

            }
        }
    }

    private class RdBtnLowercaseListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (rdBtnLowercase.isChecked()) {
                rdBtnUppercase.setChecked(false);
                rdBtnInitCap.setChecked(false);

            }
        }
    }

    private class RdBtnInitcapListner implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (rdBtnInitCap.isChecked()) {
                rdBtnLowercase.setChecked(false);
                rdBtnUppercase.setChecked(false);
            }
        }
    }

    private class BtnDoneListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            String textViewText = txtEditDialog.getText().toString();
            if (rdBtnUppercase.isChecked()){
                txtEditDialog.setAllCaps(true);
            }

            else if (rdBtnLowercase.isChecked()){
                txtEditDialog.setText(textViewText.toLowerCase(Locale.ROOT));
            }

            else if (rdBtnInitCap.isChecked()){
                String str;
                String[] strArray = txtEditDialog.getText().toString().split(" ");
                StringBuilder builder = new StringBuilder();
                for (String s : strArray) {
                    String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                    builder.append(cap + " ");
                }
                txtEditDialog.setText(builder.toString());
            }
            if(chbxReverseText.isChecked()){
                StringBuffer stringBuffer = new StringBuffer(textViewText);
                txtEditDialog.setText(stringBuffer.reverse());

            }
            onDoneListner.onDone(edttxtpopup.this);
        }
    }

    private void init(){

        txtEditDialog = findViewById(R.id.txtEditDialog);
        rdBtnUppercase = findViewById(R.id.rdBtnUppercase);
        rdBtnLowercase = findViewById(R.id.rdBtnLowercase);
        rdBtnInitCap = findViewById(R.id.rdBtnInitcap);
        chbxReverseText = findViewById(R.id.chReverseText);
        btnDone = findViewById(R.id.btnDone);
    }
}
