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

public class QuickSort3Median extends Activity implements Runnable {

    LinearLayout ll;// stores the linear layout
    int[] v;// stores the initial array on numbers to be sorted
    public Duration d = null;// stores the duration for ui thread to make changes to

    public QuickSort3Median(int[] arr, LinearLayout ll) {
        //constructor

        this.ll = ll;
        this.v = arr;
    }

    private void repaint(LinearLayout ll) {
        //clears the list view before starting the thread.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount(); //gets the number of child in the layout.
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
        // updates the UI to display the outputs.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount();
            TextView duration = (TextView) ll.getChildAt(1);
            TextView status = (TextView) ll.getChildAt(2);
            runOnUiThread(new Runnable() {// performs UI changes
                @Override
                public void run() {
                    duration.setText("Duration :"+d.toMillis()+"ms");
                    status.setText("Status: Complete");
                    status.setTextColor(Color.parseColor("#018786"));
                }
            });
        }
    }

    void swap(int[] arr, int i, int j)
    {
        // swaps 2 numbers
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void run(){
        repaint(ll);
        Instant start = null;//stores the start time of the thread
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            start = Instant.now();
            //Code for merge sort
            sort(v,0,v.length-1);
            Instant end = null; // stores the end times of the thread
            end = Instant.now();
            Duration timeElapsed = null;
            timeElapsed = Duration.between(start, end);
            this.d = timeElapsed;
            Log.e("Time","Time taken: " + timeElapsed.toMillis() + " milliseconds with "+this.v[0]);

            printer(this.ll);
        }
    }

    int medianThree(int a, int b, int c) {
        //returns the median of three numbers recieved as parameters
        if ((a > b) ^ (a > c))// if both are true of false it will not go inside the loop
            return a;
        else if ((b < a) ^ (b < c))// if both are true or flase it will not go inside the loop
            return b;
        else
            return c;
    }
    
    
    public int partition(int[] arr, int low, int high)
    {

        int ppv = medianThree(arr[low],arr[high],arr[Math.round(arr.length/2)]); // holds the pivote index for sort


        int i = (low - 1);//right element of the pivot index

        for (int j = low; j <= high - 1; j++) {



            if (arr[j] < ppv) {//performing increments and swaps
                i++; //incrementing the smaller index
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    void sort(int[] arr, int low, int high)
    {
        //performs sorts using quick sort
        if (low < high) {
            int pi = partition(arr, low, high);//partition index.
            //performing sort on arrays seperated by pivote
            sort(arr, low, pi - 1);//performing sort on left sub array
            sort(arr, pi + 1, high);//pefroming sort on right s ub array
        }
    }

}

