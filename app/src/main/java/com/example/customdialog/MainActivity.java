package com.example.customdialog;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText edtInput;
    private Button btnEdit;
    private edttxtpopup editDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEdit = findViewById(R.id.btnEdit);
        edtInput = findViewById(R.id.edtInput);
        btnEdit.setOnClickListener(new btnEditListner());
    }

    interface inputText {
        void onEditClick(MainActivity Main);
    }

    private class btnEditListner implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            editDialog = new edttxtpopup(MainActivity.this, edtInput.getText().toString(),new DoneActions());
            editDialog.setOnDoneListner(new DoneActions());
            editDialog.show();
            Log.e("Crash","ShowMethod");

        }
    }

    class DoneActions implements edttxtpopup.OnDoneListner{
        @Override
        public void onDoneListner(edttxtpopup EdtPopup, String strEditedInput) {

            edtInput.setText(strEditedInput+"");
            editDialog.dismiss();
        }
    }
}