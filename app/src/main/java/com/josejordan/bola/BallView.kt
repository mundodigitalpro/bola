package com.josejordan.bola

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import android.view.View

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

    init {
        val size = Point()
        val display = (context as MainActivity).windowManager.defaultDisplay
        display.getSize(size)
        xMax = size.x.toFloat() - dstWidth
        yMax = size.y.toFloat() - dstHeight
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


    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(ball, xPos, yPos, null)
        invalidate()
    }

}
