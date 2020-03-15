package tw.org.iii.brad.brad07;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MyView extends View {
    private Bitmap ballBmp;
    private MainActivity activity;
    private Resources resources;
    private Paint paint;
    private  int vieww,viewh;
    private  float ballw,ballh,ballx,bally,dx,dy;
    private  boolean isinit;
    private Timer timer;
    private GestureDetector gd;//手勢偵測
    public MyView(Context context) {
        super(context);
        setBackgroundResource(R.drawable.bg);

        //(MainActivity) = context 兩個物件是一樣的
        activity =(MainActivity)context;
        resources = activity.getResources();

        timer = new Timer();

       gd = new GestureDetector(new MyGDListener());

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float ex = event.getX(),ey = event.getY();
        if (ex>=1567&&ex<=2000&&ey>=650&&ey<=1000){//右下角給予按鈕寫法
            if (event.getAction() ==MotionEvent.ACTION_DOWN){

            }else if (event.getAction() ==MotionEvent.ACTION_MOVE){

            }else if(event.getAction() == MotionEvent.ACTION_UP){

            }
        }
//        return gd.onTouchEvent(event);//手勢偵測
        return true;
    }

    private  class MyGDListener extends GestureDetector.SimpleOnGestureListener{
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            Log.v("brad",velocityX+"x"+velocityY);
            if (Math.abs(velocityX)>Math.abs(velocityY)){
                //左右
                //0太敏感,使用100
                if (velocityX>100){
                    //右
                }
                if (velocityX<100){
                    //左
                }
            }
            if (Math.abs(velocityX)<Math.abs(velocityY)){
                //上下
                if (velocityY>100){
                    //下
                }
                if (velocityY<100){
                    //上
                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDown(MotionEvent e) {
//            Log.v("brad2","onDown");
            return true;
        }
    }
    private void init(){
        isinit =true;
        ballBmp = BitmapFactory.decodeResource(resources,R.drawable.pokeball);
        paint = new Paint();
//        paint.setAlpha(127);//圖片透明度
        viewh =getHeight();vieww = getWidth();
        ballw =vieww/20f;ballh =ballw;

        Matrix matrix = new Matrix();
        matrix.postScale(ballw/ballBmp.getWidth(),ballh/ballBmp.getHeight());

                ballBmp = Bitmap.createBitmap(ballBmp,0,0,
                ballBmp.getWidth(),ballBmp.getHeight(),matrix,false
        );

        ballx = bally = 100;//給予球一開始的位置
        dx = dy =8;
        timer.schedule(new BallTask(),1*1000,100);
        timer.schedule(new RefreshView(),0,17);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!isinit)init();
        canvas.drawBitmap(ballBmp,ballx,bally,paint);
        //canvas.drawBitmap();
    }
    private class RefreshView extends TimerTask{
        @Override
        public void run() {
            postInvalidate();
        }
    }
    private  class BallTask extends TimerTask{
        @Override
        public void run() {
            if (ballx<0||ballx+ballw>vieww){
                dx *=-1;
            }
            if (bally<0||bally+ballh>viewh){
                dy*=-1;
            }
            ballx +=dx;
            bally +=dy;
            postInvalidate();
        }
    }}
