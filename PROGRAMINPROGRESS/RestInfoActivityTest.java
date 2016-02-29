package com.xu.shawn.demoapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;



/**
 * Created by pro on 2/28/16.
 */
public class RestInfoActivityTest {

    @Before
    public void setUp() throws Exception {
        onView(allOf(withId(R.id.RestName),withText("restaurant name")));
        onView(allOf(withId(R.id.rating),withText("star rate")));
        onView(allOf(withId(R.id.price),withText("price")));
        onView(allOf(withId(R.id.add),withText("Address: 8330 mira mesa blvd")));
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnCreate() throws Exception {

    }
}