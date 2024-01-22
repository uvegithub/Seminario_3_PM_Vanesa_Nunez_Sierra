 package com.example.seminario_3_pm_vanesa_nunez_sierra

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService

 class Ejercicio_1 : AppCompatActivity() {

    private lateinit var boton_lanzar_notificacion: Button
    var canalId:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ejercicio1)

        boton_lanzar_notificacion = findViewById(R.id.boton_ejer1)

        boton_lanzar_notificacion.setOnClickListener {
            mostrar_notificacion(this)
        }
    }

    fun mostrar_notificacion(contexto: Context){
        if(Build.VERSION.SDK_INT >= VERSION_CODES.O){
            canalId += 1
            var canalNombre = "canal" + canalId.toString()
            val notificationChannel = NotificationChannel(canalId.toString(), canalNombre, NotificationManager.IMPORTANCE_DEFAULT)
            contexto.getSystemService<NotificationManager>()?.createNotificationChannel(notificationChannel)
        }

        val intento = Intent(contexto, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(contexto, 0, intento, PendingIntent.FLAG_UPDATE_CURRENT)

        val intento_boton = Intent(contexto, Ejercicio_1::class.java)
        val pendingIntent_boton = PendingIntent.getActivity(contexto, 1, intento_boton, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(contexto, canalId.toString())
            .setSmallIcon(R.drawable.notification_icon_124899)
            .setContentTitle("Notificacion")
            .setContentText("Esto es una notificacion. Mi id es "+canalId)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.notification_icon_124899,"Volver al ejercicio 1", pendingIntent_boton)

        with(contexto.getSystemService<NotificationManager>()){
            this?.notify(1,builder.build())
        }
    }
}