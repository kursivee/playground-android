package com.kursivee.pdf_downloader.ui.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class FileDownloader(private val folder: File) {
    init {
        if(!folder.exists()) {
            folder.mkdir()
        }
    }

    fun save(inputStream: InputStream, filename: String) {
        val file = File(folder, filename)
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(4096)
        while (true) {
            val read = inputStream.read(buffer)
            if (read == -1) {
                break
            }
            outputStream.write(buffer, 0, read)
        }
        outputStream.flush()
        outputStream.close()
    }

    fun saveWithProgress(inputStream: InputStream, filename: String, fileSize: Long) = flow {
        val file = File(folder, filename)
        val outputStream = FileOutputStream(file)
        val buffer = ByteArray(1024)
        var progress = 0L
        while (true) {
            val read = inputStream.read(buffer)
            if (read == -1) {
                break
            }
            outputStream.write(buffer, 0, read)
            progress += read.toLong()
            emit(progress * 100 / fileSize)
        }
        outputStream.flush()
        outputStream.close()
    }
}