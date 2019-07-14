package com.example.user.andeladouglaschallenge;
;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;



@RunWith(AndroidJUnit4.class)
@LargeTest
public class Activity_ATest {

    @Rule
    public ActivityTestRule<Activity_A> activityRule =
            new ActivityTestRule(Activity_A.class);


//click button 1 then test webview scroll and back navigation

    @Test
public void clickButton1(){
        onView(withId(R.id.alc_info)).perform(click());

        try{
        Thread.sleep(5000);}catch (InterruptedException f){}

        onView(withId(R.id.webview)).perform(swipeUp());
        onView(withId(R.id.webview)).perform(swipeDown());
        pressBack();

        try{
            Thread.sleep(2000);
            onView(withId(R.id.idCardView)).perform(swipeUp());
        }catch (InterruptedException f){}

    }

 //click button 2 then test scroll on the activity transited to and navigate back

@Test
public void clickButton2(){
        onView(withId(R.id.user_info)).perform(click());
    try{
        Thread.sleep(1000);
        onView(withId(R.id.idCardView)).perform(swipeUp());
    }catch (InterruptedException f){}
    try{
        Thread.sleep(1000);
        onView(withId(R.id.idCardView)).perform(swipeUp());
    }catch (InterruptedException f){}
    try{
        Thread.sleep(2000);
        onView(withId(R.id.idCardView)).perform(swipeUp());
    }catch (InterruptedException f){}
    try{
        Thread.sleep(1000);
        onView(withId(R.id.idCardView)).perform(swipeDown());
    }catch (InterruptedException f){}
    try{
        Thread.sleep(1000);
        onView(withId(R.id.idCardView)).perform(swipeDown());
    }catch (InterruptedException f){}
    try{
        Thread.sleep(1000);
        onView(withId(R.id.idCardView)).perform(swipeDown());
    }catch (InterruptedException f){}


        onView(withId(R.id.name_val)).check(matches(withText(Activity_A.NAME)));
        onView(withId(R.id.track_val)).check(matches(withText(Activity_A.TRACK)));
        onView(withId(R.id.email_value)).check(matches(withText(Activity_A.EMAIL)));
        onView(withId(R.id.phone_no_value)).check(matches(withText(Activity_A.PHONE_NUMBER)));
        onView(withId(R.id.country_value)).check(matches(withText(Activity_A.COUNTRY)));

        pressBack();
    try{
        Thread.sleep(2000);
        onView(withId(R.id.idCardView)).perform(swipeUp());
    }catch (InterruptedException f){}

    }

}