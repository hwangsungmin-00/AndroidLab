package com.example.ch8_event

import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //뒤로가기 누른 시간 저장
    var initTime=0L
    //멈춘 시간 저장
    var pauseTime=0L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.chronometer.base= SystemClock.elapsedRealtime()+pauseTime
            binding.chronometer.start()

            binding.stopButton.isEnabled=true
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=false
        }

        binding.stopButton.setOnClickListener {
            pauseTime=binding.chronometer.base-SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=true
        }

        binding.resetButton.setOnClickListener {
            pauseTime=0L
            binding.chronometer.base= SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=false
            binding.startButton.isEnabled=true
        }
    }
    //뒤로가기 이벤트
    override fun onKeyDown(KeyCode: Int, event: KeyEvent?): Boolean {
        //뒤로가기 눌렀을 때
        if(KeyCode === KeyEvent.KEYCODE_BACK) {
            //뒤로가기 눌렀거나, 누른 지 3초 지났을 때
            if(System.currentTimeMillis()-initTime>3000){
                Toast.makeText(this, "종료하려면 한 번 더 누르세요!!",
                Toast.LENGTH_SHORT).show()
                initTime=System.currentTimeMillis()
                return true
            }
        }

        return super.onKeyDown(KeyCode, event)
    }
}