package com.example.imagefilter.repositories

import android.graphics.Bitmap
import android.net.Uri
import com.example.imagefilter.data.ImageFilter

interface EditImageRepository {
    suspend fun prepareImagePreview(imageUri:Uri):Bitmap?
    suspend fun gameImageFilters(image:Bitmap):List<ImageFilter>
}