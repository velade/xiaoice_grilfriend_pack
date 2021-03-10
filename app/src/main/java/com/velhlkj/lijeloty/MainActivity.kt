package com.velhlkj.lijeloty

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.webkit.URLUtil
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.webkit.WebViewClient
import android.widget.Toast
import java.lang.Exception
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    var exitTime = 0L;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var wvsettings = mainWV.settings
        wvsettings.also {
            it.setSupportZoom(false)
            it.javaScriptEnabled = true
            it.domStorageEnabled = true
        }
        mainWV.webViewClient = WVC()

        mainWV.loadUrl("https://ux-plus.xiaoice.com/virtualgirlfriend")
    }

    override fun onBackPressed() {
        var nowurl = mainWV.url.toString().split("?")
        if(nowurl[0] == "https://ux-plus.xiaoice.com/virtualgirlfriend"){
            if(System.currentTimeMillis() - exitTime > 2000) {
                Toast.makeText(this,R.string.press_exit, Toast.LENGTH_SHORT).show()
                exitTime = System.currentTimeMillis()
            } else {
                finish()
                exitProcess(0)
            }
        }else{
            mainWV.goBack()
        }
    }
}

class WVC : WebViewClient() {
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if(URLUtil.isNetworkUrl(url)) return false
        try {
            var intent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
            view?.context?.startActivity(intent);
        }catch (e: Exception) {

        }
        return true;
    }
}