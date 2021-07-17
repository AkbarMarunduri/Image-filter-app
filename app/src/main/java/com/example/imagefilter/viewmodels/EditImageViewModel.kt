package com.example.imagefilter.viewmodels

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imagefilter.data.ImageFilter 
import com.example.imagefilter.repositories.EditImageRepository
import com.example.imagefilter.utilities.Coroutines

class EditImageViewModel(private val editimagerepository: EditImageRepository) : ViewModel() {
    //region:: prepare image preview
    private val imagePreviewDataState = MutableLiveData<ImagePreviewDataState>()
    val imagePrevieUiState: LiveData<ImagePreviewDataState> get() = imagePreviewDataState

    fun prepareImagePrevie(imageUri: Uri) {
        Coroutines.io {
            runCatching {
                emitImagePreviewUiState(islOading = true)
                editimagerepository.prepareImagePreview(imageUri)
            }.onSuccess { bitmap ->
                if (bitmap != null)
                    emitImagePreviewUiState(bitmap = bitmap)
                else
                    emitImagePreviewUiState(error = "unable to prepare image view")
            }.onFailure {
                emitImagePreviewUiState(error = it.message.toString())
            }
        }
    }

    fun emitImagePreviewUiState(
        islOading: Boolean = false,
        bitmap: Bitmap? = null,
        error: String? = null
    ) {
        val dataState = ImagePreviewDataState(islOading, bitmap, error)
        imagePreviewDataState.postValue(dataState)
    }

    data class ImagePreviewDataState(
        val isLoading: Boolean,
        val bitmap: Bitmap?,
        val error: String?
    )
    //endregion

    //region:: load imege filter
    private val imageFiltersDataState=MutableLiveData<ImagePreviewDataState>()
   // val imageFilterUiState:LiveData<imageFilterDataState> get() = imageFiltersDataState()

    data class imageFilterDataState(
        val isLoading: Boolean,
        val imageFilters: List<ImageFilter>?,
        val error: String?
    )
    //endregion
}