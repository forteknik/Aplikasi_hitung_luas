package com.example.administrator.hitungluas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {
    EditText et_panjang, et_lebar, et_tinggi;
    Button bt_hitung;
    TextView tv_hasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_panjang = findViewById(R.id.et_panjang);
        et_lebar = findViewById(R.id.et_lebar);
        et_tinggi = findViewById(R.id.et_tinggi);
        bt_hitung = findViewById(R.id.bt_hitung);
        tv_hasil = findViewById(R.id.tv_hasil);

        bt_hitung.setOnClickListener(this);

        if(savedInstanceState !=null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tv_hasil.setText(result);
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId()== R.id.bt_hitung){
            String isi_panjang = et_panjang.getText().toString().trim();
            String isi_lebar = et_lebar.getText().toString().trim();
            String isi_tinggi = et_tinggi.getText().toString().trim();

            boolean nodata = false;
            boolean inputsalah= false;

            if (TextUtils.isEmpty(isi_lebar)){
                nodata=true;
                et_lebar.setText("Harap Di-Isi");
            }

            if (TextUtils.isEmpty(isi_panjang)){
                nodata=true;
                et_panjang.setText("Harap Isi Panjang");
            }
            if (TextUtils.isEmpty(isi_tinggi)){
                nodata=true;
                et_tinggi.setText("Harap isi Tinggi");
            }

            Double panjang = toDouble(isi_panjang);
            Double lebar = toDouble(isi_lebar);
            Double tinggi = toDouble(isi_panjang);

            if (panjang==null){
                inputsalah = true;
                et_panjang.setText("harap isi dengan benar");
            }
            if (lebar==null){
                inputsalah = true;
                et_lebar.setText("harap isi dengan benar");
            }
            if (tinggi==null){
                inputsalah = true;
                et_tinggi.setText("harap isi dengan benar");
            }
            if(!nodata && !inputsalah){
                double volume = panjang * lebar * tinggi;
                tv_hasil.setText(String.valueOf(volume));
            }
        }
    }
    Double toDouble(String str){
        try{
            return Double.valueOf(str);
            }catch (NumberFormatException e){
            return null;
        }
    }
    private static final String STATE_RESULT="state_result";
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, tv_hasil.getText().toString());

    }
}
