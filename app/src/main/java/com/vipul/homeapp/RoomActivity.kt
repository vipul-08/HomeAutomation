 package com.vipul.homeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.firebase.database.*

 class RoomActivity : AppCompatActivity() {
    private lateinit var dbRef: DatabaseReference
    private lateinit var roomNameString: String

    private lateinit var roomName: TextView

    private lateinit var lightLayout: LinearLayout
    private lateinit var fanLayout: LinearLayout
    private lateinit var nightBulbLayout: LinearLayout
    private lateinit var socketLayout: LinearLayout

     private lateinit var lightText: TextView
     private lateinit var fanText: TextView
     private lateinit var nightBulbText: TextView
     private lateinit var socketText: TextView

     private lateinit var lightImage: ImageView
     private lateinit var fanImage: ImageView
     private lateinit var nightBulbImage: ImageView
     private lateinit var socketImage: ImageView

     private var lightVal: Boolean = false
     private var fanVal: Boolean = false
     private var nightBulbVal: Boolean = false
     private var socketVal: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room)

        roomNameString = intent.getStringExtra("roomName")!!
        dbRef = FirebaseDatabase.getInstance().reference.child("HomeAutomation").child(roomNameString)

        roomName = findViewById(R.id.roomName)

        roomName.text = if (roomNameString == "Room1") "Drawing Room" else "Eshal's Room"

        lightLayout = findViewById(R.id.lightLayout)
        fanLayout = findViewById(R.id.fanLayout)
        nightBulbLayout = findViewById(R.id.nightBulbLayout)
        socketLayout = findViewById(R.id.socketLayout)

        lightText = findViewById(R.id.lightText)
        fanText = findViewById(R.id.fanText)
        nightBulbText = findViewById(R.id.nightBulbText)
        socketText = findViewById(R.id.socketText)

        lightImage = findViewById(R.id.lightImage)
        fanImage = findViewById(R.id.fanImage)
        nightBulbImage = findViewById(R.id.nightBulbImage)
        socketImage = findViewById(R.id.socketImage)

        lightLayout.setOnClickListener {
            dbRef.child("Light").setValue(!lightVal)
        }
        fanLayout.setOnClickListener {
            dbRef.child("Fan").setValue(!fanVal)
        }
        nightBulbLayout.setOnClickListener {
            dbRef.child("NightBulb").setValue(!nightBulbVal)
        }
        socketLayout.setOnClickListener {
            dbRef.child("Socket").setValue(!socketVal)
        }

        dbRef.child("Light").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value == true) {
                    lightVal = true
                    lightText.text = "Light: ON"
                    lightImage.setImageResource(R.drawable.light_on)
                } else {
                    lightVal = false
                    lightText.text = "Light: OFF"
                    lightImage.setImageResource(R.drawable.light_off)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        dbRef.child("Fan").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value == true) {
                    fanVal = true
                    fanText.text = "Fan: ON"
                    fanImage.setImageResource(R.drawable.fan_on)
                } else {
                    fanVal = false
                    fanText.text = "Fan: OFF"
                    fanImage.setImageResource(R.drawable.fan_off)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        dbRef.child("NightBulb").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value == true) {
                    nightBulbVal = true
                    nightBulbText.text = "Bulb: ON"
                    nightBulbImage.setImageResource(R.drawable.bulb_on)
                } else {
                    nightBulbVal = false
                    nightBulbText.text = "Bulb: OFF"
                    nightBulbImage.setImageResource(R.drawable.bulb_off)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        dbRef.child("Socket").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.value
                if (value == true) {
                    socketVal = true
                    socketText.text = "Socket: ON"
                    socketImage.setImageResource(R.drawable.socket_on)
                } else {
                    socketVal = false
                    socketText.text = "Socket: OFF"
                    socketImage.setImageResource(R.drawable.socket_off)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}