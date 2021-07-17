package com.example.imagefilter.activities.editimage

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.example.imagefilter.activities.main.MainActivity
import com.example.imagefilter.databinding.ActivityEditImageBinding
import com.example.imagefilter.utilities.displayToast
import com.example.imagefilter.utilities.show
import com.example.imagefilter.viewmodels.EditImageViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EditImageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditImageBinding
    private val viewModel: EditImageViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditImageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
        //displayImagePreview()
        prepareImagePreview()
        setupObservers()
    }

    fun setupObservers() {
        viewModel.imagePrevieUiState.observe(this, Observer {
            val dataState = it ?: return@Observer
            binding.previewProgresBar.visibility =
                if (dataState.isLoading) View.VISIBLE else View.GONE
            dataState.bitmap?.let { bitmap ->
                binding.imagePreview.setImageBitmap(bitmap)
                binding.imagePreview.show()
            } ?: kotlin.run {
                dataState.error?.let { error ->
                    displayToast(error)
                }
            }
        })
    }

    private fun prepareImagePreview() {
        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let { imageUri ->
            viewModel.prepareImagePrevie(imageUri)
        }
    }

//    private fun displayImagePreview() {
//        intent.getParcelableExtra<Uri>(MainActivity.KEY_IMAGE_URI)?.let { imageUri ->
//            val inputStream = contentResolver.openInputStream(imageUri)
//            val bitmap = BitmapFactory.decodeStream(inputStream)
//            binding.imagePreview.setImageBitmap(bitmap)
//            binding.imagePreview.visibility = View.VISIBLE
//        }
//    }

    private fun setListener() {
        binding.imageBack.setOnClickListener {
            onBackPressed()
        }
    }
}