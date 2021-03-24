package com.alex.colormatrixdemo.filter

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

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
open class FilterView @JvmOverloads constructor(
    context: Context, attributeSet: AttributeSet? = null, defStyleAttr: Int = 0
) :
    AppCompatImageView(
        context,
        attributeSet,
        defStyleAttr), Filter {

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun setFloat(floats: FloatArray?) {
    }

    override fun getChangeBitmap(): Bitmap? {
        return null
    }

    override fun scaleBitmap() {
    }
}
