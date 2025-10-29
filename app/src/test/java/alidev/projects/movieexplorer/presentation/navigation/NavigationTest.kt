package alidev.projects.movieexplorer.presentation.navigation

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class NavigationTest {

    @Test
    fun `routes should be defined correctly`() {
        // Given - When
        val homeRoute = NavigationRoutes.HOME
        val detailRoute = NavigationRoutes.DETAIL

        // Then
        assertThat(homeRoute).isEqualTo("home")
        assertThat(detailRoute).isEqualTo("detail/{movieId}")
    }

    @Test
    fun `navigation destinations should have correct routes`() {
        // Given - When
        val destinations = NavigationDestinations.getAllDestinations()

        // Then
        assertThat(destinations).isNotEmpty()
        assertThat(destinations).contains(NavigationRoutes.HOME)
        assertThat(destinations).contains(NavigationRoutes.DETAIL)
    }
}