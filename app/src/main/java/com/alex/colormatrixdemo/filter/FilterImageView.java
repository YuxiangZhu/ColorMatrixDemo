package com.alex.colormatrixdemo.filter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.alex.colormatrixdemo.utils.Utils;

public class FilterImageView extends FilterView {

    public static final int DRAW_TYPE_MATRIX = 0;
    public static final int DRAW_TYPE_MASK = 1;
    protected int scaleWidth = 0;
    protected int scaleHeight = 0;
    private Bitmap bitmap;
    private Paint paint;
    private RectF rectF;
    private int drawType = 0;
    private ColorMatrixColorFilter colorMatrixColorFilter;
    private ColorMatrix colorMatrix;
    private int screenWidth = 0;
    private int screenHeight = 0;
    private int maxHeight = 0;
    private BlurMaskFilter blurMaskFilter;

    public FilterImageView(Context context) {
        this(context, null);
    }

    public FilterImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FilterImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //警用硬件加速
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        screenWidth = Utils.getScreenWidth(context)[0];
        screenHeight = Utils.getScreenWidth(context)[1];
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (bitmap != null) {
            setMeasuredDimension((int) (scaleWidth - 0.1), (int) (scaleHeight - 0.1));
            scaleBitmap();
            invalidate();
        }
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        this.bitmap = bitmap;
        scaleBitmap();
    }

    public void setMaxHight(int maxHeight) {
        this.maxHeight = maxHeight;
    }

    /**
     * @param light 0f -2f
     *              明亮度
     */
    public void changeLight(float light) {
        colorMatrix = new ColorMatrix();
        colorMatrix.setScale(light, light, light, 1f);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        invalidate();
    }

    /**
     * @param saturaction 0f - 2f
     *                    饱和度
     */
    public void changeSaturation(float saturaction) {
        colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(saturaction);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        invalidate();
    }

    /**
     * 修改色温
     *
     * @param hue
     */
    public void changeHue(float hue) {
        // mHueMatrix = new ColorMatrix();
        // mHueMatrix.setSaturation(hue);
        // // colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        // invalidate();

        colorMatrix = new ColorMatrix();
        colorMatrix.setScale(hue, hue, hue, 1);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        paint.reset();
        paint.setAntiAlias(true);
        if (bitmap == null || colorMatrix == null) {
            return;
        }
        switch (drawType) {
            case DRAW_TYPE_MATRIX:
                if (colorMatrixColorFilter != null) {
                    paint.setColorFilter(colorMatrixColorFilter);
                }
                break;
            case DRAW_TYPE_MASK:
                if (blurMaskFilter != null) {
                    paint.setMaskFilter(blurMaskFilter);
                }
                break;
        }
        canvas.drawBitmap(bitmap, null, rectF, paint);
        canvas.save();
    }

    @Override
    public Bitmap getChangeBitmap() {
        Bitmap bitmapAltered = Bitmap.createBitmap((int) rectF.right, (int) rectF.bottom, bitmap.getConfig());
        Canvas canvas = new Canvas(bitmapAltered);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColorFilter(colorMatrixColorFilter);
        if (drawType == DRAW_TYPE_MASK) {
            if (blurMaskFilter != null) {
                paint.setMaskFilter(blurMaskFilter);
            }
        }
        canvas.drawBitmap(bitmap, null, rectF, paint);
        return bitmapAltered;
    }

    @Override
    public void setFloat(float[] floats) {
        colorMatrix = new ColorMatrix();
        colorMatrix.set(floats);
        colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        invalidate();
    }

    public void setMaskWidth(int maskWidth) {
        drawType = DRAW_TYPE_MASK;
        if (maskWidth == 0) {
            blurMaskFilter = null;
        } else {
            blurMaskFilter = new BlurMaskFilter(maskWidth * 2, BlurMaskFilter.Blur.NORMAL);
        }
        invalidate();
    }

    @Override
    public void scaleBitmap() {
        if (bitmap == null) {
            return;
        }
        int bWidth = bitmap.getWidth();
        int bHeight = bitmap.getHeight();
        float scale = 0;
        if (bWidth > bHeight) {
            //最大宽为屏幕的宽度
            if (bWidth > screenWidth) {
                scale = bWidth * 1f / screenWidth * 1f;
                scaleHeight = (int) (bHeight * 1f / scale);
            } else {
                scale = screenWidth * 1f / bWidth * 1f;
                scaleHeight = (int) (bHeight * 1f * scale);
            }
            scaleWidth = screenWidth;
        } else if (bWidth < bHeight) {
            //最大高为屏幕的 5/6
            int maxHeight = screenHeight * 5 / 6;
            if (bHeight > maxHeight) {
                scale = bHeight * 1f / maxHeight * 1f;
                scaleWidth = (int) (bWidth * 1f / scale);
            } else {
                scale = maxHeight * 1f / bHeight;
                scaleWidth = (int) (bWidth * 1f * scale);
            }
            scaleHeight = screenHeight * 5 / 6;
        } else {
            scaleWidth = screenWidth;
            scaleHeight = screenWidth;
        }
        rectF = new RectF(0, 0, scaleWidth, scaleHeight);
    }
}
