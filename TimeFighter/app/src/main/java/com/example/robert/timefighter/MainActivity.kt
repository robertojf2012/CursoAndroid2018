package com.example.robert.timefighter

import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tap_me_button: Button
    internal lateinit var game_score_text_view: TextView
    internal lateinit var time_left_text_view: TextView

    internal var score = 0
    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown: Long = 20000
    internal val countDownInterval: Long = 1000
    internal val TAG = MainActivity::class.java.simpleName
    internal var timeLeftOntimer: Long = 20000

    var startMusic: MediaPlayer? = null
    var endSound: MediaPlayer? = null

    companion object {
        private val SCORE_KEY = "SCORE_KEY"
        private val TIME_LEFT_KEY = "TIME_LEFT_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"onCreate called, score is: $score")

        tap_me_button = findViewById(R.id.tap_me_button)
        game_score_text_view = findViewById(R.id.game_score_text_view)
        time_left_text_view = findViewById(R.id.time_left_text_view)
        game_score_text_view.text = getString(R.string.your_score,score.toString())

        startMusic = MediaPlayer.create(this, R.raw.game)
        endSound = MediaPlayer.create(this, R.raw.endgame)

        if(savedInstanceState!=null){

            score = savedInstanceState.getInt(SCORE_KEY)
            timeLeftOntimer = savedInstanceState.getLong(TIME_LEFT_KEY)
            restoreGame()
        }else{
            resetGame()
        }

        tap_me_button.setOnClickListener {view ->
            val bounceAnimation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            view.startAnimation(bounceAnimation)
            incrementScore()
        }



    }

    private fun restoreGame(){

        game_score_text_view.text = getString(R.string.your_score,score.toString())
        val restoredTime = timeLeftOntimer/1000
        time_left_text_view.text = getString(R.string.time_left,restoredTime.toString())

        countDownTimer = object: CountDownTimer(timeLeftOntimer,countDownInterval){

            override fun onTick(millisUntilFinished: Long) {
                timeLeftOntimer = millisUntilFinished
                val timeLeft = millisUntilFinished/1000
                time_left_text_view.text= getString(R.string.time_left,timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }

        }

        countDownTimer.start()
        gameStarted = true


    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(SCORE_KEY,score)
        outState.putLong(TIME_LEFT_KEY,timeLeftOntimer)
        countDownTimer.cancel()
        Log.d(TAG,"onSaveInstanceState: saving Score: $score & Time Left: $timeLeftOntimer")


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.action_about){
            showInfo()
        }
        if(item.itemId == R.id.action_exit){
            exitGame()
        }
        return true
    }

    private fun exitGame(){
        finishAffinity()
    }


    private fun showInfo(){
        val dialogTitle = getString(R.string.about_title,BuildConfig.VERSION_NAME)
        val dialogMessage = getString(R.string.about_message)
        val builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.create().show()
    }

    private fun resetGame(){
        score = 0
        game_score_text_view.text = getString(R.string.your_score,score.toString())
        val initialTimeLeft = initialCountDown/1000
        time_left_text_view.text = getString(R.string.time_left,initialTimeLeft.toString())

        countDownTimer = object: CountDownTimer(initialCountDown,countDownInterval){

            override fun onTick(millisUntilFinished: Long) {
                timeLeftOntimer = millisUntilFinished
                val timeLeft = millisUntilFinished/1000
                time_left_text_view.text= getString(R.string.time_left,timeLeft.toString())
            }

            override fun onFinish() {
                endGame()
            }

        }
        gameStarted = false
    }

    private fun endGame(){
        startMusic?.stop()
        startMusic?.release()
        startMusic = null
        endSound?.start()

        Toast.makeText(this,getString(R.string.game_over_message,score.toString()),Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun startGame(){
        countDownTimer.start()
        gameStarted = true

        if(startMusic == null){
            startMusic = MediaPlayer.create(this, R.raw.game)
        }
        startMusic?.start()
    }

    private fun incrementScore() {
        if(!gameStarted){
            startGame()
        }
        score = score + 1
        val newScore = getString(R.string.your_score,score.toString())
        game_score_text_view.text = newScore
    }




}
