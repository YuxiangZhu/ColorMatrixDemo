package com.alex.colormatrixdemo

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.LogUtils
private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /**
     * 选择照片
     */
    fun doGallery(view: View) {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, REQUEST_ALBUM)
    }

    /**
     * 拍照
     */
    fun doTakePhoto(view: View) {
    }

    companion object {
        const val REQUEST_CAMERA = 1
        const val REQUEST_ALBUM = 2
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ALBUM && resultCode == Activity.RESULT_OK) {
            val selectedImage = data!!.data
            val filePathColumns = arrayOf(MediaStore.Images.Media.DATA)
            val c = contentResolver.query(selectedImage!!,
                filePathColumns,
                null,
                null,
                null)
            if (c != null) {
                c.moveToFirst()
                val columnIndex = c.getColumnIndex(filePathColumns[0])
                val imagePath = c.getString(columnIndex)
                LogUtils.e(imagePath)
                go2Adjust(imagePath)
                c.close()
            }
        }
    }

    /**
     * 跳转到图片调整界面
     */
    private fun go2Adjust(url: String){
        val intent = Intent(this,AdjustActivity::class.java)
        intent.putExtra(AdjustActivity.URL,url)
        startActivity(intent)
    }

//    override fun onPause() {
//        super.onPause()
//        Log.e(TAG, "onPause: " )
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Log.e(TAG, "onStop: " )
//    }
}
