package com.example.vamosrachar

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), TextWatcher, View.OnClickListener,
    TextToSpeech.OnInitListener, Contract.View {
    private var valueInput: EditText? = null
    private var peopleInput: EditText? = null
    private var result: TextView? = null
    private var share: FloatingActionButton? = null
    private var speak: FloatingActionButton? = null
    private var ttsPlayer: TextToSpeech? = null

    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        valueInput = findViewById(R.id.valueInput)
        peopleInput = findViewById(R.id.peopleInput)
        result = findViewById(R.id.resultTextView)
        share = findViewById(R.id.shareButton)
        speak = findViewById(R.id.speakButton)

        presenter = Presenter(this, Model())

        valueInput?.addTextChangedListener(this)
        peopleInput?.addTextChangedListener(this)
        share?.setOnClickListener(this)
        speak?.setOnClickListener(this)

        ttsPlayer = TextToSpeech(this, this)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

    override fun afterTextChanged(p0: Editable?) {
            val value: Double = valueInput?.text.toString().toDouble()
            val people: Int = peopleInput?.text.toString().toInt()
            result?.text = presenter!!.getResult(value, people)
    }

    override fun getCurrency(): String {
        return getString(R.string.value)
    }

    override fun getMessageToUser(): String {
        return getString(R.string.valuePerPerson) + result?.text.toString() + getString(R.string.exchange)
    }

    override fun getTtsPlayer(): TextToSpeech? {
        return ttsPlayer
    }

    override fun onClick(p0: View?) {
        if (p0 == share) {
            val intent = presenter!!.getMessageIntent()
            startActivity(intent)
        } else if (p0 == speak && ttsPlayer != null) {
            presenter!!.speak()
        }
    }

    override fun onInit(initStatus: Int) {}
}