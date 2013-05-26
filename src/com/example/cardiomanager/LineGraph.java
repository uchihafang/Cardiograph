package com.example.cardiomanager;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.graphics.Color;

public class LineGraph {

	//variables
	int counter;
	
	//objects
	GraphicalView gView;
	TimeSeries timeSeries = new TimeSeries("Line");
	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
	XYSeriesRenderer renderer = new XYSeriesRenderer();
	XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
	
	//methods
	public LineGraph() {
		counter = 0;
		
		dataset.addSeries(timeSeries);
		
		renderer.setColor(Color.BLACK);
		mRenderer.setBackgroundColor(Color.BLACK);
    	mRenderer.setAxesColor(Color.BLACK);
    	mRenderer.setMarginsColor(Color.WHITE);
    	mRenderer.setZoomButtonsVisible(true);
    	
    	mRenderer.addSeriesRenderer(renderer);
	}

	public GraphicalView getView(Context context)
    {	
		gView = ChartFactory.getLineChartView(context, dataset, mRenderer);
    	return gView;
    }
	
	public synchronized void addData(int value)
	{
		timeSeries.add(counter, value);
		counter++;
	}
	
}
