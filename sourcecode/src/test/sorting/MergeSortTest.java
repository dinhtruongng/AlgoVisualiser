package test.sorting;

import java.util.Scanner;

import visualso.sorting.MergeSort;

public class MergeSortTest {
	public static void main(String[] args) {
		int[] array = new int[] {3, 3, 2, 1, 1, 2, 0};
		MergeSort sorter = new MergeSort(array);
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
