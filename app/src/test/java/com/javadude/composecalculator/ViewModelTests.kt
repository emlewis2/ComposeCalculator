package com.javadude.composecalculator

import app.cash.turbine.test
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ViewModelTests {
    // add your view model tests here
    @Test
    fun `test add digit`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(5)

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("5", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test equals`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(8)
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("8.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test addition`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(1)
        viewModel.addDigit(2)
        viewModel.plus()
        viewModel.addDigit(8)
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("20.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test subtraction`() {  // Should fail since logic is wrong for minus
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(1)
        viewModel.addDigit(2)
        viewModel.minus()
        viewModel.addDigit(8)
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("4.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test multiplication`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(7)
        viewModel.addDigit(6)
        viewModel.times()
        viewModel.addDigit(5)
        viewModel.addDigit(4)
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("4104.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test division`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(9)
        viewModel.addDigit(3)
        viewModel.divide()
        viewModel.addDigit(4)
        viewModel.addDigit(0)
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("2.325", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test remove digit`() {     // Should fail since remove digit removes two numbers
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(7)
        viewModel.addDigit(6)
        viewModel.addDigit(3)
        viewModel.removeDigit()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("76", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test clear`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(7)
        viewModel.plus()
        viewModel.addDigit(6)
        viewModel.addDigit(3)
        viewModel.clear()
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("0.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test clear entry`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(7)
        viewModel.plus()
        viewModel.addDigit(6)
        viewModel.addDigit(3)
        viewModel.clearEntry()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("0.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("7.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test decimal`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(7)
        viewModel.decimal()
        viewModel.addDigit(6)
        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("7.6", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        viewModel.addDigit(7)
        viewModel.decimal()
        viewModel.addDigit(6)
        viewModel.decimal()
        viewModel.addDigit(5)

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("7.65", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun `test negate`() {
        val viewModel = CalculatorViewModel()

        viewModel.addDigit(7)
        viewModel.negate()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("-7", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }

        viewModel.equals()

        runBlocking {
            viewModel.display.test {
                TestCase.assertEquals("-7.0", awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}