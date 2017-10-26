package fu.alfie.com.zxingexdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private String source_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        source_text = "A123456789";
    }

    public void onQRcodeGenerateClick(View view){
        startActivity(new Intent(this,QRcodeGenerateActivity.class).putExtra("source_text",source_text));
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////


    public void onQRcodeScanClick(View view){
        new IntentIntegrator(this)
//                .setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES) //只掃bar_code，不設定時什麼都掃
                .setPrompt("Scan a barcode")//提示文字
                .setCameraId(0)//後攝影機
                .setBeepEnabled(false)//是否要有音效
                .setBarcodeImageEnabled(true)//保留掃描成功的截圖
                .setOrientationLocked(false)//鎖定方向
                .initiateScan();//只留這個就可全部用預設
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Scanned_text: " + result.getContents(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "Scanned_format: " + result.getFormatName(), Toast.LENGTH_LONG).show();

                File imgFile = new  File(result.getBarcodeImagePath());
                if(imgFile.exists()){
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    ImageView imageView = (ImageView) findViewById(R.id.imageView2);
                    imageView.setImageBitmap(myBitmap);
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
