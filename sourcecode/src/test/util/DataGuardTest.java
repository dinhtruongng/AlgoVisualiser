package test.util;

import visualso.exception.DataTypeException;
import visualso.exception.NullException;
import visualso.exception.OutOfBoundException;
import visualso.util.DataUtils;

public class DataGuardTest {
	public static void main(String[] args) {
		// DataUtils dataHandler = new DataUtils();
		
		// Random array test
		int[] randomArray = DataUtils.randomArray(20, 50);
		for (int e : randomArray) {
			if (e <= 50 && e >= 1) {
				System.out.println(e);
			} else {
				System.out.println("FAILED " + e);
				break;
			}
		}
		
		// Parse string
		String virtualInput = "10, 9, 11, 50, 1000000";
		try {
			DataUtils.parseString(virtualInput, 20, 50);
		} catch (DataTypeException | OutOfBoundException | NullException e1) {
			e1.printStackTrace();
		}
	}
}
