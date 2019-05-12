package com.cristianroot;

import java.math.BigInteger;

public class Main {

	private static final int NUMBER = 30;
	private static final long[] summation = new long[10];

	public static void main(String[] args) {
		initializeSummations();

		long time = System.currentTimeMillis();
		long totalInc = totalInc(NUMBER);
		System.out.println("INC -- " + totalInc + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		long totalDec = totalDec(NUMBER);
		System.out.println("DEC -- " + totalDec + ", " + (System.currentTimeMillis() - time) + "ms");

		System.out.println("TOTAL -- " + (totalDec + totalInc));
	}

	private static void initializeSummations() {
		for (int i = 1; i <= 10; i++) {
			summation[i - 1] = summation(BigInteger.valueOf(i));
		}
	}

	private static long totalDec(int pow) {
		long count = 0;

		int recursionLevel = pow - 2;
		for (int i = 0; i < recursionLevel; i++) {
			count += countDecreaseNumbers(0, i);
		}

		return count;
	}

	private static long totalInc(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow).longValue();

		long count = 100;
		int recursionLevel = pow - 2;

		for (int i = 0; i < recursionLevel; i++) {
			count += countIncreaseNumbers(0, i);
		}

		return count;
	}

	private static long summation(BigInteger n) {
		return n.pow(2).add(n).divide(BigInteger.valueOf(2L)).longValue();
	}

	private static long countIncreaseNumbers(int start, int recursionLevel) {
		long count = 0;

		if (recursionLevel == 0) {
			for (int i = start; i < 9; i++) {
				count += summation[8 - i];
			}
		} else {
			for (int i = start; i < 9; i++) {
				count += countIncreaseNumbers(i, recursionLevel - 1);
			}
		}

		return count;
	}

	private static long countDecreaseNumbers(int start, int recursionLevel) {
		long count = 0;

		if (recursionLevel == 0) {
			for (int i = 2; i <= 10 - start; i++) {
				count += summation[i - 1] - 1;
			}
		} else {
			for (int i = start; i < 9; i++) {
				count = count + countDecreaseNumbers(i, recursionLevel - 1) + 9 - i;
			}
		}

		return count;
	}

}
