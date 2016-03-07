package com.xu.shawn.demoapp;

import android.app.Instrumentation.ActivityMonitor;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest{

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    @Test
    public void login()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
        String username = "shawnxu1318@gmail.com";
        String password = "123456";
        onView(withId(R.id.email)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password),closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).perform(click());
        LoginActivity nextActivity = (LoginActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
    }


    @Test
    public void register(){
        ActivityMonitor activityMonitor =
                getInstrumentation().addMonitor(LoginActivity.class.getName(),null, false);

        String username  = "shawnxu1318@gmail.com";
        String password = "123456";
        onView(withId(R.id.email)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.register)).perform(click());

        LoginActivity nextActivity =
                (LoginActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);


        Espresso.onView(ViewMatchers.withId(R.id.email))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.password))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.password2))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.signUp))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }

    @Test
    public void preference()
    {
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
        String username = "shawnxu1318@gmail.com";
        String password = "123456";
        onView(withId(R.id.email)).perform(typeText(username),closeSoftKeyboard());
        onView(withId(R.id.password)).perform(typeText(password),closeSoftKeyboard());
        onView(withId(R.id.email_sign_in_button)).perform(click());
        LoginActivity nextActivity = (LoginActivity) getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        // next activity is opened and captured.
        //assertNotNull(nextActivity);

        Espresso.onView(ViewMatchers.withId(R.id.tV1)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.sB2))
                .perform(ViewActions.swipeLeft());
        onView(withId(R.id.sB4))
                .perform(ViewActions.swipeRight());
        onView(withId(R.id.btnSubmit))
                .perform(ViewActions.scrollTo(), click());
        //onView(withId(R.id.btnSubmit)).perform(click());

    }

    @Test
    public void chooseOne(){

        preference();
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
        LoginActivity nextActivity =
                (LoginActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        onView(withId(R.id.imgViewPic))
                .perform(click());
        Espresso.onView(ViewMatchers.withId(R.id.add))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.rating))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));


    }

    @Test
    public void shake(){

        preference();
        ActivityMonitor activityMonitor = getInstrumentation().addMonitor(LoginActivity.class.getName(), null, false);
        LoginActivity nextActivity =
                (LoginActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        onView(withId(R.id.button12))
                .perform(click());
        onView(withId(R.id.imgViewPic))
                .perform(click());

    }
}