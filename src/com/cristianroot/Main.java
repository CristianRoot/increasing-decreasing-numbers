package com.cristianroot;

import java.math.BigInteger;

public class Main {

	private static final long[] summation = new long[10];
	private static final long[][] decResultStack = new long[2][9];
	private static final long[][] incResultStack = new long[2][9];

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		initializeSummations();
		System.out.println("TOTAL -- " + totalIncDec(100) + " in " + (System.currentTimeMillis() - time) + "ms");
	}

	private static void initializeSummations() {
		for (int i = 1; i <= 10; i++) {
			summation[i - 1] = summation(BigInteger.valueOf(i));
		}
	}

	private static long summation(BigInteger n) {
		return n.pow(2).add(n).divide(BigInteger.valueOf(2L)).longValue();
	}

	private static BigInteger totalIncDec(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow);

		int recursionLevel = pow - 3;
		long countIncNumbers = 100;
		long countDecNumbers = 0;

		// First calc increase numbers
		for (int i = 0; i < 9; i++) {
			long calc = summation[i];
			incResultStack[0][i] = calc;
			countIncNumbers += calc;
		}

		// First calc decrease numbers
		for (int i = 2; i <= 10; i++) {
			long calc = summation[i - 1] - 1;
			decResultStack[0][i - 2] = calc;
			countDecNumbers += calc;
		}

		// Simulate recursion
		for (int r = 0; r < recursionLevel; r++) {
			for (int i = 0; i < 9; i++) {
				countIncNumbers += incResultStack[0][i] * (9 - i);
				countDecNumbers += (decResultStack[0][i] + 1) * (9 - i);

				// Update recursion stack
				for (int j = 0; j <= i; j++) {
					incResultStack[1][i] += incResultStack[0][j];
					decResultStack[1][i] += decResultStack[0][j];
				}
				decResultStack[1][i] += i + 1;
			}

			// Prepare stack for the next round
			for (int i = 0; i < 9; i++) {
				incResultStack[0][i] = incResultStack[1][i];
				decResultStack[0][i] = decResultStack[1][i];
				incResultStack[1][i] = 0;
				decResultStack[1][i] = 0;
			}
		}

		return BigInteger.valueOf(countIncNumbers + countDecNumbers);
	}

}
