package com.pamella.moviesapp.domain.model

import junit.framework.Assert.assertEquals
import org.junit.Test

class Details {

    @Test
    fun `On the film details, the runtime needs to be on de right format for under 60 minutes`() {
        val testList = mutableListOf<Genre>()
        val runtime = 50
        val details = MovieDetail("", testList, 0, "", "", runtime, 3f, "", false)
        val actual = details.getRuntime()
        assertEquals("50min", actual)

    }

    @Test
    fun `On the film details, the runtime needs to be on de right format for over 60 minutes`() {
        val testList = mutableListOf<Genre>()
        val runtime = 90
        val details = MovieDetail("", testList, 0, "", "", runtime, 3f, "", false)
        val actual = details.getRuntime()
        assertEquals("1h 30min", actual)

    }

    @Test
    fun `On the film details, the rating should show correctly the percentage`() {
        val testList = mutableListOf<Genre>()
        val percentage = 2f
        val details = MovieDetail("", testList, 0, "", "", 50, percentage, "", false)
        val actual = details.getRating()
        assertEquals("20%", actual)

    }

    @Test
    fun `On the film details, the release year should appear separated `() {
        val testList = mutableListOf<Genre>()
        val date = "2008-07-18"
        val details = MovieDetail("", testList, 0, "", date, 50, 2f, "", false)
        val actual = details.getReleaseYear()
        assertEquals("2008", actual)

    }
}