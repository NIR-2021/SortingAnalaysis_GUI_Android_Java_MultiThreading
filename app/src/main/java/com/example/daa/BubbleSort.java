package com.example.daa;
//c
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.Duration;
import java.time.Instant;

public class BubbleSort extends Activity implements Runnable {

    int[] v; // hold the initial array to perform sorting on,
    LinearLayout ll; // hold the lineary layout for the ui thread .
    public Duration d = null; // hold the duration of sort

    public BubbleSort(int[] v, LinearLayout ll) {
        //constructor
        this.v = v;
        this.ll = ll;
    }

    public void run(){
        repaint(ll);// resets all the values on GUI
        Instant start = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            start = Instant.now();
            //Code for merge sort
            sort(v);
            Instant end = null;
            end = Instant.now();
            Duration timeElapsed = null;
            timeElapsed = Duration.between(start, end);
            this.d = timeElapsed;
            Log.e("Time","Time taken: " + timeElapsed.toMillis() + " milliseconds with "+this.v[0]);

            printer(this.ll);
        }
    }

    private void repaint(LinearLayout ll) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount();
            TextView duration = (TextView) ll.getChildAt(1);
            TextView status = (TextView) ll.getChildAt(2);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    duration.setText("Duration : --");
                    status.setText("Status: Working....");
                    status.setTextColor(Color.parseColor("#000000"));
                }
            });
        }
    }

    private void printer(LinearLayout ll) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount();
            TextView duration = (TextView) ll.getChildAt(1);
            TextView status = (TextView) ll.getChildAt(2);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    duration.setText("Duration :"+d.toMillis()+"ms");
                    status.setText("Status: Complete");
                    status.setTextColor(Color.parseColor("#018786"));
                }
            });
        }
    }

    void sort(int[] arr)
    {
        int n = arr.length;//holds the length of array to be sorted.
        for (int i = 0; i < n - 1; i++) // iterating front pointer.
            for (int j = 0; j < n - i - 1; j++) // iterating back pointer.
                if (arr[j] > arr[j + 1]) {//comparing
                    //interchanging the arr[j] with arr[j+1]
                    int temp = arr[j];//temporary variable
                    arr[j] = arr[j + 1]; // performing swap
                    arr[j + 1] = temp;// performing swap
                }
    }



}
