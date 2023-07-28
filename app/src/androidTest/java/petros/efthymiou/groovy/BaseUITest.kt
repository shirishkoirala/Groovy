package petros.efthymiou.groovy

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import petros.efthymiou.groovy.playlist.idelingResource

@RunWith(AndroidJUnit4::class)
abstract class BaseUITest {
    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(idelingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(idelingResource)
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            public override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view)
            }

            override fun describeTo(description: org.hamcrest.Description?) {
                description?.appendText("position $childPosition of parent ")
                parentMatcher.describeTo(description)
            }
        }
    }
}