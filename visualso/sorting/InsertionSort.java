package visualso.sorting;

public class InsertionSort extends SortingAlgorithm {

    public InsertionSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        for (int i = 1; i < inputArray.length; i++) {
            int key = inputArray[i];
            int j = i - 1;
            
            // Log the current state before insertion
            addLogs(inputArray, new int[inputArray.length], new int[] {i, j},
                    "Key selected: " + key + " at position " + i);

            // Shift elements of the sorted portion of the array to the right
            while (j >= 0 && inputArray[j] > key) {
                inputArray[j + 1] = inputArray[j];
                j--;

                // Log each shift
                addLogs(inputArray, new int[inputArray.length], new int[] {j + 1, j},
                        "Since " + inputArray[j + 1] + " > " + key + ". Move " + inputArray[j + 1] + " to position " + (j + 2));
            }
            inputArray[j + 1] = key;

            // Log insertion of the key
            addLogs(inputArray, new int[inputArray.length], new int[] {j + 1, i},
                    "Inserted key " + key + " at position " + (j + 1));
        }
        
        addLogs(inputArray, new int[inputArray.length], new int[] {-1, -1},
                "The array has been sorted.");
    }
}