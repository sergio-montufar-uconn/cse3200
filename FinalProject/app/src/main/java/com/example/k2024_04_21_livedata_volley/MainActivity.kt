package com.example.k2024_04_21_livedata_volley

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.k2024_04_21_livedata_volley.databinding.ActivityMainBinding
import com.example.k2024_04_21_livedata_volley.models.JSON_MetMuseum
import com.example.k2024_04_21_livedata_volley.view_models.UrlViewModel
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var container: ConstraintLayout
    private val gson = Gson()
    private val metPublicDomainUrl = "https://collectionapi.metmuseum.org/public/collection/v1/objects/"
    private var imageData : JSON_MetMuseum? = null
    private lateinit var volleyQueue: RequestQueue
    private var currentImageIndex = 0
    private val backgroundImages = intArrayOf(R.drawable.museum1,
                                              R.drawable.museum2,
                                              R.drawable.museum3,
                                              R.drawable.mueseum4,
                                              R.drawable.museum5)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        volleyQueue = Volley.newRequestQueue(this)

        val uriViewModel: UrlViewModel by viewModels()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        container = findViewById(R.id.container)
        binding.changeBackground.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % backgroundImages.size
            container.setBackgroundResource((backgroundImages[currentImageIndex]))
        }


        binding.nextImageButton.setOnClickListener {
            val nextIndex = uriViewModel.nextImageNumber()
            val metUrl = metPublicDomainUrl + nextIndex.toString()
            uriViewModel.setMetaDataUrl(metUrl)

            val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET,
                uriViewModel.getMetaDataUrl(),
                null,
                { response ->
                    imageData = gson.fromJson(response.toString(), JSON_MetMuseum::class.java )
                    val title = imageData?.title
                    val artist = imageData?.artistDisplayName
                    val region = imageData?.region
                    val culture = imageData?.culture

                    binding.title.text = if (title != "") "$title" else "N/A"
                    binding.artist.text = if (artist != "") "Artist - $artist" else "N/A"
                    binding.region.text = if (region != "") "Region - $region" else "N/A"
                    binding.culture.text = if (culture != "") "Culture - $culture" else "N/A"


                    val image = imageData?.primaryImage.toString()
                    if (image.isNotEmpty()) {
                        uriViewModel.setImageUrl(image)
                        val imageRequest = ImageRequest(
                            image,
                            { response: Bitmap ->
                                binding.imageView.setImageBitmap(response)
                            },
                            0,0,
                            ImageView.ScaleType.CENTER_CROP, Bitmap.Config.RGB_565,
                            { error ->  Log.i("PGB", "Error: ${error}" )})
                        volleyQueue.add(imageRequest)
                    }
                },
                { error ->  Log.i("PGB" ,"Error: ${error}") })
            volleyQueue.add(jsonObjectRequest)
        }
    }
}