package com.capstone.craftopia

import MessageAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatterActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var editTextMessageInput: EditText
    private lateinit var buttonSend: Button
    private lateinit var navBarBottom: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatter)

        // Initialize views
        recyclerView = findViewById(R.id.recycler_view_chat)
        editTextMessageInput = findViewById(R.id.edit_text_message_input)
        buttonSend = findViewById(R.id.button_send)
        navBarBottom = findViewById(R.id.navbar_bottom)

        // Set up RecyclerView
        messageAdapter = MessageAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ChatterActivity)
            adapter = messageAdapter
        }

        // Set click listener for send button
        buttonSend.setOnClickListener {
            val message = editTextMessageInput.text.toString().trim()
            if (message.isNotEmpty()) {
                messageAdapter.addMessage(message)
                editTextMessageInput.text.clear()
                // Scroll to the bottom of RecyclerView
                recyclerView.scrollToPosition(messageAdapter.itemCount - 1)
            }
        }

        // Bottom Navigation View Listener
        navBarBottom.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this, HomeActivity::class.java))
                    true
                }
                R.id.nav -> {
                    // Start for NavigationActivity
                    Toast.makeText(this, "Navigation Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.fav -> {
                    // Start for FavoritesActivity
                    Toast.makeText(this, "Favorites Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.order -> {
                    // Start for OrderlistActivity
                    startActivity(Intent(this, Orderlist2Activity::class.java))
                    true
                }
                R.id.profile -> {
                    // Start for ProfileActivity
                    Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
