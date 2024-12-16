package visualso.sorting;

public class ShellSort extends SortingAlgorithm {

    public ShellSort(int[] array) {
        super(array);
    }

    public void sort() {
        int n = inputArray.length;

        // Initial log before sorting starts
        addLogs(inputArray, new int[n], new int[] {-1, -1}, "Starting Shell Sort");

        for (int gap = n / 2; gap > 0; gap /= 2) {
            addLogs(inputArray, new int[n], new int[] {-1, -1}, "Current gap: " + gap);

            for (int i = gap; i < n; i++) {
                int key = inputArray[i];
                int j = i;

                // Log before starting the inner loop
                addLogs(inputArray, new int[n], new int[] {i, j - gap}, 
                        "Key selected: " + key + " at index " + i);

                while (j >= gap && inputArray[j - gap] > key) {
                    inputArray[j] = inputArray[j - gap];

                    // Log each shift
                    addLogs(inputArray, new int[n], new int[] {j, j - gap}, 
                            "Since "+ inputArray[j - gap] + " > " + key + ". Move " + inputArray[j - gap] + " from index " + (j - gap) + " to index " + j);

                    j -= gap;
                }
                inputArray[j] = key;

                // Log after placing the key
                addLogs(inputArray, new int[n], new int[] {j, -1}, 
                        "Placed key " + key + " at index " + j);
            }
        }

        // Final log when sorting is complete
        addLogs(inputArray, new int[n], new int[] {-1, -1}, "Array is sorted");
    }
}

