package visualso.sorting;

public class QuickSort extends SortingAlgorithm {

    public QuickSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        quickSort(0, inputArray.length - 1);
        addLogs(inputArray, new int[inputArray.length], new int[] {-1, -1}, 
                "The array has been sorted.");
    }
    
    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            
            // Log the pivot selection and partition result
            addLogs(inputArray, new int[inputArray.length], new int[] {pivotIndex, -1},
                    "Partitioned with pivot at index " + pivotIndex + " (value: " + inputArray[pivotIndex] + ")");
            
            quickSort(low, pivotIndex - 1);  // Recursively sort left of pivot
            quickSort(pivotIndex + 1, high); // Recursively sort right of pivot
        }
    }

    private int partition(int low, int high) {
        int pivot = inputArray[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            // Log the current elements being compared
            addLogs(inputArray, new int[inputArray.length], new int[] {j, high},
                    "Comparing element " + inputArray[j] + " at index " + j + " with pivot " + pivot);

            if (inputArray[j] < pivot) {
                i++;
                // Swap elements and log the swap
                int temp = inputArray[i];
                inputArray[i] = inputArray[j];
                inputArray[j] = temp;
                
                addLogs(inputArray, new int[inputArray.length], new int[] {i, j},
                        "Swapped " + inputArray[i] + " and " + inputArray[j]);
            }
        }

        // Swap pivot to the correct position and log it
        int temp = inputArray[i + 1];
        inputArray[i + 1] = inputArray[high];
        inputArray[high] = temp;
        
        addLogs(inputArray, new int[inputArray.length], new int[] {i + 1, high},
                "Moved pivot " + pivot + " to position " + (i + 1));
        
        return i + 1;
    }
}

