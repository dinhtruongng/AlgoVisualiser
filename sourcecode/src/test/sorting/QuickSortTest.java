package test.sorting;

import java.util.Scanner;
import visualso.sorting.QuickSort;

public class QuickSortTest {
    public static void main(String[] args) {
        int[] arrayToSort = {31, 36, 42, 32, 7, 38, 7, 29, 4, 6};
        QuickSort sorter = new QuickSort(arrayToSort);
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