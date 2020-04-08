//package streamstudy;

public class DataPoint {
	private String location;
	private int year;
	private double flowRate;
	private double turbid;
	private double temp;
	private double TDS;
	private double conduct;
	private double salinity;
	private double hardness;
	private double totalCl;
	private double freeCl;
	private double alk;
	private double pH;
	private double nitrate;
	private double nitrite;
	private double phosphate;
	
	public DataPoint()
	{
		location = "Unspecified";
		year = -1;
		flowRate = -1;
		turbid = -1;
		temp = -99;
		TDS = -1;
		conduct = -1;
		salinity = -1;
		hardness = -1;
		totalCl = -1;
		freeCl = -1;
		alk = -1;
		pH = -1;
		nitrate = -1;
		nitrite = -1;
		phosphate = -1;
	}
	
	public DataPoint(Object[] o)
	{
		location = (String)o[0];
		year = (int)o[1];
		if(o[2] == null)
			flowRate = -1;
		else
			flowRate = (double)o[2];
		if(o[3] == null)
			turbid = -1;
		else
			turbid = (double)o[3];
		temp = (double)o[4];
		TDS = (double)o[5];
		conduct = (double)o[6];
		salinity = (double)o[7];
		hardness = (double)o[8];
		totalCl = (double)o[9];
		freeCl = (double)o[10];
		alk = (double)o[11];
		pH = (double)o[12];
		nitrate = (double)o[13];
		nitrite = (double)o[14];
		if(o[15] == null)
			phosphate = -1;
		else
			phosphate = (double)o[15];
	}
	
	public void setStats(Object[] o)
	{
		location = (String)o[0];
		year = (int)o[1];
		if(o[2] == null)
			flowRate = -1;
		else
			flowRate = (double)o[2];
		if(o[3] == null)
			turbid = -1;
		else
			turbid = (double)o[3];
		temp = (double)o[4];
		TDS = (double)o[5];
		conduct = (double)o[6];
		salinity = (double)o[7];
		hardness = (double)o[8];
		totalCl = (double)o[9];
		freeCl = (double)o[10];
		alk = (double)o[11];
		pH = (double)o[12];
		nitrate = (double)o[13];
		nitrite = (double)o[14];
		if(o[15] == null)
			phosphate = -1;
		else
			phosphate = (double)o[15];
	}
	
// Get Methods
	public String getLocation() {
		return location;
	}
	public int getYear() {
		return year;
	}
	public double getFlowRate() {
		return flowRate;
	}
	public double getTurbid() {
		return turbid;
	}
	public double getTemp() {
		return temp;
	}
	public double getTDS() {
		return TDS;
	}
	public double getConduct() {
		return conduct;
	}
	public double getSalinity() {
		return salinity;
	}
	public double getHardness() {
		return hardness;
	}
	public double getTotalCl() {
		return totalCl;
	}
	public double getFreeCl() {
		return freeCl;
	}
	public double getAlk() {
		return alk;
	}
	public double getPH() {
		return pH;
	}
	public double getNitrate() {
		return nitrate;
	}
	public double getNitrite() {
		return nitrite;
	}
	public double getPhosphate() {
		return phosphate;
	}

// toString
	public String toString() {
		String str = "Location: " + getLocation() + ", " +
				"Year: " + getYear() + ", " +
				"Flow Rate: " + getFlowRate() + ", " +
				"Turbid: " + getTurbid() + ", " +
				"Temp: " + getTemp() + ", " +
				"TDS: " + getTDS() + ", " +
				"Conduct: " + getConduct() + ", " +
				"Salinity: " + getSalinity() + ", " +
				"Hardness: " + getHardness() + ", " +
				"Total Cl: " + getTotalCl() + ", " +
				"Free Cl: " + getFreeCl() + ", " +
				"Alk: " + getAlk() + ", " +
				"pH: " + getPH() + ", " +
				"Nitrate: " + getNitrate() + ", " +
				"Nitrite: " + getNitrite() + ", " +
				"Phosphate: " + getPhosphate();
		return str;
	}

	
}
