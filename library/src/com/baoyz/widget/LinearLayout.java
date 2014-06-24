package com.baoyz.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.baoyz.dividerlinearlayout.R;

/**
 * 
 * @author baoyz
 * @date 2014Äê6ÔÂ25ÈÕ
 */
public class LinearLayout extends android.widget.LinearLayout {

	private Drawable mDivider;
	private int mDividerWidth;
	private int mDividerHeight;
	private int mShowDividers;
	private int mDividerPadding;

	@SuppressLint("NewApi")
	public LinearLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(attrs);
	}

	public LinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(attrs);
	}

	public LinearLayout(Context context) {
		super(context);
	}

	private void initView(AttributeSet attrs) {

		TypedArray a = getContext().obtainStyledAttributes(attrs,
				R.styleable.LinearLayout);
		setDividerDrawable(a.getDrawable(R.styleable.LinearLayout_divider));
		mShowDividers = a.getInt(R.styleable.LinearLayout_showDividers,
				SHOW_DIVIDER_NONE);
		mDividerPadding = a.getDimensionPixelSize(
				R.styleable.LinearLayout_dividerPadding, 0);
		a.recycle();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (mDivider == null) {
			return;
		}

		if (getOrientation() == VERTICAL) {
			drawDividersVertical(canvas);
		} else {
			drawDividersHorizontal(canvas);
		}
	}

	void drawDividersVertical(Canvas canvas) {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);

			if (child != null && child.getVisibility() != GONE) {
				if (hasDividerBeforeChildAt(i)) {
					final LayoutParams lp = (LayoutParams) child
							.getLayoutParams();
					final int top = child.getTop() - lp.topMargin
							- mDividerHeight;
					drawHorizontalDivider(canvas, top);
				}
			}
		}

		if (hasDividerBeforeChildAt(count)) {
			final View child = getChildAt(count - 1);
			int bottom = 0;
			if (child == null) {
				bottom = getHeight() - getPaddingBottom() - mDividerHeight;
			} else {
				final LayoutParams lp = (LayoutParams) child.getLayoutParams();
				bottom = child.getBottom() + lp.bottomMargin;
			}
			drawHorizontalDivider(canvas, bottom);
		}
	}

	void drawDividersHorizontal(Canvas canvas) {
		final int count = getChildCount();
		for (int i = 0; i < count; i++) {
			final View child = getChildAt(i);

			if (child != null && child.getVisibility() != GONE) {
				if (hasDividerBeforeChildAt(i)) {
					final LayoutParams lp = (LayoutParams) child
							.getLayoutParams();
					final int position;
					position = child.getLeft() - lp.leftMargin - mDividerWidth;
					drawVerticalDivider(canvas, position);
				}
			}
		}

		if (hasDividerBeforeChildAt(count)) {
			final View child = getChildAt(count - 1);
			int position;
			if (child == null) {
				position = getWidth() - getPaddingRight() - mDividerWidth;
			} else {
				final LayoutParams lp = (LayoutParams) child.getLayoutParams();
				position = child.getRight() + lp.rightMargin;
			}
			drawVerticalDivider(canvas, position);
		}
	}

	void drawHorizontalDivider(Canvas canvas, int top) {
		mDivider.setBounds(getPaddingLeft() + mDividerPadding, top, getWidth()
				- getPaddingRight() - mDividerPadding, top + mDividerHeight);
		mDivider.draw(canvas);
	}

	void drawVerticalDivider(Canvas canvas, int left) {
		mDivider.setBounds(left, getPaddingTop() + mDividerPadding, left
				+ mDividerWidth, getHeight() - getPaddingBottom()
				- mDividerPadding);
		mDivider.draw(canvas);
	}

	protected boolean hasDividerBeforeChildAt(int childIndex) {
		if (childIndex == 0) {
			return (mShowDividers & SHOW_DIVIDER_BEGINNING) != 0;
		} else if (childIndex == getChildCount()) {
			return (mShowDividers & SHOW_DIVIDER_END) != 0;
		} else if ((mShowDividers & SHOW_DIVIDER_MIDDLE) != 0) {
			boolean hasVisibleViewBefore = false;
			for (int i = childIndex - 1; i >= 0; i--) {
				if (getChildAt(i).getVisibility() != GONE) {
					hasVisibleViewBefore = true;
					break;
				}
			}
			return hasVisibleViewBefore;
		}
		return false;
	}

	public void setDividerDrawable(Drawable divider) {
		if (divider == mDivider) {
			return;
		}
		mDivider = divider;
		if (divider != null) {
			mDividerWidth = divider.getIntrinsicWidth();
			mDividerHeight = divider.getIntrinsicHeight();
		} else {
			mDividerWidth = 0;
			mDividerHeight = 0;
		}
		setWillNotDraw(divider == null);
		requestLayout();
	}

	public void setDividerPadding(int padding) {
		mDividerPadding = padding;
	}
}
