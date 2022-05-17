package com.example.vamosrachar

import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DecimalFormat
import java.util.*


class MainActivity : AppCompatActivity(), TextWatcher, View.OnClickListener, TextToSpeech.OnInitListener {
    private var valueInput: EditText? = null
    private var peopleInput: EditText? = null
    private var result: TextView? = null
    private var share: FloatingActionButton? = null
    private var speak: FloatingActionButton? = null
    private var ttsPlayer: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valueInput = findViewById(R.id.valueInput)
        peopleInput = findViewById(R.id.peopleInput)
        result = findViewById(R.id.resultTextView)
        share = findViewById(R.id.shareButton)
        speak = findViewById(R.id.speakButton)

        valueInput?.addTextChangedListener(this)
        peopleInput?.addTextChangedListener(this)
        share?.setOnClickListener(this)
        speak?.setOnClickListener(this)

        ttsPlayer = TextToSpeech(this, this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
        try {
            val value: Double = valueInput?.text.toString().toDouble()
            val people: Int = peopleInput?.text.toString().toInt()
            val res = if (people == 0) value else value / people
            val df = DecimalFormat("#.00")
            val formattedResult = getString(R.string.value) + " " + df.format(res)
            result?.text = formattedResult
        } catch (e: Exception) {
            val value = getString(R.string.value) + " 0.00"
            result?.text = value
        }
    }

    override fun onClick(p0: View?) {
        if (p0 == share) {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                getString(R.string.valuePerPerson) + result?.text.toString() + getString(R.string.exchange)
            )
            startActivity(intent)
        } else if (p0 == speak && ttsPlayer != null) {
            ttsPlayer!!.speak(
                getString(R.string.valuePerPerson) + result?.text.toString() + getString(R.string.exchange),
                TextToSpeech.QUEUE_FLUSH,
                null,
                "ID1"
            )
        }
    }

    override fun onInit(initStatus: Int) {}
}