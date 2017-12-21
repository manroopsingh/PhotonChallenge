package com.example.singh.photonchallenge;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
public class MainActivityTest {




    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(MainActivity.class, false, true);

    @Test
    public void validateDimentions(){

        inputValues();

        onView(withId(R.id.editTextHeight)).check(matches(withText(String.valueOf(4))));
        onView(withId(R.id.editTextWidth)).check(matches(withText(String.valueOf(4))));

    }


    public void inputValues(){
        onView(withId(R.id.editTextHeight))
                .perform(typeText(String.valueOf(4)), closeSoftKeyboard());
        onView(withId(R.id.editTextWidth))
                .perform(typeText(String.valueOf(4)), closeSoftKeyboard());

    }




}
