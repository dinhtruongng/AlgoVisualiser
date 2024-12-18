package test.sorting;

import java.util.Scanner;

import visualso.sorting.BubbleSort;

public class BubbleSortTest {
    public static void main(String[] args) {
        int[] arrayToSort = {64, 34, 25, 12, 22, 11, 90};
        BubbleSort sorter = new BubbleSort(arrayToSort);
        sorter.sort();


		Scanner sc = new Scanner(System.in);
		int testOption = sc.nextInt();
		sc.close();
		
		if (testOption == 1) {
			for (String e : sorter.getGuideLog()) {
				System.out.println(e);
			}
		} else if (testOption == 2) {
			for (int[] e : sorter.getArrayLog()) {
				System.out.println(e);
			}
		} else if (testOption == 3) {
			for (int[] e : sorter.getTempLog()) {
				System.out.println(e);
			}
		} else if (testOption == 4) {
			for (int[] e : sorter.getPointerLog()) {
				System.out.println(e);
			}
		}
		System.out.println("The number of steps is: " + sorter.getNumSteps());
		
	}
}
