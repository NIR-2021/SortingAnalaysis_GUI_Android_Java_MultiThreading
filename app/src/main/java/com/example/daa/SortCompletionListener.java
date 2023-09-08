package com.example.daa;
//c
import android.app.Activity;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;

public class SortCompletionListener extends Activity implements Runnable{

    BarChart chart;
    ArrayList plotData;
    String[] DAYS;//stores the names of the sorts begin performed.
    MergeSort ms;
    SelectionSort ss;
    BubbleSort bs;
    HeapSort hs;
    QuickSort qs;
    QuickSort3Median qs3m;
    InsertionSort is;

    public SortCompletionListener(BarChart chart, String[] DAYS, MergeSort ms, SelectionSort ss, BubbleSort bs, HeapSort hs, QuickSort qs, QuickSort3Median qs3m, InsertionSort is) {
        this.chart = chart;
        this.DAYS = DAYS;
        this.ms = ms;
        this.ss = ss;
        this.bs = bs;
        this.hs = hs;
        this.qs = qs;
        this.qs3m = qs3m;
        this.is = is;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void run() {
        Log.e("ThreadListener","Starting the Thread listener");
        int nowork = 0;

        //constantly keep checking for a varibgle length
        while(ss.d == null || is.d == null || ms.d == null ||  hs.d == null || bs.d == null || qs.d == null || qs3m.d == null ){
            Log.d("ThreadListener","Waiting for the threads to complete");
//                nowork = 1;
//            continue;//making the thread wait till all the thread are done processing
        }
        Log.e("ThreadListener","All thread done executing");
        configureChartAppearance();//setting the chart apparance
        plotData = new ArrayList();
        plotData.add(new BarEntry(0,ss.d.toMillis()));
        plotData.add(new BarEntry(1,ms.d.toMillis()));
        plotData.add(new BarEntry(2,qs.d.toMillis()));
        plotData.add(new BarEntry(3,qs3m.d.toMillis()));
        plotData.add(new BarEntry(4,hs.d.toMillis()));
        plotData.add(new BarEntry(5,is.d.toMillis()));
        plotData.add(new BarEntry(6,bs.d.toMillis()));

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                BarDataSet set1 = new BarDataSet(plotData,"Sorts"); // seting the dataset for the bar chart
                BarData barData = new BarData(set1);// converting the dataset into bardata
                chart.setData(barData);//Giving the data to the bar chart.
                chart.getDescription().setEnabled(true);//setting description to true.
                chart.invalidate();//making graph paint it self.
            }
        });
    }

    private void configureChartAppearance() {
        chart.getDescription().setEnabled(false);//setting the description to invisible
        chart.setDrawValueAboveBar(false);//setting draw above bar to be false

        XAxis x = chart.getXAxis();// stores the x-axis of the bar chart
        x.setValueFormatter(new ValueFormatter() { //making x-axis show names of the sort
            @Override
            public String getFormattedValue(float value) {
                return DAYS[(int) value];//return the corrersponding value
            }
        });

        YAxis y = chart.getAxisLeft();//stores the y-axis for the bar chart
        y.setGranularity(10f); // defining the granularity of the chart
        y.setAxisMinimum(0);// we dont want the y-axis to show negative values

        YAxis yR = chart.getAxisRight();
        yR.setGranularity(10f);
        yR.setAxisMinimum(0);
    }

}
