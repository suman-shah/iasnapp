package com.project75.ioeallsubjectnotes.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class PaintView extends View {

    private List<Path> paths = new ArrayList<>();
    private List<Paint> paints = new ArrayList<>();
    private Path currentPath;
    private Paint currentPaint;

    private float lastX, lastY;
    private static final float TOUCH_TOLERANCE = 4;

    private boolean isEraserMode = false;

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        currentPath = new Path();
        currentPaint = new Paint();
        currentPaint.setColor(Color.BLACK);  // Default color
        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(10);  // Default brush size
        currentPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            canvas.drawPath(paths.get(i), paints.get(i));
        }
        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.reset();
                currentPath.moveTo(x, y);
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                float dx = Math.abs(x - lastX);
                float dy = Math.abs(y - lastY);
                if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
                    currentPath.lineTo(x, y);
                    lastX = x;
                    lastY = y;
                }
                break;

            case MotionEvent.ACTION_UP:
                paths.add(currentPath);
                paints.add(new Paint(currentPaint));
                currentPath = new Path();
                break;
        }

        invalidate();
        return true;
    }

    public void setBrushSize(float size) {
        currentPaint.setStrokeWidth(size);
    }

    public void setBrushColor(int color) {
        if (!isEraserMode) {
            currentPaint.setColor(color);
        }
    }

    public void enableEraserMode(boolean isEraser) {
        isEraserMode = isEraser;
        if (isEraserMode) {
            currentPaint.setColor(Color.WHITE);  // Set eraser color (background color)
        } else {
            currentPaint.setColor(Color.BLACK);  // Set back to default brush color (or the last selected color)
        }
    }

    public void clearCanvas() {
        paths.clear();
        paints.clear();
        currentPath.reset();
        invalidate();
    }
}
