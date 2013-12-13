package com.example.electronicscouting.customViews;

import java.util.ArrayList;

import com.example.electronicscouting.R;
import com.example.electronicscouting.data.FloatPoint;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

public class PathDrawer extends View {
	Paint paint;
	ArrayList<FloatPoint> pathPoints = new ArrayList<FloatPoint>();
	int num = 0;
	CustomPager pager;
	ScrollView scrollToDisable;
	Rect size;
	public PathDrawer(Context context) {
		super(context);
		init();
	}
	
	public PathDrawer(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	public PathDrawer(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	public boolean onTouchEvent(MotionEvent evt) {
		FloatPoint pressed = new FloatPoint(evt.getX(), evt.getY());
		pathPoints.add(pressed);
		this.invalidate();
		return true;
	}
	public boolean onDragEvent(DragEvent evt) {
		FloatPoint pressed = new FloatPoint(evt.getX(), evt.getY());
		pathPoints.add(pressed);
		Toast.makeText(getContext(), "Dragged", Toast.LENGTH_SHORT).show();
		this.invalidate();
		return true;
	}
	@Override
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		 int minw = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
		   int w = resolveSizeAndState(minw, widthMeasureSpec, 1);
		   int minh = MeasureSpec.getSize(w) - (int)getPaddingBottom() + getPaddingTop();
		   int h = resolveSizeAndState(MeasureSpec.getSize(w), heightMeasureSpec, 0);
		   setMeasuredDimension(w, h);
	}
	
	private void init() {
		this.setOnGenericMotionListener(new OnGenericMotionListener() {

			@Override
			public boolean onGenericMotion(View arg0, MotionEvent arg1) {
				onTouchEvent(arg1);
				return true;
			}
			
		});
		this.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				onTouchEvent(arg1);
				return false;
			}
			
		});
		this.setOnDragListener(new OnDragListener() {

			@Override
			public boolean onDrag(View arg0, DragEvent arg1) {
				onDragEvent(arg1);
				return false;
			}
			
			
		
		});
		paint = new Paint();
		paint.setStrokeWidth(3);
		paint.setColor(Color.BLACK);
		
		pager = (CustomPager) ((Activity)getContext()).findViewById(R.id.pager);
		pager.setPagingEnabled(false);
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		FloatPoint previousPoint = null;
		paint.setStrokeWidth(3);
		paint.setColor(Color.BLACK);
		for (FloatPoint point : pathPoints) {
			if (previousPoint == null)
			{
				previousPoint = point;
				continue;
			}
			
			canvas.drawLine(previousPoint.x, previousPoint.y, point.x, point.y, paint);
			previousPoint = point;
			
		}
	}

}
