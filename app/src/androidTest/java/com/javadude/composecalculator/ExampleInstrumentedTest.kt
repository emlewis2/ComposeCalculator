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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    // add your instrumented tests here
    @Test
    fun testUi() {
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

        composeTestRule
            .onNodeWithTag("calculator")
            .onSibling()
            .assertTextEquals("0")


    }
}