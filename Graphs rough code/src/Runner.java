import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Runner {
	 public static void main(String[] args) throws FileNotFoundException {
		 Scanner nick = new Scanner(System.in);
		 int c =1;
		 Scanner key = new Scanner (new File("StreamDataSpreadsheet.txt"));
			
			ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
			key.nextLine();
//			System.out.println("row 1");
			String big;
			while(key.hasNextLine()) {
				big = key.nextLine();
				Object[] dataFields = new Object[16];
				String[] stringFields = big.split(",");
//				System.out.println(Arrays.toString(stringFields));
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
//				System.out.println("another row");
				DataPoint newDP = new DataPoint();
				dataPoints.add(newDP);
				newDP.setStats(dataFields);
			}
		 do{
			// System.out.println(dataPoints.get(0).getYear());
		 System.out.println("What is the first variable? (Spell Exact)");
		 String variable1 = nick.nextLine();
		 System.out.println("What is the second variable? (Spell Exact)");
		 String variable2 = nick.nextLine();
		 String variable3 = variable1.trim().toLowerCase();
		 String variable4 = variable2.trim().toLowerCase();
		 //System.out.println(variable3+variable4);
		 
		// System.out.println(variable1+variable2);
		 
		    SwingUtilities.invokeLater(() -> {
		    	graph.datapoints(dataPoints);
		    	graph.variable(variable3, variable4);
		    	//graph var = new graph(variable1,variable2);
		      graph example = new graph(""+variable1+ " VS "+variable2+" comparison chart");
		    
		      example.setSize(800, 400);
		      example.setLocationRelativeTo(null);
		      example.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		      example.setVisible(true);
		    });
		    System.out.println("New Graph?");
		    if(nick.nextLine().equals("no"))
		    	c=0;
		  }while(c==1);
	 }

}

