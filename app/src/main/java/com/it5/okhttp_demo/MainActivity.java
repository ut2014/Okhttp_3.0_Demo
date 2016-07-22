package com.it5.okhttp_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.it5.okhttp_demo.https.HttpsSSL;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getRequest();
//        postRequest();
//        download_tb();
//        Access_Header();
//        get_json1();
        upload();
    }

    private void getRequest() {
        OkhttpDemo okhttpDemo = new OkhttpDemo();
        okhttpDemo.getRequest();
    }

    private void postRequest() {
        OkhttpDemo okhttpDemo = new OkhttpDemo();
        okhttpDemo.postRequest();
    }

    //同步下载
    private void download_tb() {
        final OkhttpDemo okhttpDemo = new OkhttpDemo();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        okhttpDemo.download_tb();
                    }
                }
        ).start();

    }

    private void Access_Header() {
        final OkhttpDemo okhttpDemo = new OkhttpDemo();
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        okhttpDemo.accessHeaders(OkhttpDemo.client);
                    }
                }
        ).start();

    }

    private void get_SSl() {//ssl https
        HttpsSSL customTrust = new HttpsSSL(this);
        try {
//            customTrust.run();
            customTrust.run_all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void get_json() {
        OkhttpJson okhttpJson = new OkhttpJson();
        try {
            okhttpJson.run_json();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void get_json1() {
        OkhttpJson okhttpJson = new OkhttpJson();
        try {
            okhttpJson.run_json1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void upload() {//上传图像
        String filePath = "/sdcard/download/com.andbase/tp.png";//head.png
        String newPath = "/sdcard/download/com.andbase/tp.jpg";
        /*if (!fileIsExists(newPath)) {
            try {
                new File(newPath).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        if (!fileIsExists(filePath)) {
            Log.e("file: ", "file no found!!");
            return;
        }
        Bitmap bit = BitmapFactory.decodeFile(filePath);
//        ((ImageView)findViewById(R.id.img)).setImageBitmap(bit);
//        saveJPG_After(bit,newPath);
//        Bitmap newBit=codec(bit, Bitmap.CompressFormat.JPEG,0);
//        Bitmap newBit=compressBitmapJPG(bit,100);
        Bitmap newBit = createBit(bit);
        ((ImageView) findViewById(R.id.img)).setImageBitmap(newBit);
//        Retrofit_Demo.upload(newPath);

       /* if (!fileIsExists(newPath)){
            Log.e("file: ","file no found!!");
            return;
        }*/
    }

    /**
     * 质量压缩
     *
     * @param image
     * @param maxkb
     * @return
     * @author ping 2015-1-5 下午1:29:58
     */
    public static Bitmap compressBitmapJPG(Bitmap image, int maxkb) {
       /* Bitmap output = Bitmap.createBitmap(image.getWidth(), image
                .getHeight(), Bitmap.Config.RGB_565);*/
        //L.showlog(压缩图片);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 50, baos);// 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
//      Log.i(test,原始大小 + baos.toByteArray().length);
/*        while (baos.toByteArray().length / 1024 > maxkb) { // 循环判断如果压缩后图片是否大于(maxkb)50kb,大于继续压缩
//          Log.i(test,压缩一次!);
            baos.reset();// 重置baos即清空baos
            options -= 10;// 每次都减少10
            image.compress(Bitmap.CompressFormat.PNG, options, baos);// 这里压缩options%，把压缩后的数据存放到baos中
        }*/
//      Log.i(test,压缩后大小 + baos.toByteArray().length);
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数据生成图片
        Canvas canvas = new Canvas();
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        return bitmap;
    }

    private static Bitmap createBit(Bitmap image) {
       /* Bitmap out=Bitmap.createBitmap(image.getWidth(), image
                .getHeight(), Bitmap.Config.ARGB_8888);*/
        Bitmap out=image.copy(Bitmap.Config.ARGB_8888,true);
        // 建立Paint 物件
        Paint vPaint = new Paint();
//        vPaint .setStyle( Paint.Style.STROKE );   //空心
//        vPaint .setAlpha(75);   // Bitmap透明度(0 ~ 100)
        Canvas canvas=new Canvas(out);
       /* canvas.drawBitmap ( image , 50, 100, null );  //无透明
        canvas.drawBitmap ( image , 50, 200, vPaint );  //有透明*/
        /*int[] color=new int[]{Color.WHITE};
        Bitmap output = Bitmap.createBitmap(color, image.getWidth(), image
                .getHeight(), RGB_565);*/
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(image, 0, 0, null);
//        vPaint.setColor(Color.GREEN);
        //从原位图中提取只包含alpha的位图
//        Bitmap alphaBitmap = image.extractAlpha();
//        canvas.drawBitmap(alphaBitmap, 0, 0, vPaint);
//        image.recycle();
        return codec(out, Bitmap.CompressFormat.JPEG,100);
    }

    private static Bitmap codec(Bitmap src, Bitmap.CompressFormat format,
                                int quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(format, quality, os);

        byte[] array = os.toByteArray();
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }

    /**
     * 把bitmap,png格式的图片 转换成jpg图片
     * 因jpg不支持透明，如png透明图片，则转成白底！
     * @param bitmap  源图
     * @param newFilepath 新图片的路径
     */
    public static void saveJPG_After(Bitmap bitmap, String newFilepath) {
        //复制Bitmap  因为png可以为透明，jpg不支持透明，把透明底明变成白色
        Bitmap outB=bitmap.copy(Bitmap.Config.ARGB_8888,true);
        Canvas canvas=new Canvas(outB);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);

        File file = new File(newFilepath);
        try {
            FileOutputStream out = new FileOutputStream(file);
            if (outB.compress(Bitmap.CompressFormat.JPEG, 100, out)) {
                out.flush();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    //判断文件是否存在
    public boolean fileIsExists(String strFile) {
        try {
            File f = new File(strFile);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }

        return true;
    }
}
