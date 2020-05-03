import java.util.ArrayList;
import java.util.Arrays;
public class LinearRegression 
{
	private ArrayList<Double> xs;
	private ArrayList<Double> ys;
	private double xaverage;
	private double yaverage;
	private double slope;
	private double yIntercept;
	private double[] format;
	private double cc;
	public LinearRegression()//https://medium.com/@equipintelligence/java-algorithms-the-linear-regression-classifier-41a31f1c9018 Used this to teach myself how linear regression is calculated and modified it to make it fit with the program.
	{
		
	}
	public LinearRegression(ArrayList<Double> x, ArrayList<Double> y)
	{
		xs=x;
		ys=y;
	}
	public void xAverage()
	{
		double xTotal = 0;
		for(int i = 0; i<xs.size(); i++)
		{
			xTotal+=xs.get(i);
		}
		xaverage = xTotal/xs.size();
	}
	public void yAverage()
	{
		double yTotal = 0;
		for(int i = 0; i<ys.size(); i++)
		{
			yTotal+=ys.get(i);
		}
		yaverage = yTotal/ys.size();
		
	}
	public void slopeFinder()
	{
		double extra1 = xs.get(0)-xaverage;
		double extra2 = ys.get(0)-yaverage;
		double bottom = (xs.get(0)-xaverage)*(xs.get(0)-xaverage);
		if(bottom == 0)
			slope = 0;
		else
			slope = (extra1*extra2)/bottom;
//		System.out.println(extra1);
//		System.out.println(extra2);
//		System.out.println(xs.get(0));
//		System.out.println(xaverage);
//		System.out.println(ys.get(0));
//		System.out.println(yaverage);
//		System.out.println(bottom);
//		System.out.println(slope);
	}
	public void yInterceptFinder()
	{
		yIntercept = yaverage-(slope * xaverage);
	}
	public void correlationCoefficent()
	{
		if(slope>1)
		{
			cc=1/slope;
		}
		else if(slope<-1)
		{
			cc=1/slope;
		}
		else if(slope == 0) {
			cc = 1;
		}
		else
		{
			cc=slope;
		}
	}
	public double[] formater()
	{
		xAverage();
		yAverage();
		slopeFinder();
		yInterceptFinder();
		correlationCoefficent();
		format = new double[5];
		format[0]=cc;
		format[1]=0;
		format[2]=slope;
		format[3]=yIntercept;
		format[4]=0;
		return format;
	}
}
