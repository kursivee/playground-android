package com.kursivee.pdf_downloader.ui.main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import com.kursivee.pdf_downloader.R
import com.kursivee.pdf_downloader.ui.util.FileDownloader
import kotlinx.android.synthetic.main.main_fragment.*
import java.io.File

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        val manager = requireActivity().getSystemService(NotificationManager::class.java)?.also {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val name = "channel name"
                val descriptionText = "description"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("1", name, importance).apply {
                    description = descriptionText
                }
                it.createNotificationChannel(channel)
            }
        }
        val builder = NotificationCompat.Builder(requireContext(), "1")
            .setContentText("Downloading")
            .setSmallIcon(R.drawable.ic_notification)

        builder.setProgress(100, 0, false)
        viewModel.fileDownloader = FileDownloader(File("${requireActivity().getExternalFilesDir(null)}", "pdf"))
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            progressBar.progress = it
            builder.setProgress(100, it, false)
            if(it == 100 )  {
                builder.setContentText("Download complete")
            }
            manager!!.notify(0, builder.build())
        })

        button.setOnClickListener {
            viewModel.download()
        }
    }

}
