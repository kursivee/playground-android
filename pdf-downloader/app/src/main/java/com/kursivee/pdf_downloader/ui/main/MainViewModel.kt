package com.kursivee.pdf_downloader.ui.main

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.kursivee.pdf_downloader.ui.util.FileDownloader
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Streaming
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class MainViewModel : ViewModel() {
    // I know this isn't the way to do it
    lateinit var activity: Activity

    private val retrofit = Retrofit.Builder().baseUrl("http://www.pdf995.com/").build()

    fun download() {
        val api = retrofit.create(PdfApi::class.java)
        GlobalScope.launch {
            api.getPdf().body()?.let {
                FileDownloader(File("${activity.getExternalFilesDir(null)}", "pdf"))
                    .save(it.byteStream(), "b.pdf")
            }
        }
    }
}

interface PdfApi {
    @GET("samples/pdf.pdf")
    suspend fun getPdf(): Response<ResponseBody>
}