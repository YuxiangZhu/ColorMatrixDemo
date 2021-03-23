package com.alex.colormatrixdemo

import android.content.Context
import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.widget.ImageViewCompat

/**
 * Author: alexchu
 * Version V1.0
 * Date: 2021-03-23
 * Description:
 * Modification History:
 * Date Author Version Description
 * -----------------------------------------------------------------------------------
 * 3/23/21 alexchu 1.0
 * Why & What is modified:
 */
class FilterView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) :
    AppCompatImageView(
        context,
        attributeSet,
        defStyleAttr), Filter {
    override fun setFloat(floats: FloatArray?) {
    }

    override fun getChangeBitmap(): Bitmap? {
        return null
    }

    override fun scaleBitmap() {
    }
}
