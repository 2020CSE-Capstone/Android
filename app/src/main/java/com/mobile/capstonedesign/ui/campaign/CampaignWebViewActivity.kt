package com.mobile.capstonedesign.ui.campaign

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Xml
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.mobile.capstonedesign.R
import kotlinx.android.synthetic.main.activity_campaign_web_view.*

class CampaignWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_campaign_web_view)

        supportActionBar?.hide()

        webView.apply {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            settings.defaultTextEncodingName = Xml.Encoding.UTF_8.name
            webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    horizontalProgressBar.visibility = View.GONE
                }
            }
        }



        webView.loadUrl(intent.getStringExtra("link"))
    }
}