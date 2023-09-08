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

public class SelectionSort extends Activity implements Runnable {

    int[] v;//hold the initial array for sorting
    LinearLayout ll;// holds the linear layout for UI thread
    public Duration d = null;// hold the duration of completion of sort

    public SelectionSort(int[] arr, LinearLayout ll) {
        //constructor for the selection sort
        this.v = arr;
        this.ll = ll;
    }

    public void run(){// Thread method
        repaint(ll);//setting the values on the ui
        Instant start = null; // holds the start time of the sort
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            start = Instant.now(); //setting the start to the current time
            //Code for merge sort
            sort(v);//performing sort
            Instant end = null;//storing the end of execution value of the sort
            end = Instant.now();//setting the end of execution to the current time value.
            Duration timeElapsed = null;//holds the duration object
            timeElapsed = Duration.between(start, end);//saving the duration
            this.d = timeElapsed;
            Log.e("Time","Time taken: " + timeElapsed.toMillis() + " milliseconds with "+this.v[0]);//

            printer(this.ll);//painting the ui with the new values.
        }
    }

    private void repaint(LinearLayout ll) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount(); // hold the number of child to the linear layout.
            TextView duration = (TextView) ll.getChildAt(1); // hold the text view that show the duration of execution
            TextView status = (TextView) ll.getChildAt(2);// hold the text view that show the status of execution.
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    duration.setText("Duration : --"); //setting the text view to the duration of execution
                    status.setText("Status: Working...."); // setting the status of the ui to working.....
                    status.setTextColor(Color.parseColor("#000000"));
                }
            });
        }
    }

    private void printer(LinearLayout ll) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int childcount = ll.getChildCount(); // holds the number of child of the linear layout .
            TextView duration = (TextView) ll.getChildAt(1); //hold the text view that shows the duration of exectuion
            TextView status = (TextView) ll.getChildAt(2);// hold the text view that show the status of the execution.
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
        int n = arr.length;//holds the length of the array.

        //iteration throught the boundary
        for (int i = 0; i < n-1; i++)// increaing for loop to iterate over the items in the arry to be sorted.
        {
            // finding the largest element.
            int min_idx = i; // holds the current element.
            for (int j = i+1; j < n; j++) // iterating over everyh element after the current element.
                if (arr[j] < arr[min_idx]) // if current element is greater than the iterated element
                    min_idx = j; //make the iterated element the current element.

            //swaping the elements
            int temp = arr[min_idx]; // creating temp element.
            arr[min_idx] = arr[i]; // swapping
            arr[i] = temp;//swapping
        }
    }
}
