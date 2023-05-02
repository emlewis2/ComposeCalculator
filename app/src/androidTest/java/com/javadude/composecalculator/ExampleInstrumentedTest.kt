package com.javadude.composecalculator

import androidx.activity.viewModels
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
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // add your instrumented tests here
    @Test
    fun printSemanticTree() {
        val viewModel = CalculatorViewModel()

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

//        composeTestRule
//            .onNodeWithTag("calculator")
//            .onSibling()
//            .onChildAt(0)
//            .assertTextEquals("0")

        composeTestRule.onRoot().printToLog("MY TAG")


    }

    @Test
    fun testUiText() {
        val viewModel = CalculatorViewModel()

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

        val buttons = listOf("CE", "C", "DEL", "/", "7", "8", "9", "*", "4", "5",
        "6", "-", "1", "2", "3", "+", "+/-", "0", ".", "=")
//        val row2 = listOf("7", "8", "9", "*")
//        val row3 = listOf("4", "5", "6", "-")
//        val row4 = listOf("1", "2", "3", "+")
//        val row5 = listOf("+/-", "0", ".", "=")

        buttons.forEach { string ->
            composeTestRule
                .onNodeWithTag("button$string")
                .assertTextEquals(string)
        }

//        row2.forEachIndexed { n, string ->
//            composeTestRule
//                .onNodeWithTag("calculator")
//                .onChildAt(2)
//                .onChildAt(n)
//                .assertTextEquals(string)
//        }
//
//        row3.forEachIndexed { n, string ->
//            composeTestRule
//                .onNodeWithTag("calculator")
//                .onChildAt(3)
//                .onChildAt(n)
//                .assertTextEquals(string)
//        }
//
//        row4.forEachIndexed { n, string ->
//            composeTestRule
//                .onNodeWithTag("calculator")
//                .onChildAt(4)
//                .onChildAt(n)
//                .assertTextEquals(string)
//        }
//
//        row5.forEachIndexed { n, string ->
//            composeTestRule
//                .onNodeWithTag("calculator")
//                .onChildAt(5)
//                .onChildAt(n)
//                .assertTextEquals(string)
//        }
    }

    @Test
    fun testUiButtons() {
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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

//        composeTestRule
//            .onNodeWithTag("calculator")
//            .onChildAt(1)
//            .onChildAt(0)
//            .run {
//                performClick()
//                composeTestRule.onNodeWithTag("calculator")
//                    .onChildAt(0)
//                    .assertTextEquals("0.0")
//            }
//
//        composeTestRule
//            .onNodeWithTag("button7")
//            .run {
//                performClick()
//                composeTestRule.onNodeWithTag("calculator")
//                    .onChildAt(0)
//                    .assertTextEquals("7")
//            }

    }

    @Test
    fun testSequenceOne() {
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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

        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button*").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("buttonC").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("0.0")
    }

    @Test
    fun testClearEntryButton() {
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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

        // Should fail since delete button is implemented incorrectly in logic
        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("buttonDEL").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("58")
    }

    @Test
    fun testNegateButton() {
        val viewModel = CalculatorViewModel()

        composeTestRule.setContent {
            ComposeCalculatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val display by viewModel.display.collectAsState(initial = "")
                    Scaffold(
                        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.calculator)) }) },
                        content = { paddingValues ->
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

        composeTestRule.onNodeWithTag("button5").performClick()
        composeTestRule.onNodeWithTag("button+/-").performClick()
        composeTestRule.onNodeWithTag("button8").performClick()
        composeTestRule.onNodeWithTag("button+").performClick()
        composeTestRule.onNodeWithTag("button3").performClick()
        composeTestRule.onNodeWithTag("button=").performClick()
        composeTestRule.onNodeWithTag("display").assertTextEquals("-55.0")
    }

}