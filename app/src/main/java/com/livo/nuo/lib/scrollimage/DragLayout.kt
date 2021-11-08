package com.livo.nuo.lib.scrollimage

import android.content.Context

import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.livo.nuo.R

class DragLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) :
    FrameLayout(context, attrs, defStyleAttr) {

    private val bottomDragVisibleHeight: Int
    private val bototmExtraIndicatorHeight: Int
    private val dragTopDest = 0
    private val downState: Int = 0

    private val mDragHelper: ViewDragHelper
    private val moveDetector: GestureDetectorCompat
    private var mTouchSlop = 5
    private val originX: Int = 0
    private val originY: Int = 0
    private val bottomView: View? = null
    private val topView: View? = null

    private var gotoDetailListener: GotoDetailListener? = null

    init {

        val a = context.obtainStyledAttributes(attrs, R.styleable.app, 0, 0)
        bottomDragVisibleHeight = a.getDimension(R.styleable.app_bottomDragVisibleHeight, 0f).toInt()
        bototmExtraIndicatorHeight = a.getDimension(R.styleable.app_bototmExtraIndicatorHeight, 0f).toInt()
        a.recycle()

        mDragHelper = ViewDragHelper
            .create(this, 10f, DragHelperCallback())
        mDragHelper.setEdgeTrackingEnabled(ViewDragHelper.EDGE_TOP)
        moveDetector = GestureDetectorCompat(context, MoveDetector())
        moveDetector.setIsLongpressEnabled(false)

        val configuration = ViewConfiguration.get(getContext())
        mTouchSlop = configuration.scaledTouchSlop
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

    }

    private fun gotoDetailActivity() {
        if (null != gotoDetailListener) {
            gotoDetailListener!!.gotoDetail()
        }
    }

    private inner class DragHelperCallback : ViewDragHelper.Callback() {

        override fun onViewPositionChanged(changedView: View, left: Int, top: Int, dx: Int, dy: Int) {
            if (changedView === topView) {
                processLinkageView()
            }
        }

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === topView
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            val currentTop = child.top
            if (top > child.top) {
                return currentTop + (top - currentTop) / 2
            }

            val result: Int
            if (currentTop > DECELERATE_THRESHOLD * 3) {
                result = currentTop + (top - currentTop) / 2
            } else if (currentTop > DECELERATE_THRESHOLD * 2) {
                result = currentTop + (top - currentTop) / 4
            } else if (currentTop > 0) {
                result = currentTop + (top - currentTop) / 8
            } else if (currentTop > -DECELERATE_THRESHOLD) {
                result = currentTop + (top - currentTop) / 16
            } else if (currentTop > -DECELERATE_THRESHOLD * 2) {
                result = currentTop + (top - currentTop) / 32
            } else if (currentTop > -DECELERATE_THRESHOLD * 3) {
                result = currentTop + (top - currentTop) / 48
            } else {
                result = currentTop + (top - currentTop) / 64
            }
            return result
        }

        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return child.left
        }

        override fun getViewHorizontalDragRange(child: View): Int {
            return 600
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return 600
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            var finalY = originY
            if (downState == STATE_CLOSE) {
                if (originY - releasedChild.top > DRAG_SWITCH_DISTANCE_THRESHOLD || yvel < -DRAG_SWITCH_VEL_THRESHOLD) {
                    finalY = dragTopDest
                }
            } else {
                val gotoBottom =
                    releasedChild.top - dragTopDest > DRAG_SWITCH_DISTANCE_THRESHOLD || yvel > DRAG_SWITCH_VEL_THRESHOLD
                if (!gotoBottom) {
                    finalY = dragTopDest

                    if (dragTopDest - releasedChild.top > mTouchSlop) {
                        gotoDetailActivity()
                        postResetPosition()
                        return
                    }
                }
            }

            if (mDragHelper.smoothSlideViewTo(releasedChild, originX, finalY)) {
                ViewCompat.postInvalidateOnAnimation(this@DragLayout)
            }
        }
    }


    private fun postResetPosition() {
        this.postDelayed({ topView!!.offsetTopAndBottom(dragTopDest - topView.top) }, 500)
    }

    private fun processLinkageView() {
        if (topView!!.top > originY) {
            bottomView!!.alpha = 0f
        } else {
            var alpha = (originY - topView.top) * 0.01f
            if (alpha > 1) {
                alpha = 1f
            }
            bottomView!!.alpha = alpha
            val maxDistance = originY - dragTopDest
            val currentDistance = topView.top - dragTopDest
            var scaleRatio = 1f
            val distanceRatio = currentDistance.toFloat() / maxDistance
            if (currentDistance > 0) {
                scaleRatio = MIN_SCALE_RATIO + (MAX_SCALE_RATIO - MIN_SCALE_RATIO) * (1 - distanceRatio)
            }
            bottomView.scaleX = scaleRatio
            bottomView.scaleY = scaleRatio
        }
    }

    internal inner class MoveDetector : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent, e2: MotionEvent, dx: Float,
            dy: Float
        ): Boolean {
            return Math.abs(dy) + Math.abs(dx) > mTouchSlop
        }
    }

    override fun computeScroll() {
        if (mDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        if (!changed) {
            return
        }

        super.onLayout(changed, left, top, right, bottom)

    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val yScroll = moveDetector.onTouchEvent(ev)
        var shouldIntercept = false
        try {
            shouldIntercept = mDragHelper.shouldInterceptTouchEvent(ev)
        } catch (e: Exception) {
        }

        val action = ev.actionMasked
        if (action == MotionEvent.ACTION_DOWN) {
            mDragHelper.processTouchEvent(ev)
        }

        return shouldIntercept && yScroll
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        try {
            mDragHelper.processTouchEvent(e)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        return true
    }

    fun setGotoDetailListener(gotoDetailListener: GotoDetailListener) {
        this.gotoDetailListener = gotoDetailListener
    }

    interface GotoDetailListener {
        fun gotoDetail()
    }

    companion object {
        private val DECELERATE_THRESHOLD = 120
        private val DRAG_SWITCH_DISTANCE_THRESHOLD = 100
        private val DRAG_SWITCH_VEL_THRESHOLD = 800

        private val MIN_SCALE_RATIO = 0.5f
        private val MAX_SCALE_RATIO = 1.0f

        private val STATE_CLOSE = 1
        private val STATE_EXPANDED = 2
    }
}
