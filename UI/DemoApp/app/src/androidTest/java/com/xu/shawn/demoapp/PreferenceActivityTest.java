package com.xu.shawn.demoapp;

import android.test.InstrumentationTestCase;

import org.junit.Test;
import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Sky on 2/28/16.
 */
public class PreferenceActivityTest extends InstrumentationTestCase{

    @Test
    public void testOnCreate() throws Exception {
        onView(allOf(withId(R.id.tV1),withText("Price")));
        onView(allOf(withId(R.id.tV2),withText("Rate")));
        onView(allOf(withId(R.id.tV3),withText("Japanese Food")));
        onView(allOf(withId(R.id.tV4),withText("Chinese Food")));
        onView(allOf(withId(R.id.tV5),withText("American Food")));
        onView(allOf(withId(R.id.tV6),withText("Vietnamese Food")));
        onView(allOf(withId(R.id.tV7),withText("Hispanic Food")));
        onView(allOf(withId(R.id.tV8),withText("Italian Food")));
        onView(allOf(withId(R.id.tV9),withText("French Food")));
        onView(allOf(withId(R.id.tV10),withText("Fast Food")));
        onView(allOf(withId(R.id.tV11),withText("Thai Food")));
        onView(allOf(withId(R.id.tV12),withText("Others")));
        onView(allOf(withId(R.id.textView11),withText("Ha")));

    }

    @Test
    public void testOnClick() throws Exception {
        onView(allOf(withId(R.id.tV1),withText("Price")));
        onView(allOf(withId(R.id.tV2),withText("Rate")));
        onView(allOf(withId(R.id.tV3),withText("Japanese Food")));
        onView(allOf(withId(R.id.tV4),withText("Chinese Food")));
        onView(allOf(withId(R.id.tV5),withText("American Food")));
        onView(allOf(withId(R.id.tV6),withText("Vietnamese Food")));
        onView(allOf(withId(R.id.tV7),withText("Hispanic Food")));
        onView(allOf(withId(R.id.tV8),withText("Italian Food")));
        onView(allOf(withId(R.id.tV9),withText("French Food")));
        onView(allOf(withId(R.id.tV10),withText("Fast Food")));
        onView(allOf(withId(R.id.tV11),withText("Thai Food")));
        onView(allOf(withId(R.id.tV12),withText("Others")));
        onView(allOf(withId(R.id.textView11),withText("Ha")));
    }
}