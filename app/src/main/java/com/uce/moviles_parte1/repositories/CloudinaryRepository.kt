package com.uce.moviles_parte1.repositories

import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import java.io.File

object CloudinaryRepository {
    fun subirImagenFirmada(
        archivoImagen: File,
        onResultado: (Success: Boolean, urlOError: String) -> Unit
    ) {

        // Obtenemos la ruta absoluta del almacenamiento local
        val rutaLocal = archivoImagen.absolutePath

        // Iniciamos la subida asíncrona mediante el SDK
        MediaManager.get().upload(rutaLocal)
            .callback(object : UploadCallback {

                override fun onStart(requestId: String) {
                    Log.d("CloudinaryService", "Subida iniciada...")
                }

                override fun onProgress(requestId: String, bytes: Long, totalBytes: Long) {
                    val progreso = (bytes.toDouble() / totalBytes * 100).toInt()
                    Log.d("CloudinaryService", "Progreso: $progreso%")
                }

                override fun onSuccess(requestId: String, resultData: Map<*, *>) {
                    // Cloudinary devuelve la URL HTTPS segura dentro del campo "secure_url"
                    val urlSegura = resultData["secure_url"] as? String ?: ""
                    Log.d("CloudinaryService", "¡Éxito! URL: $urlSegura")

                    // Retornamos el éxito al hilo principal
                    onResultado(true, urlSegura)
                }

                override fun onError(
                    requestId: String?,
                    error: ErrorInfo?
                ) {
                    Log.e("CloudinaryService", "Error: ${error?.description}")
                    onResultado(false, error?.description ?: "Error generico")
                }

                override fun onReschedule(
                    requestId: String?,
                    error: ErrorInfo?
                ) {
                    Log.d("CloudinaryService", "Subida reprogramada: ${error?.description}")
                }


            })
            .dispatch() // Envía la ejecución a un hilo secundario de forma nativa
    }



}

