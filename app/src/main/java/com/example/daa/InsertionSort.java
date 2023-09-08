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

public class InsertionSort extends Activity implements Runnable {
    int[] v;// array that is to be sorted.
    LinearLayout ll; // holds the linear layout for UI
    public Duration d = null;// holds the duration object.

    public InsertionSort(int[] v, LinearLayout ll) {
        //constructor for the insertion sort.
        this.v = v;
        this.ll = ll;
    }

    public void run(){
        repaint(ll);
        Instant start = null; // hold the start of the exectuion of the sort.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            start = Instant.now();
            //Code for inserting sort
            sort(v);// performing insertion sort.
            Instant end = null;// holds the time of end of exectuion
            end = Instant.now();
            Duration timeElapsed = null; // hold the duration object for the sort.
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

    void sort(int arr[])
    {
        int leng = arr.length;
        for (int i = 1; i < leng; ++i) {
            int k = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > k) {
                arr[j + 1] = arr[j];// moving greater element to the next of current.
                j = j - 1;
            }
            arr[j + 1] = k;
        }
    }
}
