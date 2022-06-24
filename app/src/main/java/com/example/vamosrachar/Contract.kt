package com.example.vamosrachar

import android.content.Intent
import android.speech.tts.TextToSpeech

interface Contract {
    interface Model {
        fun getValue(): Double
        fun setValue(newValue: Double)
        fun getPeople(): Int
        fun setPeople(newPeople: Int)
        fun getDividedValue(): Double
        fun setDividedValue(newDividedValue: Double)
        fun getFormattedResult(): String
        fun setFormattedResult(newFormattedResult: String)
    }

    interface View {
        fun getCurrency(): String
        fun getMessageToUser(): String
        fun getTtsPlayer(): TextToSpeech?
    }

    interface Presenter {
        fun getResult(value: Double, people: Int): String
        fun divideValue()
        fun formatResult()
        fun getMessageIntent(): Intent
        fun speak()
    }
}