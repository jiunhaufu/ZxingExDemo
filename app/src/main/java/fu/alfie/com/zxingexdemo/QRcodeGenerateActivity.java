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
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import static fu.alfie.com.zxingexdemo.ZxingUtil.encodeAsBitmap;

public class QRcodeGenerateActivity extends AppCompatActivity {

    private String source_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fullScreen(true,true);
        setContentView(R.layout.activity_qrcode_generate);

        source_text = getIntent().getStringExtra("source_text");

        String[] encodeType = {"EAN_8", "EAN_13", "CODE_39", "CODE_93", "CODE_128", "ITF", "PDF_417", "CODABAR", "UPC_A", "UPC_E", "UPC_EAN_EXTENSION", "DATA_MATRIX", "AZTEC", "RSS_14", "RSS_EXPANDED", "MAXICODE", "QR_CODE"};
        Spinner spinner = (Spinner)findViewById(R.id.spinner) ;
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,encodeType);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(encodeType.length-1, true);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                switch (item){
                    case "EAN_8":
                        codeGenerate(source_text, BarcodeFormat.EAN_8, 900, 300);
                        break;
                    case "EAN_13":
                        codeGenerate(source_text, BarcodeFormat.EAN_13, 900, 300);
                        break;
                    case "CODE_39":
                        codeGenerate(source_text, BarcodeFormat.CODE_39, 900, 300);
                        break;
                    case "CODE_93":
                        codeGenerate(source_text, BarcodeFormat.CODE_93, 900, 300);
                        break;
                    case "CODE_128":
                        codeGenerate(source_text, BarcodeFormat.CODE_128, 900, 300);
                        break;
                    case "ITF":
                        codeGenerate(source_text, BarcodeFormat.ITF, 900, 300);
                        break;
                    case "PDF_417":
                        codeGenerate(source_text, BarcodeFormat.PDF_417, 900, 300);
                        break;
                    case "CODABAR":
                        codeGenerate(source_text, BarcodeFormat.CODABAR, 900, 300);
                        break;
                    case "UPC_A":
                        codeGenerate(source_text, BarcodeFormat.UPC_A, 900, 300);
                        break;
                    case "UPC_E":
                        codeGenerate(source_text, BarcodeFormat.UPC_E, 900, 300);
                        break;
                    case "UPC_EAN_EXTENSION":
                        codeGenerate(source_text, BarcodeFormat.UPC_EAN_EXTENSION, 900, 300);
                        break;
                    case "DATA_MATRIX":
                        codeGenerate(source_text, BarcodeFormat.DATA_MATRIX, 900, 300);
                        break;
                    case "AZTEC":
                        codeGenerate(source_text, BarcodeFormat.AZTEC, 900, 300);
                        break;
                    case "MAXICODE":
                        codeGenerate(source_text, BarcodeFormat.MAXICODE, 900, 300);
                        break;
                    case "RSS_14":
                        codeGenerate(source_text, BarcodeFormat.RSS_14, 900, 300);
                        break;
                    case "RSS_EXPANDED":
                        codeGenerate(source_text, BarcodeFormat.RSS_EXPANDED, 900, 300);
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
            // ZXing 還可以生成其他形式條碼，如：BarcodeFormat.CODE_39、BarcodeFormat.CODE_93、BarcodeFormat.CODE_128、BarcodeFormat.EAN_8、BarcodeFormat.EAN_13
            imageView.setImageBitmap(encodeAsBitmap(source_text, type, width, height));
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}
