import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class TwoVarTest {

	public static void main(String[] args) throws IOException {
		TwoVar tester = new TwoVar();
		tester.getBestFit();
		tester.writeCSV();	
	}

}
