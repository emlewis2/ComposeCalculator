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

    private val viewModel = CalculatorViewModel()

    // add your unit tests here
    @Before
    fun setup() {
        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
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

    @Test
    fun testSequenceOne() {
        // 73 + 8 - 15 * 9 + 8 = 602
        // Should fail since the subtraction method is incorrect
        composeTestRule.onNodeWithTag("button7").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button-").performClick()
        composeTestRule.onNodeWithTag("button1").performClick()
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("button9").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("602.0")
    }

    @Test
    fun testSequenceTwo() {
        // 23 * 6 / 5 + 0.41 = 28.01
        // Should fail since button "2" is implemented incorrect
        composeTestRule.onNodeWithTag("button2").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("button6").performClick()
        composeTestRule.onNodeWithTag("button/").performClick()
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button0").performClick()
        composeTestRule.onNodeWithTag("button.").performClick()
        composeTestRule.onNodeWithTag("button4").performClick()
        composeTestRule.onNodeWithTag("button1").performClick()
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("28.01")
    }

    @Test
    fun testSequenceThree() {
        // 19 * 3 + 48 / 7 * 6 / 5 = 18
        composeTestRule.onNodeWithTag("button1").performClick()
        composeTestRule.onNodeWithTag("button9").performClick()
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button4").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button/").performClick()
        composeTestRule.onNodeWithTag("button7").performClick()
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("button6").performClick()
        composeTestRule.onNodeWithTag("button/").performClick()
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("18.0")
    }

    @Test
    fun testClearButton() {
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("buttonC").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.0")
    }

    @Test
    fun testClearEntryButton() {
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("buttonCE").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.0")
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58.0")
    }

    @Test
    fun testDeleteButton() {
        // Should fail since delete button is implemented incorrectly in logic
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("buttonDEL").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58")
    }

    @Test
    fun testNegateButton() {
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button+/-").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-55.0")
    }
}