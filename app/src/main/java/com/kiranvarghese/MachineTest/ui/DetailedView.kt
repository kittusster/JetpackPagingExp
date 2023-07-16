package com.kiranvarghese.MachineTest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiranvarghese.MachineTest.R
import kotlinx.android.synthetic.main.activity_detailed_view.*

class DetailedView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view)
        val bundle:Bundle = intent.extras!!


        val firstnameandlastname = bundle.get("firstnameandlastname")
        val language = bundle.get("domain")
        val s= bundle.get("image")
        text_view_headquarters.text=firstnameandlastname.toString() +
              "\n"+   "Domain = " +language.toString() +
        "\n"+ "BirthDate = "   +bundle.get("birthDate") +
                "\n"+ "BloodGroup = " + bundle.get("bloodGroup") +
                "\n"+ "Ein = " +bundle.get("ein") +
                "\n"+ "Email = " + bundle.get("email") +
                "\n"+ "EyeColor = " + bundle.get("eyeColor") +
                "\n"+ "MaidenName = " + bundle.get("maidenName") +
                "\n"+ "Phone = " + bundle.get("phone") +
                "\n"+ "Ssn = " + bundle.get("ssn") +
                "\n"+ "Height = " + bundle.get("height")
    image_view_airlines_logo.loadImage(s.toString())

    }
}


