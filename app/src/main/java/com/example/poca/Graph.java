package com.example.poca;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.sql.Array;
import java.util.ArrayList;

public class Graph extends AppCompatActivity {

    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barArraylist;

    int goal;
    int current;
    int output;

    /*  String itemRef;
    TextView itemName;
    String value;*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        barChart = findViewById(R.id.barchart);
        /*value = getIntent().getExtras().getString("data");
        itemRef = getIntent().getExtras().getString("ref");
        Toast.makeText(this, itemRef, Toast.LENGTH_SHORT).show();

        itemName.setText(value);*/
        goal = getIntent().getExtras().getInt("goal");
        current = getIntent().getExtras().getInt("current");
        String value = getIntent().getExtras().getString("name");

        // calling method to get bar entries.
        getData();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barArraylist, "");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // to our bar chart
        barChart.setData(barData);

        //adding colour to the bar data
        barDataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        //setting text colour
        barDataSet.setValueTextColor(Color.WHITE);

        //setting text size
        barDataSet.setValueTextSize(18f);
        barChart.getDescription().setEnabled(false);


    }

    private void getData()
    {
        // creating a new array list
        barArraylist = new ArrayList();

        //passing x and y axis value
        barArraylist.add(new BarEntry(2f,current));
        barArraylist.add(new BarEntry(3f,goal));

    }


}