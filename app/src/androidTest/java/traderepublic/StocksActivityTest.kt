package websocket

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import samples.mohamed.websocket.features.stocks.StocksActivity

@RunWith(AndroidJUnit4ClassRunner::class)
class StocksActivityTest {

  @get:Rule
  var activityRule: ActivityTestRule<StocksActivity>
      = ActivityTestRule(StocksActivity::class.java)

  @Test
  fun activityStarted() {
    Espresso.onView(withText("Trade Republic"))
      .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
  }
}