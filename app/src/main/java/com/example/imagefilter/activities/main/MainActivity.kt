package com.example.imagefilter.activities.main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import com.example.imagefilter.activities.editimage.EditImageActivity
import com.example.imagefilter.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    companion object {
        private const val REQUES_CODE_PICK_IMAGE = 1
        const val KEY_IMAGE_URI = "ImageUri"
    }

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setListener()
    }

    private fun setListener() {
        binding.buttonEditNewImage.setOnClickListener {
            Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            ).also { pickerIntent ->
                pickerIntent.addFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION)
                startActivityForResult(pickerIntent, REQUES_CODE_PICK_IMAGE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUES_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            data?.data?.let { imageUri->
                Intent(applicationContext, EditImageActivity::class.java).also { editImageIntent ->
                    editImageIntent.putExtra(KEY_IMAGE_URI,imageUri)
                    startActivity(editImageIntent)
                }
            }
        }
    }
}