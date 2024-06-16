package de.immanuel_online.myapplication

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.immanuel_online.myapplication.databinding.MainUiBinding
import java.net.MalformedURLException
import java.net.URL

class UiFragment : Fragment() {
    private lateinit var binding: MainUiBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainUiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val oldUrl = requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE)
            .getString(teamsUrlKey, null)
        if (oldUrl != null)
            binding.url.setText(oldUrl)

        binding.direct.setOnClickListener { findNavController().navigate(UiFragmentDirections.openWeb()) }
        binding.openurl.setOnClickListener { openCustomUrl() }
    }

    private fun openCustomUrl() {
        val url = binding.url.text.toString()
        try {
            if (URL(url).host != "teams.microsoft.com")
                throw MalformedURLException()
        } catch (e: MalformedURLException) {
            Toast.makeText(requireContext(), "Malformed URL.", Toast.LENGTH_SHORT).show()
            return
        }
        requireContext().getSharedPreferences(prefName, Context.MODE_PRIVATE).edit()
            .putString(teamsUrlKey, url)
            .apply()
        findNavController().navigate(UiFragmentDirections.openWeb(url))
    }
}

private const val prefName = "AppStorage"
private const val teamsUrlKey = "TeamsUrl"