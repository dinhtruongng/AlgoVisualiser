package test.util;

import visualso.util.ArrayUtils;

public class ArrayUtilsTest {
	public static void main(String[] args) {
		int[] testArray = {4, 2, 5, 60, -1};
		
		// Test max()
		System.out.println(ArrayUtils.max(testArray));
		
		// Test min()
		System.out.println(ArrayUtils.min(testArray));
		
		// Test resize()
		int[][] test2DArray = {{4, 2, 3, 1}, {2, 3, 100, 50}};
		
		System.out.println(test2DArray.length);
		System.out.println(ArrayUtils.resizeIntegers(test2DArray).length);
	}
}
