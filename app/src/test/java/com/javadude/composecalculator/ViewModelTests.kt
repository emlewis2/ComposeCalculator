package com.javadude.composecalculator

import app.cash.turbine.test
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ViewModelTests {
    // add your view model tests here
    private val viewModel = CalculatorViewModel()

    private fun checkDisplay(string: String) {
        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals(string, awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    //Testing add digit function in viewmodel
    @Test
    fun `test add digit`() {
        viewModel.addDigit(5)
        checkDisplay("5")
    }

    //Testing equals function in viewmodel
    @Test
    fun `test equals`() {
        viewModel.addDigit(8)
        checkDisplay("8")
        viewModel.equals()
        checkDisplay("8.0")
    }

    //Testing addition function in viewmodel
    @Test
    fun `test addition`() {
        // 12 + 8 = 20.0
        viewModel.addDigit(1)
        checkDisplay("1")
        viewModel.addDigit(2)
        checkDisplay("12")
        viewModel.plus()
        checkDisplay("12.0")
        viewModel.addDigit(8)
        checkDisplay("8")
        viewModel.equals()
        checkDisplay("20.0")
    }

    //Testing subtraction function in viewmodel
    @Test
    fun `test subtraction`() {  // Should fail since logic is wrong for minus
        // 12 - 8 = 4.0
        viewModel.addDigit(1)
        checkDisplay("1")
        viewModel.addDigit(2)
        checkDisplay("12")
        viewModel.minus()
        checkDisplay("12.0")
        viewModel.addDigit(8)
        checkDisplay("8")
        viewModel.equals()
        checkDisplay("4.0")
    }

    //Testing multiplication function in viewmodel
    @Test
    fun `test multiplication`() {
        // 76 * 54 = 4104.0
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.addDigit(6)
        checkDisplay("76")
        viewModel.times()
        checkDisplay("76.0")
        viewModel.addDigit(5)
        checkDisplay("5")
        viewModel.addDigit(4)
        checkDisplay("54")
        viewModel.equals()
        checkDisplay("4104.0")
    }

    //Testing division function in viewmodel
    @Test
    fun `test division`() {
        // 93 / 40 = 2.325
        viewModel.addDigit(9)
        checkDisplay("9")
        viewModel.addDigit(3)
        checkDisplay("93")
        viewModel.divide()
        checkDisplay("93.0")
        viewModel.addDigit(4)
        checkDisplay("4")
        viewModel.addDigit(0)
        checkDisplay("40")
        viewModel.equals()
        checkDisplay("2.325")
    }

    //Testing remove digit function in viewmodel
    @Test
    fun `test remove digit`() {     // Should fail since remove digit removes two numbers
        // 763 -> 76
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.addDigit(6)
        checkDisplay("76")
        viewModel.addDigit(3)
        checkDisplay("763")
        viewModel.removeDigit()
        checkDisplay("76")
    }

    //Testing clear function in viewmodel
    @Test
    fun `test clear`() {
        // 7 + 63 C = 0.0
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.plus()
        checkDisplay("7.0")
        viewModel.addDigit(6)
        checkDisplay("6")
        viewModel.addDigit(3)
        checkDisplay("63")
        viewModel.clear()
        checkDisplay("0.0")
        viewModel.equals()
        checkDisplay("0.0")
    }

    //Testing clear entry function in viewmodel
    @Test
    fun `test clear entry`() {
        // 7 + 63 CE = 7.0
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.plus()
        checkDisplay("7.0")
        viewModel.addDigit(6)
        checkDisplay("6")
        viewModel.addDigit(3)
        checkDisplay("63")
        viewModel.clearEntry()
        checkDisplay("0.0") // Display should show 0.0 after CE
        viewModel.equals()
        checkDisplay("7.0") // Hitting equals should return display to 7 + 0.0 = 7.0
    }

    //Testing decimal function in viewmodel
    @Test
    fun `test decimal`() {
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.decimal()
        checkDisplay("7.")
        viewModel.addDigit(6)
        checkDisplay("7.6")
        viewModel.equals()
        checkDisplay("7.6")

        // Testing that hitting decimal when a decimal is already present will not add another period
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.decimal()
        checkDisplay("7.")
        viewModel.addDigit(6)
        checkDisplay("7.6")
        viewModel.decimal()
        checkDisplay("7.6")
        viewModel.addDigit(5)
        checkDisplay("7.65")
    }

    //Testing negate function in viewmodel
    @Test
    fun `test negate`() {
        viewModel.addDigit(7)
        checkDisplay("7")
        viewModel.negate()
        checkDisplay("-7")
        viewModel.equals()
        checkDisplay("-7.0")
    }
}