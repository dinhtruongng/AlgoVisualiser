package visualso.sorting;


public class BubbleSort extends SortingAlgorithm {
    
    public BubbleSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        int n = inputArray.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                // Compare and swap if needed
                if (inputArray[j] > inputArray[j + 1]) {
                    // Swap
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                    swapped = true;
                    
                    // Log the steps
                    addLogs(inputArray, new int[inputArray.length], new int[] {j, j + 1},
                             "Since " + inputArray[j + 1] + " > " + inputArray[j] + ", swapped " + inputArray[j + 1] + " and " + inputArray[j]);
                }
            }
            // If no swaps occurred, the array is sorted
            if (!swapped) break;
        }

        addLogs(inputArray, new int[inputArray.length], new int[] {-1, -1}, 
                 "The array has been sorted.");
    }
}
