package com.zwj.testandroidserver;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * <b>Create Date:</b> 2018/1/31<br>
 * <b>Author:</b> Zhongwenjie <br>
 * <b>Description:</b>
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    private ImageView backImg;
    private ImageView upImg;
    private Bitmap alterBitmap;
    private Canvas canvas;
    private Paint paint;
    private final static String TAG="MainActivity";

    private Button guaguaka_btn;
    private Button strip_btn;
    private Button pie_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setData ();
          initview();
         initEvent();
    }
    private void setData (){
        //改变图片大小
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 1;

        backImg =  findViewById(R.id.back_img);
        upImg = findViewById(R.id.up_img);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        Log.e(TAG," screen  width---"+width+",screen  height----"+height);


        //只读的图片
        Bitmap back = BitmapFactory.decodeResource(getResources(), R.drawable.set_women,opts);
        Bitmap up = BitmapFactory.decodeResource(getResources(), R.drawable.get_women,opts);
        //可以修改的空白的Bitmap
        alterBitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);

        Log.e(TAG,"width---"+up.getWidth()+",height----"+up.getHeight());
        Log.e(TAG,"alterBitmap  width---"+alterBitmap.getWidth()+",alterBitmap  height----"+alterBitmap.getHeight());
        //将alterBitmap作为画布,然后将Bitmap up画到画布上。
        canvas = new Canvas(alterBitmap);
        paint = new Paint();
        paint.setStrokeWidth(50);
        paint.setColor(Color.BLACK);

        Rect src = new Rect(0,0,up.getWidth(),up.getHeight());

        Rect dst=new Rect(0,0,width,height);
        canvas.drawBitmap(up,src,dst ,paint);


        backImg.setImageBitmap(back);
        upImg.setImageBitmap(alterBitmap);


        upImg.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        int x = (int) event.getX();
                        int y = (int) event.getY();
//                        Log.e("x:",x+"");
//                        Log.e("y:",y+"");
                        for(int i=-20; i<20; i++){
                            for(int j=-20; j<20; j++){
                                //更改画布上该像素点的颜色
                                alterBitmap.setPixel(i+x,j+y, Color.TRANSPARENT);
                            }
                        }
                        //重新绘制到ImageView上面
                        upImg.setImageBitmap(alterBitmap);
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }


    private void initview(){
        guaguaka_btn = findViewById(R.id.guaguaka_btn);
        strip_btn = findViewById(R.id.strip_btn);
        pie_btn = findViewById(R.id.pie_btn);
    }
    private void initEvent(){
        guaguaka_btn.setOnClickListener(this);
        strip_btn.setOnClickListener(this);
        pie_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == guaguaka_btn){//跳转到刮刮卡页面
            Intent intent = new Intent(MainActivity.this, GuaGuaKaActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view == strip_btn){//跳转到美女脱衣界面
            Intent intent = new Intent(MainActivity.this, StripActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }else if (view == pie_btn){//饼状图界面
            Intent intent = new Intent(MainActivity.this, PieViewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }
}
