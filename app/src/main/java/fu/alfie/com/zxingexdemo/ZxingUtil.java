package fu.alfie.com.zxingexdemo;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jiunhau.Fu on 2017/10/24.
 */

public class ZxingUtil {

    public static Bitmap encodeAsBitmap(String contents, BarcodeFormat format, int desiredWidth, int desiredHeight) throws WriterException {
        if (contents.length() == 0) return null;

        HashMap<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 判斷編碼是否為UTF-8
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                break;
            }
        }
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        // 容錯率姑且可以將它想像成解析度，分為 4 級：L(7%)，M(15%)，Q(25%)，H(30%)
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 白色邊界
        hints.put(EncodeHintType.MARGIN, 1);

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = writer.encode(contents, format, desiredWidth, desiredHeight, hints);
        Bitmap bitmap = Bitmap.createBitmap(desiredWidth, desiredHeight, Bitmap.Config.ARGB_8888);
        // 建立 QR code 的資料矩陣
        for (int y = 0; y < desiredHeight; y++) {
            for (int x = 0; x < desiredWidth; x++) {
                bitmap.setPixel(x, y, result.get(x, y) ? Color.BLACK : Color.WHITE); //條碼為黑字白底
            }
        }
        //建立點陣圖
        return bitmap;
    }

}
