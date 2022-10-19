package com.axichise.flashlightapp

import android.content.Context
import android.hardware.camera2.CameraManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
    private lateinit var cameraM :CameraManager
    private lateinit var powerBtn: ImageButton
    var isOn = false
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        powerBtn = findViewById(R.id.flashlightBtn)
        cameraM = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        powerBtn.setOnClickListener{ flashlightOnOrOff(it)}
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun flashlightOnOrOff(v: View?){
        if(!isOn){
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId,true)
            isOn = true
            powerBtn.setImageResource(R.drawable.torch_on)
            textMessage ("Flashlight is on", this)
        }
        else{
            val cameraListId = cameraM.cameraIdList[0]
            cameraM.setTorchMode(cameraListId, false)
            isOn = false
            powerBtn.setImageResource(R.drawable.torch_off)
            textMessage ("Flashlight is off", this)
        }
    }
    private fun textMessage(s: String, c:Context) {
        Toast.makeText(c,s, Toast.LENGTH_SHORT).show()
    }
}