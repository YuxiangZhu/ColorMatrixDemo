package com.alex.colormatrixdemo

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alex.colormatrixdemo.adapter.FilterAdapter
import com.alex.colormatrixdemo.bean.FilterInfo
import com.alex.colormatrixdemo.utils.Utils
import kotlinx.android.synthetic.main.activity_adjust.*

class AdjustActivity : AppCompatActivity() {
    private var imageUrl: String? = null
    private lateinit var adapter: FilterAdapter

    //当前被选中的特效
    private lateinit var mCurrentFilter: FilterInfo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adjust)
        imageUrl = intent.getStringExtra(URL)
        initView()
        initListener()
        setImageBitmap()
        initData()
    }

    private fun initView() {
        seekbar.progress = 50
        adapter = FilterAdapter()
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
    }

    private fun initListener() {
        seekbar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val value = progress / 50f
                doChange(value)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun doChange(value: Float) {
        when (mCurrentFilter.id) {
            ID_BRIGHT -> filter_view.changeLight(value)
            ID_TEMPERATURE -> filter_view.changeHue(value)
            ID_SATURATION -> filter_view.changeSaturation(value)
        }
    }

    private fun initData() {
        val list = mutableListOf<FilterInfo>()
        list.add(FilterInfo(ID_BRIGHT, "亮度", R.drawable.capa_ic_contrast, true))
        list.add(FilterInfo(ID_CONTRAST, "对比度", R.drawable.capa_ic_contrast, false))
        list.add(FilterInfo(ID_TEMPERATURE, "色温", R.drawable.capa_ic_contrast, false))
        list.add(FilterInfo(ID_SATURATION, "饱和度", R.drawable.capa_ic_contrast, false))
        list.add(FilterInfo(ID_PARTICLE, "颗粒度", R.drawable.capa_ic_contrast, false))
        //默认选中的是第一个
        mCurrentFilter = list[0]
        adapter.updateList(list)
        adapter.setListener(object : FilterAdapter.OnItemListener {
            override fun onItemClick(bean: FilterInfo) {
                list.forEach {
                    it.isSelected = false
                }
                bean.isSelected = true
                mCurrentFilter = bean

                adapter.notifyDataSetChanged()
            }
        })
    }

    private fun setImageBitmap() {
        var bitmap: Bitmap? = null
//        bitmap = if (alreadyChange) {
//            BitmapFactory.decodeFile(imageUrl)
//        } else {
//            Utils.readBitmap(imageUrl, 2)
//        }
        bitmap = Utils.readBitmap(imageUrl, 2)

        filter_view.setImageBitmap(bitmap)
        filter_view.setFloat(ColorMatrixValue.src)
    }

    companion object {
        //图片的地址
        const val URL = ""

        //明亮度
        const val ID_BRIGHT = 0

        //对比度
        const val ID_CONTRAST = 1

        //色温
        const val ID_TEMPERATURE = 2

        //饱和度
        const val ID_SATURATION = 3

        //颗粒度
        const val ID_PARTICLE = 4
    }
}
