package com.alex.colormatrixdemo.bean

/**
 * Author: alexchu
 * Version V1.0
 * Date: 2021-03-24
 * Description:
 * Modification History:
 * Date Author Version Description
 * -----------------------------------------------------------------------------------
 * 3/24/21 alexchu 1.0
 * Why & What is modified:
 * such as id = 0,name = "饱和度",src = icon
 */
data class FilterInfo(
    val id: Int,
    val name: String,
    val src: Int,
    var isSelected: Boolean
)
