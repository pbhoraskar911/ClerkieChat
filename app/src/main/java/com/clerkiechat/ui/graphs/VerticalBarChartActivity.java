package com.clerkiechat.ui.graphs;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.clerkiechat.R;
import com.clerkiechat.utils.ViewUtils;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pranav Bhoraskar
 */

public class VerticalBarChartActivity extends AppCompatActivity {

    @BindView(R.id.vertical_bar_chart)
    BarChart verticalBarChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vertical_bar_chart);
        ButterKnife.bind(this);

        verticalBarChart.setDrawBarShadow(false);
        verticalBarChart.setDrawValueAboveBar(true);

        verticalBarChart.getDescription().setEnabled(false);
        verticalBarChart.setPinchZoom(false);
        verticalBarChart.setDrawGridBackground(false);

        List<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(258f, 175f));
        barEntries.add(new BarEntry(191f, 104f));
        barEntries.add(new BarEntry(194f, 96f));
        barEntries.add(new BarEntry(315f, 87f));
        barEntries.add(new BarEntry(172f, 70f));
        barEntries.add(new BarEntry(267f, 65f));
        barEntries.add(new BarEntry(189f, 62f));
        barEntries.add(new BarEntry(109f, 53f));

        BarDataSet set = new BarDataSet(barEntries, "BarDataSet");
        set.setColors(ViewUtils.getColorInts());

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set);

        BarData data = new BarData(dataSets);
        data.setBarWidth(8f);
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.DKGRAY);

        verticalBarChart.setData(data);
        verticalBarChart.setFitBars(true);
        verticalBarChart.invalidate();
        verticalBarChart.getLegend().setEnabled(false);
    }
}
