package com.cristianroot;

import java.math.BigInteger;

public class Main {

	private static final int NUMBER = 3;

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		BigInteger totalIncMath = totalIncMath(NUMBER);
		System.out.println("INC -- " + totalIncMath.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		BigInteger totalDecMath = totalDecMath(NUMBER);
		System.out.println("DEC -- " + totalDecMath.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		System.out.println("TOTAL -- " + (totalDecMath.add(totalIncMath)));
	}

	private static BigInteger totalDecMath(int pow) {
		BigInteger count = BigInteger.ZERO;

		int recursionLevel = pow - 2;
		for (int i = 0; i < recursionLevel; i++) {
			count = count.add(countDecreaseNumbers(0, i));
		}

		return count;
	}

	private static BigInteger totalIncMath(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow);

		BigInteger count = BigInteger.valueOf(100L);
		int recursionLevel = pow - 2;

		for (int i = 0; i < recursionLevel; i++) {
			count = count.add(countIncreaseNumbers(0, i));
		}

		return count;
	}

	private static BigInteger sumatorium(BigInteger n) {
		return n.pow(2).add(n).divide(BigInteger.valueOf(2L));
	}

	private static BigInteger countIncreaseNumbers(int start, int recursionLevel) {
		BigInteger count = BigInteger.ZERO;

		for (int i = start; i < 9; i++) {
			count = count.add(recursionLevel > 0 ? countIncreaseNumbers(i, recursionLevel - 1)
												 : sumatorium(BigInteger.valueOf(9L - i)));
		}

		return count;
	}

	private static BigInteger countDecreaseNumbers(int start, int recursionLevel) {
		BigInteger count = BigInteger.ZERO;

		if (recursionLevel == 0) {
			for (int i = 2; i <= 10 - start; i++) {
				count = count.add(sumatorium(BigInteger.valueOf(i)).subtract(BigInteger.ONE));
			}
		} else {
			for (int i = start; i < 9; i++) {
				count = count.add(countDecreaseNumbers(i, recursionLevel - 1));
				count = count.add(BigInteger.valueOf(9 - i));
			}
		}

		return count;
	}

}
