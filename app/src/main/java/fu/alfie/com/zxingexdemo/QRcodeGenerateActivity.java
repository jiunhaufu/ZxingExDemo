package fu.alfie.com.zxingexdemo;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

import static fu.alfie.com.zxingexdemo.ZxingUtil.encodeAsBitmap;

public class QRcodeGenerateActivity extends AppCompatActivity {

    private String source_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(true,true);
        setContentView(R.layout.activity_qrcode_generate);

        source_text = getIntent().getStringExtra("source_text");

        String[] encodeType = {"CODE_39", "CODE_93", "CODE_128", "CODE_128B", "QR_CODE"};
        Spinner spinner = (Spinner)findViewById(R.id.spinner) ;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,encodeType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
//        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                switch (item){
                    case "CODE_39":
                        codeGenerate(source_text, BarcodeFormat.CODE_39, 900, 300);
                        break;
                    case "CODE_93":
                        codeGenerate(source_text, BarcodeFormat.CODE_93, 900, 300);
                        break;
                    case "CODE_128":
                        codeGenerate(source_text, BarcodeFormat.CODE_128, 900, 300);
                        break;
                    case "CODE_128B":
                        Code128 code = new Code128(QRcodeGenerateActivity.this);
                        code.setData(source_text);
                        Bitmap bitmap = code.getBitmap(900, 300);
                        ImageView ivBarcode = (ImageView)findViewById(R.id.imageView);
                        ivBarcode.setImageBitmap(bitmap);
                        break;
                    case "QR_CODE":
                        codeGenerate(source_text, BarcodeFormat.QR_CODE, 500, 500);
                        break;
                    default:
                        codeGenerate(source_text, BarcodeFormat.QR_CODE, 500, 500);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void fullScreen(Boolean title, Boolean actionbar){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Remove action bar
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        if (getSupportActionBar() != null && actionbar) getSupportActionBar().hide();
        //Remove notification bar
        if (title) getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void codeGenerate(String source_text,BarcodeFormat type, int width, int height) {
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        try {
            imageView.setImageBitmap(encodeAsBitmap(source_text, type, width, height));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
