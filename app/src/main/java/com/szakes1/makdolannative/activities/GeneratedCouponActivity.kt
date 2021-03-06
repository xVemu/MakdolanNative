package com.szakes1.makdolannative.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View
import androidx.core.content.ContextCompat
import com.szakes1.makdolannative.R
import com.szakes1.makdolannative.helpers.Makdolan
import com.szakes1.makdolannative.helpers.OnPinchZoomListener
import kotlinx.android.synthetic.main.activity_generated_coupon.*
import net.danlew.android.joda.JodaTimeAndroid

class GeneratedCouponActivity : AppCompatActivity() {
    private lateinit var mScaleGestureDetector: ScaleGestureDetector
    private lateinit var zoomableView: View
    private var mScaleFactor: Float = 1.0f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_generated_coupon)
        JodaTimeAndroid.init(this)

        val mk = Makdolan()

        release_date_TV.text = mk.calculateDate()
        unique_code_TV.text = mk.calculateUniqueCode()

        val couponImage = intent.getIntExtra("coupon_image", 0)
        generated_coupon_IMGV.setImageDrawable(ContextCompat.getDrawable(applicationContext, couponImage))

        // Enables immersive mode
        generatedCouponWrapperLayout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                                            or View.SYSTEM_UI_FLAG_FULLSCREEN)

        // Add Pinch to Zoom gestures
        zoomableView = findViewById(R.id.generatedCouponWrapperLayout)
        mScaleGestureDetector = ScaleGestureDetector(this, OnPinchZoomListener(mScaleFactor, zoomableView))
    }

    // Enables immersive mode if activity is focused
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) generatedCouponWrapperLayout.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                                                          or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                                                          or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    // Listen for touch events
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleGestureDetector.onTouchEvent(event)

        return true
    }
}
