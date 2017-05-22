package com.vaporware.reverendcode.colors

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import java.util.*


class MainActivity : AppCompatActivity() {

    val options = listOf<Int>(R.id.buttonBlue,R.id.buttonGreen,R.id.buttonRed,R.id.buttonYellow)
    val game = Game(this,options)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        game.keepGoing(randomColor())

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    fun handleInput(view: View) {
        game.handleInput(view)
    }

    fun randomColor(): Int {
        val random = Random()
        when (random.nextInt(4)) {
            1 -> return R.id.buttonBlue
            2 -> return R.id.buttonRed
            3 -> return R.id.buttonGreen
            else -> return R.id.buttonYellow

        }
    }
}



