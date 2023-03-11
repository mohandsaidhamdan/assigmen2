package com.example.assigment2;

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
var count = 0
    // Write a message to the database
    val database = Firebase.database
    val myRef = database.reference
    @SuppressLint("MissingInflatedId")
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnRead = findViewById<Button>(R.id.btnRead)
        val name = findViewById<EditText>(R.id.editname)
        val id = findViewById<EditText>(R.id.editId)
        val age = findViewById<EditText>(R.id.efitage)


        btnSave.setOnClickListener {


            val person = hashMapOf(
                "name" to name ,
                "id" to id ,
                "age" to age
            )
            myRef.child("person").child("$count").setValue(person)
            count ++
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        }

        btnRead.setOnClickListener  {
            // Read from the database
    myRef.child("person").addValueEventListener(object: ValueEventListener{

        override fun onDataChange(snapshot: DataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            val value = snapshot.getValue()
            Log.d("ContentValues", "Value is: " + value)
        }

        override fun onCancelled(error: DatabaseError) {
            Log.w("ContentValues", "Failed to read value.", error.toException())
        }
        })
    }

    }
}