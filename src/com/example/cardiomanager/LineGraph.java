package com.example.cardiomanager;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;

public class LineGraph {

	public LineGraph() {
		// TODO Auto-generated constructor stub
	}

	public GraphicalView getGraph(Context context)
    {
    	int x[] = {1, 2, 3, 4, 5};
    	int y[] = {10, 8, 55, 3, 8};
    	
    	TimeSeries timeSeries = new TimeSeries("Line");
    	for (int i = 0; i < y.length; i++) {
			timeSeries.add(x[i], y[i]);
		}
    	
    	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    	dataset.addSeries(timeSeries);
    	
    	XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
    	XYSeriesRenderer renderer = new XYSeriesRenderer();
    	mRenderer.addSeriesRenderer(renderer);
    	
    	return ChartFactory.getLineChartView(context, dataset, mRenderer);
    }

}
