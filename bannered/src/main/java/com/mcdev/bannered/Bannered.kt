package com.mcdev.bannered

import android.content.Context
import android.content.SharedPreferences
import android.content.res.TypedArray
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.core.content.res.getStringOrThrow
import com.google.android.material.card.MaterialCardView
import com.mcdev.bannered.databinding.ActivityBanneredBinding

class Bannered @JvmOverloads constructor(context: Context,
                                         attributeSet: AttributeSet? = null,
                                         defStyle: Int = 0):
    MaterialCardView(context, attributeSet, defStyle){

    private var sharedPreferences: SharedPreferences? = null

    private var binding = ActivityBanneredBinding.inflate(LayoutInflater.from(context), this, true)

    private var bannerViewKey: String? = null
    private var bannerViewStrokeWidth: Float = 0F
    private var bannerViewStrokeColor: Int? = null
    private var bannerViewBgColor: Int? = null
    private var title: String? = resources.getString(R.string.app_name)
    private var titleTextColor: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(R.color.black, context.theme)
    } else {
        Color.parseColor("#2a2a2a")
    }
    private var titleTextSize: Float = resources.getDimension(R.dimen.def_text_size)
    private var titleEnabled : Boolean = true
    private var description: String? = resources.getString(R.string.lorem_ipsum)
    private var descriptionTextColor: Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        resources.getColor(R.color.black, context.theme)
    } else {
        Color.parseColor("#2a2a2a")
    }
    private var descriptionTextSize: Float = resources.getDimension(R.dimen.def_text_size)
    private var dismissButtonText: String? = null
    private var dismissButtonEnabled: Boolean = true
    private var dismissTextColor: Int? = null


    init {
        sharedPreferences = context.getSharedPreferences(context.packageName.toString(),Context.MODE_PRIVATE)
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            /*BANNER VIEW*/
            bannerViewBgColor = attributes.getColor(
                R.styleable.Bannered_cardBackgroundColor,
                resources.getColor(R.color.bg_color, context.theme)
            )
            bannerViewStrokeColor = attributes.getColor(
                R.styleable.Bannered_strokeColor,
                resources.getColor(R.color.black, context.theme)
            )

            /*TITLE*/
            titleTextColor = attributes.getColor(
                R.styleable.Bannered_titleTextColor,
                resources.getColor(R.color.black, context.theme)
            )

            /*DESCRIPTION*/
            descriptionTextColor = attributes.getColor(
                R.styleable.Bannered_descriptionTextColor,
                resources.getColor(R.color.black, context.theme)
            )

        } else {
            /*BANNER VIEW*/
            bannerViewBgColor = attributes.getColor(
                R.styleable.Bannered_cardBackgroundColor,
                Color.parseColor("#2a2a2a")
            )
            bannerViewStrokeColor = attributes.getColor(
                R.styleable.Bannered_strokeColor,
                Color.parseColor("#2a2a2a")
            )

            /*TITLE*/
            titleTextColor = attributes.getColor(
                R.styleable.Bannered_titleTextColor,
                Color.parseColor("#2a2a2a")
            )

            /*DESCRIPTION*/
            descriptionTextColor = attributes.getColor(
                R.styleable.Bannered_descriptionTextColor,
                Color.parseColor("#2a2a2a")
            )
        }

        /*Get banner view*/
        bannerViewStrokeWidth = attributes.getDimension(R.styleable.Bannered_strokeWidth, 0F)
        bannerViewKey = attributes.getStringOrThrow(R.styleable.Bannered_key)

        /*Get title text*/
        title = attributes.getString(R.styleable.Bannered_title)
        titleTextSize = attributes.getDimension(R.styleable.Bannered_titleTextSize, resources.getDimension(R.dimen.def_text_size))
        titleEnabled = attributes.getBoolean(R.styleable.Bannered_titleEnabled, true)

        /*Get description text*/
        description = attributes.getString(R.styleable.Bannered_description)
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

        /*title*/
        binding.bannerTitle.apply {
            if (titleEnabled)
                this.visibility = View.VISIBLE
            else
                this.visibility = View.GONE

            this.text = title
            this.setTextColor(titleTextColor)
            this.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize)
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