package com.xu.shawn.demoapp;

import android.app.Instrumentation.ActivityMonitor;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest{

    //private String email;
    //private String password;


    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule(LoginActivity.class);

    /*@Before
    public void initValidString()
    {
        // Specify a valid string.
        email = "shawnxu1318@gmail.com";
        password = "123456";
    }*/

    @Test
    public void testOnClick()
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

        Espresso.onView(ViewMatchers.withId(R.id.tV1))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        

    }




}
