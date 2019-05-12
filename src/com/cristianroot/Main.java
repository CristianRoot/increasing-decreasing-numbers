package com.cristianroot;

import java.math.BigInteger;

public class Main {

	private static final int NUMBER = 40;
	private static final long[] summation = new long[10];
	private static long[][] decResultStack;
	private static long[][] incResultStack;

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

	private static long summation(BigInteger n) {
		return n.pow(2).add(n).divide(BigInteger.valueOf(2L)).longValue();
	}

	private static long totalInc(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow).longValue();

		long count = 100;
		int recursionLevel = pow - 2;

		incResultStack = new long[recursionLevel][9];
		for (int i = 0; i < recursionLevel; i++) {
			count += countIncreaseNumbers(i);
		}

		return count;
	}

	private static long countIncreaseNumbers(int recursionLevel) {
		long count = 0;

		if (recursionLevel == 0) {
			for (int i = 0; i < 9; i++) {
				long calc = summation[i];
				incResultStack[recursionLevel][i] = calc;
				count += calc;
			}
		} else {
			for (int i = 0; i < 9; i++) {
				count += incResultStack[recursionLevel - 1][i] * (9 - i);

				// Update recursion stack
				long acc = 0;
				for (int j = 0; j <= i; j++) {
					acc += incResultStack[recursionLevel - 1][j];
				}
				incResultStack[recursionLevel][i] = acc;
			}
		}

		return count;
	}

	private static long totalDec(int pow) {
		long count = 0;
		int recursionLevel = pow - 2;

		decResultStack = new long[recursionLevel][9];
		for (int i = 0; i < recursionLevel; i++) {
			count += countDecreaseNumbers(i);
		}

		return count;
	}

	private static long countDecreaseNumbers(int recursionLevel) {
		long count = 0;

		if (recursionLevel == 0) {
			for (int i = 2; i <= 10; i++) {
				long calc = summation[i - 1] - 1;
				decResultStack[recursionLevel][i - 2] = calc;
				count += calc;
			}
		} else {
			for (int i = 2; i <= 10; i++) {
				count += (decResultStack[recursionLevel - 1][i - 2] + 1) * (11 - i);

				// Update recursion stack
				long acc = 0;
				for (int j = 2; j <= i; j++) {
					acc += decResultStack[recursionLevel - 1][j - 2];
				}
				decResultStack[recursionLevel][i - 2] = acc + (i - 1);
			}
		}

		return count;
	}

}
