package com.example.jan.textcolorbraingame

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.view.GestureDetectorCompat
import android.view.GestureDetector
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

const val EXTRA_MESSAGE = "com.example.jan.TextColorBrainGame.Score"


class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener{


    private lateinit var mDetector : GestureDetectorCompat
    var score = 0
    var isItStopped : Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mDetector = GestureDetectorCompat(this,this)
        tvScore.setText(score.toString())
        randomColour()
        randomText()
        gameTimer()
    }

    /************************Start Timer and new Activity***************************/
    public fun isStopped(): Boolean {
        return isItStopped
    }

    private fun gameTimer() {
       object : CountDownTimer(10000, 1000) {

        override fun onTick(millisUntilFinished: Long) {
                tvTime.setText(""+millisUntilFinished / 1000 + "s")

            if(isStopped()) {
                cancel()
                startResultActivity()
            }
            }

            override fun onFinish() {
                startResultActivity()


            }
        }.start()
    }

    fun startResultActivity() {
        val intent = Intent(this, ResultActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, score.toString())
        }
        startActivity(intent)

    }

    /*****************************Generating the Question*****************************/
//        val red = 0
//        val blue = 1
//        val yellow = 2
//        val green = 3
//        val black = 4

    fun randomText(){

        val rnd = Random()
        var colourNum = rnd.nextInt(5)
        if (colourNum == 0) {
            tvQuestion.setText("red")
        } else if (colourNum == 1) {
            tvQuestion.setText("blue")

        }else if (colourNum == 2) {
            tvQuestion.setText("yellow")

        }else if (colourNum == 3) {
            tvQuestion.setText("green")

        }else if (colourNum == 4) {
            tvQuestion.setText("black")
        }
    }

    fun randomColour(){
        val rnd = Random()
        var colourNum = rnd.nextInt(5)
        if (colourNum == 0) {
            tvQuestion.setTextColor(-65536)
        } else if (colourNum == 1) {
            tvQuestion.setTextColor( -256)

        }else if (colourNum == 2) {
            tvQuestion.setTextColor(-16776961)

        }else if (colourNum == 3) {
            tvQuestion.setTextColor(-16711936)

        }else if (colourNum == 4) {
            tvQuestion.setTextColor(-16777216)

        }
    }

    /******************Check if Answer Color and Text are correct***********************/

    fun getColorInt() : Int {
        return tvQuestion.currentTextColor
    }

    fun isTextColor() : Boolean {
        if(tvQuestion.text.equals("red") && getColorInt() == -65536){
            return true;
        }else if(tvQuestion.text.equals("blue") && getColorInt() == -16776961){
            return true;
        }else if(tvQuestion.text.equals("yellow") && getColorInt() ==  -256){
            return true;
        }else if(tvQuestion.text.equals("green") && getColorInt() == -16711936){
            return true;
        }else if(tvQuestion.text.equals("black") && getColorInt() == -16777216){
            return true;
        } else {
            return false;
        }
    }


    /********************************************************************************/
    /*******************************Gesture Detector Methods***********************/
    /*******************************************************************************/
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }
    override fun onShowPress(e: MotionEvent?) {
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        if (velocityX > 0) {
            if (isTextColor()) {
                score++
            } else {
                score--
                isItStopped = true
            }
        } else{
            if (isTextColor()) {
                score--
                isItStopped = true



            } else {
                score++
            }
        }
        randomColour()
        randomText()
        tvScore.setText("$score")
        return true
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
    }

    override fun onBackPressed() {
        isItStopped = true
        
    }

}
