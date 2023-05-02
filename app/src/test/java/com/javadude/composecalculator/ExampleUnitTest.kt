package com.javadude.composecalculator

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Test

class ExampleUnitTest {
    private var display0 = MutableStateFlow("")
    private var logic = CalculatorLogic {
        display0.value = it
    }
    // add your unit tests here
    @Before
    fun setup() {
        display0 = MutableStateFlow("")
        logic = CalculatorLogic {
            display0.value = it
        }
    }
    @Test
    fun `test add digit`() {
        logic.addDigit(5)
        assertEquals("5", display0.value)
    }

    @Test
    fun `test equals`() {
        logic.addDigit(8)
        logic.equals()
        assertEquals("8.0", display0.value)
    }

    @Test
    fun `test addition`() {
        logic.addDigit(1)
        logic.addDigit(2)
        logic.plus()
        logic.addDigit(8)
        logic.equals()
        assertEquals("20.0", display0.value)
    }

    @Test
    fun `test subtraction`() {      // Should fail since logic is wrong for minus
        logic.addDigit(1)
        logic.addDigit(2)
        logic.minus()
        logic.addDigit(8)
        logic.equals()
        assertEquals("4.0", display0.value)
    }

    @Test
    fun `test multiplication`() {
        logic.addDigit(7)
        logic.addDigit(6)
        logic.times()
        logic.addDigit(5)
        logic.addDigit(4)
        logic.equals()
        assertEquals("4104.0", display0.value)
    }

    @Test
    fun `test division`() {
        logic.addDigit(9)
        logic.addDigit(3)
        logic.divide()
        logic.addDigit(4)
        logic.addDigit(0)
        logic.equals()
        assertEquals("2.325", display0.value)
    }

    @Test
    fun `test remove digit`() {      // Should fail since remove digit removes two numbers
        logic.addDigit(7)
        logic.addDigit(6)
        logic.addDigit(3)
        logic.removeDigit()
        assertEquals("76", display0.value)
    }

    @Test
    fun `test clear`() {
        logic.addDigit(7)
        logic.plus()
        logic.addDigit(6)
        logic.addDigit(3)
        logic.clear()
        logic.equals()
        assertEquals("0.0", display0.value)
    }

    @Test
    fun `test clear entry`() {
        logic.addDigit(7)
        logic.plus()
        logic.addDigit(6)
        logic.addDigit(3)
        logic.clearEntry()
        assertEquals("0.0", display0.value)
        logic.equals()
        assertEquals("7.0", display0.value)
    }

    @Test
    fun `test decimal`() {
        logic.addDigit(7)
        logic.decimal()
        logic.addDigit(6)
        logic.equals()
        assertEquals("7.6", display0.value)
    }

    @Test
    fun `test negate`() {
        logic.addDigit(7)
        logic.negate()
        assertEquals("-7", display0.value)
        logic.equals()
        assertEquals("-7.0", display0.value)
    }
}