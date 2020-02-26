package com.kursivee.pdf_downloader.ui.util

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
}