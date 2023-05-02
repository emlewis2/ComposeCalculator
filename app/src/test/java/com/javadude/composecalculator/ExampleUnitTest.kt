package com.javadude.composecalculator

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    // Display value for each test
    private var display0 = MutableStateFlow("")
    // Calculator logic instance for each test
    private var logic = CalculatorLogic {
        display0.value = it
    }
    // add your unit tests here
    @Before
    fun setup() {
        // Set display to empty initially for each test
        display0 = MutableStateFlow("")
        // CalculatorLogic instance for individual test
        logic = CalculatorLogic {
            display0.value = it
        }
    }

    // Testing add digit function
    @Test
    fun `test add digit`() {
        logic.addDigit(5)
        assertEquals("5", display0.value)
    }

    // Testing equals function
    @Test
    fun `test equals`() {
        logic.addDigit(8)
        assertEquals("8", display0.value)
        logic.equals()
        assertEquals("8.0", display0.value)
    }

    // Testing addition function
    @Test
    fun `test addition`() {
        // 12 + 8 = 20.0
        logic.addDigit(1)
        assertEquals("1", display0.value)
        logic.addDigit(2)
        assertEquals("12", display0.value)
        logic.plus()
        assertEquals("12.0", display0.value)
        logic.addDigit(8)
        assertEquals("8", display0.value)
        logic.equals()
        assertEquals("20.0", display0.value)
    }

    // Testing subtraction function
    @Test
    fun `test subtraction`() {      // Should fail since logic is wrong for minus
        // 12 - 8 = 4.0
        logic.addDigit(1)
        assertEquals("1", display0.value)
        logic.addDigit(2)
        assertEquals("12", display0.value)
        logic.minus()
        assertEquals("12.0", display0.value)
        logic.addDigit(8)
        assertEquals("8", display0.value)
        logic.equals()
        assertEquals("4.0", display0.value)
    }

    // Testing multiplication function
    @Test
    fun `test multiplication`() {
        // 76 * 54 = 4140.0
        logic.addDigit(7)
        assertEquals("7", display0.value)
        logic.addDigit(6)
        assertEquals("76", display0.value)
        logic.times()
        assertEquals("76.0", display0.value)
        logic.addDigit(5)
        assertEquals("5", display0.value)
        logic.addDigit(4)
        assertEquals("54", display0.value)
        logic.equals()
        assertEquals("4104.0", display0.value)
    }

    // Testing division function
    @Test
    fun `test division`() {
        // 93 / 40 = 2.325
        logic.addDigit(9)
        assertEquals("9", display0.value)
        logic.addDigit(3)
        assertEquals("93", display0.value)
        logic.divide()
        assertEquals("93.0", display0.value)
        logic.addDigit(4)
        assertEquals("4", display0.value)
        logic.addDigit(0)
        assertEquals("40", display0.value)
        logic.equals()
        assertEquals("2.325", display0.value)
    }

    // Testing remove digit function
    @Test
    fun `test remove digit`() {      // Should fail since remove digit removes two numbers
        // 763 -> 76
        logic.addDigit(7)
        assertEquals("7", display0.value)
        logic.addDigit(6)
        assertEquals("76", display0.value)
        logic.addDigit(3)
        assertEquals("763", display0.value)
        logic.removeDigit()
        assertEquals("76", display0.value)
    }

    // Testing clear function
    @Test
    fun `test clear`() {
        // 7 + 63 C = 0.0
        logic.addDigit(7)
        assertEquals("7", display0.value)
        logic.plus()
        assertEquals("7.0", display0.value)
        logic.addDigit(6)
        assertEquals("6", display0.value)
        logic.addDigit(3)
        assertEquals("63", display0.value)
        logic.clear()
        assertEquals("0.0", display0.value)
        logic.equals()
        assertEquals("0.0", display0.value)
    }

    // Testing clear entry function
    @Test
    fun `test clear entry`() {
        // 7 + 63 CE = 7.0
        logic.addDigit(7)
        assertEquals("7", display0.value)
        logic.plus()
        assertEquals("7.0", display0.value)
        logic.addDigit(6)
        assertEquals("6", display0.value)
        logic.addDigit(3)
        assertEquals("63", display0.value)
        logic.clearEntry()
        assertEquals("0.0", display0.value) // Display will show 0.0 after CE
        logic.equals()  // Hitting equals will return display to 7 + 0.0 = 7.0
        assertEquals("7.0", display0.value)
    }

    // Testing decimal function
    @Test
    fun `test decimal`() {
        logic.addDigit(7)
        assertEquals("7", display0.value)
        logic.decimal()
        assertEquals("7.", display0.value)
        logic.addDigit(6)
        assertEquals("7.6", display0.value)
        logic.equals()
        assertEquals("7.6", display0.value)
    }

    // Testing negate function
    @Test
    fun `test negate`() {
        logic.addDigit(7)
        assertEquals("7", display0.value)
        logic.negate()
        assertEquals("-7", display0.value)
        logic.equals()
        assertEquals("-7.0", display0.value)
    }
}