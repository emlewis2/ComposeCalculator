package com.javadude.composecalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.javadude.composecalculator.ui.theme.ComposeCalculatorTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // Viewmodel for instrumented tests
    private val viewModel = CalculatorViewModel()

    // add your unit tests here
    @Before
    fun setup() {
        // Setup content for instrumented test
        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    // Display value for individual test
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = {paddingValues ->
                            Calculator(
                                display = display,
                                viewModel = viewModel,
                                modifier = Modifier.padding(paddingValues)
                            )
                        }
                    )
                }
            }
        }
    }

    // add your instrumented tests here

    // Not really a test, but used to print semantic tree for developing tests
    @Test
    fun printSemanticTree() {
        composeTestRule.onRoot().printToLog("MY TAG")
    }

    // Ensure each button has the correct text value displayed
    @Test
    fun testUiText() {
        val buttons = listOf("CE", "C", "DEL", "/", "7", "8", "9", "*", "4", "5",
        "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "=")

        buttons.forEach { string ->
            composeTestRule
                .onNodeWithTag("button$string")
                .assertTextEquals(string)
        }
    }

    // Ensure number buttons perform properly
    @Test
    fun testUiButtons() {
        val numbers = listOf("0", "1", "3", "4", "5", "6", "7", "8", "9", "0", "2")

        // Should fail because the button for "2" does not work properly
        numbers.forEach { string ->
            composeTestRule
                .onNodeWithTag("button$string")
                .run {
                    assertTextEquals(string)
                    performClick()
                    composeTestRule
                        .onNodeWithTag("display")
                        .assertTextEquals(string)
                    composeTestRule
                        .onNodeWithTag("buttonC")
                        .performClick()
                }
        }
    }

    // First sequence to test
    @Test
    fun testSequenceOne() {
        // 73 + 8 - 15 * 9 + 8 = 602
        // Should fail since the subtraction method is incorrect
        composeTestRule.onNodeWithTag("button7").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("7")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("73")
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("73.0")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("8")
        composeTestRule.onNodeWithTag("button-").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("81.0")
        composeTestRule.onNodeWithTag("button1").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1")
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("15")
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("66.0")
        composeTestRule.onNodeWithTag("button9").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("9")
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("594.0")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("8")
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("602.0")
    }

    // Second sequence to test
    @Test
    fun testSequenceTwo() {
        // 53 * 6 / 5 + 0.42 = 64.02
        // Should fail since button "2" is implemented incorrectly
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("53")
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("53.0")
        composeTestRule.onNodeWithTag("button6").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("6")
        composeTestRule.onNodeWithTag("button/").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("318.0")
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("63.6")
        composeTestRule.onNodeWithTag("button0").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0")
        composeTestRule.onNodeWithTag("button.").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.")
        composeTestRule.onNodeWithTag("button4").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.4")
        composeTestRule.onNodeWithTag("button2").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.42")
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("64.02")
    }

    // Third sequence to test
    @Test
    fun testSequenceThree() {
        // 19 * 3 + 48 / 7 * 6 / 5 = 18
        composeTestRule.onNodeWithTag("button1").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("1")
        composeTestRule.onNodeWithTag("button9").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("19")
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("19.0")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("3")
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("57.0")
        composeTestRule.onNodeWithTag("button4").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("4")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("48")
        composeTestRule.onNodeWithTag("button/").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("105.0")
        composeTestRule.onNodeWithTag("button7").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("7")
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("15.0")
        composeTestRule.onNodeWithTag("button6").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("6")
        composeTestRule.onNodeWithTag("button/").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("90.0")
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("18.0")
    }

    // Testing clear button functionality
    @Test
    fun testClearButton() {
        // 58 * 3 C -> 0.0
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58")
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58.0")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("3")
        composeTestRule.onNodeWithTag("buttonC").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.0")
    }

    // Testing clear entry button functionality
    @Test
    fun testClearEntryButton() {
        // 58 + 3 CE -> 0.0 = 58.0
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58")
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58.0")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("3")
        composeTestRule.onNodeWithTag("buttonCE").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.0")
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58.0")
    }

    // Testing delete button functionality
    @Test
    fun testDeleteButton() {
        // Should fail since delete button is implemented incorrectly in logic
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("583")
        composeTestRule.onNodeWithTag("buttonDEL").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58")
    }

    // Testing negate button functionality
    @Test
    fun testNegateButton() {
        // -58 + 3 = -55
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("5")
        composeTestRule.onNodeWithTag("button+/-").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-5")
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-58")
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-58.0")
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("3")
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-55.0")
    }
}