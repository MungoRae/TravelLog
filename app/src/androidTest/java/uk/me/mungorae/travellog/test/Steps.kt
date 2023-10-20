package uk.me.mungorae.travellog.test

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.widget.DatePicker
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.intent.Intents.intending
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withClassName
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import dagger.hilt.android.testing.HiltAndroidTest
import io.cucumber.java.en.And
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.hamcrest.Matchers
import uk.me.mungorae.travellog.MainActivity
import java.time.LocalDateTime

@HiltAndroidTest
class Steps(
    composeRuleHolder: ComposeRuleHolder,
    intentsTestRuleHolder: EspressoIntentsRuleHolder,
    private val scenarioHolder: ActivityScenarioHolder,
) {

    private val composeTestRule = composeRuleHolder.composeRule

    @Given("The app has started")
    fun the_app_has_started() {
        scenarioHolder.launch(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                MainActivity::class.java,
            ),
        )
    }

    @When("I press the Add button")
    fun i_press_the_add_button() {
        composeTestRule.onNodeWithContentDescription("Add Travel").performClick()
    }

    @Then("I see the Add Travel screen")
    fun i_should_see_the_add_travel_screen() {
        composeTestRule.onNodeWithText("Add Travel").assertIsDisplayed()
    }

    @When("I enter a travel name, and description and press Submit")
    fun i_enter_a_travel_name_and_description_and_press_submit() {
        composeTestRule.onNodeWithText("Name").assertExists().performTextInput("Travel name")
        composeTestRule.onNodeWithText("Description").assertExists()
            .performTextInput("Travel Description")
        composeTestRule.onNodeWithText("Confirm").assertExists().performClick()
    }

    @Then("I see the travel list")
    fun i_see_the_travel_list() {
        composeTestRule.onNodeWithText("Travels").assertIsDisplayed()
    }

    @Then("I see my travel item")
    fun i_see_my_travel_item() {
        composeTestRule.onNodeWithText("Travel name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Travel Description").assertIsDisplayed()
    }

    @Given("^I have got to the Add Travel screen$")
    fun iHaveGotToTheAddTravelScreen() {
        scenarioHolder.launch(
            Intent(
                InstrumentationRegistry.getInstrumentation().targetContext,
                MainActivity::class.java,
            ),
        )
        composeTestRule.onNodeWithContentDescription("Add Travel").performClick()
    }

    @When("^I press the date field$")
    fun iPressTheCurrentDate() {
        composeTestRule.onNodeWithText("Date").assertIsDisplayed().performClick()
    }

    @Then("^I can select the date I want from a calendar$")
    fun iCanSelectTheDateIWantFromACalendar() {
        composeTestRule.onNodeWithText("05/04/19289").assertIsDisplayed().performClick()
    }

    @And("I select the date yesterday")
    fun iSelectTheDateYesterday() {
        val yesterday = LocalDateTime.now(ClockModule.TEST_CLOCK).minusDays(1)

        onView(withClassName(Matchers.equalTo(DatePicker::class.java.name)))
            .check(matches(isDisplayed()))
            .perform(
                PickerActions.setDate(
                    yesterday.year,
                    yesterday.monthValue,
                    yesterday.dayOfMonth,
                ),
            )
        onView(withId(android.R.id.button1)).perform(click())
    }

    @Then("I can see the date displayed as text")
    fun iCanSeeTheDateDisplayedAsText() {
        composeTestRule.onNodeWithText("31 December 2000").assertIsDisplayed()
    }

    @When("I press the add photo button")
    fun iPressTheAddPhotoButton() {
        val resultData = Intent()
        resultData.data = Uri.parse("/my/fake/uri")
        val result = Instrumentation.ActivityResult(Activity.RESULT_OK, resultData)
        intending(hasAction(MediaStore.ACTION_PICK_IMAGES)).respondWith(result)
        composeTestRule.onNode(hasContentDescription("Add photo"))
            .assertExists()
            .performClick()
    }

    @And("I select a photo")
    fun iSelectAPhoto() {
        // no action here as intent is mocked already
    }

    @Then("I see the photo on the screen")
    fun iSeeThePhotoOnTheScreen() {
        composeTestRule.onNode(hasTestTag("ImageRow")).onChildren().assertCountEquals(2)
    }
}
