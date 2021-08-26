package com.pamella.moviesapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pamella.moviesapp.ui.activitys.HomeActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivity {

    @get:Rule
    val homeActivity = ActivityScenarioRule(HomeActivity::class.java)


    @Test
     fun search_test(){
        onView(withId(R.id.searchMovie)).perform(click())
        onView(withId(R.id.searchMovie)).perform(typeText("Frozen"), pressKey(66))
        onView(withId(R.id.backHomeButton)).perform(click())
    }


}