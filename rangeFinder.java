package rangeFinder;

import java.util.Arrays;
import java.util.Collections;

public class rangeFinder {
	
	
	//Returns the range of an array of intergers
	public static int findRange(int[] a) {
		Arrays.sort(a);
		return(a[a.length-1]-a[0]);
	}
	
	//Returns the range of an array of doubles
	public static double findRange (double[] a) {
		Arrays.sort(a);
		return(a[a.length-1]-a[0]);
	}
	
	//Returns the median of an array
	public static int getMedian(int[] a) {
		Arrays.sort(a);
		return a.length/2;
	}
	
	//Returns the inter-quartile range of an array of integers 
	public static int getIQR(int[] a) {
		Arrays.sort(a);
		
		int mid = getMedian(a);
		
		int[] firstHalf = Arrays.copyOfRange(a, 0, mid);
		int[] secondHalf = Arrays.copyOfRange(a, mid, a.length-1);
		
		int firstQ = getMedian(firstHalf);
		int secondQ = getMedian(secondHalf);
		
		return secondHalf[secondQ]-firstHalf[firstQ];
	}
}
