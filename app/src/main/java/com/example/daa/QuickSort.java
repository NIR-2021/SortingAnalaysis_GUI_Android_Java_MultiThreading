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

public class QuickSort extends Activity implements Runnable {

    LinearLayout ll; // holds the linear layout for thread
    int[] v; // hold the initial array for sorting
    public Duration d = null; // hold the duration of the sort

    public QuickSort(int[] ar,LinearLayout ll) {
        //constuctor
        this.ll = ll;
        this.v = ar;
    }

    private void repaint(LinearLayout ll) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount(); //holds the number of child in in the layout
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

    void swap(int[] ar, int in, int jn)
    {
        int temp = ar[in];//creating temporary variable
        ar[in] = ar[jn];
        ar[jn] = temp;
    }

    public void run(){
        repaint(ll);
        Instant start = null;//holds the start of the thread time
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            start = Instant.now();
            //Code for merge sort
            sort(v,0,v.length-1);
            Instant end = null; //holds end of the thread time
            end = Instant.now();
            Duration timeElapsed = null;
            timeElapsed = Duration.between(start, end);
            this.d = timeElapsed;
            Log.e("Time","Time taken: " + timeElapsed.toMillis() + " milliseconds with "+this.v[0]);

            printer(this.ll);
        }
    }


    public int partition(int[] ar, int lw, int hg)
    {
        //creates partition for the quick sort
        int p = ar[hg]; // hold the pivot index.
        int i = (lw - 1); // hold the lower index for quick sort

        for (int j = lw; j <= hg - 1; j++) {
            if (ar[j] < p) {
                i++;
                swap(ar, i, j);
            }
        }
        swap(ar, i + 1, hg);
        return (i + 1);
    }

    void sort(int[] ar, int lw, int hg)
    {
        //performs quick sort
        if (lw < hg) {
            int pi = partition(ar, lw, hg);// partition index
            sort(ar, lw, pi - 1); // performing recussion on the lower half
            sort(ar, pi + 1, hg); // performing recussion on the higher half
        }
    }

}

