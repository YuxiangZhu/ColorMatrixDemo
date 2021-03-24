package com.alex.colormatrixdemo.filter

import android.graphics.Bitmap

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
interface Filter {
    /**
     * 设置滤镜的
     *
     * @param floats
     */
    fun setFloat(floats: FloatArray?)

    /**
     * 获取处理过的bitmap
     *
     * @return
     */
    fun getChangeBitmap(): Bitmap?

    /**
     * 缩放bitmap
     *
     */
    fun scaleBitmap()
}
