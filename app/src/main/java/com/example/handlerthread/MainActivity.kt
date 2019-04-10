package com.example.handlerthread

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Handler

class MainActivity : AppCompatActivity() {
    private lateinit var handler: Looper
    private var counter: Int = 0
    private lateinit var thread: Thread
    private var msg: Message? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }


    private fun init() {
        handler = Looper.getMainLooper()
        handler.setMessageLogging {
            tvCounter.text = "$counter"
            tvCounterStyle.text = "$counter"
        }
        thread = Thread(Runnable {
            kotlin.run {
                for (i in 1 until 100) {
                    counter++
                    try {
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        return@run
                    }
                }
            }
        })
        thread.start()
    }

}
