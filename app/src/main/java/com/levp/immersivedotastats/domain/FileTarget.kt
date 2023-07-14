package com.levp.immersivedotastats.domain

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.transition.Transition
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class FileTarget(private val file: File) : CustomTarget<Drawable>() {
    override fun onLoadStarted(placeholder: Drawable?) {
        Log.i("heheh","load started")
    }
    override fun onLoadFailed(errorDrawable: Drawable?) {
        Log.e("heheh","load failed $errorDrawable")
    }
    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
        val bitmap = (resource as BitmapDrawable).bitmap
        Log.d("heheh","fileoutputstream to $file")
        try {
            val outputStream = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream.flush()
            outputStream.close()
        } catch (e: IOException) {
            Log.e("hehe", "FileTarget: IO Exception")
        }
    }

    override fun onLoadCleared(placeholder: Drawable?) {}


    override fun onStart() {}
    override fun onStop() {}
    override fun onDestroy() {}
}