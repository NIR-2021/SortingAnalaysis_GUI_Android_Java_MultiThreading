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

public class HeapSort extends Activity implements Runnable {
    int[] v; // holds the arry to be sorted.
    LinearLayout ll; // hold the linear layout for sort UI
    public Duration d = null;// holds the duration of the execution of the variable.


    public HeapSort(int[] v, LinearLayout ll) {
        //constructor for heap sort class.
        this.v = v;
        this.ll = ll;
    }

    public void run(){
        repaint(ll);
        Instant start = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            start = Instant.now();
            //Code for merge sort
            sort(v); // performing merge sort
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
            int childcount = ll.getChildCount(); // holds the number of child that the linear layout has.
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

    public void sort(int[] arr)
    {

        int leng = arr.length;// stores the length of the array to be sorted.

        // modifying array
        for (int i = leng / 2 - 1; i >= 0; i--)
            heapify_heap(arr, leng, i); //hepifying the heap

        // fetching element from heap
        for (int i = leng - 1; i > 0; i--) {
            //swaping the current element with the root
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            //performing max heap.
            heapify_heap(arr, i, 0);
        }
    }

    void heapify_heap(int arr[], int leng, int i)//does the heapify for the heap
    {
        int lg_value = i; // stores the largest root
        int l = 2 * i + 1; // stores left item
        int r = 2 * i + 2; // stores right item

        // when root is smaller than left child
        if (l < leng && arr[l] > arr[lg_value])
            lg_value = l;

        // when largest is smaller than the right child.
        if (r < leng && arr[r] > arr[lg_value])
            lg_value = r;

        // If lg_value is not root
        if (lg_value != i) {
            int swap_temp = arr[i]; //performing swap
            arr[i] = arr[lg_value]; // pefroming swap
            arr[lg_value] = swap_temp; // peforming swap

            heapify_heap(arr, leng, lg_value);// recurssive heapifying
        }
    }

}
