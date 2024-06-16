package de.immanuel_online.myapplication

import android.webkit.PermissionRequest
import android.webkit.WebChromeClient

class CustomWebChromeClient : WebChromeClient() {
    override fun onPermissionRequest(request: PermissionRequest) {
        request.grant(request.resources)
    }
}