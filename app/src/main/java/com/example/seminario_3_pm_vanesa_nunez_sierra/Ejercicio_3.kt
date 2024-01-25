package com.example.seminario_3_pm_vanesa_nunez_sierra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import androidx.core.graphics.drawable.toBitmap

class Ejercicio_3 : AppCompatActivity() {

    private lateinit var boton_imagen: Button
    private lateinit var titulo: EditText
    private lateinit var texto: EditText

    private lateinit var boton_mas: Button
    private lateinit var boton_menos: Button

    private lateinit var vistaBotones: TextView

    var canalId:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio3)

        boton_imagen = findViewById(R.id.boton_noti)
        titulo = findViewById(R.id.textinputedittexttitulo)
        texto = findViewById(R.id.textinputedittexttexto)

        boton_mas=findViewById(R.id.boton_mas)
        boton_menos=findViewById(R.id.boton_menos)
        vistaBotones=findViewById(R.id.numero_botones)

        var  n_botones = vistaBotones.text.toString().toInt()

        boton_mas.setOnClickListener {
            n_botones += 1
        }

        boton_menos.setOnClickListener {
            n_botones-=1
        }

        boton_imagen.setOnClickListener {
            notificacion_imagen(this)
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

        val intento_boton = Intent(this, Ejercicio_3::class.java)
        val pendingIntent_boton =
            PendingIntent.getActivity(this, 1, intento_boton, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(this, canalId.toString())
            .setSmallIcon(R.drawable.notification_icon_124899)
            .setContentTitle(titulo.text.toString())
            .setContentText(texto.text.toString())
//            .setContentIntent(pendingIntent)
            .addAction(R.drawable.notification_icon_124899, "COMPARTIR", pendingIntent_boton)
            .addAction(R.drawable.notification_icon_124899, "BORRAR", pendingIntent_boton)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(getDrawable(R.drawable.granespacio)!!.toBitmap())
            )

        with(this.getSystemService<NotificationManager>()) {
            this?.notify(1, builder.build())
        }
    }
}