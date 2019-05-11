package com.cristianroot;

import java.math.BigInteger;
import java.util.Arrays;

public class Main {

	public static final int NUMBER = 6;

	public static void main(String[] args) {
		long time = System.currentTimeMillis();
		BigInteger totalIncMath = totalIncMath(NUMBER);
		System.out.println("INC MATH -- " + totalIncMath.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		BigInteger totalIncProg = totalInc(NUMBER);
		System.out.println("INC PROG -- " + totalIncProg.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		BigInteger totalDecMath = totalDecMath(NUMBER);
		System.out.println("DEC MATH -- " + totalDecMath.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		time = System.currentTimeMillis();
		BigInteger totalDecProg = totalDec(NUMBER);
		System.out.println("DEC PROG -- " + totalDecProg.toString() + ", " + (System.currentTimeMillis() - time) + "ms");

		System.out.println("TOTAL MATH -- " + (totalDecMath.add(totalIncMath)));
		System.out.println("TOTAL PROG -- " + (totalDecProg.add(totalIncProg)));
	}

	private static BigInteger totalDec(int x) {
		if (x <= 2)
			return BigInteger.TEN.pow(x);

		long limit = BigInteger.valueOf(10).pow(x).longValue();
		BigInteger count = BigInteger.ZERO;

		for (int i = 101; i <= limit; i++) {
			if (isDecreasing(i))
				count = count.add(BigInteger.ONE);
		}

		return count;
	}

	private static BigInteger totalDecMath(int pow) {
		if (pow <= 2)
			return BigInteger.TEN.pow(pow);

		BigInteger count = BigInteger.ZERO;

		int recursionLevel = pow - 2;
		for (int i = 0; i < recursionLevel; i++) {
			count = count.add(countDecreaseNumbers(0, i));
		}
//		count = count.add(decreasing(BigInteger.valueOf(10).pow(pow).longValue()));

		return count;
	}

	private static BigInteger totalInc(int x) {
		if (x <= 2)
			return BigInteger.TEN.pow(x);

		long limit = BigInteger.valueOf(10).pow(x).longValue();
		BigInteger count = BigInteger.valueOf(100L);

		for (int i = 101; i <= limit; i++) {
			if (isIncreasing(i))
				count = count.add(BigInteger.ONE);
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

//	private static BigInteger decreasing(long limit) {
//		BigInteger count = BigInteger.ZERO;
//
//		for (int i = 2; i <= 10; i++) {
//			count = count.add(sumatorium(BigInteger.valueOf(i)).subtract(BigInteger.ONE));
//		}
//
//		for (int j = 0; j < 9; j++) {
//			for (int i = 2; i <= 10 - j; i++) {
//				count = count.add(sumatorium(BigInteger.valueOf(i)).subtract(BigInteger.ONE));
//			}
//			count = count.add(BigInteger.valueOf(9 - j));
//		}
//
//		for (int k = 0; k < 9; k++) {
//			for (int j = k; j < 9; j++) {
//				for (int i = 2; i <= 10 - j; i++) {
//					count = count.add(sumatorium(BigInteger.valueOf(i)).subtract(BigInteger.ONE));
//				}
//				count = count.add(BigInteger.valueOf(9 - j));
//			}
//			count = count.add(BigInteger.valueOf(9 - k));
//		}
//
//		return count;
//	}

	private static boolean isIncreasing(long n) {
		String[] number = String.valueOf(n).split("");
		int i = 0;
		boolean increasing = true;

		while (i < number.length - 1 && increasing) {
			if (Integer.parseInt(number[i]) <= Integer.parseInt(number[i + 1]))
				i++;
			else
				increasing = false;
		}

		return increasing;
	}

	private static boolean isDecreasing(long n) {
		String[] number = String.valueOf(n).split("");

		if (Arrays.stream(number).distinct().count() == 1)
			return false;

		int i = 0;
		boolean decreasing = true;

		while (i < number.length - 1 && decreasing) {
			if (Integer.parseInt(number[i]) >= Integer.parseInt(number[i + 1]))
				i++;
			else
				decreasing = false;
		}

//		if(decreasing && n >= 3000 && n < 4000)
//			System.out.println(n);

		return decreasing;
	}

}
