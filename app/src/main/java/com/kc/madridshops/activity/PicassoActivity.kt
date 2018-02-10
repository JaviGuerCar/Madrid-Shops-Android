package com.kc.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kc.madridshops.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        Picasso.with(this).setIndicatorsEnabled(true)

        Picasso.with(this)
                .load("https://i.pinimg.com/564x/50/54/3a/50543adfc79f3209893aa528d35142ba--internet-memes-the-internet.jpg")
                .fit()
                .placeholder(android.R.drawable.gallery_thumb)
                .into(img1)

        Picasso.with(this)
                .load("http://i0.kym-cdn.com/photos/images/facebook/000/641/298/448.jpg")
                .fit()
                .placeholder(android.R.drawable.gallery_thumb)
                .into(img2)

        Picasso.with(this)
                .load("https://3.bp.blogspot.com/-b78Yw_PkvCQ/WgeZLBoFLSI/AAAAAAAApFU/y5f3C4g1SmQoS_kzhs4mkqwYQLqfqr_zgCLcBGAs/s1600/jarl.jpg")
                .fit()
                .placeholder(android.R.drawable.gallery_thumb)
                .into(img3)
    }
}
