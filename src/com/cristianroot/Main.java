package com.cristianroot;

import java.math.BigInteger;

public class Main {

	private static final int NUMBER = 6;
	private static final long[] summation = new long[10];
	private static final long[][] decResultStack = new long[2][9];
	private static final long[][] incResultStack = new long[2][9];

	public static void main(String[] args) {
		initializeSummations();

		long time = System.currentTimeMillis();
		long totalInc = countIncreaseNumbers(NUMBER);
		System.out.println("INC -- " + totalInc + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		long totalDec = countDecreaseNumbers(NUMBER);
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

	private static long countIncreaseNumbers(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow).longValue();

		int recursionLevel = pow - 3;
		long count = 100;

		// First calc
		for (int i = 0; i < 9; i++) {
			long calc = summation[i];
			incResultStack[0][i] = calc;
			count += calc;
		}

		// Simulate recursion
		for (int r = 0; r < recursionLevel; r++) {
			for (int i = 0; i < 9; i++) {
				count += incResultStack[0][i] * (9 - i);

				// Update recursion stack
				for (int j = 0; j <= i; j++) {
					incResultStack[1][i] += incResultStack[0][j];
				}
			}

			// Prepare stack for the next round
			for (int i = 0; i < 9; i++) {
				incResultStack[0][i] = incResultStack[1][i];
				incResultStack[1][i] = 0;
			}
		}

		return count;
	}

	private static long countDecreaseNumbers(int pow) {
		long count = 0;
		int recursionLevel = pow - 3;

		// First calc
		for (int i = 2; i <= 10; i++) {
			long calc = summation[i - 1] - 1;
			decResultStack[0][i - 2] = calc;
			count += calc;
		}

		// Simulate recursion
		for (int r = 0; r < recursionLevel; r++) {
			for (int i = 2; i <= 10; i++) {
				count += (decResultStack[0][i - 2] + 1) * (11 - i);

				// Update recursion stack
				for (int j = 2; j <= i; j++) {
					decResultStack[1][i - 2] += decResultStack[0][j - 2];
				}
				decResultStack[1][i - 2] += i - 1;
			}

			// Prepare stack for the next round
			for (int i = 0; i < 9; i++) {
				decResultStack[0][i] = decResultStack[1][i];
				decResultStack[1][i] = 0;
			}
		}

		return count;
	}

}
