import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.apache.commons.math3.fitting.WeightedObservedPoint;

import com.opencsv.CSVWriter;

public class TwoVar {
	private CSVWriter writer;
	private File csv;
	private Scanner key;
	private ArrayList<DataPoint> dataPoints;
	private QuadraticRegression test;  
	private double[][] bestFit;
	
	public TwoVar() throws FileNotFoundException {
		key = new Scanner (new File("StreamDataSpreadsheet.txt"));
		test = new QuadraticRegression();
		getData();
	}
	
	public double[][] getBestFit() {
		bestFit = new double[9][5];
		double[][] equationHolder = new double [3][5];
		String[] var1 = {"temp", "flowrate", "conduct", "conduct", "salinity", "nitrate", "year" , "year", "temp"};
		String[] var2 = {"free chloride", "hardness","salinity", "hardness", "hardness", "phosphate", "phosphate", "PH", "TDS"};
		for(int i = 0; i < 9; i++) {
			equationHolder[0] = getLinReg(var1[i],var2[i]);
			//System.out.println(Arrays.toString(equationHolder[0]));
			equationHolder[1]= getQuadReg(var1[i], var2[i]);
			//System.out.println(Arrays.toString(equationHolder[1]));
			
			equationHolder[2] = getExpReg(var1[i], var2[i]);
			//System.out.println(Arrays.toString(equationHolder[2]));
			
			int fit = 0;
			double temp = equationHolder[0][0];
			if(equationHolder[1][0] > temp) {
				temp = equationHolder[1][0];
				fit = 1;
			}
			if(equationHolder[2][0] > temp) {
				temp = equationHolder[2][0];
				fit = 2;
			}
			bestFit[i] = equationHolder[fit];
			
		}
		return bestFit;
	}
	
	public double[] getQuadReg(String variable1, String variable2) {
		switch(variable1) {
			case "temp":
				switch(variable2) {
					case "free chloride":
						return test.getVar1();
					case "TDS":
						return test.getVar9();
				}
				break;	
			case "flowrate":
				return test.getVar2();
			case "conduct":
				switch(variable2) {
				case "salinity":
					return test.getVar3();
				case "hardness":
					return test.getVar4();
			}
				break;
			case "salinity":
				return test.getVar5();
			case "nitrate":
				return test.getVar6();
			case "year":
				switch(variable2) {
				case "phosphate":
					return test.getVar7();
				case "PH":
					return test.getVar8();
				}
			default:
				break;
		}
		return null;	
	}
	
	public double[] getLinReg(String variable1, String variable2) {
		ArrayList<DataPoint> data1 = dataPoints;
		ArrayList<DataPoint> data2 = dataPoints;
		ArrayList<Double> var1 = new ArrayList<Double>();
		ArrayList<Double> var2 = new ArrayList<Double>();
		for (DataPoint item: data1) {
			switch (variable1) {
			
				case "year":
					var1.add(item.getYear()*1.0);
					break;
				case "flowrate":
					//System.out.println(item.getFlowRate());
					var1.add(item.getFlowRate()*1.0);
					break;
				case "turbid":
					var1.add(item.getTurbid()*1.0);
					break;
				case "temp":
					var1.add(item.getTemp()*1.0);
					break;
				case "TDS":
					var1.add(item.getTDS()*1.0);
					break;
				case "conduct":
					var1.add(item.getConduct()*1.0);
					break;
				case "salinity":
					var1.add(item.getSalinity()*1.0);
					break;
				case "hardness":
					var1.add(item.getHardness()*1.0);
					break;
				case "total chloride":
					var1.add(item.getTotalCl()*1.0);
					break;
				case "free chloride":
					var1.add(item.getFreeCl()*1.0);
					break;
				case "alkine":
					var1.add(item.getAlk()*1.0);
					break;
				case "PH":
					var1.add(item.getPH()*1.0);
					break;
				case "nitrate":
					var1.add(item.getNitrate()*1.0);
					break;
				case "nitrite":
					var1.add(item.getNitrite()*1.0);
					break;
				case "phosphate":
					var1.add(item.getPhosphate()*1.0);
					break;
				default:
					break;
			}
		}
			for (DataPoint item: data2) {
				switch (variable2) {
					case "year":
						var2.add(item.getYear()*1.0);
						break;
					case "flowrate":
						var2.add(item.getFlowRate()*1.0);
						break;
					case "turbid":
						var2.add(item.getTurbid()*1.0);
						break;
					case "temp":
						var2.add(item.getTemp()*1.0);
						break;
					case "TDS":
						var2.add(item.getTDS()*1.0);
						break;
					case "conduct":
						var2.add(item.getConduct()*1.0);
						break;
					case "salinity":
						var2.add(item.getSalinity()*1.0);
						break;
					case "hardness":
						//System.out.println(item.getHardness());
						var2.add(item.getHardness()*1.0);
						break;
					case "total chloride":
						var2.add(item.getTotalCl()*1.0);
						break;
					case "free chloride":
						var2.add(item.getFreeCl()*1.0);
						break;
					case "alkine":
						var2.add(item.getAlk()*1.0);
						break;
					case "PH":
						var2.add(item.getPH()*1.0);
						break;
					case "nitrate":
						var2.add(item.getNitrate()*1.0);
						break;
					case "nitrite":
						var2.add(item.getNitrite()*1.0);
						break;
					case "phosphate":
						var2.add(item.getPhosphate()*1.0);
						break;
					default:
						break;
				}
			}
			LinearRegression JANK = new LinearRegression(var1,var2);
			return JANK.formater();
	}
	
	public double[] getExpReg(String variable1, String variable2){
		ArrayList<DataPoint> data1 = dataPoints;
		ArrayList<DataPoint> data2 = dataPoints;
		ExpodentialRegession SPELLING = new ExpodentialRegession(data1, variable1, data2, variable2);
		SPELLING.setEquation();
		return SPELLING.format();
	}
	
	public void getData() {
		
		dataPoints = new ArrayList<DataPoint>();
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
		
	}
	
	public void writeCSV() throws IOException {
		String[] var1 = {"Temp v Free Chloride", 
						"Flowrate v Hardness", 
						"Conductivity v Salinity", 
						"Conductivity v Hardness", 
						"Salinity v Hardness", 
						"Nitrate v Phosphate", 
						"Year v Phosphate" , 
						"Year v PH", 
						"Temperature v TDS" };
		csv = new File("BestFit.csv");
		FileWriter temp = new FileWriter(csv);
		writer = new CSVWriter(temp);
		for(int i = 0; i< 9; i++) {
			var1[i] += " " + Arrays.toString(bestFit[i]);
			System.out.println(Arrays.toString(var1));
		}
		writer.writeNext(var1);
		writer.close();
	}
	
	public String toString() {
		return null;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
	
	}
}
