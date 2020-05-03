
import java.awt.Color;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;

public class graph extends JFrame {
	private static final long serialVersionUID = 1L;
	private static String var1="";
    private static String var2="";
    private static ArrayList<DataPoint> data;
 
	
public static void variable(String v1, String v2)
{
	var1 = v1;
	var2 = v2;
}

public static void datapoints(ArrayList<DataPoint> d)
{
	data = d;
}

public graph(String title) throws FileNotFoundException {
    super(title);
    
    // Create dataset
    XYDataset dataset = createDataset();

    // Create chart

    JFreeChart chart = ChartFactory.createScatterPlot(
        ""+title, 
        var1,""+var2, dataset);

    
    //Changes background color
    XYPlot plot = (XYPlot)chart.getPlot();
    plot.setBackgroundPaint(new Color(255,228,196));
    
   
    // Create Panel
    ChartPanel panel = new ChartPanel(chart);
    setContentPane(panel);
  }

  private XYDataset createDataset() throws FileNotFoundException {
    XYSeriesCollection dataset = new XYSeriesCollection();
    
    XYSeries series1 = new XYSeries(var1+" and "+var2);
    XYSeries series2 = new XYSeries("Best Fit");
    double y = 0.0;
    double x = 0.0;
  //  System.out.println("g"+data.get(0).getNitrate());
    for(int i=0;i<16;i++)
    {
 //   int place = data.get(i).indexOf(var1);
   // System.
    	switch(var1)
    	{
        case "flowrate":
        x = data.get(i).getFlowRate();
        break;
        case "year":
        	x= data.get(i).getYear();
        break;
    case "turbidity":
        x = data.get(i).getTurbid();
        break;
    case "temperature":
        x = data.get(i).getTemp();
        break;
    case "tds":
        x = data.get(i).getTDS();
        break;
    case "salinity":
        x = data.get(i).getSalinity();
        break;
    case "conductivity":
        x = data.get(i).getConduct();
        break;
    case "hardness":
        x = data.get(i).getHardness();
        break;
    case "totalchlorine":
        x = data.get(i).getTotalCl();
        break;
    case "freechlorine":
        x = data.get(i).getFreeCl();
        break;
    case "alkalinity":
        x = data.get(i).getAlk();
        break;
    case "ph":
        x = data.get(i).getPH();
        break;
    case "nitrate":
        x = data.get(i).getNitrate();
        break;
    case "nitrite":
        x = data.get(i).getNitrite();
        break;
    case "phosphate":
        x = data.get(i).getPhosphate();
        break;
    	}
    	
    	
    	
    	switch(var2)
    	{
    	  case "flowrate":
    	        y = data.get(i).getFlowRate();
    	        break;
    	        case "year":
    	        	y= data.get(i).getYear();
    	        break;
    	    case "turbidity":
    	        y = data.get(i).getTurbid();
    	        break;
    	    case "temperature":
    	        y = data.get(i).getTemp();
    	        break;
    	    case "tds":
    	        y = data.get(i).getTDS();
    	        break;
    	    case "salinity":
    	        y = data.get(i).getSalinity();
    	        break;
    	    case "conductivity":
    	        y = data.get(i).getConduct();
    	        break;
    	    case "hardness":
    	        y = data.get(i).getHardness();
    	        break;
    	    case "totalchlorine":
    	        y = data.get(i).getTotalCl();
    	        break;
    	    case "freechlorine":
    	        y = data.get(i).getFreeCl();
    	        break;
    	    case "alkalinity":
    	        y = data.get(i).getAlk();
    	        break;
    	    case "ph":
    	        y = data.get(i).getPH();
    	        break;
    	    case "nitrate":
    	        y = data.get(i).getNitrate();
    	        break;
    	    case "nitrite":
    	        y = data.get(i).getNitrite();
    	        break;
    	    case "phosphate":
    	        y = data.get(i).getPhosphate();
    	        break;
    	    	}
    	
    	
    	series1.add(x,y);
    }
    TwoVar tester = new TwoVar();
	double[][] graphBestFit = tester.getBestFit();
	
	
	if((var1.equals("nitrate") && var2.equals("phosphate")) || (var2.equals("nitrate") && var1.equals("phosphate")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[5][i];
	    	series2.add(data.get(i*4).getNitrate(), adder);
	    }
	}
	
	else if((var1.equals("temperature") && var2.equals("freechlorine")) || (var1.equals("freechlorine") && var2.equals("temperature")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[0][i];
	    	series2.add(data.get(i*4).getTemp(), adder);
	    }
	}
	
	else if((var1.equals("flowrate") && var2.equals("hardness")) || (var1.equals("hardness") && var2.equals("flowRate")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[1][i];
	    	series2.add(data.get(i*4).getFlowRate(), adder);
	    }
	}
	
	else if((var1.equals("conductivity") && var2.equals("salinity")) || (var1.equals("salinity") && var2.equals("conductivity")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[2][i];
	    	series2.add(data.get(i*4).getConduct(), adder);
	    }
	}
	
	else if((var1.equals("conductivity") && var2.equals("hardness")) || (var1.equals("hardness") && var2.equals("conductivity")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[3][i];
	    	series2.add(data.get(i*4).getHardness(), adder);
	    }
	}
	
	else if((var1.equals("hardness") && var2.equals("salinity")) || (var1.equals("salinity") && var2.equals("hardness")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[4][i];
	    	series2.add(data.get(i*4).getHardness(), adder);
	    }
	}

	else if((var1.equals("phosphate") && var2.equals("year")) || (var1.equals("year") && var2.equals("phosphate")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[6][i];
	    	series2.add(data.get(i*4).getPhosphate(), adder);
	    }
	}
	
	else if((var1.equals("year") && var2.equals("ph")) || (var1.equals("ph") && var2.equals("year")))
	{
		for(int i = 0; i < 4; i++)
	    {
			double adder = graphBestFit[7][i];
	    	series2.add(data.get(i*4).getPH(), adder);
	    }
	}
	
	else if((var1.equals("temperature") && var2.equals("tds")) || (var1.equals("tds") && var2.equals("temperature")))
	{
		for(int i = 0; i < 4; i++)
		   {
				double adder = graphBestFit[8][i];
		    	series2.add(data.get(i*4).getTemp(), adder);
		   }
	}
	
	
    dataset.addSeries(series1);
    dataset.addSeries(series2);
    	/*
    	public int getYear() {
    		return year;
    	}
    	
    	}/*
   
    
  

    return dataset;
  }

  /*public static void setPoints1(String var1)
  {
	  for(int i = 0; i < )
  }*/
return dataset;
}
}


