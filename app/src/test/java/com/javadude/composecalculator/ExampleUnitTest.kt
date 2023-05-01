package com.javadude.composecalculator

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExampleUnitTest {
    // add your unit tests here
    @Test
    fun `test adding digit`() {
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(5)
        assertEquals("5", display0.value)
    }

    @Test
    fun `test equals`() {
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(8)
        logic.equals()
        assertEquals("8.0", display0.value)
    }

    @Test
    fun `test addition`() {
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(1)
        logic.addDigit(2)
        logic.plus()
        logic.addDigit(8)
        logic.equals()
        assertEquals("20.0", display0.value)
    }

    @Test
    fun `test subtraction`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(1)
        logic.addDigit(2)
        logic.minus()
        logic.addDigit(8)
        logic.equals()
        assertEquals("4.0", display0.value)
    }

    @Test
    fun `test multiplication`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(7)
        logic.addDigit(6)
        logic.times()
        logic.addDigit(5)
        logic.addDigit(4)
        logic.equals()
        assertEquals("4104.0", display0.value)
    }

    @Test
    fun `test division`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(9)
        logic.addDigit(3)
        logic.divide()
        logic.addDigit(4)
        logic.addDigit(0)
        logic.equals()
        assertEquals("2.325", display0.value)
    }

    @Test
    fun `test remove digit`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(7)
        logic.addDigit(6)
        logic.addDigit(3)
        logic.removeDigit()
        assertEquals("76", display0.value)
    }

//    @Test
//    fun `test on display changed`() {      // Should fail since logic is wrong for minus
//        val display0 = MutableStateFlow("")
//        val logic = CalculatorLogic {
//            display0.value = it
//        }
//        logic.addDigit(7)
//        print(logic.onDisplayChanged)
//        assertEquals("7", logic.onDisplayChanged.toString())
//    }

    @Test
    fun `test clear`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(7)
        logic.plus()
        logic.addDigit(6)
        logic.addDigit(3)
        logic.clear()
        logic.equals()
        assertEquals("0.0", display0.value)
    }

    @Test
    fun `test clear entry`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
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
    fun `test decimal`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(7)
        logic.decimal()
        logic.addDigit(6)
        logic.equals()
        assertEquals("7.6", display0.value)
    }

    @Test
    fun `test negate`() {      // Should fail since logic is wrong for minus
        val display0 = MutableStateFlow("")
        val logic = CalculatorLogic {
            display0.value = it
        }
        logic.addDigit(7)
        logic.negate()
        assertEquals("-7", display0.value)
        logic.equals()
        assertEquals("-7.0", display0.value)
    }





}