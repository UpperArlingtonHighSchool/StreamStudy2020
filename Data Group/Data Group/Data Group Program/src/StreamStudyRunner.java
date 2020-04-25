//package UAStreamStudy;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
public class StreamStudyRunner{
	public static void main (String args[]) throws FileNotFoundException {
		Scanner key = new Scanner (new File("StreamDataSpreadsheet.txt"));
		
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
		for(DataPoint item: dataPoints) {
			System.out.println(item.toString());
		}
	}
}
