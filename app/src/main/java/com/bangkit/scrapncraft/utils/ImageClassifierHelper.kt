package com.bangkit.scrapncraft.utils

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.task.core.BaseOptions
import org.tensorflow.lite.task.core.vision.ImageProcessingOptions
import org.tensorflow.lite.task.vision.classifier.Classifications
import org.tensorflow.lite.task.vision.classifier.ImageClassifier

class ImageClassifierHelper(
    var threshold: Float = 0.5f,
    var maxResults: Int = 5,
    var numThreads: Int = 4,
    val modelName: String = "model_metadata.tflite",
    val context: Context,
    val imageClassifierListener: ClassifierListener?
) {
    private var imageClassifier: ImageClassifier? = null

    init {
        setupImageClassifier()
    }

    fun setupImageClassifier() {
        val baseOptionsBuilder = BaseOptions.builder()
            .setNumThreads(numThreads)
            .build()

        val optionsBuilder = ImageClassifier.ImageClassifierOptions.builder()
            .setBaseOptions(baseOptionsBuilder)
            .setMaxResults(maxResults)
            .setScoreThreshold(threshold)
            .build()

        try {
            imageClassifier =
                ImageClassifier.createFromFileAndOptions(context, modelName, optionsBuilder)
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    fun classifyImageUri(imageUri: Uri, imageView: ImageView) {
        val imageBitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
        val tensorImage = TensorImage.fromBitmap(imageBitmap)

        val imageProcessingOptions = ImageProcessingOptions.builder()
            .setOrientation(ImageProcessingOptions.Orientation.TOP_LEFT)
            .build()

        val results = imageClassifier?.classify(tensorImage, imageProcessingOptions)
        imageClassifierListener?.onResults(results, 0, imageView)
    }

    interface ClassifierListener {
        fun onError(error: String)
        fun onResults(
            results: List<Classifications>?,
            inferenceTime: Long,
            imageView: ImageView
        )
    }
}