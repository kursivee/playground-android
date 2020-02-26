package com.kursivee.pdf_downloader.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kursivee.pdf_downloader.ui.util.FileDownloader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

class MainViewModel : ViewModel() {
    lateinit var fileDownloader: FileDownloader

    private val retrofit = Retrofit.Builder().baseUrl("http://www.pdf995.com/").build()
    private val api = retrofit.create(PdfApi::class.java)

    private val _progress: MutableLiveData<Int> = MutableLiveData()
    val progress: LiveData<Int> = _progress

    fun download() {
        _progress.value = 0
        viewModelScope.launch {
            api.getPdf().body()?.let {
                fileDownloader
                    .saveWithProgress(it.byteStream(), "b.pdf", it.contentLength())
                    .collect { progression ->
                        _progress.postValue(progression.toInt())
                    }
            }
        }
    }
}