package visualso.util;

import java.math.BigInteger;
import java.util.Random;

import visualso.exception.DataTypeException;
import visualso.exception.NullException;
import visualso.exception.OutOfBoundException;

public class DataUtils {
    public static int[] randomArray(int qtBound, int maxValue) {
    	Random generator = new Random();
        int[] randArray = new int[generator.nextInt(qtBound - 1) + 2];
        for (int i = 0; i < randArray.length; i++) {
        	randArray[i] = generator.nextInt(maxValue) + 1;
        }
        return randArray ;
    }

    private static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static int[] parseString(String str, int qtBound, int valueBound) 
    		throws DataTypeException, OutOfBoundException, NullException {
        str = str.replaceAll(" ","");
        String[] tokens = str.split(",");
        if (tokens.length == 1) {
            tokens = str.split(" ");
        }
        int[] inputArray = new int[tokens.length];
        if (isNullOrEmpty(str)) {
            throw new NullException("None of array was passed. Please pass an array.");
        }

        if (tokens.length > qtBound) {
            throw new OutOfBoundException("A valid array only contains up to " + qtBound + " elements");
        }

        try {
            for (int i = 0; i < tokens.length; i++) {
                if (new BigInteger(tokens[i]).compareTo(new BigInteger(valueBound + "")) > 0) {
                	throw new OutOfBoundException("A valid array only has max value smaller than " + valueBound);
                }
                inputArray[i] = Integer.parseInt(tokens[i]);
                if (inputArray[i] < 0) {
                    throw new NumberFormatException();
                }
            }
        } catch (NumberFormatException e) {
            throw new DataTypeException("A valid array only contains positive integers");
        }

        return inputArray;
    }
}