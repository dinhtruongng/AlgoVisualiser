package test.sorting;

import java.util.Scanner;

import visualso.sorting.SelectionSort;

public class SelectionSortTest {
	public static void main(String[] args) {
		int[] array = new int[] {22, 43, 32, 23, 41, 35, 65, 2, 6, 9};
		SelectionSort sorter = new SelectionSort(array);
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
