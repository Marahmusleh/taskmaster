package com.example.taskmaster;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkSettingsBtn() {
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.username)).check(matches(isDisplayed()));
    }
    @Test
    public void checkRecyclerView() {
        onView(withId(R.id.ListOfTasks)).check(matches(isDisplayed()));
    }
    @Test
    public void checkTaskButton() {
        onView(withId(R.id.addTaskBtn)).perform(click());
        onView(withId(R.id.titleInput)).check(matches(isDisplayed()));
        onView(withId(R.id.bodyInput)).check(matches(isDisplayed()));
        onView(withId(R.id.stateInput)).check(matches(isDisplayed()));

    }
    @Test
    public void testTapOnTask() {
        //for the first task
        onView(withId(R.id.ListOfTasks)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.taskDetailTitle)).check(matches(withText("lab1")));
        onView(withId(R.id.taskDetaiDesc)).check(matches(withText("bitmap")));
        onView(withId(R.id.test)).check(matches(withText("new")));
    }
    @Test
    public void testTapOnTask2() {
        //for the second task
        onView(withId(R.id.ListOfTasks)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.taskDetailTitle)).check(matches(withText("lab2")));
        onView(withId(R.id.taskDetaiDesc)).check(matches(withText("TaskMaster Room")));
        onView(withId(R.id.test)).check(matches(withText("complete")));
    }
    @Test
    public void testSettingsButton() {
        onView(withId(R.id.button8)).perform(click());
        onView(withId(R.id.username))
                .perform(typeText("marah"), closeSoftKeyboard());
        onView(withId(R.id.saveBtn)).perform(click());
        Espresso.pressBackUnconditionally();
        onView(withId(R.id.ShowName)).check(matches(withText("marah's tasks")));

    }
}