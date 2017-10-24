package fu.alfie.com.zxingexdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
}
