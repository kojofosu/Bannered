package com.mcdev.bannered

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.cardview.widget.CardView
import androidx.core.content.res.getStringOrThrow
import com.google.android.material.card.MaterialCardView
import com.mcdev.bannered.databinding.ActivityBanneredBinding

class Bannered @JvmOverloads constructor(context: Context,
                                         attributeSet: AttributeSet? = null,
                                         defStyle: Int = 0):
    MaterialCardView(context, attributeSet, defStyle){

    private var sharedPreferences: SharedPreferences? = null;

    private var binding = ActivityBanneredBinding.inflate(LayoutInflater.from(context), this, true)

    private var bannerViewKey: String? = null
    private var bannerViewStrokeWidth: Float = 0F
    private var bannerViewStrokeColor: Int? = null
    private var bannerViewBgColor: Int? = null
    private var description: String? = resources.getString(R.string.lorem_ipsum)
    private var descriptionTextColor: Int = resources.getColor(R.color.black, context.theme)
    private var descriptionTextSize: Float = resources.getDimension(R.dimen.def_text_size)
    private var dismissButtonText: String? = null
    private var dismissButtonEnabled: Boolean = true
    private var dismissTextColor: Int? = null


    init {
        sharedPreferences = context.getSharedPreferences("com.mcdev.bannered",Context.MODE_PRIVATE)
        val attributes = context.obtainStyledAttributes(
            attributeSet, R.styleable.Bannered, defStyle, 0
        )

        /*Get and set attributes*/
        getAttributes(attributes)
        setAttributes()

        if (sharedPreferences!!.getBoolean(bannerViewKey, true)) {
            binding.bannerView.visibility = View.VISIBLE
        } else {
            binding.bannerView.visibility = View.GONE
        }

        binding.bannerDismissButton.setOnClickListener {
//            val id: String = binding.bannerView.id.toString()
            sharedPreferences!!.edit().putBoolean(bannerViewKey, false).apply()
            binding.bannerView.visibility = View.GONE
        }

        /*TypedArrays are heavyweight objects that should be recycled immediately
        after all the attributes you need have been extracted.*/
        attributes.recycle()
    }

    private fun getAttributes(attributes: TypedArray) {
        /*Get banner view*/
        bannerViewBgColor = attributes.getColor(R.styleable.Bannered_cardBackgroundColor, resources.getColor(R.color.bg_color, context.theme))
        bannerViewStrokeColor = attributes.getColor(R.styleable.Bannered_strokeColor, resources.getColor(R.color.black, context.theme))
        bannerViewStrokeWidth = attributes.getDimension(R.styleable.Bannered_strokeWidth, 0F)
        bannerViewKey = attributes.getStringOrThrow(R.styleable.Bannered_key)

        /*Get description text*/
        description = attributes.getString(R.styleable.Bannered_description)
        descriptionTextColor = attributes.getColor(R.styleable.Bannered_descriptionTextColor, resources.getColor(R.color.black, context.theme))
        descriptionTextSize = attributes.getDimension(R.styleable.Bannered_descriptionTextSize, resources.getDimension(R.dimen.def_text_size))

        /*Get dismiss Button text*/
        dismissButtonText = attributes.getString(R.styleable.Bannered_dismissButtonText)
        dismissButtonEnabled = attributes.getBoolean(R.styleable.Bannered_dismissButtonEnabled, true)
        dismissTextColor = attributes.getColor(R.styleable.Bannered_dismissButtonTextColor, com.google.android.material.R.attr.colorPrimary)

    }

    private fun setAttributes() {
        /*banner view*/
        binding.bannerView.apply {
            this.cardElevation = 0F
            this.strokeWidth = bannerViewStrokeWidth.toInt()
            this.strokeColor = bannerViewStrokeColor!!
            this.setCardBackgroundColor(bannerViewBgColor!!)
        }

        /*description*/
        binding.bannerDescription.apply {
            this.text = description
            this.setTextColor(descriptionTextColor)
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, descriptionTextSize)
        }

        /*dismiss button*/
        binding.bannerDismissButton.apply {
            if (dismissButtonEnabled)
                this.visibility = View.VISIBLE
            else
                this.visibility = View.GONE

            this.text = dismissButtonText
            this.setTextColor(dismissTextColor!!)
        }
    }
}