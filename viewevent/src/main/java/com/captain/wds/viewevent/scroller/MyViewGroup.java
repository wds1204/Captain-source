package com.captain.wds.viewevent.scroller;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Scroller;

/**
 * Created by wudongsheng on 2018/4/22.
 */

public class MyViewGroup extends ViewGroup {

	private Scroller		scroller;

	private VelocityTracker	obtain;

	private int				mLastX;

	private int				mLastY;

	private int				mChildrenSize;

	private int				mChildWidth;
    private int mChildIndex;

	public MyViewGroup(Context context) {
		this(context,null);
	}

	public MyViewGroup(Context context, @Nullable AttributeSet attrs) {
		this(context, attrs,0);
	}

	public MyViewGroup(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}

	private void init(Context context) {
		scroller = new Scroller(context, new DecelerateInterpolator(5.0f));
		obtain = VelocityTracker.obtain();
	}

	@Override public boolean onTouchEvent(MotionEvent event) {
		obtain.addMovement(event);
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (!scroller.isFinished()) {
					scroller.abortAnimation();
				}
				break;
			case MotionEvent.ACTION_MOVE:
				int deltaX = x - mLastX;
				int deltaY = y - mLastX;
				scrollBy(-deltaX, 0);
				break;
			case MotionEvent.ACTION_UP:
				int scrollX = getScrollX();
				float xVelocity = obtain.getXVelocity();

				obtain.computeCurrentVelocity(1000);

                if (Math.abs(xVelocity) >= 50) {
                    mChildIndex = xVelocity > 0 ? mChildIndex - 1 : mChildWidth + 1;
                } else {
                    mChildIndex = (scrollX + mChildWidth / 2) / mChildWidth;
                }
                mChildIndex = Math.max(0, Math.min(mChildIndex, mChildrenSize - 1));
                int dx = mChildIndex * mChildWidth - scrollX;
                smoothScrollBy(dx, 0);
                obtain.clear();

                break;
		}
		mLastX = x;
		mLastY = y;
		return true;

	}



    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int measuredWidth = 0;
		int measuredHeight = 0;
		int childCount = getChildCount();
		measureChildren(widthMeasureSpec, heightMeasureSpec);

		int widthSpaceMode = MeasureSpec.getMode(widthMeasureSpec);
		int widthSpaceSize = MeasureSpec.getSize(widthMeasureSpec);
		int heightSpaceMode = MeasureSpec.getMode(heightMeasureSpec);
		int heightSpaceSize = MeasureSpec.getSize(heightMeasureSpec);

		if (childCount == 0) {
			setMeasuredDimension(0, 0);
		} else if (widthSpaceMode == MeasureSpec.AT_MOST && heightSpaceMode == MeasureSpec.AT_MOST) {
			View childView = getChildAt(0);
			measuredWidth = childView.getMeasuredWidth() * childCount;
			measuredHeight = childView.getMeasuredHeight();
			setMeasuredDimension(measuredWidth, measuredHeight);
		} else if (heightSpaceMode == MeasureSpec.AT_MOST) {
			View childView = getChildAt(0);
			measuredHeight = childView.getMeasuredHeight();

			setMeasuredDimension(widthSpaceSize, measuredHeight);
		} else if (widthSpaceMode == MeasureSpec.AT_MOST) {
			View childView = getChildAt(0);

			measuredWidth = childView.getMeasuredWidth() * childCount;

			setMeasuredDimension(measuredWidth, heightSpaceSize);
		}
	}

	@Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
		int childLeft = 0;

		int childCount = getChildCount();
		mChildrenSize = childCount;

		for (int i = 0; i < childCount; i++) {

			View childView = getChildAt(i);

			if (childView.getVisibility() != GONE) {
				int childWidth = childView.getMeasuredWidth();
				mChildWidth = childWidth;

                childView.layout(childLeft, 0, childLeft + childWidth, childView.getMeasuredHeight());
                childLeft += childWidth;
            }
		}

	}

	@Override public void computeScroll() {

		super.computeScroll();

        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();

        }
    }

    private void smoothScrollBy(int dx, int dy) {
        scroller.startScroll(getScrollX(), 0, dx, 0, 500);
        invalidate();
    }
}
