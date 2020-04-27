import java.util.*;
import java.text.*;
import java.awt.*;

public  class ExpodentialRegession{
	
	
	
	
	
	ArrayList<Object> var1;
	ArrayList<Object> var2;
	double a = 1.0;
	double b = 1.0;
	
	public ExpodentialRegession(){
		var1 = new ArrayList<Object>();
		var2 = new ArrayList<Object>();
	}
	public ExpodentialRegession(ArrayList<Object> data1, ArrayList<Object> data2){
			var1 = data1;
			var2 = data2;
		}
	
	public ExpodentialRegession(ArrayList<DataPoint> data1, String varible1, ArrayList<DataPoint> data2, String varible2){
		int i = 0;
		for (DataPoint item: data1) {
			switch (varible1) {
				case "year":
					var1.add(item.getYear()*1.0);
					break;
				case "flowrate":
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
				var1.add(0);
					break;
			}
		}
			for (DataPoint item: data2) {
				switch (varible2) {
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
					var2.add(0);
						break;
				}
			}
		
		
		
		
		this.setEquation();
		
	}
	
	public void setEquation(){
		int k = 0;
		double scaler = 1.0;
		double scalerb = 1.0;
		boolean gradAisPositive = true;
		boolean gradBisPositive = true;
		do{
			double gradA = 0.0;
			double gradB = 0.0;
			int i = 0;
			for(Object item: var1){
				Double x = (Double)item;
				Double y = (Double)var2.get(i);
			gradA = gradA + 2*(y-a*Math.pow(Math.E, b*x))*(-1*Math.pow(Math.E, b*x));
			gradB = gradB + 2*(y-a*Math.pow(Math.E, b*x))*(-1*a*x*Math.pow(Math.E, x*b));
			i++;	
			}
			double vectorMag = Math.sqrt(Math.pow(gradA,2)+Math.pow(gradB,2));
			if (gradAisPositive && (gradA <= 0.0)) {
				scaler = scaler*.1;
				gradAisPositive = false;
			}
			else if (!gradAisPositive && (gradA > 0)){
				scaler = scalerb*.1;
				gradAisPositive = true;
			}
			
			if (gradBisPositive && (gradB <= 0)) {
				scalerb = scalerb*.1;
				gradBisPositive = false;
			}
			else if (!gradBisPositive && (gradB > 0)){
				scalerb = scalerb*.1;
				gradBisPositive = true;
			}
			a = a-gradA/vectorMag*scaler;
			b = b-gradB/vectorMag*scalerb;
			k++;
			//System.out.println(gradB/vectorMag*scaler);			
		}while(k<10000000);
	}
	
	public String toString(){
		return "The equation of the line of best fit is: f(x) = "+a+" * e^"+b+"x";
	}
	
	public double getBValue(){
		return b;
	}
	
	public double getAValue(){
		return a;
			
	}
	
	public double getRValue(){
		double sumAvg = 0.0;
		for (Object item: var2) {
			sumAvg = sumAvg+(Double)item;
		}
		sumAvg = sumAvg/var2.size();
		Double ssTot = 0.0;
		Double ssReg = 0.0;
		for (Object item: var2) {
			ssTot = ssTot+Math.pow(((Double)item-sumAvg),2);
			
		}
		int i = 0;
		for (Object item: var1) {
			ssReg = ssReg+Math.pow((Double)var2.get(i)-a*Math.pow(Math.E, b*(Double)item),2);
			i++;
		}
		return 1-ssReg/ssTot;
	}
	
	
	
	
	public static void main(String[] args) {
		ArrayList<Object> test1 = new ArrayList<Object>();
		ArrayList<Object> test2 = new ArrayList<Object>();
		for(int j = 0; j<5; j++){
			test1.add(j*1.0);
			test2.add(100*Math.pow(Math.E,j*-.25)+Math.random()*5);
		}
		ExpodentialRegession tester = new ExpodentialRegession(test1,test2);
		tester.setEquation();
		System.out.println(tester);
		System.out.println(tester.getRValue());
	}
}