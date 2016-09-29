package cn.xsk.polygonview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by xsk on 2016/9/27.
 */

public class RegularPolygonView extends View {

    private static final int DEFAULT_N = 4;
    private static final String[] COLORS = {"#d9ddf0" , "#a5b1d9" , "#7c8abb" , "#404e86"};

    public RegularPolygonView(Context context) {
        super(context);
    }

    public RegularPolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RegularPolygonView , 0 ,0);
        n = typedArray.getInt(R.styleable.RegularPolygonView_n , DEFAULT_N);
    }

    public RegularPolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint = new Paint();
    private Path path = new Path();
    private int n;
    private float x, y, r;
    private double piDouble = Math.PI * 2;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        x = getWidth() /2;
        y = getHeight()/2;
        r = getWidth()/2;
        // TODO: 2016/9/29 待完善
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);

        float tempR = r;
        //绘制每一个区域
        for (int i = 0; i < 4; i++) {
            drawArea(canvas , tempR , COLORS[i]);
            tempR -= r / 4;
        }

        //绘制对角线
        paint.setColor(Color.parseColor("#c9c8d9"));
        paint.setStrokeWidth(2);
        for (double i = 0; i < piDouble; i = i + (piDouble / n)) {
            float ansX = (float) (r * Math.cos(i) + x);
            float ansY = (float) (r * Math.sin(i) + y);
            canvas.drawLine(x , y , ansX , ansY , paint);
        }

        //绘制能力值
        path.rewind();
        for (int i = 0; i < n; i++) {
            double angel = piDouble * i/n;
            tempR = (float) (Math.random() * r);
            float ansX = (float) (tempR * Math.cos(angel) + x);
            float ansY = (float) (tempR * Math.sin(angel) + y);
            if (i == 0){
                path.moveTo(ansX , ansY);
            }else {
                path.lineTo(ansX , ansY);
            }
        }

        path.close();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#a97072"));
        paint.setStrokeWidth(5);
        canvas.drawPath(path , paint);
    }

    public void setN(int n){
        this.n = n;
        invalidate();
    }

    private void drawArea(Canvas canvas , float r , String color){
        canvas.save();
        path.rewind();
        for (double i = 0; i < piDouble; i = i + (piDouble / n)) {
            float ansX = (float) (r * Math.cos(i) + x);
            float ansY = (float) (r * Math.sin(i) + y);
            if (i == 0){
                path.moveTo(ansX , ansY);
            }else {
                path.lineTo(ansX , ansY);
            }
        }

        path.close();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor(color));
        canvas.drawPath(path , paint);

        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#c9c8d9"));
        paint.setStrokeWidth(2);
        canvas.drawPath(path , paint);
        canvas.restore();
    }
}
