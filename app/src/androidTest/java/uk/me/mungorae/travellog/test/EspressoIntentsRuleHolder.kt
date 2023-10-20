package uk.me.mungorae.travellog.test

import androidx.test.espresso.intent.rule.IntentsRule
import io.cucumber.junit.WithJunitRule
import org.junit.Rule

@WithJunitRule
class EspressoIntentsRuleHolder {

    @get:Rule
    val intentsTestRule = IntentsRule()
}
