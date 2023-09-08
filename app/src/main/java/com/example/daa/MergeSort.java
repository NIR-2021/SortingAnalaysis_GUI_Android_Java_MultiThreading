package com.example.daa;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MergeSort extends Activity implements Runnable {

    int[] v ;//stores the array to be sorted
    public Duration d = null;// holds the duration object for sort.
    LinearLayout ll; //  hold the linear layout for UI

    public MergeSort(int[] v, LinearLayout ll) {
        //constructor for merge sort.
        this.v = v;
        this.ll= ll;
    }

    @Override
    public void run() {
        repaint(ll);
        Instant start = null;// hold the start time of the exectuion.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            start = Instant.now();// savin the current time


            //Code for merge sort
            sort(this.v, 0, this.v.length - 1);


            Instant end = null; //hold the eend ot execution time

            end = Instant.now(); // saving the current time .

            Duration timeElapsed = null; // holds the temporary duration 

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

    void sort(int[] sortArr, int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(sortArr, l, m);
            sort(sortArr, m + 1, r);

            // Merge the sorted halves
            merge(sortArr, l, m, r);
        }
    }

    void merge(int[] sortArr, int l, int m, int r)
    {
        int leng1 = m - l + 1;// holds the length of subarray
        int leng2 = r - m; // holds the length of subarray

        int leftPart[] = new int[leng1]; // holds the left part of the array
        int rightPart[] = new int[leng2]; // holds the right part of the array.

        for (int i = 0; i < leng1; ++i)
            leftPart[i] = sortArr[l + i];
        for (int j = 0; j < leng2; ++j)
            rightPart[j] = sortArr[m + 1 + j];

        int i = 0, j = 0;

        int k = l;
        while (i < leng1 && j < leng2) {
            if (leftPart[i] <= rightPart[j]) {
                sortArr[k] = leftPart[i];
                i++;
            }
            else {
                sortArr[k] = rightPart[j];
                j++;
            }
            k++;
        }

        //copying the left p art
        while (i < leng1) {
            sortArr[k] = leftPart[i];
            i++;
            k++;
        }
        //copyin the right part
        while (j < leng2) {
            sortArr[k] = rightPart[j];
            j++;
            k++;
        }
    }

}


