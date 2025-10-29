package alidev.projects.movieexplorer.presentation.navigation

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import alidev.projects.movieexplorer.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NavigationInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun navigationHost_startsAtHomeDestination() {
        // El test verificará que la navegación inicie en la pantalla correcta
        // cuando implementemos la navegación
        composeTestRule.waitForIdle()
    }
}