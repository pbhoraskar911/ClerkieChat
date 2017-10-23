package com.clerkiechat.ui.graphs;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.clerkiechat.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.utils.EntryXComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pranav Bhoraskar
 */

public class SingleLineChart extends AppCompatActivity implements OnChartGestureListener {

    @BindView(R.id.single_line_chart)
    LineChart singleLineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_line_chart);
        ButterKnife.bind(this);

        singleLineChart.setOnChartGestureListener(this);
        singleLineChart.setDrawGridBackground(false);

        singleLineChart.getDescription().setEnabled(false);
        singleLineChart.setDragDecelerationFrictionCoef(0.9f);

        List<Entry> values = new ArrayList<>();
        values.add(new Entry(258f, 175f));
        values.add(new Entry(191f, 104f));
        values.add(new Entry(194f, 96f));
        values.add(new Entry(315f, 87f));
        values.add(new Entry(172f, 70f));
        values.add(new Entry(267f, 65f));
        values.add(new Entry(189f, 62f));
        values.add(new Entry(109f, 53f));
        Collections.sort(values, new EntryXComparator());

        LineDataSet lineDataSet = new LineDataSet(values, "Arsenal Top Goal Scorers");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);


        lineDataSet.setDrawIcons(true);

        // Animating Chart Line
        lineDataSet.setColor(Color.RED);
        lineDataSet.setCircleColor(Color.BLACK);
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleRadius(3f);
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setValueTextSize(11f);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFormLineWidth(1f);
        lineDataSet.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet.setFormSize(15.f);
        lineDataSet.setFillColor(Color.WHITE);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet);

        LineData data = new LineData(dataSets);
        singleLineChart.setData(data);
        singleLineChart.animateX(2500);

        // legend
        Legend l = singleLineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);

    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP) {
            singleLineChart.highlightValues(null);
        }
    }

    @Override
    public void onChartLongPressed(MotionEvent me) {

    }

    @Override
    public void onChartDoubleTapped(MotionEvent me) {

    }

    @Override
    public void onChartSingleTapped(MotionEvent me) {

    }

    @Override
    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {

    }

    @Override
    public void onChartScale(MotionEvent me, float scaleX, float scaleY) {

    }

    @Override
    public void onChartTranslate(MotionEvent me, float dX, float dY) {

    }
}
