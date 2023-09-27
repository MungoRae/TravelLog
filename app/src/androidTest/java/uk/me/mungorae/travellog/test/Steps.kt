package uk.me.mungorae.travellog.test

import android.content.Intent
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.test.printToLog
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidTest
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import uk.me.mungorae.travellog.MainActivity
import cucumber.api.PendingException

@HiltAndroidTest
class Steps(
    private val composeRuleHolder: ComposeRuleHolder,
    val scenarioHolder: ActivityScenarioHolder,
) : SemanticsNodeInteractionsProvider by composeRuleHolder.composeRule {

    @Given("The app has started")
    fun the_app_has_started() {
        scenarioHolder.launch(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                MainActivity::class.java
            )
        )
    }

    @When("I press the Add button")
    fun i_press_the_add_button() {
        onNodeWithContentDescription("Add Travel").performClick()
    }

    @Then("I see the Add Travel screen")
    fun i_should_see_the_add_travel_screen() {
        onNodeWithText("Add travel here").assertIsDisplayed()
    }

    @When("I enter a travel name, and description and press Submit")
    fun i_enter_a_travel_name_and_description_and_press_submit() {
        onNodeWithText("Name").assertExists().performTextInput("Travel name")
        onNodeWithText("Description").assertExists().performTextInput("Travel Description")
        onNodeWithText("Confirm").assertExists().performClick()
    }

    @Then("I see the travel list")
    fun i_see_the_travel_list() {
        onNodeWithText("Travels").assertIsDisplayed()
    }

    @Then("I see my travel item")
    fun i_see_my_travel_item() {
        onNodeWithText("Travel name").assertIsDisplayed()
        onNodeWithText("Travel Description").assertIsDisplayed()
    }

    @Given("^I have got to the Add Travel screen$")
    fun iHaveGotToTheAddTravelScreen() {
        scenarioHolder.launch(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                MainActivity::class.java
            )
        )
        onNodeWithContentDescription("Add Travel").performClick()
    }

    @When("^I press the current date$")
    fun iPressTheCurrentDate() {
        onNodeWithText("Travel date").assertIsDisplayed().performClick()
    }

    @Then("^I can select the date I want from a calendar$")
    fun iCanSelectTheDateIWantFromACalendar() {
        onNodeWithText("05/04/19289").assertIsDisplayed().performClick()
    }
}