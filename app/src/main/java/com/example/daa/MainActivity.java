package com.example.daa;

import static com.github.mikephil.charting.utils.ColorTemplate.*;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {
    LinearLayout merge,heapll,insertionll,selectionll,bubblell,quickll,quick3mll;
    EditText limit,input;
    Button sort;
    Thread t2;
    BarChart chart;
    ArrayList plotData;
    private static final String[] DAYS = { "Selection", "Merge", "Quick", "Quick3M", "Heap", "Insertion", "Bubble" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initi_items();
        listeners();

    }

    private void listeners() {
        sort.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                if(limit.getText().length() == 0 && input.getText().length() == 0){
                    Toast.makeText(MainActivity.this, "Invalid inputs", Toast.LENGTH_LONG).show();
                }
                else{
                    if(limit.getText().length() != 0){
                        //create a thread completion listener

                        //end of thread completion listener
                        Toast.makeText(MainActivity.this, "Inside limiter search", Toast.LENGTH_SHORT).show();
                        int[] ar = getRandon(limit.getText().toString());
                        int[] arr = ar;
                        TextView status ;
                        //merge sort
                        MergeSort ms = new MergeSort(arr,merge);
                        Thread mergeThread = new Thread(ms);

                        //heap sort
                        arr = ar.clone();
                        HeapSort hs = new HeapSort(arr,heapll);
                        Thread heapThread = new Thread(hs);

                        //Insertion sort
                        arr = ar.clone();
                        InsertionSort is = new InsertionSort(arr,insertionll);
                        Thread insertionThread = new Thread(is);

                        //Selection sort
                        arr = ar.clone();
                        SelectionSort ss = new SelectionSort(arr,selectionll);
                        Thread selectionThread = new Thread(ss);

                        //bubble sort
                        arr = ar.clone();
                        BubbleSort bs = new BubbleSort(arr,bubblell);
                        Thread bubbleThread = new Thread(bs);

                        //quick sort
                        arr = ar.clone();
                        QuickSort qs = new QuickSort(arr,quickll);
                        Thread quickThread = new Thread(qs);

                        //quick sort 3 median
                        arr = ar.clone();
                        QuickSort3Median qs3m = new QuickSort3Median(arr,quick3mll);
                        Thread quick3Thread = new Thread(qs3m);

                        //Plotting graph Thread.
                        SortCompletionListener listener = new SortCompletionListener(chart,DAYS,ms,ss,bs,hs,qs,qs3m,is);
                        Thread listnerThread = new Thread(listener);
                        listnerThread.start();

                        selectionThread.start();
                        insertionThread.start();
                        mergeThread.start();
                        heapThread.start();
                        bubbleThread.start();
                        quickThread.start();
                        quick3Thread.start();

                    }
                }
            }
        });
    }



    private int[] getRandon(String text) {
        int i = Integer.parseInt(text);
        int[] result = new int[i+1];
        Random rand = new Random();
        for(int j=0;j<i;j++){
            result[j] = rand.nextInt();
        }

        return result;
    }


    private void initi_items() {
        merge = findViewById(R.id.megersort);
        limit = findViewById(R.id.limiter);
        heapll = findViewById(R.id.heapsort);
        sort = findViewById(R.id.sort);
        selectionll = findViewById(R.id.selectionsort);
        insertionll = findViewById(R.id.insertionsort);
        bubblell = findViewById(R.id.bubblesort);
        quickll = findViewById(R.id.quicksort);
        quick3mll = findViewById(R.id.quicksort3median);
        chart = findViewById(R.id.charts);
    }
}