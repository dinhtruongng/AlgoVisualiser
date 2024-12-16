 package visualso.sorting;

public class SelectionSort extends SortingAlgorithm {

    public SelectionSort(int[] array) {
        super(array);
    }

    @Override
    public void sort() {
        for (int i = 0; i < inputArray.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < inputArray.length; j++) {
                if (inputArray[j] < inputArray[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                int temp = inputArray[i];
                inputArray[i] = inputArray[minIndex];
                inputArray[minIndex] = temp;
            }

            addLogs(inputArray, new int[inputArray.length], new int[] {i, minIndex},
            		"Since " + inputArray[minIndex] + " at the index " + minIndex +" is the current minimum.\nSwap elements at index " + i + " and " + minIndex);
        }

        addLogs(inputArray, new int[inputArray.length], new int[] {-1, -1},
                 "Array is sorted.");
    }
}
