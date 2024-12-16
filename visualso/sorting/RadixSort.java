package visualso.sorting;

import visualso.util.ArrayUtils;

public class RadixSort extends SortingAlgorithm {
	private int max; 

	public RadixSort(int[] array) {
		super(array);
		max = ArrayUtils.max(inputArray);
	}

	@Override
	public void sort() {
		int maxNumDigits = numDigits(max);
		int digitPlacing = 1;
		for (int i=0; i < maxNumDigits; i++) {
			countingSortByDigit(digitPlacing);
			digitPlacing *= 10;
		}
	}
	
	private int numDigits(int max) {
		int numDigits = 0;
		while (max != 0) {
			max /= 10;
			numDigits++;
		}
		return numDigits;
	}
	
	private void countingSortByDigit(int placing) {
		int[][] buckets = new int[10][inputArray.length];
		int[][] bucketBounds = new int[2][10];
		addLogs(inputArray, bucketBounds[1], new int[] {-1, -1},
				"Create 10 buckets for each digit (0 to 9)");
		
		for (int i=0; i < inputArray.length; i++) {
			int digit = (inputArray[i] / placing) % 10;
			buckets[digit][bucketBounds[1][digit]] = inputArray[i];
			bucketBounds[1][digit]++;
			addLogs(inputArray, bucketBounds[1], new int[] {i, digit},
					"Moving " + inputArray[i] + " to bucket no " + digit);
		}
		
		int numRestored = 0;
		for (int i=0; i < buckets.length; i++) {
			if (numRestored >= inputArray.length) {
				break;
			}
			
			int numElementsInBucket = bucketBounds[1][i];
			while (bucketBounds[0][i] < numElementsInBucket) {
				inputArray[numRestored] = buckets[i][bucketBounds[0][i]];
				bucketBounds[0][i]++;
				bucketBounds[1][i]--;
				addLogs(inputArray, bucketBounds[1], new int[] {numRestored, i},
						"Restore " + inputArray[numRestored] + " to position " + numRestored + " in the list");
				numRestored++;
			}
		}
	}
}

