package com.medialink.deco16service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log

class MyBoundService : Service() {

    companion object {
        private val TAG = MyBoundService::class.java.simpleName
    }
    private var mBinder = MyBinder()
    private var mTimer: CountDownTimer = object : CountDownTimer(60000, 1000) {
        override fun onFinish() {
            Log.d(TAG, "on finish")
        }

        override fun onTick(millisUntilFinished: Long) {
            val ellapsedTime = System.currentTimeMillis() - startTime
            Log.d(TAG, "on tick: ${ellapsedTime}")
        }

    }
    private val startTime = System.currentTimeMillis()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "on create")
    }

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "on bind")
        mTimer.start()
        return mBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "on destroy")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "on unbind")
        mTimer.cancel()
        return super.onUnbind(intent)
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.d(TAG, "on rebind")
    }

    internal inner class MyBinder : Binder() {
        val getService: MyBoundService = this@MyBoundService
    }
}
