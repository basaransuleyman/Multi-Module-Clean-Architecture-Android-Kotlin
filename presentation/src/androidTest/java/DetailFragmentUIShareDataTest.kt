import androidx.navigation.NavController
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.product.sideapp.home.R
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.productapp.presentation.common.extension.shareLink
import com.productapp.presentation.detail.DetailFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.runner.RunWith
import io.mockk.mockk
import io.mockk.verify
import org.junit.After
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailFragmentUIShareDataTest() {

    private lateinit var navController: NavController

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        navController = mockk(relaxed = true)
    }

    @After
    fun tearDown() {
        // We can delete shared pref, jetpack data store, sql etc.
    }

    @Test
    fun shareButtonClickTest() {

        val scenario = launchFragmentInContainer<DetailFragment>()
        val shareLink = "Samsung Galaxy A9"
        scenario.onFragment { fragment ->
            Navigation.setViewNavController(fragment.requireView(), navController)
            onView(withId(R.id.detail_share_image_view)).perform(click())
            verify {
                fragment.shareLink(shareLink, "SharingLink")
            }
        }

    }

}