package com.kursivee.pdf_downloader.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        viewModel.fileDownloader = FileDownloader(File("${requireActivity().getExternalFilesDir(null)}", "pdf"))
        viewModel.progress.observe(viewLifecycleOwner, Observer {
            progressBar.progress = it
        })
        button.setOnClickListener {
            viewModel.download()
        }
    }

}
