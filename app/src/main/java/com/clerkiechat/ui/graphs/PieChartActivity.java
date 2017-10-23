package com.clerkiechat.ui.graphs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.util.Log;

import com.clerkiechat.R;
import com.clerkiechat.utils.ViewUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Pranav Bhoraskar
 */

public class PieChartActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    @BindView(R.id.pie_chart)
    PieChart pieChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        ButterKnife.bind(this);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);
        pieChart.setCenterText(generateCenterSpannableText());
        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);

        List<PieEntry> yvalues = new ArrayList<>();
        yvalues.add(new PieEntry(175f, "Henry"));
        yvalues.add(new PieEntry(104f, "Wright"));
        yvalues.add(new PieEntry(96f, "Persie"));
        yvalues.add(new PieEntry(87f, "Bergkamp"));
        yvalues.add(new PieEntry(70f, "Giroud"));
        yvalues.add(new PieEntry(65f, "Walcott"));
        yvalues.add(new PieEntry(62f, "Pires"));
        yvalues.add(new PieEntry(53f, "Sanchez"));

        PieDataSet dataSet = new PieDataSet(yvalues, "Arsenal Top Goal Scorers");

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        dataSet.setColors(ViewUtils.getColorInts());

        pieChart.setOnChartValueSelectedListener(this);
        pieChart.animateXY(1400, 1400);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        pieChart.setData(data);
    }

    private SpannableString generateCenterSpannableText() {
        return new SpannableString("Pie Chart\n Arsenal Top Goal Scorers");
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if (e == null) {
            return;
        }
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }


}
