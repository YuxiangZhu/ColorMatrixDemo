package com.alex.colormatrixdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alex.colormatrixdemo.bean.FilterInfo
import com.alex.colormatrixdemo.databinding.ItemFilterBinding

/**
 * 作者： Alex
 * 日期： 2020-01-20
 * 签名： 摊牌了，我是沙雕
 *                    .::::.
 *                  .::::::::.
 *                 :::::::::::
 *             ..:::::::::::'
 *           '::::::::::::'
 *             .::::::::::
 *        '::::::::::::::..
 *             ..::::::::::::.
 *           ``::::::::::::::::
 *            ::::``:::::::::'        .:::.
 *           ::::'   ':::::'       .::::::::.
 *         .::::'      ::::     .:::::::'::::.
 *        .:::'       :::::  .:::::::::' ':::::.
 *       .::'        :::::.:::::::::'      ':::::.
 *      .::'         ::::::::::::::'         ``::::.
 *  ...:::           ::::::::::::'              ``::.
 * ```` ':.          ':::::::::'                  ::::..
 *                    '.:::::'                    ':'````..
 * ----------------------------------------------------------------
 * 证件类型的数据适配器
 */
class FilterAdapter : RecyclerView.Adapter<FilterAdapter.MyVH>() {
    private var listener: OnItemListener? = null

    //数据列表
    private val mList = mutableListOf<FilterInfo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVH {
        val binding =
            ItemFilterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyVH(binding)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: MyVH, position: Int) {
        holder.bind(mList[position], listener)
    }

    /**
     * 接口注册
     */
    fun setListener(listener: OnItemListener) {
        this.listener = listener
    }

    /**
     *
     */
    interface OnItemListener {
        fun onItemClick(bean: FilterInfo)
    }

    /**
     * 刷新数据
     */
    fun updateList(list: List<FilterInfo>?) {
        mList.clear()
        list?.let {
            mList.addAll(list)
        }
        notifyDataSetChanged()
    }

    class MyVH(private val binding: ItemFilterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            bean: FilterInfo,
            onItemListener: OnItemListener?
        ) {
            binding.info = bean
            binding.itemLayout.setOnClickListener {
                onItemListener?.onItemClick(bean)
            }
            binding.executePendingBindings()
        }
    }
}
