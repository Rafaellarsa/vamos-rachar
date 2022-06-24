package com.example.vamosrachar

class Model : Contract.Model {
    private var value: Double = 0.00
    private var people: Int = 0
    private var dividedValue: Double = 0.00
    private var formattedResult: String = ""

    override fun getValue(): Double {
        return value
    }
    override fun setValue(newValue: Double) {
        value = newValue
    }

    override fun getPeople(): Int {
        return people
    }
    override fun setPeople(newPeople: Int) {
        people = newPeople
    }

    override fun getDividedValue(): Double {
        return dividedValue
    }
    override fun setDividedValue(newDividedValue: Double) {
        dividedValue = newDividedValue
    }

    override fun getFormattedResult(): String {
        return formattedResult
    }
    override fun setFormattedResult(newFormattedResult: String) {
        formattedResult = newFormattedResult
    }
}