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

public class DoubleLineActivity extends AppCompatActivity implements OnChartGestureListener {

    @BindView(R.id.double_line_chart)
    LineChart doubleLineChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_line_chart);
        ButterKnife.bind(this);

        doubleLineChart.setOnChartGestureListener(this);
        doubleLineChart.setDrawGridBackground(false);

        doubleLineChart.getDescription().setEnabled(false);
        doubleLineChart.setDragDecelerationFrictionCoef(0.9f);

        List<Entry> values1 = new ArrayList<>();
        values1.add(new Entry(258f, 175f));
        values1.add(new Entry(191f, 104f));
        values1.add(new Entry(194f, 96f));
        values1.add(new Entry(315f, 87f));
        values1.add(new Entry(189f, 62f));

        List<Entry> values2 = new ArrayList<>();
        values2.add(new Entry(109f, 53f));
        values2.add(new Entry(172f, 70f));
        values2.add(new Entry(267f, 65f));
        Collections.sort(values1, new EntryXComparator());
        Collections.sort(values2, new EntryXComparator());

        LineDataSet lineDataSet1 = new LineDataSet(values1, "Arsenal Top Goal Scorers - Old");
        lineDataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);

        LineDataSet lineDataSet2 = new LineDataSet(values2, "Arsenal Top Goal Scorers - New");
        lineDataSet2.setAxisDependency(YAxis.AxisDependency.LEFT);


        // Animating Chart Line1
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setCircleColor(Color.BLACK);
        lineDataSet1.setLineWidth(2f);
        lineDataSet1.setCircleRadius(3f);
        lineDataSet1.setDrawCircleHole(false);
        lineDataSet1.setValueTextSize(11f);
        lineDataSet1.setDrawFilled(true);
        lineDataSet1.setFormLineWidth(1f);
        lineDataSet1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet1.setFormSize(15.f);
        lineDataSet1.setFillColor(Color.WHITE);

        // Animating Chart Line2
        lineDataSet2.setColor(Color.BLUE);
        lineDataSet2.setCircleColor(Color.BLACK);
        lineDataSet2.setLineWidth(2f);
        lineDataSet2.setCircleRadius(3f);
        lineDataSet2.setDrawCircleHole(false);
        lineDataSet2.setValueTextSize(11f);
        lineDataSet2.setDrawFilled(true);
        lineDataSet2.setFormLineWidth(1f);
        lineDataSet2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
        lineDataSet2.setFormSize(15.f);
        lineDataSet2.setFillColor(Color.WHITE);

        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet1);
        dataSets.add(lineDataSet2);

        LineData data = new LineData(dataSets);
        doubleLineChart.setData(data);
        doubleLineChart.invalidate();
        doubleLineChart.animateX(2500);

        // legend
        Legend l = doubleLineChart.getLegend();
        l.setForm(Legend.LegendForm.LINE);
        l.setTextSize(11f);
        l.setTextColor(Color.DKGRAY);
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
    }

    @Override
    public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {

    }

    @Override
    public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
        // un-highlight values after the gesture is finished and no single-tap
        if (lastPerformedGesture != ChartTouchListener.ChartGesture.SINGLE_TAP) {
            doubleLineChart.highlightValues(null);
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

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

