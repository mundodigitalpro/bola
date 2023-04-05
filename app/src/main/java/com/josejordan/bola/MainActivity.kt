package com.josejordan.bola

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var ballView: BallView

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        ballView = BallView(this)
        setContentView(ballView)
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN

    }

    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onStop() {
        sensorManager.unregisterListener(this)
        super.onStop()
    }

    override fun onSensorChanged(sensorEvent: SensorEvent?) {
        if (sensorEvent?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
            val xFactor = 0.08f // ajustar el factor de multiplicación según sea necesario
            val yFactor = 0.08f // ajustar el factor de multiplicación según sea necesario
            val xAccel = -sensorEvent.values[0] * xFactor
            val yAccel = sensorEvent.values[1] * yFactor // usar un signo negativo para invertir la dirección en el eje Y
            ballView.setXAccel(xAccel)
            ballView.setYAccel(yAccel)
            ballView.updateBall()
        }
    }


    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

}
