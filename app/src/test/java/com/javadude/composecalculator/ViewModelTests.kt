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
}