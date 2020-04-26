//package UAStreamStudy;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoint;
import org.apache.commons.math3.fitting.AbstractCurveFitter;
public class QuadraticRegression{
	private double[] tempvchlorine, flowvhardness, conductivityvsalinity, conductivityvhardness, salinityvhardness, nitratevphosphate, yearvphosphate, yearvph, tempvtds;
	public QuadraticRegression() throws FileNotFoundException {
		Scanner key = new Scanner (new File("/Users/kwinstein/eclipse-workspace/QuadraticRegression/src/StreamDataSpreadsheet.txt"));
		
		ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
		key.nextLine();
//		System.out.println("row 1");
		String big;
		while(key.hasNextLine()) {
			big = key.nextLine();
			Object[] dataFields = new Object[16];
			String[] stringFields = big.split(",");
//			System.out.println(Arrays.toString(stringFields));
			dataFields[0] = stringFields[0];
			Scanner a = new Scanner(stringFields[1]);
			dataFields[1] = a.nextInt();
			for(int i = 2; i< 16; i++) {
				if(stringFields[i].equals("null")) {
					dataFields[i] = null;
				}
				else {
					Scanner b = new Scanner(stringFields[i]);
					dataFields[i] = b.nextDouble();
				}
			}
//			System.out.println("another row");
			DataPoint newDP = new DataPoint();
			dataPoints.add(newDP);
			newDP.setStats(dataFields);
		}
		
		//Start of Karun's work
		PolynomialCurveFitter bob = PolynomialCurveFitter.create(2);
		List<WeightedObservedPoint> points = new ArrayList<WeightedObservedPoint>();
		
		//Temp in C v Free Chlorine
		for(DataPoint item: dataPoints) {
			points.add(new WeightedObservedPoint(1.0,item.getTemp(),item.getFreeCl()));
			
		}
		double[] equation = bob.fit(points);
		
		double sse=0;
		double sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-6.7025, 2);
			
		}
		tempvchlorine = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		
		//Flow Rate v Hardness
		points.clear();
		for(DataPoint item: dataPoints) {
			if(item.getFlowRate() == -1.0) {
			points.add(new WeightedObservedPoint(0.0,item.getFlowRate(),item.getHardness()));	
			}
			else {
			points.add(new WeightedObservedPoint(1.0,item.getFlowRate(),item.getHardness()));
			}
					
		}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			if(x==-1.0) {
			}
			else {
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-146.345, 2);
			}
			
		}
		flowvhardness = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Conductivity v Salinity
		points.clear();
		for(DataPoint item: dataPoints) {
			points.add(new WeightedObservedPoint(1.0,item.getConduct(),item.getSalinity()));
					
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-382.605, 2);
			
		}
		conductivityvsalinity = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Conductivity v Hardness
		points.clear();
		for(DataPoint item: dataPoints) {
			points.add(new WeightedObservedPoint(1.0,item.getConduct(),item.getHardness()));
					
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-146.345, 2);
			
		}
		conductivityvhardness = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Salinity v Hardness
		points.clear();
		for(DataPoint item: dataPoints) {
			points.add(new WeightedObservedPoint(1.0,item.getSalinity(),item.getHardness()));
					
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-146.345, 2);
			
		}
		salinityvhardness = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Nitrate v Phosphate
		points.clear();
		for(DataPoint item: dataPoints) {
			if(item.getPhosphate() == -1.0) {
				points.add(new WeightedObservedPoint(0.0,item.getFlowRate(),item.getHardness()));	
				}
			else {
			points.add(new WeightedObservedPoint(1.0,item.getNitrate(),item.getPhosphate()));
			}
					
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			if(y==-1.0) {
			}
			else {
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-10.78421053, 2);
			}
			
		}
		nitratevphosphate = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Year v Phosphate
		points.clear();
		for(DataPoint item: dataPoints) {
			if(item.getPhosphate() == -1.0) {
				points.add(new WeightedObservedPoint(0.0,item.getFlowRate(),item.getHardness()));	
				}
			else {
			points.add(new WeightedObservedPoint(1.0,item.getNitrate(),item.getPhosphate()));
			}	
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			if(y==-1.0) {
			}
			else {
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-10.78421053, 2);
			}
			
		}
		yearvphosphate = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Year v PH
		points.clear();
		for(DataPoint item: dataPoints) {
			points.add(new WeightedObservedPoint(1.0,item.getYear(),item.getPH()));
					
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-7.08, 2);
			
		}
		yearvph = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
		//Temp in C v TDS
		points.clear();
		for(DataPoint item: dataPoints) {
			points.add(new WeightedObservedPoint(1.0,item.getTemp(),item.getTDS()));
					
				}
		equation = bob.fit(points);
		
		sse=0;
		sst=0;
		for(int i=0; i<points.size(); i++) {
			double y = points.get(i).getY();
			double x = points.get(i).getX();
			sse += Math.pow(y-equation[2]*Math.pow(x, 2)-equation[1]*x-equation[0], 2.0);
			sst += Math.pow(y-529.72, 2);
			
		}
		tempvtds = new double[] {(1-(sse/sst)),1,equation[2],equation[1],equation[0]};
		
	}
	
	public double[] getVar1() {
		return tempvchlorine;
	}
	public double[] getVar2() {
		return flowvhardness;
	}
	public double[] getVar3() {
		return conductivityvsalinity;
	}
	public double[] getVar4() {
		return conductivityvhardness;
	}
	public double[] getVar5() {
		return salinityvhardness;
	}
	public double[] getVar6() {
		return nitratevphosphate;
	}
	public double[] getVar7() {
		return yearvphosphate;
	}
	public double[] getVar8() {
		return yearvph;
	}
	public double[] getVar9() {
		return tempvtds;
	}
	public String toString() {
		return "Variable Name: [r value, identifier, a, b, c]\n\n" +
		"\ntempvchlorine: " + Arrays.toString(tempvchlorine) +
		"\nflowvhardness: " + Arrays.toString(flowvhardness) +
		"\nconductivityvsalinity: " + Arrays.toString(conductivityvsalinity) +
		"\nconductivityvhardness: " + Arrays.toString(conductivityvhardness) +
		"\nsalinityvhardness: " + Arrays.toString(salinityvhardness) +
		"\nnitratevphosphate: " + Arrays.toString(nitratevphosphate) +
		"\nyearvphosphate: " + Arrays.toString(yearvphosphate) +
		"\nyearvph: " + Arrays.toString(yearvph) +
		"\ntempvtds: " + Arrays.toString(tempvtds);
		
	}
}
