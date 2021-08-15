package com.example.lowesmovies.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.lowesmovies.R


/**
 * Created by Neha Kushwah on 3/29/21.
 */
class CustomBindings {

    companion object {

        const val RADIUS = 10;

        @JvmStatic
        @BindingAdapter("imageUrl")
        fun setImageUrl(imgView: ImageView, imgUrl: String?) {

            imgUrl?.let {
                val imgUri = it.toUri().buildUpon().scheme("https").build()
                val requestOptions = RequestOptions().transform(
                    MultiTransformation(
                        CenterCrop(), RoundedCorners(
                            RADIUS
                        )
                    )
                ).placeholder(R.color.grey)
                        .error(R.color.grey)
                Glide.with(imgView.context)
                        .load(imgUri)
                        .apply(requestOptions)
                        .into(imgView)
            }
        }


        @JvmStatic
        @BindingAdapter("imageUrlWithId")
        fun setImageUrlWithId(imgView: ImageView, imageUrl: String?) {
            setImageUrl(imgView, imageUrl)
        }

    }
}