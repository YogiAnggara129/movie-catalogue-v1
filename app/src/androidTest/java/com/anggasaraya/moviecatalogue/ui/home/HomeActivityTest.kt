package com.anggasaraya.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.anggasaraya.moviecatalogue.R
import com.anggasaraya.moviecatalogue.helper.ApiDataDummy
import com.anggasaraya.moviecatalogue.helper.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest{
    private val dummyMovies = ApiDataDummy.generateDummyMovies()
    private val dummyMovieDetail = ApiDataDummy.generateDummyDetailMovie(dummyMovies[0].id)
    private val dummyTVShows = ApiDataDummy.generateDummyTVShows()
    private val dummyTVShowDetail = ApiDataDummy.generateDummyDetailTVShow(dummyTVShows[0].id)

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dummyMovieDetail.title)))
        onView(withId(R.id.tv_subtext)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtext)).check(matches(withText(dummyMovieDetail.dateReleased + " | " + dummyMovieDetail.genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyMovieDetail.userScore)))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(withText(dummyMovieDetail.status)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(dummyMovieDetail.language)))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(withText(dummyMovieDetail.description)))
        onView(withId(R.id.tv_item_budget)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_budget)).check(matches(withText(dummyMovieDetail.budget)))
        onView(withId(R.id.tv_item_income)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_income)).check(matches(withText(dummyMovieDetail.income)))
    }

    @Test
    fun loadFavoriteMovies(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtext)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_budget)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_income)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }

    @Test
    fun loadTVShow(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTVShows.size))
    }

    @Test
    fun loadDetailTVShow(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_title)).check(matches(withText(dummyTVShowDetail.title)))
        onView(withId(R.id.tv_subtext)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtext)).check(matches(withText(dummyTVShowDetail.dateReleased + " | " + dummyTVShowDetail.genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(dummyTVShowDetail.userScore)))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(withText(dummyTVShowDetail.status)))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(withText(dummyTVShowDetail.language)))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(withText(dummyTVShowDetail.description)))
    }

    @Test
    fun loadFavoriteTVShows(){
        onView(withId(R.id.navigation_tvshow)).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withId(R.id.navigation_favorite)).perform(click())
        onView(withText("TV SHOW")).perform(click())
        onView(withId(R.id.rv_tvshow_fav)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow_fav)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.tv_item_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_subtext)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_status)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_item_description)).check(matches(isDisplayed()))
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }
}