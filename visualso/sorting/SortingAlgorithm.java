package visualso.sorting;

import visualso.util.ArrayUtils;

public abstract class SortingAlgorithm {
	int[][] arrayLog;
	int[][] tempLog;
	int[][] pointerLog;
	String[] guideLog;
	
	int numSteps = 0;
	int[] inputArray;
	
	public int[][] getArrayLog() {
		return arrayLog;
	}

	public int[][] getTempLog() {
		return tempLog;
	}

	public int[][] getPointerLog() {
		return pointerLog;
	}

	public String[] getGuideLog() {
		return guideLog;
	}

	public int getNumSteps() {
		return numSteps;
	}
	
	public SortingAlgorithm(int[] inputArray) {
		this.inputArray = inputArray;
		
		arrayLog = new int[1][inputArray.length];
		tempLog = new int[1][inputArray.length];
		pointerLog = new int[1][2]; // two pointers per step
		guideLog = new String[1];
	}
	
	public abstract void sort();
	
	void addLogs(int[] array, int[] temp, int[] pointer, String guide) {
		if (arrayLog.length == numSteps) {
			arrayLog = ArrayUtils.resizeIntegers(arrayLog);
			tempLog = ArrayUtils.resizeIntegers(tempLog);
			pointerLog = ArrayUtils.resizeIntegers(pointerLog);
			guideLog = ArrayUtils.resizeStrings(guideLog);
		}
		
		arrayLog[numSteps] = array.clone();
		tempLog[numSteps] = temp.clone();
		pointerLog[numSteps] = pointer;
		guideLog[numSteps] = guide;
		
		numSteps++;
	}
}
