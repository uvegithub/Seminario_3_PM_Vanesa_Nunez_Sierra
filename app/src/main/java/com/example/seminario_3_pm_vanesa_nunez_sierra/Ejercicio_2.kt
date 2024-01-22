package com.example.seminario_3_pm_vanesa_nunez_sierra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap

class Ejercicio_2 : AppCompatActivity() {

    private lateinit var boton_imagen: Button
    private lateinit var boton_texto: Button
    var canalId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio2)

        boton_imagen = findViewById(R.id.boton_picture)
        boton_texto = findViewById(R.id.boton_text)

        boton_imagen.setOnClickListener {
            notificacion_imagen(this)
        }

        boton_texto.setOnClickListener {
            notificacion_texto(this)
        }
    }

    fun notificacion_imagen(contexto: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            canalId += 1
            var canalNombre = "canal" + canalId.toString()
            val notificationChannel = NotificationChannel(
                canalId.toString(),
                canalNombre,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            contexto.getSystemService<NotificationManager>()
                ?.createNotificationChannel(notificationChannel)
        }

        val intento = Intent(contexto, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(contexto, 0, intento, PendingIntent.FLAG_UPDATE_CURRENT)

        val intento_boton = Intent(contexto, Ejercicio_2::class.java)
        val pendingIntent_boton =
            PendingIntent.getActivity(contexto, 1, intento_boton, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(contexto, canalId.toString())
            .setSmallIcon(R.drawable.notification_icon_124899)
            .setContentTitle("Captura de pantalla")
            .setContentText("Pulsa para ver la captura de pantalla")
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.notification_icon_124899, "COMPARTIR", pendingIntent_boton)
            .addAction(R.drawable.notification_icon_124899, "BORRAR", pendingIntent_boton)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(getDrawable(R.drawable.granespacio)!!.toBitmap())
            )

        with(contexto.getSystemService<NotificationManager>()) {
            this?.notify(1, builder.build())
        }
    }

    fun notificacion_texto(contexto: Context){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            canalId += 1
            var canalNombre = "canal" + canalId.toString()
            val notificationChannel = NotificationChannel(canalId.toString(), canalNombre, NotificationManager.IMPORTANCE_DEFAULT)
            contexto.getSystemService<NotificationManager>()?.createNotificationChannel(notificationChannel)
        }

        val intento = Intent(contexto, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(contexto, 0, intento, PendingIntent.FLAG_UPDATE_CURRENT)

        val intento_boton = Intent(contexto, Ejercicio_2::class.java)
        val pendingIntent_boton = PendingIntent.getActivity(contexto, 1, intento_boton, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(contexto, canalId.toString())
            .setSmallIcon(R.drawable.notification_icon_124899)
            .setContentTitle("Extenso relato")
//            .setContentText("Pulsa para ver la captura de pantalla")
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.notification_icon_124899,"COMPARTIR", pendingIntent_boton)
            .addAction(R.drawable.notification_icon_124899,"BORRAR", pendingIntent_boton)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.texto))
            )

        with(contexto.getSystemService<NotificationManager>()){
            this?.notify(1,builder.build())
        }
    }
}