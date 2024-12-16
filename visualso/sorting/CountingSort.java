package visualso.sorting;

import visualso.util.ArrayUtils;

public class CountingSort extends SortingAlgorithm {
	
	public CountingSort(int[] array) {
		super(array);
	}
	
	@Override
	public void sort() {
		int[] counts = counter();
		
		int numRestored = 0;
		for (int i=0; i < counts.length; i++) {
			while (counts[i] > 0 & numRestored < inputArray.length) {
				inputArray[numRestored] = i;
				counts[i]--;
				addLogs(inputArray, counts, new int[] {numRestored, i},
						"Restore element " + i + ", and decrease relevant counter by 1");
				numRestored++;
			}
		}
	}
	
	private int[] counter() {
		int max = ArrayUtils.max(inputArray);
		int[] counts = new int[max + 1];
		addLogs(inputArray, counts, new int[] {-1, -1}, // no pointer
				"Create key counting array" + " (" + 0 + " to " + max + ")");
		
		for (int i=0; i < inputArray.length; i++) {
			counts[inputArray[i]] += 1;
			addLogs(inputArray, counts, new int[] {i, inputArray[i]},
					"Increase the counter with key " + inputArray[i] + " by 1");
		}
		
		return counts;
	}
}