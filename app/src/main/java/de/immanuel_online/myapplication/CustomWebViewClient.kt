package de.immanuel_online.myapplication

import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient

class CustomWebViewClient : WebViewClient() {
    @Deprecated("Deprecated in Java")
    override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
        view.loadUrl(url)
        CookieManager.getInstance().setAcceptCookie(true)
        return true
    }
}