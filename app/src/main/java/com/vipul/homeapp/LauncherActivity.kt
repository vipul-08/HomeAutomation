package com.vipul.homeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LauncherActivity : AppCompatActivity() {

    private lateinit var drawingRoom: Button
    private lateinit var eshalsRoom: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)

        drawingRoom = findViewById(R.id.drawingRoom)
        eshalsRoom = findViewById(R.id.eshalsRoom)

        drawingRoom.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomName", "Room1")
            startActivity(intent)
        }

        eshalsRoom.setOnClickListener {
            val intent = Intent(this, RoomActivity::class.java)
            intent.putExtra("roomName", "Room2")
            startActivity(intent)
        }
    }
}