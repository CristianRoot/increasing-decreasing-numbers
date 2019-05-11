package com.cristianroot;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class Main {

	private static final int NUMBER = 20;
	private static final BigInteger[] summation = new BigInteger[10];
	private static Map<Long, BigInteger> decResultStack = new HashMap<>();
	private static Map<Long, BigInteger> incResultStack = new HashMap<>();

	public static void main(String[] args) {
		initializeSummations();

		long time = System.currentTimeMillis();
		BigInteger totalInc = totalInc(NUMBER);
		System.out.println("INC -- " + totalInc.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		BigInteger totalDec = totalDec(NUMBER);
		System.out.println("DEC -- " + totalDec.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		System.out.println("TOTAL -- " + (totalDec.add(totalInc)));
	}

	private static void initializeSummations() {
		for (int i = 1; i <= 10; i++) {
			summation[i - 1] = summation(BigInteger.valueOf(i));
		}
	}

	private static BigInteger totalDec(int pow) {
		BigInteger count = BigInteger.ZERO;

		int recursionLevel = pow - 2;
		for (int i = 0; i < recursionLevel; i++) {
			count = count.add(countDecreaseNumbers(0, i));
		}

		return count;
	}

	private static BigInteger totalInc(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow);

		BigInteger count = BigInteger.valueOf(100L);
		int recursionLevel = pow - 2;

		for (int i = 0; i < recursionLevel; i++) {
			count = count.add(countIncreaseNumbers(0, i));
		}

		return count;
	}

	private static BigInteger summation(BigInteger n) {
		return n.pow(2).add(n).divide(BigInteger.valueOf(2L));
	}

	private static BigInteger countIncreaseNumbers(long start, int recursionLevel) {
		BigInteger count = BigInteger.ZERO;

		if (recursionLevel == 0) {
			if (incResultStack.containsKey(start))
				return incResultStack.get(start);

			for (long i = start; i < 9; i++) {
				count = count.add(summation[(int) (8 - i)]);
			}

			incResultStack.put(start, count);
		} else {
			for (long i = start; i < 9; i++) {
				count = count.add(countIncreaseNumbers(i, recursionLevel - 1));
			}
		}

		return count;
	}

	private static BigInteger countDecreaseNumbers(long start, int recursionLevel) {
		BigInteger count = BigInteger.ZERO;

		if (recursionLevel == 0) {
			if (decResultStack.containsKey(start))
				return decResultStack.get(start);

			for (int i = 2; i <= 10 - start; i++) {
				count = count.add(summation[i - 1].subtract(BigInteger.ONE));
			}

			decResultStack.put(start, count);
		} else {
			for (long i = start; i < 9; i++) {
				count = count.add(countDecreaseNumbers(i, recursionLevel - 1))
							 .add(BigInteger.valueOf(9 - i));
			}
		}

		return count;
	}

}
