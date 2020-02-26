package com.kursivee.pdf_downloader.ui.main

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PdfApi {
    @GET("samples/pdf.pdf")
    suspend fun getPdf(): Response<ResponseBody>
}