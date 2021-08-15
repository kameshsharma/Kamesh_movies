package com.example.lowesmovies.ui.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lowesmovies.R
import com.example.lowesmovies.utils.CustomBindings.Companion.RADIUS

class DetailActivity : AppCompatActivity() {
    private var coverImage: ImageView? = null
    private var display_title: TextView? = null
    private var byline: TextView? = null
    private var headline: TextView? = null
    private var publication_date: TextView? = null
    private var desc: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        coverImage = findViewById(R.id.iv_cover)
        display_title = findViewById(R.id.display_title)
        byline = findViewById(R.id.byline)
        headline = findViewById(R.id.headline)
        publication_date = findViewById(R.id.publication_date)
        desc = findViewById(R.id.desc)

        display_title?.text = intent.getStringExtra("display_title")
        headline?.text = intent.getStringExtra("headline")
        desc?.text = intent.getStringExtra("summary_short")
        byline?.text = intent.getStringExtra("byline")
        publication_date?.text = "Publish Date\n"+intent.getStringExtra("publication_date")
        val requestOptions = RequestOptions().transform(
            MultiTransformation(
                CenterCrop(), RoundedCorners(
                    RADIUS
                )
            )
        ).placeholder(R.color.grey)
            .error(R.color.grey)
        Glide.with(this)
            .load(intent.getStringExtra("multimedia"))
            .apply(requestOptions)
            .into(coverImage ?: ImageView(this))
    }
}