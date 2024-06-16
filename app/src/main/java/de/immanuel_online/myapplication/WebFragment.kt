package de.immanuel_online.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import de.immanuel_online.myapplication.databinding.TeamsBinding

class WebFragment : Fragment() {
    private lateinit var binding: TeamsBinding
    private val args: WebFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = TeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupWebView()
    }

    private fun setupWebView() = binding.web.apply {
        webViewClient = CustomWebViewClient()
        webChromeClient = CustomWebChromeClient()
        settings.apply {
            @SuppressLint("SetJavaScriptEnabled")
            javaScriptEnabled = true
            useWideViewPort = true
            loadWithOverviewMode = true
            domStorageEnabled = true
            userAgentString =
                "Mozilla/5.0 (X11; Linux x86_64; rv:127.0) Gecko/20100101 Firefox/127.0"
            setSupportZoom(true)
        }
        scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        loadUrl(args.url)
    }
}