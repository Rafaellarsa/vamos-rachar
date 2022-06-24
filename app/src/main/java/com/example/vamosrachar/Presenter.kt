package com.example.vamosrachar

import android.content.Intent
import android.speech.tts.TextToSpeech
import java.text.DecimalFormat

class Presenter(
    private var mainView: Contract.View?,
    private val model: Contract.Model
) : Contract.Presenter {
    private val df = DecimalFormat("#.00")

    override fun getResult(value: Double, people: Int): String {
        model.setValue(value)
        model.setPeople(people)

        divideValue()
        formatResult()
        return model.getFormattedResult()
    }

    override fun divideValue() {
        var dividedValue = model.getValue()
        if (model.getPeople() != 0) dividedValue = model.getValue() / model.getPeople()
        model.setDividedValue(dividedValue)
    }

    override fun formatResult() {
        model.setFormattedResult(mainView!!.getCurrency() + " " + df.format(model.getDividedValue()))
    }

    override fun getMessageIntent(): Intent {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            mainView!!.getMessageToUser()
        )
        return intent
    }

    override fun speak() {
        mainView!!.getTtsPlayer()!!.speak(
            mainView!!.getMessageToUser(),
            TextToSpeech.QUEUE_FLUSH,
            null,
            "ID1"
        )
    }
}