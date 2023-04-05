package com.josejordan.bola

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.R)
class BallView(context: Context?) : View(context) {
    private val rebound = 0.6f
    private val friction = 0.98f

    private var xPos = 0.0f
    private var xAccel = 0.0f
    private var xVel = 0.0f
    private var yPos = 0.0f
    private var yAccel = 0.0f
    private var yVel = 0.0f
    private var xMax = 0f
    private var yMax = 0f
    private var ballSrc: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.ball)
    private val dstWidth = 100
    private val dstHeight = 100
    private var ball = Bitmap.createScaledBitmap(ballSrc, dstWidth, dstHeight, true);

    //xAccel setter y getter
    fun setXAccel(xAccel: Float) {
        this.xAccel = xAccel
    }

    //yAccel setter y getter
    fun setYAccel(yAccel: Float) {
        this.yAccel = yAccel
    }

/*    init {
        val size = Point()
        val display = (context as MainActivity).windowManager.defaultDisplay
        display.getSize(size)
        xMax = size.x.toFloat() - dstWidth
        yMax = size.y.toFloat() - dstHeight
    }*/

/*
    init {
        val metrics = DisplayMetrics()
        val windowManager = (context as MainActivity).windowManager
        windowManager.defaultDisplay.getMetrics(metrics)
        xMax = metrics.widthPixels.toFloat() - dstWidth
        yMax = metrics.heightPixels.toFloat() - dstHeight
    }
*/
/*init {
    val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val display = windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    xMax = size.x.toFloat() - dstWidth
    yMax = size.y.toFloat() - dstHeight
}*/

/*    init {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = context.display
        val size = Point()
        display?.getSize(size)
        xMax = size.x.toFloat() - dstWidth
        yMax = size.y.toFloat() - dstHeight
    }*/

/*
    init {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        xMax = size.x.toFloat() - dstWidth
        yMax = size.y.toFloat() - dstHeight
    }
*/

    init {
        val windowManager = context?.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val metrics = windowManager.currentWindowMetrics.bounds
        xMax = metrics.width().toFloat() - dstWidth
        yMax = metrics.height().toFloat() - dstHeight
    }




/*        fun updateBall() {
            val frameTime = 0.666f
            xVel += xAccel * frameTime
            yVel += yAccel * frameTime
            val xS = xVel * frameTime
            val yS = yVel * frameTime
            xPos += xS
            yPos += yS
            if (xPos > xMax) {
                xPos = xMax
                xVel = 0f
            } else if (xPos < 0) {
                xPos = 0f
                xVel = 0f
            }
            if (yPos > yMax) {
                yPos = yMax
                yVel = 0f
            } else if (yPos < 0) {
                yPos = 0f
                yVel = 0f
            }
        }*/ //funciona
/*    fun updateBall() {
        val frameTime = 0.666f
        xVel += xAccel * frameTime
        yVel += yAccel * frameTime
        val xS = xVel * frameTime
        val yS = yVel * frameTime
        xPos += xS
        yPos += yS
        if (xPos < 0) {
            xPos = 0f
            xVel = 0f
        } else if (xPos > xMax) {
            xPos = xMax
            xVel = 0f
        }
        if (yPos < 0) {
            yPos = 0f
            yVel = 0f
        } else if (yPos > yMax) {
            yPos = yMax
            yVel = 0f
        }
    }*/ //funciona se ve la bola parece que esta elevada con un poco de suelo
/*    fun updateBall() {
        val frameTime = 0.666f
        xVel += xAccel * frameTime
        yVel += yAccel * frameTime
        val xS = xVel * frameTime
        val yS = yVel * frameTime
        xPos += xS
        yPos += yS
        if (xPos < 0) {
            xPos = 0f
            xVel = 0f
        } else if (xPos > xMax) {
            xPos = xMax
            xVel = 0f
        }
        if (yPos < 0) {
            yPos = 0f
            yVel = 0f
        } else if (yPos > yMax - dstHeight) {
            yPos = yMax - dstHeight
            yVel = 0f
        }
    }*/ //funciona se ve la bola pero parece que esta elevada con mas suelo

/*
    fun updateBall() {
        val frameTime = 0.666f
        xVel += xAccel * frameTime
        yVel += yAccel * frameTime
        val xS = xVel * frameTime
        val yS = yVel * frameTime
        xPos += xS
        yPos += yS

        // Detectar colisiones con las paredes
        if (xPos < 0) {
            xPos = 0f
            xVel = -xVel * rebound
        } else if (xPos > xMax) {
            xPos = xMax
            xVel = -xVel * rebound
        }
        if (yPos < 0) {
            yPos = 0f
            yVel = -yVel * rebound
        } else if (yPos > yMax) {
            yPos = yMax
            yVel = -yVel * rebound
        }

        // Aplicar la fricción para desacelerar la bola
        xVel *= friction
        yVel *= friction
    }
*/

    fun updateBall() {
        // Calcula el tiempo transcurrido desde el último frame
        val frameTime = 0.666f

        // Ajusta la velocidad según la aceleración
        xVel += xAccel * frameTime
        yVel += yAccel * frameTime

        // Calcula la cantidad de movimiento en cada eje
        val xS = xVel * frameTime
        val yS = yVel * frameTime

        // Actualiza la posición de la pelota
        xPos += xS
        yPos += yS

        // Detecta colisiones con las paredes
        if (xPos < 0) {
            // Si la pelota choca con la pared izquierda, la hace rebotar y detiene su velocidad en el eje x
            xPos = 0f
            xVel = -xVel * rebound
        } else if (xPos > xMax) {
            // Si la pelota choca con la pared derecha, la hace rebotar y detiene su velocidad en el eje x
            xPos = xMax
            xVel = -xVel * rebound
        }
        if (yPos < 0) {
            // Si la pelota choca con el techo, la hace rebotar y detiene su velocidad en el eje y
            yPos = 0f
            yVel = -yVel * rebound
        } else if (yPos > yMax - dstHeight) {
            // Si la pelota choca con el suelo, la hace rebotar y detiene su velocidad en el eje y
            yPos = yMax - dstHeight
            yVel = -yVel * rebound
        }

        // Aplica fricción para desacelerar la pelota
        xVel *= friction
        yVel *= friction
    }



    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(ball, xPos, yPos, null)
        invalidate()
    }

}
