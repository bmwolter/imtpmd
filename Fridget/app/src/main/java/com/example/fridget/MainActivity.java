package com.example.fridget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private Button scanBtn;
    private String scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scanBtn = (Button)findViewById(R.id.scan_button);

        scanBtn.setOnClickListener(this);

    }

    public void onClick(View v){
        if(v.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();

            scanResult = scanContent;
            updateContent();
        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
                    "No scan data received!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void updateContent(){

        final TextView tv_merk = findViewById(R.id.merk_naam);
        final TextView tv_product = findViewById(R.id.product_naam);
        final TextView tv_ean = findViewById(R.id.ean_code);

        NetworkManager.getInstance(this).getRequest("http://78.141.209.120/api/" + scanResult, new VolleyCallBack(){
            @Override
            public void onSucces(String result) {
                Gson gson = new Gson();
                Product c = gson.fromJson(result, Product.class);
                tv_merk.setText(c.getMerkNaam());
                tv_product.setText(c.getProductnaam());
                tv_ean.setText(c.getEanCode());

            }
        });
    }


}
