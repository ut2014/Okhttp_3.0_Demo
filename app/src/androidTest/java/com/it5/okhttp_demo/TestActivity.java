package com.it5.okhttp_demo;

import android.content.Context;
import android.content.Intent;
import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by IT5 on 2016/7/6.
 */
public class TestActivity extends ActivityInstrumentationTestCase2<MainActivity>{
    private Context ctx;

    public TestActivity() {
        super(MainActivity.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        ctx = getActivity().getApplicationContext();
    }

    public void testStart() {
        Intent intent = new Intent(ctx, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(intent);
    }

}
