package com.anggasaraya.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.anggasaraya.moviecatalogue.helper.DataDummy
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.anggasaraya.moviecatalogue.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyMovies = DataDummy.generateDummyMovies()
    private val dummyTVShows = DataDummy.generateDummyTVShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_subtext)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtext)).check(matches(withText(dummyMovies[0].dateReleased + " | " + dummyMovies[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovies[0].userScore)))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(withText(dummyMovies[0].status)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(dummyMovies[0].language)))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(withText(dummyMovies[0].description)))
        onView(withId(R.id.tv_item_budget)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_budget)).check(matches(withText(dummyMovies[0].budget)))
        onView(withId(R.id.tv_item_income)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_income)).check(matches(withText(dummyMovies[0].income)))
    }

    @Test
    fun loadTVShow(){
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVShows.size))
    }

    @Test
    fun loadDetailTVShow(){
        onView(withText("TV Show")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dummyTVShows[0].title)))
        onView(withId(R.id.tv_subtext)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtext)).check(matches(withText(dummyTVShows[0].dateReleased + " | " + dummyTVShows[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyTVShows[0].userScore)))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(withText(dummyTVShows[0].status)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(dummyTVShows[0].language)))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(withText(dummyTVShows[0].description)))
    }
}