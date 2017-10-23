package com.clerkiechat.ui.graphs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.clerkiechat.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pranav Bhoraskar
 */

public class ChartScreenActivity extends AppCompatActivity {

    @BindView(R.id.button_pie)
    Button buttonPie;
    @BindView(R.id.button_single_line)
    Button buttonSingleLine;
    @BindView(R.id.button_double_line)
    Button buttonDoubleLine;
    @BindView(R.id.button_vertical_bar)
    Button buttonVerticalBar;
    @BindView(R.id.button_horizontal_bar)
    Button buttonHorizontalBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @OnClick({R.id.button_pie, R.id.button_single_line, R.id.button_double_line,
            R.id.button_vertical_bar, R.id.button_horizontal_bar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_pie:
                startActivity(new Intent(ChartScreenActivity.this, PieChartActivity.class));
                break;
            case R.id.button_single_line:
                startActivity(new Intent(ChartScreenActivity.this, SingleLineChart.class));
                break;
            case R.id.button_double_line:
                startActivity(new Intent(ChartScreenActivity.this, DoubleLineActivity.class));
                break;
            case R.id.button_vertical_bar:
                startActivity(new Intent(ChartScreenActivity.this, VerticalBarChartActivity.class));
                break;
            case R.id.button_horizontal_bar:
                startActivity(new Intent(ChartScreenActivity.this,
                        HorizontalBarChartActivity.class));
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
