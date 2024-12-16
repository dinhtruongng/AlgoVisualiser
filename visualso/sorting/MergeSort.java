package visualso.sorting;

public class MergeSort extends SortingAlgorithm {
	
	public MergeSort(int[] array) {
		super(array);
	}

	@Override
	public void sort() {
		mergeSort(0, inputArray.length - 1);
		addLogs(inputArray, new int[inputArray.length], new int[] {-1, -1}, 
				"The array is sorted");
	}
	
	private void mergeSort(int start, int end) {
		if (start < end) {
			int mid = start + ((end - start) / 2);
			
			addLogs(inputArray, new int[inputArray.length], new int[] {mid, -1}, 
					"Split the array (index " + start + " to " + end + ") into partitions (at index " + mid + ")");
			mergeSort(start, mid);
			mergeSort(mid + 1, end);
			
			merge(start, mid, end);
		}
	}
	
	private void merge(int start, int mid, int end) {
		int l = start;
		int r = mid + 1;
		int k = start;
		
		int[] partArray = new int[inputArray.length];
		
		for (int i=start; i <= end; i++) {
			partArray[i] = inputArray[i];
		}
		
		addLogs(inputArray, partArray, new int[] {-1, -1}, 
				"Merge partitions (index " + start + " to " + mid + ") and (index " + (mid + 1) + " to " + end + ")" );
		
		while (l <= mid && r <= end) {
			if (partArray[l] <= partArray[r]) {
				inputArray[k] = partArray[l];
				addLogs(inputArray, partArray, new int[] {k, l},
						"Since " + partArray[l] + " (left partition)" + " <= " + partArray[r] + " (right partition), " + "we take " + inputArray[k]);
				l++;
				
			} else {
				inputArray[k] = partArray[r];
				addLogs(inputArray, partArray, new int[] {k, r},
						"Since " + partArray[l] + " (left partition)" + " > " + partArray[r] + " (right partition), " + "we take " + inputArray[k]);
				r++;
			}
			k++;
		}
		
		while (l <= mid) {
			inputArray[k] = partArray[l];
			addLogs(inputArray, partArray, new int[] {k, l},
					"Since right partition is empty, we take " + inputArray[k] + " (left partition)");
			k++;
			l++;
		}
		while (r <= end) {
			inputArray[k] = partArray[r];
			addLogs(inputArray, partArray, new int[] {k, r},
					"Since left partition is empty, we take " + inputArray[k] + " (right partition)");
			k++;
			r++;
		}
	}
}
