package com.vaporware.reverendcode.colors

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.Toast
import java.util.*
import java.util.logging.Handler

/**
 * Created by ReverendCode on 5/19/17.
 *
 * This game object should keep a list of the needed button presses, and when in "receive" mode
 * should listen for user clicks on buttons, each time a button is pressed, check against the next
 * id in the moves iterator. If it matches, advance the iterator, else end the game.
 * if the iterator is able to reach the end of the list. add a new button press to the end of the list
 * and start over
 */

class Game(val parent: Activity, val options: List<Int>) {
    var moves = mutableListOf<Int>()
    var score = 0
    var listening = false
    var iter = 0

    fun add(color: Int) {
        this.score++
        this.moves.add(color)
    }

    fun playSequence() {
//        TODO("make this actually play the sequence")
        var offset = 1L
        for (move in this.moves) {
            this.pushButton(parent.findViewById(move) as Button,offset)
            offset += 1000
        }
    }


    private fun pushButton(button: Button, offset: Long) {
        println(button.text)
        val shake: Animation = AnimationUtils.loadAnimation(parent,R.anim.shake)
        shake.startOffset = offset
        button.startAnimation(shake)
    }

    fun handleInput(view: View) {
        if (this.listening) {
            if (view.id == this.moves[iter]) {
                if (this.hasNext()) {
                    iter++
                } else this.keepGoing(randomColor())

            } else this.endGame()
        }
    }
    private fun randomColor(): Int {
        //TODO: generalize this
        val random = Random()
        return options[random.nextInt(options.size) - 1]
    }

    fun hasNext(): Boolean {
        return (iter+1 < moves.size)
    }

    fun keepGoing(button: Int? = null) {
        this.score++
        this.listening = false
        this.iter = 0
        this.moves.add(button ?: randomColor())
        this.playSequence()
        this.listening = true
    }

    fun endGame() {
        Toast.makeText(parent,"Final Score: " + this.score,Toast.LENGTH_SHORT).show()
        this.iter = 0
        this.moves.clear()
        this.score = 0
        this.keepGoing(randomColor())
    }
}
