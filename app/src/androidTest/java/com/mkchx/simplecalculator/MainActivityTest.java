package com.mkchx.simplecalculator;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private MainActivity mActivity;
    private Context mContext;

    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void initTargetContext() {
        mContext = InstrumentationRegistry.getTargetContext();
        mActivity = activityTestRule.getActivity();
    }

    @Before
    public void testPreconditions() {

        assertThat(mActivity, instanceOf(MainActivity.class));
        assertThat(mContext, notNullValue());
    }

    @Test
    public void testInputValue() {

        TextView textView = new TextView(mContext);
        textView.setText("2");

        mActivity.onButtonClick(textView);

        assertNotSame("2", textView.getText());
        assertTrue(mActivity.isNumeric("35"));
    }

}
